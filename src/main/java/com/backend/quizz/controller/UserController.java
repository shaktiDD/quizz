package com.backend.quizz.controller;

import com.backend.quizz.dto.AuthenticationResponseDTO;
import com.backend.quizz.dto.UserLoginDTO;
import com.backend.quizz.model.Role;
import com.backend.quizz.model.User;
import com.backend.quizz.model.UserRole;
import com.backend.quizz.security.JwtService;
import com.backend.quizz.service.UserService;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Set;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/user")
public class UserController {

    private final JwtService jwtService;

    private final UserService userService;

    public UserController(JwtService jwtService, UserService userService) {
        this.jwtService = jwtService;
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponseDTO> createUser(@RequestBody User user) {
        Set<UserRole> roles = new HashSet<>();
        Role role = new Role();
        role.setRoleId(1L);
        role.setRoleName("NORMAL");
        UserRole userRole = new UserRole();
        userRole.setUser(user);
        userRole.setRole(role);
        roles.add(userRole);
        AuthenticationResponseDTO responseDTO = userService.createUser(user, roles);
        return new ResponseEntity<>(responseDTO, responseDTO.isSuccess() ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/test")
    public ResponseEntity<String> test() {
        return new ResponseEntity<>("Hello",HttpStatus.OK);
    }

    @PostMapping("/signin")
    public ResponseEntity<AuthenticationResponseDTO> getUser(@RequestBody UserLoginDTO userLoginDTO) {
        AuthenticationResponseDTO response = userService.getUser(userLoginDTO);
        return new ResponseEntity<>(response, response.isSuccess() ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
    }
}