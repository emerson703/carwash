package com.project.service;

import com.project.dto.JwtValidationResponse;

public class AuthServiceFallback implements AuthServiceClient{
    @Override
    public JwtValidationResponse validateToken(String token) {
        return new JwtValidationResponse(false, "Auth service unavailable - using fallback");
    }
}
