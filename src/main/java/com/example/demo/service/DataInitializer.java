package com.example.demo.service;

import com.example.demo.model.Role;
import com.example.demo.model.User;
import com.example.demo.repository.RoleRepository;
import com.example.demo.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import jakarta.transaction.Transactional;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class DataInitializer {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public DataInitializer(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostConstruct
    @Transactional
    public void init() {
        // Создаем или находим роль ROLE_ADMIN
        Role adminRole = roleRepository.findByName("ROLE_ADMIN");
        if (adminRole == null) {
            adminRole = new Role("ROLE_ADMIN");
            roleRepository.save(adminRole);
        }

        // Создаем или находим роль ROLE_USER
        Role userRole = roleRepository.findByName("ROLE_USER");
        if (userRole == null) {
            userRole = new Role("ROLE_USER");
            roleRepository.save(userRole);
        }

        // Создаем или ОБНОВЛЯЕМ админа
        User admin = userRepository.findByUsername("admin");
        if (admin == null) {
            admin = new User();
            admin.setUsername("admin");
        }
        admin.setPassword(passwordEncoder.encode("admin"));
        admin.setName("Larry");
        admin.setLastname("Wachowski");
        admin.setEmail("larry@mail.ru");
        admin.setRoles(Set.of(adminRole));
        userRepository.save(admin);
        System.out.println("Админ успешно создан/обновлен!");

        // Создаем или ОБНОВЛЯЕМ обычного пользователя
        User regularUser = userRepository.findByUsername("user");
        if (regularUser == null) {
            regularUser = new User();
            regularUser.setUsername("user");
        }
        regularUser.setPassword(passwordEncoder.encode("user"));
        regularUser.setName("Thomas");
        regularUser.setLastname("Anderson");
        regularUser.setEmail("tom@mail.ru");
        regularUser.setRoles(Set.of(userRole));
        userRepository.save(regularUser);
        System.out.println("Пользователь успешно создан/обновлен!");
    }
}

