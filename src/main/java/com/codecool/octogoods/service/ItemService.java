package com.codecool.octogoods.service;

import com.codecool.octogoods.dao.ItemRepository;
import com.codecool.octogoods.model.Item;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

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

    public ResponseEntity<Item> putById(int id, Item item) {
        Optional<Item> forUpdate = itemRepository.findById(id);
        if (forUpdate.isPresent()) {
//            Item itemForUpdate = forUpdate.get();
            item.setId(id);
            return new ResponseEntity<>(itemRepository.save(item), HttpStatus.OK);
        } else {
            item.setId(id);
            return new ResponseEntity<>(itemRepository.save(item), HttpStatus.CREATED);
        }
    }
}
