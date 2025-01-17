package com.aquirozc.forohub.jwt;

import java.io.IOException;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.aquirozc.forohub.domain.repo.LoginRepository;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JWTFilter extends OncePerRequestFilter {

    private LoginRepository loginRepository;

    public JWTFilter(LoginRepository loginRepository) {
        this.loginRepository = loginRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

                String header = request.getHeader("Authorization");

                if (header != null) {

                    header = header.replace("Bearer ", "");
            
                    if (!loginRepository.existsById(header) ||loginRepository.findById(header).get().isRevoked()) {
                        response.setStatus(401);
                        return;
                    }

                }

                filterChain.doFilter(request, response);
    }
    
}
