package com.aquirozc.forohub.domain.service;

import java.sql.Date;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;
import com.aquirozc.forohub.domain.dto.UserCreationRequest;
import com.aquirozc.forohub.domain.dto.UserLoginRequest;
import com.aquirozc.forohub.domain.model.Login;
import com.aquirozc.forohub.domain.model.User;
import com.aquirozc.forohub.domain.repo.LoginRepository;
import com.aquirozc.forohub.domain.repo.UserRepository;
import com.aquirozc.forohub.infra.EmailAlreadyTakenException;
import com.aquirozc.forohub.jwt.TokenIssuer;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class AuthService {

    private AuthenticationManager manager;
    private LoginRepository loginRepo;
    private TokenIssuer issuer;
    private UserRepository userRepo;

    public String saveNewUser(UserCreationRequest req){

        User user;

        try {
             user = userRepo.save(createUserFromRegisterRequest(req));
        } catch (Exception e) {
            throw new EmailAlreadyTakenException(req.getEmail());
        }
        
        Jwt token = issuer.generateToken(user);
        loginRepo.save(createLoginEntry(token, user));
        return token.getTokenValue();
    }

    public String authenticateUser(UserLoginRequest req){
        manager.authenticate(new UsernamePasswordAuthenticationToken(
                                req.getEmail(),
                                req.getPassword()));

        User user = userRepo.findByEmail(req.getEmail()).get();

        Jwt token = issuer.generateToken(user);
        loginRepo.save(createLoginEntry(token, user));
        return token.getTokenValue();
    }

    public void revokeLogin(String token){
        Login login = loginRepo.findById(token).get();
        login.setRevoked(true);
        loginRepo.save(login);
    }

    private User createUserFromRegisterRequest(UserCreationRequest req){
        User user = new User();
        user.setName(req.getUsername());
        user.setEmail(req.getEmail());
        user.setPassword(req.getPassword());
        return user;
    }

    private Login createLoginEntry(Jwt token, User user){
       return new Login(token.getTokenValue(), 
                        false, 
                        new Date(token.getExpiresAt().toEpochMilli()), 
                        user);
    }

    
}
