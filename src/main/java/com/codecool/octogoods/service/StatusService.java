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
        if (statusRepository.existsById(id)) {
            // if so -> update with entire new object
            ActionStatus forUpdate = statusRepository
                    .findById(id)
                    .get();
            forUpdate.setName(status.getName());
            forUpdate.setAvailable(status.isAvailable());
            forUpdate.setActive(status.isActive());
            statusRepository.save(forUpdate);
        } else {
            // if not -> insert it
            status.setId(id);
            statusRepository.save(status);
        }
    }

    public void patchById(int id, String jsonBody) {
        // check if resource exists at id location
        if (statusRepository.existsById(id)) {
            // if so -> determine fields to change
            ActionStatus forUpdate = statusRepository
                    .findById(id)
                    .get();
            Gson gson = new Gson();
            Map mapBody = gson.fromJson(jsonBody, Map.class);
            // and set them with reflection and save
            mapBody.forEach((k, v) -> {
                Field field = ReflectionUtils.findField(ActionStatus.class, (String) k);
                field.setAccessible(true);
                ReflectionUtils.setField(field, forUpdate, v);
                System.out.println(String.format("Field %s set to %s", field.getName(), v.toString()));
            });
            statusRepository.save(forUpdate);
        } else {
            throw new EntityNotFoundException("Cannot find resource by 'id': " + id);
        }
    }
}
