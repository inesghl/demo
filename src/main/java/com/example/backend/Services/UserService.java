package com.example.backend.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.backend.Dto.UserUpdateRequest;
import com.example.backend.Entities.User;
import com.example.backend.Repositories.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User createUser(User user) {
        return userRepository.save(user);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(Long id) {
        return userRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("User not found with id: " + id));
    }

    public User updateUser(Long id, UserUpdateRequest userUpdateRequest) {
        User existingUser = getUserById(id);
        
        existingUser.setFirstName(userUpdateRequest.getFirstName());
        existingUser.setLastName(userUpdateRequest.getLastName());
        existingUser.setEmail(userUpdateRequest.getEmail());
        existingUser.setPassword(userUpdateRequest.getPassword());
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
        // This method might need to be implemented based on your exact requirements
        // For now, it returns the user to demonstrate the endpoint
        return List.of(getUserById(userId));
    }
}