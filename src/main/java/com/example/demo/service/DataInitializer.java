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

        // Ищем админа по его имэйл
        User admin = userRepository.findByEmail("admin@admin");
        if (admin == null) {
            admin = new User();
            admin.setFirstname("admin");
        }
        admin.setPassword(passwordEncoder.encode("admin"));
        admin.setFirstname("admin");
        admin.setLastname("admin");
        admin.setEmail("admin@admin");
        admin.setRoles(Set.of(adminRole));
        userRepository.save(admin);
        System.out.println("Админ успешно создан/обновлен!");

        //ищем обычного пользователя по имэйл
        User regularUser = userRepository.findByEmail("tom@mail.ru");
        if (regularUser == null) {
            regularUser = new User();
            regularUser.setFirstname("user");
        }
        regularUser.setPassword(passwordEncoder.encode("user"));
        regularUser.setFirstname("Thomas");
        regularUser.setLastname("Anderson");
        regularUser.setEmail("tom@mail.ru");
        regularUser.setRoles(Set.of(userRole));
        userRepository.save(regularUser);
        System.out.println("Пользователь успешно создан/обновлен!");
    }
}

