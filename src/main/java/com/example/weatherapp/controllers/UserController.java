package com.example.weatherapp.controllers;

import com.example.weatherapp.dto.EditRequestDto;
import com.example.weatherapp.dto.SignupRequestDto;
import com.example.weatherapp.models.User;
import com.example.weatherapp.security.UserDetailsImpl;
import com.example.weatherapp.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/user/login")
    public String login() {
        return "login";
    }

    @GetMapping("/user/login/error")
    public String loginError(Model model) {
        model.addAttribute("loginError", true);
        return "login";
    }

    @GetMapping("/user/signup")
    public String signup() {
        return "signup";
    }

    @PostMapping("/user/edit")
    public String editUser(EditRequestDto requestDto, Model model, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        userService.editUser(requestDto);
        return "redirect:/";
    }

    @PostMapping("/user/signup")
    public String registerUser(SignupRequestDto requestDto, Model model) {
        try {
            userService.registerUser(requestDto);
            return "redirect:/";
        } catch (Exception e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "signup";
        }
    }
}
