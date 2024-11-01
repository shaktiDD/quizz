package com.backend.quizz.service;

import com.backend.quizz.dto.AuthenticationResponseDTO;
import com.backend.quizz.dto.UserLoginDTO;
import com.backend.quizz.model.User;
import com.backend.quizz.model.UserRole;

import java.util.Set;

public interface UserService {
    AuthenticationResponseDTO createUser(User user, Set<UserRole> userRoles);
    AuthenticationResponseDTO getUser(UserLoginDTO userLoginDTO);

}

