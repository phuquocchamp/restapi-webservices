package com.phuquocchamp.restapiwebservice.service;

import com.phuquocchamp.restapiwebservice.entity.User;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserServiceTest {
    @Autowired
    private UserService userService;
    @Test
    void createUser(){
//        User user = new User(1L, "Phu Quoc", "Hoang Tan", "phuquocchamp@gmail.com");
//        User savedUser = userService.createUser(user);
//        System.out.println(savedUser.toString());
    }


}