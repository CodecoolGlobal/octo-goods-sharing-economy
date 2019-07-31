package com.codecool.octogoods.service;

import com.codecool.octogoods.dao.StatusRepository;
import com.codecool.octogoods.model.ActionStatus;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Map;

@Service
public class StatusService {

    private final StatusRepository statusRepository;
    private final FieldInspector fieldInspector;

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
                .orElseThrow(() -> new EntityNotFoundException("'id': " + id));
    }

    public void putById(int id, ActionStatus status) {
        // check if all fields are present - covered by @Valid
        // check if resource exists at id location - if not insert it.
        if (!statusRepository.existsById(id)) {
            status.setId(id);
            statusRepository.save(status);
        } else {
            ActionStatus forUpdate = statusRepository
                    .findById(id)
                    .get();
            forUpdate.setName(status.getName());
            forUpdate.setAvailable(status.isAvailable());
            forUpdate.setActive(status.isActive());
            statusRepository.save(forUpdate);
        }
    }
}
