package com.project.service;

import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final JwtService jwtService;

    public AuthService(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    public String authenticate(String username, String password) {
        // ✅ Validación MANUAL sin AuthenticationManager
        if ("user".equals(username) && "password".equals(password)) {
            return jwtService.generateToken(username);
        }
        throw new RuntimeException("Invalid credentials");
    }
}