package com.backend.quizz.controller;


import com.backend.quizz.model.Role;
import com.backend.quizz.model.User;
import com.backend.quizz.model.UserRole;
import com.backend.quizz.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Set;

@RestController
@CrossOrigin
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;

    }


    @PostMapping()
    public ResponseEntity<String> createUser(@RequestBody User user) {
        Set<UserRole> roles = new HashSet<>();
        Role role = new Role();
        role.setRoleId(1L);
        role.setRoleName("NORMAL");

        UserRole userRole = new UserRole();
        userRole.setUser(user);
        userRole.setRole(role);

        roles.add(userRole);

        boolean isCreated = userService.createUser(user, roles);
        System.out.println(user);
        System.out.println(roles);
        System.out.println(isCreated);
        if (isCreated) return new ResponseEntity<>("User Created Successfully", HttpStatus.OK);
        return new ResponseEntity<>("User Not Created",HttpStatus.NOT_FOUND);
    }

    @GetMapping("/{username}")
    public ResponseEntity<String> getUser(@PathVariable String username){
        boolean isPresent = userService.getUser(username);
        if (isPresent) return new ResponseEntity<>("User Logged in",HttpStatus.OK);
        return new ResponseEntity<>("User doesn't exsit with this username",HttpStatus.NOT_FOUND);
    }


}
