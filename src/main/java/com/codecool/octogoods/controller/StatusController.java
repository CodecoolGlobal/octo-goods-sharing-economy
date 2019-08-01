package com.codecool.octogoods.controller;

import com.codecool.octogoods.model.ActionStatus;
import com.codecool.octogoods.service.StatusService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import java.util.List;

@RequestMapping("/api/status")
@RestController
public class StatusController {

    private final StatusService statusService;

    public StatusController(StatusService statusService) {
        this.statusService = statusService;
    }

    @PostMapping
    public ResponseEntity<ActionStatus> addStatus(@Valid @RequestBody ActionStatus status) {
        return new ResponseEntity<>(statusService.add(status), HttpStatus.CREATED);
    }

    @GetMapping
    public List<ActionStatus> getAllStatuses() {
        return statusService.getAll();
    }

    @GetMapping(path = "{id}")
    public ResponseEntity<ActionStatus> getStatusById(@PathVariable int id) {
        try {
            return new ResponseEntity<>(statusService.getById(id), HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, e.getMessage(), e);
        }
    }

    @PutMapping(path = "{id}")
    public ResponseEntity<ActionStatus> updateStatusById(@PathVariable int id, @Valid @RequestBody ActionStatus status) {
        return statusService.putById(id, status);
    }

    @PatchMapping(path = "{id}")
    public ResponseEntity<ActionStatus> patchStatusById(@PathVariable int id, @RequestBody String jsonBody) {
        try {
            return new ResponseEntity<>(statusService.patchById(id, jsonBody), HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, e.getMessage(), e);
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, e.getMessage(), e);
        }
    }


}
