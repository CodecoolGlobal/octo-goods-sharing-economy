package com.codecool.octogoods.controller;

import com.codecool.octogoods.model.ActionStatus;
import com.codecool.octogoods.service.StatusService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/status")
@RestController
public class StatusController {

    public final StatusService statusService;

    public StatusController(StatusService statusService) {
        this.statusService = statusService;
    }

    @PostMapping
    public void addStatus(@RequestBody ActionStatus status){
        statusService.add(status);
    }
}
