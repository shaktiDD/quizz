package com.backend.quizz.service;

import com.backend.quizz.model.User;
import com.backend.quizz.model.UserRole;

import java.util.Set;

public interface UserService {
    boolean createUser(User user, Set<UserRole> userRoles);
    boolean getUser(String username);

}
