package com.example.backend.Dto;

import java.util.Date;

import com.example.backend.Enum.Role;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserUpdateRequest {
    private String firstName; // Ensure this field is present
    private String lastName; // Ensure this field is present
    private String email; // Ensure this field is present
    private String password; // Ensure this field is present
    private Date employmentDate; // Ensure this field is present
    private String originalEstablishment; 
    private String lastDiploma; 
    private String grade; 
    private Role role; 
}