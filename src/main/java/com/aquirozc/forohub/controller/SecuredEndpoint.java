package com.aquirozc.forohub.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/secured")
public class SecuredEndpoint {


    @GetMapping
    public String secured(){     
        return "This is a secured endpoint";
    }
    
}
