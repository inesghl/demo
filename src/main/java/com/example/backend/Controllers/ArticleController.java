package com.example.backend.Controllers;

import com.example.backend.Dto.CreateArticleDTO;
import com.example.backend.Dto.UpdateArticleDTO;
import com.example.backend.Entities.Article;
import com.example.backend.Services.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/articles")
public class ArticleController {
    @Autowired
    private ArticleService articleService;

    // Create a new article
    @PostMapping
    public ResponseEntity<Article> createArticle(@RequestBody CreateArticleDTO articleDTO) {
        Article createdArticle = articleService.createArticle(articleDTO);
        return new ResponseEntity<>(createdArticle, HttpStatus.CREATED);
    }

    // Get all articles
    @GetMapping
    public ResponseEntity<List<Article>> getAllArticles() {
        List<Article> articles = articleService.getAllArticles();
        return new ResponseEntity<>(articles, HttpStatus.OK);
    }

    // Get article by ID
    @GetMapping("/{id}")
    public ResponseEntity<Article> getArticleById(@PathVariable Long id) {
        Article article = articleService.getArticleById(id);
        return new ResponseEntity<>(article, HttpStatus.OK);
    }

    // Update article
    @PutMapping("/{id}")
    public ResponseEntity<Article> updateArticle(
            @PathVariable Long id, 
            @RequestBody UpdateArticleDTO articleDTO) {
        Article updatedArticle = articleService.updateArticle(id, articleDTO);
        return new ResponseEntity<>(updatedArticle, HttpStatus.OK);
    }

    // Delete article
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteArticle(@PathVariable Long id) {
        articleService.deleteArticle(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // Search articles by keyword
    @GetMapping("/search")
    public ResponseEntity<List<Article>> searchArticles(@RequestParam String keyword) {
        List<Article> articles = articleService.searchArticlesByKeyword(keyword);
        return new ResponseEntity<>(articles, HttpStatus.OK);
    }

    // Assign article to user
    @PostMapping("/{articleId}/assign-user/{userId}")
    public ResponseEntity<Article> assignArticleToUser(
            @PathVariable Long articleId, 
            @PathVariable Long userId) {
        Article assignedArticle = articleService.assignArticleToUser(articleId, userId);
        return new ResponseEntity<>(assignedArticle, HttpStatus.OK);
    }

    // Assign article to domain
    @PostMapping("/{articleId}/assign-domain/{domainId}")
    public ResponseEntity<Article> assignArticleToDomain(
            @PathVariable Long articleId, 
            @PathVariable Long domainId) {
        Article assignedArticle = articleService.assignArticleToDomain(articleId, domainId);
        return new ResponseEntity<>(assignedArticle, HttpStatus.OK);
    }
}