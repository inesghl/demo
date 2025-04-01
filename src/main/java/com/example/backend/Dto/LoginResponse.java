package com.example.backend.Dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class LoginResponse {
    private Long id;
    private String email;
    private String role;
    private String token;
    
    public LoginResponse(Long id, String email, String role, String token) {
        this.id = id;
        this.email = email;
        this.role = role;
        this.token = token;
    }
    
    
}
