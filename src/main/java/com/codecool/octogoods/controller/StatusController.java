package com.codecool.octogoods.controller;

import com.codecool.octogoods.model.ActionStatus;
import com.codecool.octogoods.service.StatusService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.EntityNotFoundException;
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
    public void addStatus(@Valid @RequestBody ActionStatus status) {
        statusService.add(status);
    }

    @GetMapping
    public List<ActionStatus> getAllStatuses() {
        return statusService.getAll();
    }

    @GetMapping(path = "{id}")
    public ActionStatus getStatusById(@PathVariable int id) {
        try {
            return statusService.getById(id);
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, e.getMessage(), e);
        }
    }

    @PutMapping(path = "{id}")
    public void updateStatusById(@PathVariable int id, @Valid @RequestBody ActionStatus status) {
        statusService.putById(id, status);
    }

    @PatchMapping(path = "{id}")
    public void patchStatusById(@PathVariable int id, @RequestBody String jsonBody) {
        try {
            statusService.patchById(id, jsonBody);
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, e.getMessage(), e);
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, e.getMessage(), e);
        }
    }


}
