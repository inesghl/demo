// package com.example.backend.Controllers;

// import com.example.backend.Dto.UserUpdateRequest;
// import com.example.backend.Entities.User;
// import com.example.backend.Enum.Role;
// import com.example.backend.Services.UserService;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.http.HttpStatus;
// import org.springframework.http.ResponseEntity;
// import org.springframework.security.access.prepost.PreAuthorize;
// import org.springframework.web.bind.annotation.*;

// import java.util.List;

// @RestController
// @RequestMapping("/api/users")
// public class UserController {

//     @Autowired
//     private UserService userService;

//     // Create a new user
//     //@PreAuthorize("hasAuthority('ADMIN')")
//     @PostMapping
//     public ResponseEntity<User> createUser(@RequestBody User user) {
//         User createdUser = userService.createUser(user);
//         return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
//     }

//     // Only admin can get all users
// //@PreAuthorize("hasAuthority('ADMIN')")
// @GetMapping
// public ResponseEntity<List<User>> getAllUsers() {
//     List<User> users = userService.getAllUsers();
//     return new ResponseEntity<>(users, HttpStatus.OK);
// }

//     // Get user by ID
//     @PreAuthorize("hasAuthority('ADMIN') or #id == authentication.principal.id")
//     @GetMapping("/{id}")
//     public ResponseEntity<User> getUserById(@PathVariable Long id) {
//         User user = userService.getUserById(id);
//         return new ResponseEntity<>(user, HttpStatus.OK);
//     }

//     // Update user
//     @PreAuthorize("hasAuthority('ADMIN') or #id == authentication.principal.id")
//     @PutMapping("/{id}")
//     public ResponseEntity<User> updateUser(
//             @PathVariable Long id, 
//             @RequestBody UserUpdateRequest userUpdateRequest) {
//         User updatedUser = userService.updateUser(id, userUpdateRequest);
//         return new ResponseEntity<>(updatedUser, HttpStatus.OK);
//     }

//     // Delete user
//     @PreAuthorize("hasAuthority('ADMIN')")
//     @DeleteMapping("/{id}")
//     public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
//         userService.deleteUser(id);
//         return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//     }

//     // Get user's articles
//     // Users can see their own articles, ADMIN can see any user's articles
//     @PreAuthorize("hasAuthority('ADMIN') or #userId == authentication.principal.id")
//     @GetMapping("/{userId}/articles")
//     public ResponseEntity<List<User>> getUserArticles(@PathVariable Long userId) {
//         List<User> userArticles = userService.getUserArticles(userId);
//         return new ResponseEntity<>(userArticles, HttpStatus.OK);
//     }

//     // Only admin can change roles
// @PreAuthorize("hasAuthority('ADMIN')")
// @PutMapping("/{id}/role")
// public ResponseEntity<User> changeUserRole(
//         @PathVariable Long id, 
//         @RequestParam Role newRole) {
//     User updatedUser = userService.changeUserRole(id, newRole);
//     return new ResponseEntity<>(updatedUser, HttpStatus.OK);
// }
// }
package com.example.backend.Controllers;

import com.example.backend.Dto.UserUpdateRequest;
import com.example.backend.Entities.User;
import com.example.backend.Enum.Role;
import com.example.backend.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    // Get all users (admin only)
    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    // Get current user's profile (any authenticated user)
    @GetMapping("/me")
    public ResponseEntity<User> getCurrentUser(@RequestAttribute("userId") Long userId) {
        User user = userService.getUserById(userId);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    // Get user by ID (admin or self)
    @PreAuthorize("hasAuthority('ADMIN') or #id == authentication.principal.id")
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        User user = userService.getUserById(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    // Update user (admin or self)
    @PreAuthorize("hasAuthority('ADMIN') or #id == authentication.principal.id")
    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(
            @PathVariable Long id, 
            @RequestBody UserUpdateRequest userUpdateRequest) {
        User updatedUser = userService.updateUser(id, userUpdateRequest);
        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }

    // Delete user (admin only)
    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // Change user role (admin only)
    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("/{id}/role")
    public ResponseEntity<User> changeUserRole(
            @PathVariable Long id, 
            @RequestParam Role newRole) {
        User updatedUser = userService.changeUserRole(id, newRole);
        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }

     // Get user's articles
    // Users can see their own articles, ADMIN can see any user's articles
    @PreAuthorize("hasAuthority('ADMIN') or #userId == authentication.principal.id")
    @GetMapping("/{userId}/articles")
    public ResponseEntity<List<User>> getUserArticles(@PathVariable Long userId) {
        List<User> userArticles = userService.getUserArticles(userId);
        return new ResponseEntity<>(userArticles, HttpStatus.OK);
    }
}