package com.example.backend.Controllers;

import com.example.backend.Dto.UserUpdateRequest;
import com.example.backend.Entities.User;
import com.example.backend.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    // Create a new user
    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User createdUser = userService.createUser(user);
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }

    // Get all users
    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    // Get user by ID
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        User user = userService.getUserById(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    // Update user
    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(
            @PathVariable Long id, 
            @RequestBody UserUpdateRequest userUpdateRequest) {
        User updatedUser = userService.updateUser(id, userUpdateRequest);
        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }

    // Delete user
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // Get user's articles
    @GetMapping("/{userId}/articles")
    public ResponseEntity<List<User>> getUserArticles(@PathVariable Long userId) {
        List<User> userArticles = userService.getUserArticles(userId);
        return new ResponseEntity<>(userArticles, HttpStatus.OK);
    }
}