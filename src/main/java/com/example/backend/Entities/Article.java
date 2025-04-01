package com.example.backend.Entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Getter
@Setter
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String doi;

    @Column(nullable = false)
    private String titre;

    @Column(nullable = false)
    private String motsCles;

    @OneToMany(mappedBy = "article", cascade = CascadeType.ALL)
    private List<Contribution> contributions;

    @ManyToOne
    @JoinColumn(name = "domaine_id")
     @JsonBackReference
    private Domain domain;
}