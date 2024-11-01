package com.backend.quizz.dto;

public class AuthenticationResponseDTO extends BaseResponseDTO {
    private String token;
    private String email;

    public AuthenticationResponseDTO() {
        super();
    }

    public AuthenticationResponseDTO(boolean success, String message) {
        super(success, message);
    }

    public AuthenticationResponseDTO(boolean success, String message, String token) {
        super(success, message);
        this.token = token;
    }

    public AuthenticationResponseDTO(boolean success, String message, String token, String email) {
        super(success, message);
        this.token = token;
        this.email = email;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}