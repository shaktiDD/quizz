package com.backend.quizz.impl;

import com.backend.quizz.DTO.AuthResponseDTO;
import com.backend.quizz.DTO.UserLoginDTO;
import com.backend.quizz.model.User;
import com.backend.quizz.model.UserRole;
import com.backend.quizz.repo.RoleRepository;
import com.backend.quizz.repo.UserRepository;
import com.backend.quizz.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class UserServiceImpl implements UserService {
    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private UserRepository userRepository;

    @Override
    public AuthResponseDTO createUser(User user, Set<UserRole> userRoles) {
        User user1 = this.userRepository.findByEmail(user.getEmail());
        if (user1 != null) return new AuthResponseDTO(false, "User Already exist with this email");
        for (UserRole userRole : userRoles) {
            this.roleRepository.save(userRole.getRole());
        }
        user.getUserRoles().addAll(userRoles);
        user.setPassword(encoder.encode(user.getPassword()));
        this.userRepository.save(user);
        return new AuthResponseDTO(true, "User Registered successfully");
    }

    @Override
    public AuthResponseDTO getUser(UserLoginDTO userLoginDTO) {
        User user = userRepository.findByEmail(userLoginDTO.getEmail());
        if (user == null) {
            return new AuthResponseDTO(false, "Email doesn't exist");
        }
        if (encoder.matches(userLoginDTO.getPassword(), user.getPassword())) {
            return new AuthResponseDTO(true, "User Authenticated successfully");
        } else {
            return new AuthResponseDTO(false, "Incorrect Password");
        }
    }
}
