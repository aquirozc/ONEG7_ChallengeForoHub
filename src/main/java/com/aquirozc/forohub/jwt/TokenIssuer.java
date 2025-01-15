package com.aquirozc.forohub.jwt;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import com.aquirozc.forohub.data.User;

@Service
public class TokenIssuer {

    private JwtEncoder encoder;

    public TokenIssuer(JwtEncoder encoder) {
        this.encoder = encoder;
    }

    public Jwt generateToken(User user) {

        Instant now = Instant.now();
      
        JwtClaimsSet claims = JwtClaimsSet.builder()
                .issuer("com.aquirozc")
                .issuedAt(now)
                .expiresAt(now.plus(1, ChronoUnit.HOURS))
                .subject(user.getId() + "")
                .build();

        return this.encoder.encode(JwtEncoderParameters.from(claims));
        
    }

    
}
