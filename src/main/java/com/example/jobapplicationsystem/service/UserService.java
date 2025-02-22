package com.example.jobapplicationsystem.service;

import com.example.jobapplicationsystem.dto.UserDto;
import com.example.jobapplicationsystem.model.User;
import com.example.jobapplicationsystem.model.UserRole;
import com.example.jobapplicationsystem.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public void saveUser(UserDto userDto) {
        if (userRepository.findByUsername(userDto.getUsername()).isPresent()) {
            throw new RuntimeException("User already exists!");
        }

        User user = new User();
        user.setUsername(userDto.getUsername());
        user.setPassword(passwordEncoder.encode(userDto.getPassword())); // ✅ Ensure password is hashed
        user.setRole(UserRole.USER);

        userRepository.save(user);
    }
}
