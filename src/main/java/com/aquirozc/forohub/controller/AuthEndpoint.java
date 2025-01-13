package com.aquirozc.forohub.controller;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aquirozc.forohub.dao.UserLoginDAO;
import com.aquirozc.forohub.data.User;
import com.aquirozc.forohub.jwt.TokenIssuer;
import com.aquirozc.forohub.repo.UserRepository;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/auth")
public class AuthEndpoint {

    private TokenIssuer issuer;
    private UserRepository repository;
    private AuthenticationManager manager;

    @PostMapping("/register")
    public String register(@RequestBody User user){
        return issuer.generateToken(repository.save(user));
    }

    @PostMapping("/login")
    public String login(@RequestBody UserLoginDAO user){
        
        manager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        user.getEmail(),
                        user.getPassword()
                )
        );

        return issuer.generateToken(new User(user));

    }

    
}
