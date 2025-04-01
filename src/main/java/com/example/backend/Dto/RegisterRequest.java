package com.example.backend.Dto;

import com.example.backend.Enum.Role;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter

public class RegisterRequest {
    private String email;
    private String password;
    private String firstName;
    private String lastName;
  
  
    public RegisterRequest() {
    }
    
    // Constructor with fields
    public RegisterRequest(String email, String password, String firstName, String lastName, Role role) {
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
    
    }
    
    
}