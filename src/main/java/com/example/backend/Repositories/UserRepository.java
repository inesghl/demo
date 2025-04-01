// Repository Layer: Contains the methods for accessing and manipulating data.
// Service Layer: Contains your business logic. This is where you’ll call repository methods, apply any additional logic, and return the results.
// Controller Layer: The controller interacts with the service layer to handle incoming requests (e.g., REST endpoints).
package com.example.backend.Repositories;

import com.example.backend.Entities.User;

import java.util.Optional;
// jpa allows for certain methods to be used auto 

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
// queries that start with findby.. or smthg any attributs but for anything other than id we need to define it here 
public interface UserRepository extends JpaRepository<User, Long> {
    //optional cause user may not be found so no user returned
    Optional<User> findByEmail(String email);
    
    // Add this method to check if a user exists with the given email
    boolean existsByEmail(String email);
}