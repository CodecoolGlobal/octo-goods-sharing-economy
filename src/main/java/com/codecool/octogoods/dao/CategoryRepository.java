package com.codecool.octogoods.dao;

import com.codecool.octogoods.model.Category;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends CrudRepository<Category, Integer> {

    @Query("select c from Category c where c.name = :name")
    public Category getByName(String name);
}
