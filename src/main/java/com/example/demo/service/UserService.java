package com.example.demo.service;


import com.example.demo.model.User;

import java.util.List;

public interface UserService {
    List<User> getAllUsers();

    void saveUser(User user);

    User getUserById(Long id);

    void updateUser(User user);

    User findByEmail(String email);

    void deleteUser(Long id);

}
