package com.example.backend.Repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.backend.Entities.Article;
@Repository
public interface ArticleRepository extends JpaRepository<Article, Long> {
    List<Article> findByMotsClesContaining(String keyword); // Custom method
    Optional<Article> findByDoi(String doi); // Custom method
}