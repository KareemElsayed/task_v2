package com.example.task.bootstrap;

import com.example.task.domain.User;
import com.example.task.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class UserBootstrap  implements CommandLineRunner {
    private UserRepository userRepository;

    public UserBootstrap(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        User user = new User();
        user.setUserName("TestUser");
        user.setPassword("Temp123");
        user.setEmail("TestUser@gmail.com");
        userRepository.save(user);

        User user2 = new User();
        user2.setUserName("TestUser2");
        user2.setPassword("Temp123");
        user2.setEmail("TestUser2@gmail.com");
        user2.setLogged(true);
        userRepository.save(user2);
    }
}
