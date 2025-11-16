package com.ecom.controller;

import java.util.List;
import java.util.Map;

import com.ecom.security.JwtUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecom.entity.EcomUser;
import com.ecom.service.UserService;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    private final AuthenticationManager authenticationManager;

    private final JwtUtils jwtUtils;


    @PostMapping("/login")
    public Map<String, String> login(@RequestBody Map<String, String> loginRequest) {
        String username = loginRequest.get("username");
        String password = loginRequest.get("password");
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(username, password)
        );

        String token = jwtUtils.generateToken(username);
        return Map.of("token", token);
    }

    @PostMapping("/register")
    public Map<String, String> register(@RequestBody Map<String, String> registerRequest) {
        String username = registerRequest.get("username");
        String password = registerRequest.get("password");
        String email = registerRequest.get("email");
        userService.registerUser(username, password, "USER", email);

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(username, password)
        );

        String token = jwtUtils.generateToken(username);
        return Map.of("token", token);
    }

    @PostMapping("/rolechange")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Map<String, String>> alterUserRole(@RequestBody Map<String, String> registerRequest) {
        String username = registerRequest.get("username");
        String role = registerRequest.get("role");
        userService.alterUserRole(username,role);
        Map<String, String> response = Map.of(
                "message", "User role updated successfully",
                "username", username,
                "role", role
        );
        return ResponseEntity
                .status(HttpStatus.OK)   // 200
                .body(response);
    }

    @GetMapping
    public List<EcomUser> getUsers() {
        return userService.getAllUsers();
    }

    @PostMapping("user/create")
    public EcomUser createUser(@RequestBody EcomUser user) {
        return userService.saveUser(user);
    }
}
