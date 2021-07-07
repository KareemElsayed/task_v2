package com.example.task.service;

import com.example.task.domain.User;
import com.example.task.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;


class UserServiceImplTest {

    @Mock
    UserRepository userRepository;

    UserService userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        userService = new UserServiceImpl(userRepository);
    }

    @Test
    void getAllUsers() {

        //given
        User user1 = new User();
        user1.setId(1l);
        user1.setUserName("user1");
        user1.setEmail("user1@domain");
        user1.setPassword("temp123");


        User user2 = new User();
        user1.setId(2l);
        user1.setUserName("user2");
        user1.setEmail("user2@domain");
        user1.setPassword("temp123");
        when(userRepository.findAll())
                .thenReturn(Arrays.asList(user1, user2));

        //when
        List<User> users = userService.getAllUsers();

        //then
        assertEquals(2, users.size());
    }

    @Test
    void saveUser() {
        //given
        User user1 = new User();
        user1.setId(1l);
        user1.setUserName("user1");
        user1.setEmail("user1@domain");
        user1.setPassword("temp123");

        when(userRepository.save(ArgumentMatchers.any(User.class))).thenReturn(user1);

        User savedUser = userService.saveUser(user1);

        //then
        assertEquals(user1.getUserName(), savedUser.getUserName());

    }


}
