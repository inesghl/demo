package com.example.backend.Entities;

import com.example.backend.Enum.Role;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private Date employmentDate;
    private String originalEstablishment;
    private String lastDiploma;
    private String grade;

    @Enumerated(EnumType.STRING)
    private Role role;

  
@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
@JsonManagedReference
private List<Contribution> contributions;
}