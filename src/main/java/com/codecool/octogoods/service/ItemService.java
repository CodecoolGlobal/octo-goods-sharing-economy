package com.codecool.octogoods.service;

import com.codecool.octogoods.dao.ItemRepository;
import org.springframework.stereotype.Service;

@Service
public class ItemService {

    private ItemRepository itemRepository;

    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }
}
