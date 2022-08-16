package com.example.springsecurityjwt.model.request;

import lombok.Data;

@Data
public class CreateUserRequest {
    private Long id;
    private String username;
    private String password;
}
