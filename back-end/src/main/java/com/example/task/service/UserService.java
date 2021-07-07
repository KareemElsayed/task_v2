package com.example.task.service;

import com.example.task.domain.User;

import java.util.List;
import java.util.Optional;


public interface UserService {
    List<User> getAllUsers();

    User saveUser(User user);

    Optional<User> getUser(Long id);

    User loginUser(String email,String password);

    List<User> allLoggedUsers();

    User logoutUser(String email);
}
