package com.codecool.octogoods.dao;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.codecool.octogoods.model.Category;

@Repository
public interface CategoryRepository extends CrudRepository<Category, Integer> {

    Optional<Category> findByName(String name);

    Iterable<Category> findAllByIsActiveTrue();

    Iterable<Category> findAllByIsActiveFalse();
}