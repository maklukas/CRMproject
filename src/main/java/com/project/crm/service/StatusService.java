package com.project.crm.service;

import com.project.crm.domain.Status;
import com.project.crm.repository.StatusRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class StatusService {

    private static Logger LOGGER = LoggerFactory.getLogger(StatusService.class);

    @Autowired
    private StatusRepository repository;

    public void createStatus(Status status) {
        LOGGER.info("Adding department");
        repository.save(status);
    }

    public void updateStatus(Status status) {
        LOGGER.info("Updating department");
        repository.save(status);
    }

    public void deleteStatus(int id) {
        LOGGER.info("Deleting department by id");
        repository.deleteById(id);
    }

    public List<Status> getStatuses() {
        LOGGER.info("Fetching departments");
        return repository.findAll();
    }

    public Status getStatus(int id) {
        LOGGER.info("Fetching department by id");
        return repository.findById(id).orElse(null);
    }
}
