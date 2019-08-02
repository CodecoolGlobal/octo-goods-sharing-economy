package com.codecool.octogoods.service;

import com.codecool.octogoods.dao.ItemActionRepository;
import com.codecool.octogoods.model.ActionStatus;
import com.codecool.octogoods.model.ItemAction;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ItemActionService {

    private final ItemActionRepository itemActionRepository;

    public ItemActionService(ItemActionRepository itemActionRepository) {
        this.itemActionRepository = itemActionRepository;
    }

    public void add(ItemAction itemAction) {
        itemActionRepository.save(itemAction);
    }

    public List<ItemAction> getAll() {
        return (List<ItemAction>) itemActionRepository.findAll();
    }

}
