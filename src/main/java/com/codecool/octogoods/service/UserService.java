package com.codecool.octogoods.service;

import com.codecool.octogoods.dao.UserRepository;
import com.codecool.octogoods.model.User;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;


    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAll() {
        List<User> activeUsers = new ArrayList<>();
        for (User user: userRepository.findAll()) {
            if (user.isActive()) {
                activeUsers.add(user);
            }
        }
        return activeUsers;
    }

    public void add(User user) {
        userRepository.save(user);
    }

    public User getById(int id) {
        return userRepository
                .findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Cannot find resource by 'id': " + id));
    }

    public void deleteById(int id) {
        User user = userRepository.findById(id).get();
        user.setIsActive(false);
        userRepository.save(user);
    }

    public void updateUserById(int id, User user) {
        if (userRepository.existsById(id)) {
            User userToUpdate = userRepository.findById(id).get();
            userToUpdate.setName(user.getName());
            userToUpdate.setIsActive(user.isActive());
            userRepository.save(user);
        } else {
            userRepository.findById(id).orElseThrow(() ->
                    new EntityNotFoundException("Cannot find user by 'id': " + id));
        }
    }


}
