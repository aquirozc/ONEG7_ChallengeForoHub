package com.aquirozc.forohub.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;
import org.springframework.security.oauth2.server.resource.web.authentication.BearerTokenAuthenticationFilter;
import org.springframework.security.web.SecurityFilterChain;

import com.aquirozc.forohub.jwt.JWTFilter;
import com.aquirozc.forohub.jwt.RSAKeyPair;
import com.nimbusds.jose.jwk.JWK;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.ImmutableJWKSet;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private RSAKeyPair keyPair;
    private JWTFilter jwtFilter;

    public SecurityConfig(RSAKeyPair keyPair, JWTFilter jwtFilter) {
        this.keyPair = keyPair;
        this.jwtFilter = jwtFilter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http = http.csrf(AbstractHttpConfigurer::disable);
        http = http.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
    
        http = http.authorizeHttpRequests(req -> {
            req.requestMatchers(HttpMethod.POST, "/auth/**").permitAll()
                .anyRequest().authenticated();
        });

        http = http.oauth2ResourceServer(oauth2 -> oauth2.jwt(Customizer.withDefaults()));

        http = http.addFilterAfter(jwtFilter, BearerTokenAuthenticationFilter.class);

        return http.build();

    }

    @Bean 
    JwtDecoder jwtDecoder() {
        return NimbusJwtDecoder.withPublicKey(keyPair.getPublicKey()).build();
    }

    @Bean
    JwtEncoder jwtEncoder() {
        JWK jwk = new RSAKey.Builder(keyPair.getPublicKey()).privateKey(keyPair.getPrivateKey()).build();
        JWKSource<SecurityContext> jwks = new ImmutableJWKSet<>(new JWKSet(jwk));
        return new NimbusJwtEncoder(jwks);
    }

    
}
