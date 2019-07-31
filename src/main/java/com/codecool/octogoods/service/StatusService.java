package com.codecool.octogoods.service;

import com.codecool.octogoods.dao.StatusRepository;
import com.codecool.octogoods.model.ActionStatus;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

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

    public ActionStatus getById(int id){
        return statusRepository
                .findById(id)
                .orElseThrow(() -> new EntityNotFoundException("'id': " + id));
    }

    public void updateById(int id, ActionStatus status){
        ActionStatus statusForUpdate = statusRepository
                .findById(id)
                .orElseThrow(() -> new EntityNotFoundException("id: " + id));

        if (statusForUpdate != null){
            statusForUpdate.setName(status.getName());
            statusForUpdate.setAvailable(status.isAvailable());
            statusForUpdate.setActive(status.isActive());
            statusRepository.save(statusForUpdate);
        }
    }
}
