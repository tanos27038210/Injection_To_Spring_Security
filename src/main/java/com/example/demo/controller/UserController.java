package com.example.demo.controller;

import com.example.demo.model.User;
import com.example.demo.service.UserService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;


@Controller
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String showUserPage(@AuthenticationPrincipal User user, Model model) {
        model.addAttribute("user", user);
        return "user";
    }

//    @GetMapping
//    public String showUserPage(Principal principal, Model model) {
//      String email = principal.getName();
//      User user = userService.findByEmail(email);
//        model.addAttribute("user", user);
//        return "user";
//    }

}