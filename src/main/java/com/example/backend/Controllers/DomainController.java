package com.example.backend.Controllers;

import com.example.backend.Dto.CreateDomainDTO;
import com.example.backend.Dto.UpdateDomainDTO;
import com.example.backend.Entities.Article;
import com.example.backend.Entities.Domain;
import com.example.backend.Services.DomainService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/domains")
public class DomainController {
    @Autowired
    private DomainService domainService;

    // Create a new domain (Admin only)
    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping
    public ResponseEntity<Domain> createDomain(@RequestBody CreateDomainDTO domainDTO) {
        Domain createdDomain = domainService.createDomain(domainDTO);
        return new ResponseEntity<>(createdDomain, HttpStatus.CREATED);
    }
    
    // Get all domains
    @GetMapping
    public ResponseEntity<List<Domain>> getAllDomains() {
        List<Domain> domains = domainService.getAllDomains();
        return new ResponseEntity<>(domains, HttpStatus.OK);
    }
    
    // Get domain by ID
    @GetMapping("/{id}")
    public ResponseEntity<Domain> getDomainById(@PathVariable Long id) {
        Domain domain = domainService.getDomainById(id);
        return new ResponseEntity<>(domain, HttpStatus.OK);
    }
    
    // Update domain (Admin only)
    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<Domain> updateDomain(
            @PathVariable Long id,
            @RequestBody UpdateDomainDTO domainDTO) {
        Domain updatedDomain = domainService.updateDomain(id, domainDTO);
        return new ResponseEntity<>(updatedDomain, HttpStatus.OK);
    }
    
    // Delete domain (Admin only)
    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDomain(@PathVariable Long id) {
        domainService.deleteDomain(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    
    // Get all articles in a domain
    @GetMapping("/{id}/articles")
    public ResponseEntity<List<Article>> getArticlesByDomain(@PathVariable Long id) {
        List<Article> articles = domainService.getArticlesByDomain(id);
        return new ResponseEntity<>(articles, HttpStatus.OK);
    }
    
    // Search domains by name
    @GetMapping("/search")
    public ResponseEntity<List<Domain>> searchDomains(@RequestParam String keyword) {
        List<Domain> domains = domainService.searchDomainsByKeyword(keyword);
        return new ResponseEntity<>(domains, HttpStatus.OK);
    }
}