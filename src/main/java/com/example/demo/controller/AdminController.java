package com.example.demo.controller;

import com.example.demo.model.Role;
import com.example.demo.model.User;
import com.example.demo.repository.RoleRepository;
import com.example.demo.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;
    private final RoleRepository roleRepository;

    public AdminController(UserService userService, RoleRepository roleRepository) {
        this.userService = userService;
        this.roleRepository = roleRepository;
    }

    @GetMapping
    public String read(Model model, Principal principal) {
        if (principal != null) {
            System.out.println("CURRENT PRINCIPAL NAME: " + principal.getName());

            User admin = userService.findByEmail(principal.getName());
            if (admin == null) {
                admin = userService.findByEmail(principal.getName());
            }

            if (admin == null) {
                System.out.println("WARNING: User not found in database for principal: " + principal.getName());
                admin = new User();
                admin.setEmail("Not Found");
            }

            model.addAttribute("admin", admin);
        }

        model.addAttribute("users", userService.getAllUsers());
        model.addAttribute("user", new User());
        model.addAttribute("allRoles", roleRepository.findAll()); // Передаем все роли на случай, если понадобятся в HTML
        return "admin";
    }

    @GetMapping("/new")
    public String create(Model model) {
        model.addAttribute("user", new User());
        return "new";
    }

    @PostMapping
    public String save(@ModelAttribute("user") User user,
                       @RequestParam(value = "roles", required = false) List<Long> roleIds) {
        if (roleIds != null) {
            Set<Role> roles = new java.util.HashSet<>();
            for (Long id : roleIds) {
                Role role = roleRepository.findById(id).orElse(null);
                if (role != null) {
                    roles.add(role);
                }
            }
            user.setRoles(roles);
        }
        userService.saveUser(user);
        return "redirect:/admin";
    }

    @PostMapping("/edit")
    public String update(@ModelAttribute("user") User user,
                         @RequestParam(value = "roles", required = false) List<Long> roleIds) {
        if (roleIds != null) {
            Set<Role> roles = new HashSet<>();
            for (Long id : roleIds) {
                Role role = roleRepository.findById(id).orElse(null);
                if (role != null) {
                    roles.add(role);
                }
            }
            user.setRoles(roles);
        }
        userService.updateUser(user);
        return "redirect:/admin";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id) {
        userService.deleteUser(id);
        return "redirect:/admin";
    }
}

