package com.project.controller;
import com.project.model.AuthResponse;
import com.project.model.JwtValidationResponse;

import com.project.model.LogingRequest;
import com.project.service.AuthService;
import com.project.service.JwtService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final AuthService authService;
    private final JwtService jwtService;

    public AuthController(AuthService authService, JwtService jwtService) {
        this.authService = authService;
        this.jwtService = jwtService;
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LogingRequest request) {
        try {
            String token = authService.authenticate(request.getUsername(), request.getPassword());
            AuthResponse response = new AuthResponse(token, 3600L);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/validate")
    public ResponseEntity<JwtValidationResponse> validateToken(@RequestParam String token) {
        boolean isValid = jwtService.validateToken(token);
        String message = isValid ? "Token válido" : "Token inválido";
        return ResponseEntity.ok(new JwtValidationResponse(isValid, message));
    }

    @GetMapping("/test")
    public String test() {
        return "Auth Service with JWT is working!";
    }
}
