package com.example.springsecurityjwt.model.response;

import lombok.Data;

@Data
public class LoginResponse {
    private String accessToken;
    private String tokenType = "Bearer";
    private Long id;

    public LoginResponse(String accessToken, Long id) {
        this.accessToken = accessToken;
        this.id = id;
    }
}