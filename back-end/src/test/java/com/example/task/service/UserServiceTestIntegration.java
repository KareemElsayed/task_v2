package com.example.task.service;
import com.example.task.TaskApplication;
import com.example.task.domain.User;
import com.example.task.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import javax.transaction.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(classes = TaskApplication.class)
public class UserServiceTestIntegration {

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserService userServiceImpl;


    @Test
    @DirtiesContext
    @Transactional
    void loginUser() {

        //given
        User user1 = new User();
        user1.setUserName("user1");
        user1.setEmail("user1@domain");
        user1.setPassword("temp123");

        userRepository.save(user1);

        User loggedUser = userServiceImpl.loginUser(user1.getEmail(), user1.getPassword());

        //then
        assertEquals(true, loggedUser.isLogged());

    }

    @Test
    @DirtiesContext
    @Transactional
    void allLoggedUsers() {

        //given
        User user1 = new User();
        user1.setUserName("user1");
        user1.setEmail("user1@domain");
        user1.setPassword("temp123");

        userRepository.save(user1);

        userServiceImpl.loginUser(user1.getEmail(), user1.getPassword());

        List<User> users = userServiceImpl.allLoggedUsers();

        assertEquals(2, users.size());

    }

    @Test
    @DirtiesContext
    @Transactional
    void logoutUser() {

        //given
        User user1 = new User();
        user1.setId(1l);
        user1.setUserName("user1");
        user1.setEmail("user1@domain");
        user1.setPassword("temp123");
        user1.setLogged(true);

        userRepository.save(user1);

        User logoutUser = userServiceImpl.logoutUser(user1.getEmail());

        assertEquals(false, logoutUser.isLogged());

    }
}
