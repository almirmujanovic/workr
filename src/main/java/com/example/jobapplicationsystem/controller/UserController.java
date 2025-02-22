package com.example.jobapplicationsystem.controller;

import com.example.jobapplicationsystem.dto.UserDto;
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

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new UserDto());
        return "user-registration";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute UserDto userDto) {
        userService.saveUser(userDto);
        return "redirect:/login";
    }
}
