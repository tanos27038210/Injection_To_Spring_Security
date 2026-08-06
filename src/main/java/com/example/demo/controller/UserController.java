package com.example.demo.controller;

import com.example.demo.model.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String getAllUsers(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "users";
    }

    @GetMapping("/new")
    public String getNewUser(Model model) {
        model.addAttribute("user", new User());
        return "new";
    }

    @PostMapping
    public String saveNewUser(@ModelAttribute("user") User user) {
        userService.saveUser(user);
        return "redirect:/users";
    }

    @GetMapping("/edit")
    public String editUser(@RequestParam("id") Long id, Model model) {
        model.addAttribute("user", userService.getUserById((Long)id));
        return "edit";
    }

        @PostMapping("/update")
        public String updateUser(@ModelAttribute("user") User user) {
            userService.updateUser(user);
            return "redirect:/users";
        }

        @PostMapping("/delete")
        public String deleteUser(@RequestParam("id") Long id) {
            userService.deleteUser((Long)id);
            return "redirect:/users";
        }

}