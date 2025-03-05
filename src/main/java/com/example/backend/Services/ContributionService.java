package com.example.backend.Services;

import com.example.backend.Entities.Contribution;
import com.example.backend.Repositories.ContributionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContributionService {
    private final ContributionRepository contributionRepository;

    public ContributionService(ContributionRepository contributionRepository) {
        this.contributionRepository = contributionRepository;
    }

    public List<Contribution> getAllContributions() {
        return contributionRepository.findAll();
    }

    public Contribution createContribution(Contribution contribution) {
        return contributionRepository.save(contribution);
    }
}