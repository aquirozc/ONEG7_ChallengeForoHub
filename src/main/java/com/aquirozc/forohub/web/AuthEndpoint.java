package com.aquirozc.forohub.web;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.aquirozc.forohub.domain.dto.UserCreationRequest;
import com.aquirozc.forohub.domain.dto.UserLoginRequest;
import com.aquirozc.forohub.domain.service.AuthService;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/auth")
public class AuthEndpoint {

    private AuthService service;

    @PostMapping("/register")
    public String register(@RequestBody @Validated UserCreationRequest u){
        return service.saveNewUser(u);
    }

    @PostMapping("/login")
    public String login(@RequestBody @Validated UserLoginRequest u){
        return service.authenticateUser(u);
    }

    @PostMapping("/logout")
    public String logout(@RequestHeader("Authorization") String token){
      service.revokeLogin(token.replace("Bearer ", ""));  
      return "OK";
    }


    
}
