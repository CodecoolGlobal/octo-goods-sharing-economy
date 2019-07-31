package com.codecool.octogoods.controller;

import com.codecool.octogoods.model.ActionStatus;
import com.codecool.octogoods.service.StatusService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

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

    @GetMapping
    public List<ActionStatus> getAllStatuses(){
        return statusService.getAll();
    }

    @GetMapping(path = "{id}")
    public ActionStatus getStatusById(@PathVariable int id){
        return statusService.getById(id);
    }

    @PutMapping(path = "{id}")
    public void updateStatusById(@PathVariable int id, @Valid @RequestBody ActionStatus status){
        statusService.putById(id, status);
    }


}
