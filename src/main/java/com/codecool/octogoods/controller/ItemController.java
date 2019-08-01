package com.codecool.octogoods.controller;

import com.codecool.octogoods.service.ItemService;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ItemController {

    private ItemService itemService;

    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }


}
