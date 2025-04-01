package com.example.backend.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.example.backend.Enum.Role;
import com.example.backend.Dto.UserUpdateRequest;
import com.example.backend.Entities.User;
import com.example.backend.Repositories.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User createUser(User user) {
        
        user.setFirstName(user.getFirstName());
        user.setLastName(user.getLastName());
        user.setEmail(user.getEmail());
        // Encoder le mot de passe
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setEmploymentDate(user.getEmploymentDate());
        user.setOriginalEstablishment(user.getOriginalEstablishment());
        user.setLastDiploma(user.getLastDiploma());
        user.setGrade(user.getGrade());
        
        // Par défaut, assigner le rôle UTILISATEUR
        user.setRole(Role.UTILISATEUR);
        
        return userRepository.save(user);
    }
    
    // Méthode pour changer le rôle d'un utilisateur (accessible uniquement par ADMIN)
    public User changeUserRole(Long userId, Role newRole) {
        User user = getUserById(userId);
        user.setRole(newRole);
        return userRepository.save(user);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(Long id) {
        return userRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("User not found with id: " + id));
    }
    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email)
            .orElseThrow(() -> new RuntimeException("User not found with email: " + email));
    }
    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }
    public User updateUser(Long id, UserUpdateRequest userUpdateRequest) {
        User existingUser = getUserById(id);
        
        existingUser.setFirstName(userUpdateRequest.getFirstName());
        existingUser.setLastName(userUpdateRequest.getLastName());
        existingUser.setEmail(userUpdateRequest.getEmail());
       // Only update password if provided and encode it
       if (userUpdateRequest.getPassword() != null && !userUpdateRequest.getPassword().isEmpty()) {
        existingUser.setPassword(passwordEncoder.encode(userUpdateRequest.getPassword()));
    }
        existingUser.setEmploymentDate(userUpdateRequest.getEmploymentDate());
        existingUser.setOriginalEstablishment(userUpdateRequest.getOriginalEstablishment());
        existingUser.setLastDiploma(userUpdateRequest.getLastDiploma());
        existingUser.setGrade(userUpdateRequest.getGrade());
        existingUser.setRole(userUpdateRequest.getRole());

        return userRepository.save(existingUser);
    }

    public void deleteUser(Long id) {
        User user = getUserById(id);
        userRepository.delete(user);
    }

    public List<User> getUserArticles(Long userId) {
        // 
        return List.of(getUserById(userId));
    }

    
}