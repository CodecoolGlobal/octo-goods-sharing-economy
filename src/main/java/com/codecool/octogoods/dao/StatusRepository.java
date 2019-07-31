package com.codecool.octogoods.dao;

import com.codecool.octogoods.model.ActionStatus;
import org.springframework.data.repository.CrudRepository;

public interface StatusRepository extends CrudRepository<ActionStatus, Integer> {

}
