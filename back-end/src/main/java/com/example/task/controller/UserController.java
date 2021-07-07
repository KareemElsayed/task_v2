package com.example.task.controller;

import com.example.task.domain.User;
import com.example.task.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins="http://localhost:4200")
@RestController
public class UserController {
    private final UserService userServiceImpl;
    private final SimpMessagingTemplate template;

    public UserController(UserService userServiceImpl, SimpMessagingTemplate template) {
        this.userServiceImpl = userServiceImpl;
        this.template = template;
    }

    @GetMapping("/users")
    public List<User> getAllUsers(){
        return userServiceImpl.getAllUsers();
    }

    @PostMapping("/users")
    @ResponseStatus(HttpStatus.CREATED)
    public User saveUser(@RequestBody User user){
        return userServiceImpl.saveUser(user);
    }

    @GetMapping("/user/login")
    public User getLoggedUser(@RequestParam String email, @RequestParam String password){
        return userServiceImpl.loginUser(email,password);
    }

    @GetMapping("/user/logout")
    public User logoutUser(@RequestParam String email){
        return userServiceImpl.logoutUser(email);
    }

    @GetMapping("/users/logged")
    public List<User> getAllLoggedUsers(){
        return userServiceImpl.allLoggedUsers();
    }

//    @MessageMapping("/users")
//    public void createUser(User user){
//        User saveUser = userServiceImpl.saveUser(user);
//        this.template.convertAndSend("/users",  saveUser);
//    }
}
