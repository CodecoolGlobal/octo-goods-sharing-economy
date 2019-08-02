package com.codecool.octogoods.controller;

import com.codecool.octogoods.model.*;
import com.codecool.octogoods.service.ItemActionService;
import com.codecool.octogoods.service.ItemService;
import com.codecool.octogoods.service.StatusService;
import com.codecool.octogoods.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequestMapping("/api/item-action")
@RestController
public class ItemActionController {

    private final ItemActionService itemActionService;
    private final ItemService itemService;
    private final UserService userService;
    private final StatusService statusService;
    private final ModelMapper modelMapper;

    public ItemActionController(ItemActionService itemActionService,
                                ItemService itemService,
                                UserService userService,
                                StatusService statusService,
                                ModelMapper modelMapper) {
        this.itemActionService = itemActionService;
        this.itemService = itemService;
        this.userService = userService;
        this.statusService = statusService;
        this.modelMapper = modelMapper;
    }

    @PostMapping
    public void add(@Valid @RequestBody ItemActionDTO itemActionDTO) {
        Item item = itemService.getById(itemActionDTO.getItem_id());
        User user = userService.getById(itemActionDTO.getUser_id());
        ActionStatus actionStatus = statusService.getStatusByName(itemActionDTO.getActionStatus());
        ItemAction itemAction = modelMapper.map(itemActionDTO, ItemAction.class);

        itemAction.setItem(item);
        itemAction.setUser(user);
        itemAction.setActionStatus(actionStatus);
        itemAction.setDate(itemActionDTO.setData());

        itemActionService.add(itemAction);
    }

    @GetMapping
    public List<ItemAction> getAll() {
        return itemActionService.getAll();
    }

}
