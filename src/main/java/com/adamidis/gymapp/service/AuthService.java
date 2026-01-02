package com.adamidis.gymapp.service;

import com.adamidis.gymapp.dto.AuthResponse;
import com.adamidis.gymapp.dto.LoginRequest;
import com.adamidis.gymapp.dto.RegisterRequest;
import com.adamidis.gymapp.model.Role;
import com.adamidis.gymapp.model.User;
import com.adamidis.gymapp.repository.UserRepository;
import com.adamidis.gymapp.security.JwtService;
import com.adamidis.gymapp.security.UserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtService jwtService;

    // Register new user
    public User registerUser(RegisterRequest registerRequest) {
        // Check if email already exists
        if (userRepository.existsByEmail(registerRequest.getEmail())) {
            throw new RuntimeException("Email already in use!");
        }

        // Create new user
        User user = new User();
        user.setUsername(registerRequest.getUsername());
        user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        user.setEmail(registerRequest.getEmail());
        user.setRole(Role.USER); // Default role

        return userRepository.save(user);
    }

    // Login user and generate JWT token
    public AuthResponse loginUser(LoginRequest loginRequest) {
        // Authenticate user
        Authentication auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getEmail(),
                        loginRequest.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(auth);
        String jwt = jwtService.generateToken(auth);

        UserPrincipal userPrincipal = (UserPrincipal) auth.getPrincipal();

        return new AuthResponse(
                jwt,
                userPrincipal.getId(),
                userPrincipal.getUsername(),
                userPrincipal.getEmail(),
                userPrincipal.getAuthorities().iterator().next().getAuthority()
        );
    }
}
