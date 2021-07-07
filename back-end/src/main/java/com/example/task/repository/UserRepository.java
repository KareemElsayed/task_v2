package com.example.task.repository;

import com.example.task.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

     User findUserByEmailAndAndPassword(String email,String password);

     User findByEmail(String email);

     List<User> findUsersByIsLogged(boolean isLoggedIn);

     User findByIsLogged(String email);
}
