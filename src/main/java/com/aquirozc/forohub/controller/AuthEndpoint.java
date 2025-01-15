package com.aquirozc.forohub.controller;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aquirozc.forohub.data.Login;
import com.aquirozc.forohub.data.User;
import com.aquirozc.forohub.jwt.TokenIssuer;
import com.aquirozc.forohub.repo.LoginRepository;
import com.aquirozc.forohub.repo.UserRepository;
import com.aquirozc.forohub.transitional.UserLoginDAO;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/auth")
public class AuthEndpoint {

    private AuthenticationManager manager;
    private TokenIssuer issuer;
    private LoginRepository loginRepo;
    private UserRepository userRepo;

    @PostMapping("/register")
    public String register(@RequestBody User u){

        User user = userRepo.save(u);
        Jwt token = issuer.generateToken(user);
        Login login = new Login(token.getTokenValue(), false, user);

        loginRepo.save(login);

        return token.getTokenValue();
    }

    @PostMapping("/login")
    public String login(@RequestBody UserLoginDAO u){
        
        manager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        u.getEmail(),
                        u.getPassword()
                )
        );

        User user = userRepo.findByEmail(u.getEmail()).get();
        Jwt token = issuer.generateToken(user);
        Login login = new Login(token.getTokenValue(), false, user);

        loginRepo.save(login);

        return token.getTokenValue();

    }

    @PostMapping("/logout")
    public String logout(@RequestHeader("Authorization") String token){
        
        Login login = loginRepo.findById(token.replace("Bearer ", "")).get();
        login.setRevoked(true);
        loginRepo.save(login);

        return "OK";

    }


    
}
