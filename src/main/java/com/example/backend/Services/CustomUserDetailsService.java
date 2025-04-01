

package com.example.backend.Services;

import java.util.Collection;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.backend.Entities.User;
import com.example.backend.Repositories.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email));
        
        return new org.springframework.security.core.userdetails.User(
                user.getEmail(),
                user.getPassword(),
                getAuthorities(user)
        ) {
            private static final long serialVersionUID = 1L;

            // Add a method to get the user's ID for use in @PreAuthorize expressions
            public Long getId() {
                return (long) user.getId();
            }
        };
    }
    
    private Collection<? extends GrantedAuthority> getAuthorities(User user) {
        // Convert the user's role to a Spring Security authority
        return Collections.singletonList(new SimpleGrantedAuthority(user.getRole().name()));
    }
}