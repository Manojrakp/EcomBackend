package com.ecom.service;
import java.util.List;

import ch.qos.logback.classic.encoder.JsonEncoder;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.User;

import com.ecom.entity.EcomUser;
import com.ecom.repository.UserRepository;

@RequiredArgsConstructor
@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public List<EcomUser> getAllUsers() {
        return userRepository.findAll();
    }

//    @PostConstruct
//    public void initDefaultUser() {
//        if (userRepository.findByUsername("admin").isEmpty()) {
//            registerUser("ms", "mm", "user", "admin@ecom.com");
//            System.out.println("Default admin user created: username='admin', password='admin123'");
//        }
//    }

    //  Save a new user (with encoded password)
    public EcomUser registerUser(String username, String rawPassword, String role, String email) {
        EcomUser newUser = new EcomUser();
        newUser.setUsername(username);
        newUser.setPassword(passwordEncoder.encode(rawPassword)); // encode password
        newUser.setRole(role.startsWith("ROLE_") ? role : "ROLE_" + role);
        newUser.setEmail(email);
        return userRepository.save(newUser);
    }

    // (Overloaded version) Save directly if already have an EcomUser object
    public EcomUser saveUser(EcomUser user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        if (!user.getRole().startsWith("ROLE_")) {
            user.setRole("ROLE_" + user.getRole());
        }
        return userRepository.save(user);
    }

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        EcomUser user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));
        return User.builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .roles(user.getRole().replace("ROLE_", "")) // ensures consistent naming
                .build();
    }
}
