package com.example.task.service;

import com.example.task.domain.User;
import com.example.task.exception.UserBadRequestException;
import com.example.task.exception.UserNotFoundException;
import com.example.task.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User saveUser(User user) {
        if (userRepository.findByEmail(user.getEmail())==null)
            return userRepository.save(user);
        else
            throw new UserBadRequestException("User already exist");
    }

    @Override
    public Optional<User> getUser(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public User loginUser(String email, String password) {
        User user = userRepository.findByEmail(email);
        if(user.getEmail()==null || !user.getPassword().equals(password))
             throw new UserNotFoundException("User Does Not Exist");
        else if(user.isLogged()==true)
            throw new UserBadRequestException("User already logged");
        else {
            user.setLogged(true);
            user = userRepository.save(user);
        }
        return user;
    }

    @Override
    public List<User> allLoggedUsers() {
        return userRepository.findUsersByIsLogged(true);
    }

    @Override
    public User logoutUser(String email) {
        User user = userRepository.findByEmail(email);
        if (user.isLogged()==false)
            throw new UserBadRequestException("User already logged out");
        user.setLogged(false);
        user = userRepository.save(user);
        return user;
    }


}
