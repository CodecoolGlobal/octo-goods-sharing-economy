package com.codecool.octogoods.controller;


import com.codecool.octogoods.model.User;
import com.codecool.octogoods.service.UserService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
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

    @GetMapping(path = "{id}")
    public User getUserById(@PathVariable("id") int id) {
        return userService.getById(id);
    }

    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAll();
    }

    @DeleteMapping(path = "{id}")
    public void deleteById(@PathVariable("id") int id, @Valid @NotNull @RequestBody User user) {
        userService.deleteById(id);
    }

    @PutMapping(path = "{id}")
    public void updateById(@PathVariable("id") int id, @Valid @NotNull @RequestBody User user) {
        userService.updateUserById(id, user);
    }
}
