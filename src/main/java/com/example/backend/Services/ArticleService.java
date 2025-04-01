package com.example.backend.Services;

import com.example.backend.Dto.CreateArticleDTO;
import com.example.backend.Dto.UpdateArticleDTO;
import com.example.backend.Entities.Article;
import com.example.backend.Entities.Contribution;
import com.example.backend.Entities.Domain;
import com.example.backend.Entities.User;
import com.example.backend.Repositories.ArticleRepository;
import com.example.backend.Repositories.ContributionRepository;
import com.example.backend.Repositories.DomainRepository;
import com.example.backend.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Arrays;
import java.util.stream.Collectors;

@Service
public class ArticleService {

    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private DomainRepository domainRepository;
    
    @Autowired
    private ContributionRepository contributionRepository;

    public List<Article> getAllArticles() {
        return articleRepository.findAll();
    }

    public Article getArticleById(Long id) {
        return articleRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Article non trouvé avec l'ID : " + id));
    }

    public Article createArticle(CreateArticleDTO dto) {
        Article article = new Article();
        article.setDoi(dto.getDoi());
        article.setTitre(dto.getTitre());
        article.setMotsCles(dto.getMotsCles());
        
        if (dto.getDomainId() != null) {
            Domain domain = domainRepository.findById(dto.getDomainId())
                .orElseThrow(() -> new RuntimeException("Domaine non trouvé avec l'ID : " + dto.getDomainId()));
            article.setDomain(domain);
        }
        
        return articleRepository.save(article);
    }

    public Article updateArticle(Long id, UpdateArticleDTO dto) {
        Article article = articleRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Article non trouvé avec l'ID : " + id));
        
        if (dto.getTitre() != null) {
            article.setTitre(dto.getTitre());
        }
        
        if (dto.getMotsCles() != null) {
            article.setMotsCles(dto.getMotsCles());
        }
        
        if (dto.getDomainId() != null) {
            Domain domain = domainRepository.findById(dto.getDomainId())
                .orElseThrow(() -> new RuntimeException("Domaine non trouvé avec l'ID : " + dto.getDomainId()));
            article.setDomain(domain);
        }
        
        return articleRepository.save(article);
    }

    public void deleteArticle(Long id) {
        articleRepository.deleteById(id);
    }

    /**
     * Assigne un article à un utilisateur en créant une contribution
     * 
     * @param articleId ID de l'article
     * @param userId ID de l'utilisateur
     * @return Article mis à jour
     */
    @Transactional
    public Article assignArticleToUser(Long articleId, Long userId) {
        Article article = articleRepository.findById(articleId)
            .orElseThrow(() -> new RuntimeException("Article non trouvé avec l'ID : " + articleId));
        
        User user = userRepository.findById(userId)
            .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé avec l'ID : " + userId));

        // Création d'une nouvelle contribution
        Contribution contribution = new Contribution();
        contribution.setUser(user);
        contribution.setArticle(article);
        contribution.setType("AUTEUR"); // Type par défaut, peut être modifié selon les besoins
        contribution.setDate(new Date());
        contribution.setLieu("Non spécifié"); // Lieu par défaut
        
        // Sauvegarde de la contribution
        contributionRepository.save(contribution);
        
        // Récupérer l'article mis à jour
        return articleRepository.findById(articleId).orElseThrow();
    }

    /**
     * Recherche des articles par mot clé dans le champ motsCles
     * 
     * @param keyword Mot clé à rechercher
     * @return Liste des articles correspondants
     */
    public List<Article> searchArticlesByKeyword(String keyword) {
        return articleRepository.findByMotsClesContaining(keyword);
    }
    
    /**
     * Recherche des articles par plusieurs mots clés (séparés par des virgules)
     * 
     * @param keywords Mots clés séparés par des virgules
     * @return Liste des articles correspondants à au moins un des mots clés
     */
    public List<Article> searchArticlesByMultipleKeywords(String keywords) {
        List<String> keywordList = Arrays.asList(keywords.split(","))
            .stream()
            .map(String::trim)
            .collect(Collectors.toList());
        
        return articleRepository.findAll()
            .stream()
            .filter(article -> keywordList.stream()
                .anyMatch(keyword -> article.getMotsCles().toLowerCase().contains(keyword.toLowerCase())))
            .collect(Collectors.toList());
    }

    /**
     * Assigne un article à un domaine
     * 
     * @param articleId ID de l'article
     * @param domainId ID du domaine
     * @return Article mis à jour
     */
    @Transactional
    public Article assignArticleToDomain(Long articleId, Long domainId) {
        Article article = articleRepository.findById(articleId)
            .orElseThrow(() -> new RuntimeException("Article non trouvé avec l'ID : " + articleId));
        
        Domain domain = domainRepository.findById(domainId)
            .orElseThrow(() -> new RuntimeException("Domaine non trouvé avec l'ID : " + domainId));
        
        article.setDomain(domain);
        return articleRepository.save(article);
    }
}