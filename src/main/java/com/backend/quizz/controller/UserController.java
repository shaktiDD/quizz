package com.backend.quizz.controller;


import com.backend.quizz.DTO.AuthResponseDTO;
import com.backend.quizz.DTO.UserLoginDTO;
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
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }
    @GetMapping("/test")
    public ResponseEntity<String> testEndpoint() {
        return ResponseEntity.ok("CORS is working!");
    }


    @PostMapping("/register")
    public ResponseEntity<AuthResponseDTO> createUser(@RequestBody User user) {
        Set<UserRole> roles = new HashSet<>();
        Role role = new Role();
        role.setRoleId(1L);
        role.setRoleName("NORMAL");
        UserRole userRole = new UserRole();
        userRole.setUser(user);
        userRole.setRole(role);
        roles.add(userRole);
        AuthResponseDTO responseDTO = userService.createUser(user, roles);
        if (responseDTO.isSuccess()) {
            return new ResponseEntity<>(responseDTO, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(responseDTO, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/signin")
    public ResponseEntity<AuthResponseDTO> getUser(@RequestBody UserLoginDTO userLoginDTO) {
        AuthResponseDTO response = userService.getUser(userLoginDTO);
        return new ResponseEntity<>(response, response.isSuccess() ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
    }
}
