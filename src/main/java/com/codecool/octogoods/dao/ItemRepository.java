package com.codecool.octogoods.dao;

import com.codecool.octogoods.model.Item;
import org.springframework.data.repository.CrudRepository;

public interface ItemRepository extends CrudRepository<Item, Integer> {
}
