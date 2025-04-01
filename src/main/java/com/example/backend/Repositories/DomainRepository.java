package com.example.backend.Repositories;

import com.example.backend.Entities.Domain;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DomainRepository extends JpaRepository<Domain, Long> {
    // Add this method to match the usage in DomainService
    Optional<Domain> findByNomDomaine(String nomDomaine);
    List<Domain> findByNomDomaineContainingIgnoreCase(String keyword);
}
