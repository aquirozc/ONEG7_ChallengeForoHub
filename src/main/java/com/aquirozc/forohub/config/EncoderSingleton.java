package com.aquirozc.forohub.config;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class EncoderSingleton {

    private static PasswordEncoder encoder = new BCryptPasswordEncoder();

    private EncoderSingleton() {}

    public static PasswordEncoder getInstance() {
        return encoder;
    }
    
}
