package com.example.backend.Services;

import com.example.backend.Dto.CreateArticleDTO;
import com.example.backend.Dto.UpdateArticleDTO;
import com.example.backend.Entities.Article;
import com.example.backend.Entities.Contribution;
import com.example.backend.Entities.Domain;
import com.example.backend.Entities.User;
import com.example.backend.Repositories.ArticleRepository;
import com.example.backend.Repositories.DomainRepository;
import com.example.backend.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleService {

    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private DomainRepository domainRepository;

    public List<Article> getAllArticles() {
        return articleRepository.findAll();
    }

    public Article getArticleById(Long id) {
        return articleRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("article not found with id: " + id));
    }

    public Article createArticle(CreateArticleDTO dto) {
        Article article = new Article();
        article.setDoi(dto.getDoi());
        article.setTitre(dto.getTitre());
        article.setMotsCles(dto.getMotsCles());
        Domain domain = domainRepository.findById(dto.getDomainId()).orElseThrow(() -> new RuntimeException("Domain not found"));
        article.setDomain(domain);
        return articleRepository.save(article);
    }

    public Article updateArticle(Long id, UpdateArticleDTO dto) {
        Article article = articleRepository.findById(id).orElseThrow(() -> new RuntimeException("Article not found"));
        article.setTitre(dto.getTitre());
        article.setMotsCles(dto.getMotsCles());
        Domain domain = domainRepository.findById(dto.getDomainId())
                .orElseThrow(() -> new RuntimeException("Domain not found"));
        article.setDomain(domain);
        return articleRepository.save(article);
    }

    public void deleteArticle(Long id) {
        articleRepository.deleteById(id);
    }

    public Article assignArticleToUser(Long articleId, Long userId) {
        Article article = articleRepository.findById(articleId).orElseThrow(() -> new RuntimeException("Article not found"));
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));

        Contribution contribution = new Contribution();
        contribution.setUser(user);
        contribution.setArticle(article);
        contribution.setType("AUTHOR"); // Example contribution type

        article.getContributions().add(contribution);
        return articleRepository.save(article);
    }

    public List<Article> searchArticlesByKeyword(String keyword) {
        return articleRepository.findByMotsClesContaining(keyword);
    }

    public Article assignArticleToDomain(Long articleId, Long domainId) {
        Article article = articleRepository.findById(articleId)
                .orElseThrow(() -> new RuntimeException("Article not found"));
        Domain domain = domainRepository.findById(domainId)
                .orElseThrow(() -> new RuntimeException("Domain not found"));
        article.setDomain(domain);
        return articleRepository.save(article);
    }
}