package com.backend.quizz.service;

import com.backend.quizz.DTO.AuthResponseDTO;
import com.backend.quizz.DTO.UserLoginDTO;
import com.backend.quizz.model.User;
import com.backend.quizz.model.UserRole;

import java.util.Set;

public interface UserService {
    AuthResponseDTO createUser(User user, Set<UserRole> userRoles);
    AuthResponseDTO getUser(UserLoginDTO userLoginDTO);

}
