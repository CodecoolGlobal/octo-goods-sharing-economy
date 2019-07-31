package com.codecool.octogoods.controller;


import com.codecool.octogoods.model.User;
import com.codecool.octogoods.service.UserService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequestMapping("/api/user")
@RestController
public class UserController {

    public final UserService userService;


    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public void addUser(@Valid @RequestBody User user) {
        userService.add(user);
    }

    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAll();
    }
}
