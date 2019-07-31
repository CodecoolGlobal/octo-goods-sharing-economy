package com.codecool.octogoods.dao;

import com.codecool.octogoods.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer>{

}
