package com.example.jobapplicationsystem.service;

import com.example.jobapplicationsystem.model.User;
import com.example.jobapplicationsystem.model.UserRole;
import com.example.jobapplicationsystem.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public void saveUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole(UserRole.USER); // ✅ FIXED: Now uses ENUM, not String
        userRepository.save(user);
    }

    // ✅ Add findByUsername method
    public User findByUsername(String username) {
        Optional<User> userOptional = userRepository.findByUsername(username);
        return userOptional.orElse(null);
    }
}
