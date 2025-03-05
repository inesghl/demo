package com.example.backend.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.backend.Entities.Domain;
import com.example.backend.Repositories.DomainRepository;

@Service
public class DomainService {
    private final DomainRepository domainRepository;

    public DomainService(DomainRepository domainRepository) {
        this.domainRepository = domainRepository;
    }

    public List<Domain> getAllDomains() {
        return domainRepository.findAll();
    }

    public Domain createDomain(Domain domain) {
        return domainRepository.save(domain);
    }

    public Optional<Domain> getDomainByName(String name) {
        return domainRepository.findByNomDomaine(name);
    }
}