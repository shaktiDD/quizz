package com.backend.quizz.impl;

import com.backend.quizz.dto.AuthenticationResponseDTO;
import com.backend.quizz.dto.UserLoginDTO;
import com.backend.quizz.model.User;
import com.backend.quizz.model.UserRole;
import com.backend.quizz.repo.RoleRepository;
import com.backend.quizz.repo.UserRepository;
import com.backend.quizz.security.JwtService;
import com.backend.quizz.service.UserService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {
    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);
    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final JwtService jwtService;

    public UserServiceImpl(RoleRepository roleRepository, UserRepository userRepository, JwtService jwtService) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
        this.jwtService = jwtService;
    }

    @Override
    public AuthenticationResponseDTO createUser(User user, Set<UserRole> userRoles) {
        // Use Optional to check for existing user
        Optional<User> existingUserOptional = userRepository.findByEmail(user.getEmail());
        if (existingUserOptional.isPresent()) {
            return new AuthenticationResponseDTO(false, "User already exists with this email");
        }
        // Save roles if they don't already exist
        for (UserRole userRole : userRoles) {
            roleRepository.save(userRole.getRole());
        }
        // Set password and save the new user
        user.getUserRoles().addAll(userRoles);
        user.setPassword(encoder.encode(user.getPassword()));
        userRepository.save(user);
        // Generate JWT token for the new user
        String token = jwtService.generateToken(user.getEmail());
        return new AuthenticationResponseDTO(true, "User registered successfully", token, user.getEmail());
    }

    @Override
    public AuthenticationResponseDTO getUser(UserLoginDTO userLoginDTO) {
        // Use Optional to retrieve the user
        User user = userRepository.findByEmail(userLoginDTO.getEmail()).orElse(null); // or handle it differently if you want to throw an exception
        if (user == null) {
            return new AuthenticationResponseDTO(false, "Email doesn't exist");
        }
        // Check password match
        if (encoder.matches(userLoginDTO.getPassword(), user.getPassword())) {
            String token = jwtService.generateToken(user.getEmail());
            return new AuthenticationResponseDTO(true, "User authenticated successfully", token, user.getEmail());
        } else {
            return new AuthenticationResponseDTO(false, "Incorrect password");
        }
    }
}