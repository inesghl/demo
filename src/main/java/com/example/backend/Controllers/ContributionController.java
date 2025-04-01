package com.example.backend.Controllers;

import com.example.backend.Dto.CreateContributionDTO;
import com.example.backend.Dto.UpdateContributionDTO;
import com.example.backend.Entities.Contribution;
import com.example.backend.Services.ContributionService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/contributions")
public class ContributionController {
    
    @Autowired
    private ContributionService contributionService;
    
    // Get all contributions (Admin only)
    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping
    public ResponseEntity<List<Contribution>> getAllContributions() {
        List<Contribution> contributions = contributionService.getAllContributions();
        return new ResponseEntity<>(contributions, HttpStatus.OK);
    }
    
    // Get contribution by ID
    @GetMapping("/{id}")
    public ResponseEntity<Contribution> getContributionById(@PathVariable Long id) {
        Contribution contribution = contributionService.getContributionById(id);
        return new ResponseEntity<>(contribution, HttpStatus.OK);
    }
    
    // Create a new contribution
    @PreAuthorize("hasAnyAuthority('ADMIN', 'MODERATEUR')")
    @PostMapping
    public ResponseEntity<Contribution> createContribution(@RequestBody CreateContributionDTO contributionDTO) {
        Contribution createdContribution = contributionService.createContribution(contributionDTO);
        return new ResponseEntity<>(createdContribution, HttpStatus.CREATED);
    }
    
    // Update contribution
    @PreAuthorize("hasAnyAuthority('ADMIN', 'MODERATEUR')")
    @PutMapping("/{id}")
    public ResponseEntity<Contribution> updateContribution(
            @PathVariable Long id,
            @RequestBody UpdateContributionDTO contributionDTO) {
        Contribution updatedContribution = contributionService.updateContribution(id, contributionDTO);
        return new ResponseEntity<>(updatedContribution, HttpStatus.OK);
    }
    
    // Delete contribution
    @PreAuthorize("hasAnyAuthority('ADMIN', 'MODERATEUR')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteContribution(@PathVariable Long id) {
        contributionService.deleteContribution(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    
    // Get contributions by article ID
    @GetMapping("/article/{articleId}")
    public ResponseEntity<List<Contribution>> getContributionsByArticleId(@PathVariable Long articleId) {
        List<Contribution> contributions = contributionService.getContributionsByArticleId(articleId);
        return new ResponseEntity<>(contributions, HttpStatus.OK);
    }
    
    // Get contributions by user ID
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Contribution>> getContributionsByUserId(@PathVariable Long userId) {
        List<Contribution> contributions = contributionService.getContributionsByUserId(userId);
        return new ResponseEntity<>(contributions, HttpStatus.OK);
    }
}