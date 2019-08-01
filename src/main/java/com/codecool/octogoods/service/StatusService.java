package com.codecool.octogoods.service;

import com.codecool.octogoods.dao.StatusRepository;
import com.codecool.octogoods.model.ActionStatus;
import com.google.gson.Gson;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import javax.persistence.EntityNotFoundException;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

@Service
public class StatusService {

    private final StatusRepository statusRepository;

    public StatusService(StatusRepository statusRepository) {
        this.statusRepository = statusRepository;
    }

    public void add(ActionStatus status) {
        statusRepository.save(status);
    }

    public List<ActionStatus> getAll() {
        return (List<ActionStatus>) statusRepository.findAll();
    }

    public ActionStatus getById(int id) {
        return statusRepository
                .findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Cannot find resource by 'id': " + id));
    }

    public void putById(int id, ActionStatus status) {
        // check if all fields are present - covered by @Valid
        // check if resource exists at id location..
        Optional<ActionStatus> forUpdate = statusRepository
                .findById(id);
        if (forUpdate.isPresent()) {
            // if so -> update with entire new object
            ActionStatus statusForUpdate = forUpdate.get();
            statusForUpdate.setName(status.getName());
            statusForUpdate.setAvailable(status.isAvailable());
            statusForUpdate.setActive(status.isActive());
            statusRepository.save(statusForUpdate);
        } else {
            // if not -> insert it
            status.setId(id);
            statusRepository.save(status);
        }
    }

    public void patchById(int id, String jsonBody) {
        // check if resource exists at id location
        Optional<ActionStatus> forUpdate = statusRepository
                .findById(id);
        if (forUpdate.isPresent()) {
            // if so -> determine fields to change
            Gson gson = new Gson();
            Map mapBody = gson.fromJson(jsonBody, Map.class);
            // and set them with reflection and save
            try {
                mapBody.forEach((k, v) -> {
                    Field field = ReflectionUtils.findField(ActionStatus.class, (String) k);
                    Objects.requireNonNull(field).setAccessible(true);
                    ReflectionUtils.setField(field, forUpdate.get(), v);
                });
                statusRepository.save(forUpdate.get());
            } catch (NullPointerException e) {
                throw new IllegalArgumentException("Incorrect field set, please verify request body");
            }
        } else {
            throw new EntityNotFoundException("Cannot find resource by 'id': " + id);
        }
    }


}
