package com.codecool.octogoods.controller;

import com.codecool.octogoods.model.Category;
import com.codecool.octogoods.model.Item;
import com.codecool.octogoods.model.ItemAddDTO;
import com.codecool.octogoods.model.User;
import com.codecool.octogoods.service.CategoryService;
import com.codecool.octogoods.service.ItemService;
import com.codecool.octogoods.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/item")
public class ItemController {

    private ItemService itemService;
    private ModelMapper modelMapper;
    private UserService userService;
    private CategoryService categoryService;


    public ItemController(ItemService itemService, ModelMapper modelMapper, UserService userService, CategoryService categoryService) {
        this.itemService = itemService;
        this.modelMapper = modelMapper;
        this.userService = userService;
        this.categoryService = categoryService;
    }

    @PostMapping
    public ResponseEntity<Item> addItem(@Valid @RequestBody ItemAddDTO itemAddDTO) {
        try {
            Item item = convertToEntity(itemAddDTO);
            return new ResponseEntity<>(itemService.add(item), HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage(), e);
        }
    }

    @GetMapping
    public ResponseEntity<List<Item>> getAllItems() {
        return new ResponseEntity<>(itemService.getAll(), HttpStatus.OK);
    }

    @GetMapping(path = "{id}")
    public ResponseEntity<Item> getItemById(@PathVariable int id) {
        try {
            return new ResponseEntity<>(itemService.getById(id), HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, e.getMessage(), e);
        }
    }

    @PutMapping(path = "{id}")
    public ResponseEntity<Item> updateItemById(@PathVariable int id, @Valid @RequestBody Item item) {
        return itemService.putById(id, item);
    }

    private Item convertToEntity(ItemAddDTO itemAddDTO) {
        User owner = userService.getById(itemAddDTO.getUserId());

        Category category = categoryService.getByName(itemAddDTO.getCategoryName());

        Item item = modelMapper.map(itemAddDTO, Item.class);
        item.setOwner(owner);
        item.setCategory(category);

        return item;
    }
}
