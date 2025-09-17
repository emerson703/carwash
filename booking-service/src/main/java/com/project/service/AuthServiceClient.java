package com.project.service;

import com.project.dto.JwtValidationResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "auth-service", fallback = AuthServiceFallback.class)
public interface AuthServiceClient {
    @PostMapping("/auth/validate")
    JwtValidationResponse validateToken(@RequestParam String token);
}
