package com.example.task.controller;

import com.example.task.domain.User;
import com.example.task.exception.CustomizedErrorExceptionHandler;
import com.example.task.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class UserControllerTest {

    @Mock
    UserService userService;

    @InjectMocks
    UserController userController;

    MockMvc mockMvc;

    @BeforeEach
    void setUp() {

        MockitoAnnotations.openMocks(this);

        mockMvc = MockMvcBuilders.standaloneSetup(userController)
                .setControllerAdvice(new CustomizedErrorExceptionHandler())
                .build();
    }

    @Test
    void getAllUsers() throws Exception {
        //given
        User user1 = new User();
        user1.setId(1l);
        user1.setUserName("user1");
        user1.setEmail("user1@domain");
        user1.setPassword("temp123");

        User user2 = new User();
        user2.setId(2l);
        user2.setUserName("user2");
        user2.setEmail("user2@domain");
        user2.setPassword("temp123");

        when(userService.getAllUsers()).thenReturn(Arrays.asList(user1,user2));

        mockMvc.perform(get("/users")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.*", hasSize(2)));
    }
}
