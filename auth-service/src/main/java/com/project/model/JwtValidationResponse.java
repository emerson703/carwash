package com.project.model;

public class JwtValidationResponse {
    private boolean valid;
    private String message;

    // Constructores
    public JwtValidationResponse() {}
    public JwtValidationResponse(boolean valid, String message) {
        this.valid = valid;
        this.message = message;
    }

    // Getters y setters
    public boolean isValid() { return valid; }
    public void setValid(boolean valid) { this.valid = valid; }
    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }

}
