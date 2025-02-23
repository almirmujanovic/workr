package com.example.jobapplicationsystem.controller;

import com.example.jobapplicationsystem.dto.UserDto;
import com.example.jobapplicationsystem.model.User;
import com.example.jobapplicationsystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    // ✅ Show registration form
    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new UserDto());
        return "user-registration";
    }

    // ✅ Handle user registration
    @PostMapping("/register")
    public String registerUser(@ModelAttribute UserDto userDto) {
        User user = convertToEntity(userDto); // ✅ Convert DTO to Model before saving
        userService.saveUser(user);
        return "redirect:/login";
    }

    // ✅ Helper method to convert UserDto → User Model
    private User convertToEntity(UserDto userDto) {
        User user = new User();
        user.setUsername(userDto.getUsername());
        user.setPassword(userDto.getPassword());
        return user;
    }
}
