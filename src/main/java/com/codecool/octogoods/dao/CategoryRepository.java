package com.codecool.octogoods.dao;

import com.codecool.octogoods.model.Category;
import org.springframework.data.repository.CrudRepository;

public interface CategoryRepository extends CrudRepository<Category, Integer> {
}
