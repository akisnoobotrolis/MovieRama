package com.example.demo.controller;


import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    UserService userService;


    @GetMapping(value = "/users/findAllUsers")
    public List<User> findAllMovies(){
        return userService.findAllMovies();
    }
}
