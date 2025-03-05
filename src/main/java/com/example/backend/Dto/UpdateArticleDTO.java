package com.example.backend.Dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateArticleDTO {
    private String titre; 
    private String motsCles; 
    private Long domainId; 
}