package com.project.model;

public class AuthResponse {
    private String token;
    private String type = "Bearer";
    private Long expiresIn;

    // Constructores
    public AuthResponse() {}
    public AuthResponse(String token, Long expiresIn) {
        this.token = token;
        this.expiresIn = expiresIn;
    }

    // Getters y setters
    public String getToken() { return token; }
    public void setToken(String token) { this.token = token; }
    public String getType() { return type; }
    public void setType(String type) { this.type = type; }
    public Long getExpiresIn() { return expiresIn; }
    public void setExpiresIn(Long expiresIn) { this.expiresIn = expiresIn; }
}
