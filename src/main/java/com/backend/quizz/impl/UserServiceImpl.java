package com.backend.quizz.impl;

import com.backend.quizz.model.User;
import com.backend.quizz.model.UserRole;
import com.backend.quizz.repo.RoleRepository;
import com.backend.quizz.repo.UserRepository;
import com.backend.quizz.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository  userRepository) {
        this.userRepository = userRepository;
    }
    @Autowired
    private RoleRepository roleRepository;

    @Override
    public boolean createUser(User user, Set<UserRole> userRoles) {
        User user1 = this.userRepository.findByUsername(user.getUsername());
        if(user1 != null) return false;
        for (UserRole userRole : userRoles){
            this.roleRepository.save(userRole.getRole());
        }
        user.getUserRoles().addAll(userRoles);
        this.userRepository.save(user);
        return true;
    }

    @Override
    public boolean getUser(String username) {
        User user = userRepository.findByUsername(username);
        return user != null;
    }
}
