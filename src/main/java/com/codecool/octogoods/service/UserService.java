package com.codecool.octogoods.service;

import com.codecool.octogoods.dao.UserRepository;
import com.codecool.octogoods.model.User;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;


    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAll() {
        return (List<User>) userRepository.findAll();
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
        userRepository.deleteById(id);
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
