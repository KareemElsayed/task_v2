package com.example.task.controller;

import com.example.task.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserControllerTestIntegration {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Autowired
    UserService userService;

    @BeforeEach
    void setUp() {
    }

    @Test
    void getAllLoggedUsers(){
        String url = "/users/logged";
        List userList = testRestTemplate.getForObject(url,List.class);
        assertThat(userList).hasSize(1);
    }
}
