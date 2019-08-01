package com.codecool.octogoods.controller;

import com.codecool.octogoods.model.AddItemDTO;
import com.codecool.octogoods.model.Category;
import com.codecool.octogoods.model.Item;
import com.codecool.octogoods.model.User;
import com.codecool.octogoods.service.CategoryService;
import com.codecool.octogoods.service.ItemService;
import com.codecool.octogoods.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
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
    public void addItem(@Valid @RequestBody AddItemDTO addItemDTO) {
        Item item = convertToEntity(addItemDTO);

        itemService.add(item);
    }


    @GetMapping
    public List<Item> getAllItems() {
        return itemService.getAll();
    }

    @GetMapping(path = "{id}")
    public Item getItemById(@PathVariable int id) {
        try {
            return itemService.getById(id);
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, e.getMessage(), e);
        }
    }

    private Item convertToEntity(AddItemDTO addItemDTO) {
        User owner = userService.getById(addItemDTO.getUserId());
        Category category = categoryService.getByName(addItemDTO.getCategoryName());

        Item item = modelMapper.map(addItemDTO, Item.class);
        item.setOwner(owner);
        item.setCategory(category);

        return item;
    }


}
