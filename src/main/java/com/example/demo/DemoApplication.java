package com.example.demo;

import com.example.demo.model.Role;
import com.example.demo.model.User;
import com.example.demo.repository.RoleRepository;
import com.example.demo.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Set;

@SpringBootApplication
public class DemoApplication {
    public static void main(String[] args) {

        SpringApplication.run(DemoApplication.class, args);
    }
}

//    @Transactional
//    @Bean
//    public CommandLineRunner initDatabase(UserRepository userRepository,
//                                          PasswordEncoder password,
//                                          RoleRepository roleRepository) {
//        return args -> {
//            //  Создаем или находим роль ROLE_ADMIN
//            Role adminRole = roleRepository.findByName("ROLE_ADMIN");
//            if (adminRole == null) {
//                adminRole = new Role("ROLE_ADMIN");
//                roleRepository.save(adminRole);
//            }
//
//            //  Создаем или находим роль ROLE_USER
//            Role userRole = roleRepository.findByName("ROLE_USER");
//            if (userRole == null) {
//                userRole = new Role("ROLE_USER");
//                roleRepository.save(userRole);
//            }
//
//            // Создаем или ОБНОВЛЯЕМ админа
//            User admin = userRepository.findByUsername("admin");
//            if (admin == null) {
//                admin = new User();
//                admin.setUsername("admin");
//            }
//            admin.setPassword(password.encode("admin"));
//            admin.setName("Larry");
//            admin.setLastname("Wachowski");
//            admin.setEmail("larry@mail.ru");
//            admin.setRoles(Set.of(adminRole));
//            userRepository.save(admin);
//            System.out.println("Админ успешно создан/обновлен!");
//
//            // 4. Создаем или ОБНОВЛЯЕМ обычного пользователя
//            User regularUser = userRepository.findByUsername("user");
//            if (regularUser == null) {
//                regularUser = new User();
//                regularUser.setUsername("user");
//            }
//            regularUser.setPassword(password.encode("user"));
//            regularUser.setName("Thomas");
//            regularUser.setLastname("Anderson");
//            regularUser.setEmail("tom@mail.ru");
//            regularUser.setRoles(Set.of(userRole));
//            userRepository.save(regularUser);
//            System.out.println("Пользователь успешно создан/обновлен!");
//        };
//    }
//}