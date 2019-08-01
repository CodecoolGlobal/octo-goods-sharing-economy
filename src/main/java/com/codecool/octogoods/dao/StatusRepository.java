package com.codecool.octogoods.dao;

import com.codecool.octogoods.model.ActionStatus;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface StatusRepository extends CrudRepository<ActionStatus, Integer> {

    @Query("select s from action_status a where a.name = :name")
    public ActionStatus getByName(String name);
}
