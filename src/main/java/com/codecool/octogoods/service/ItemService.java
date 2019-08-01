package com.codecool.octogoods.service;

import com.codecool.octogoods.dao.ItemRepository;
import com.codecool.octogoods.model.Item;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.List;

@Service
public class ItemService {

    private ItemRepository itemRepository;

    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @Transactional
    public Item add(Item item) {
        // TODO: add a record to ItemActions
        return itemRepository.save(item);
    }

    public List<Item> getAll() {
        return (List<Item>) itemRepository.findAll();
    }

    public Item getById(int id) {
        return itemRepository
                .findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Cannot find resource by 'id': " + id));
    }




}
