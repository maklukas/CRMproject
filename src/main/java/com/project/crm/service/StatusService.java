package com.project.crm.service;

import com.project.crm.domain.Status;
import com.project.crm.repository.StatusRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StatusService {

    private static Logger LOGGER = LoggerFactory.getLogger(StatusService.class);

    @Autowired
    private StatusRepository repository;

    public void createStatus(Status status) {
        LOGGER.info("Adding status");

        boolean result = repository.findAll().stream()
                .filter(status1 -> status1.getName().equals(status.getName()))
                .count() == 0;

        if (result) {
            repository.save(status);
        } else {
            LOGGER.error("Status already exists.");
        }
    }

    public void updateStatus(Status status) {
        LOGGER.info("Updating status");

        boolean result = repository.findAll().stream()
                .filter(status1 -> status1.getName().equals(status.getName()))
                .count() == 0;

        if (result) {
            repository.save(status);
        } else {
            LOGGER.error("Status already exists.");
        }
    }

    public void deleteStatus(int id) {
        LOGGER.info("Deleting status by id");
        try {
            repository.deleteById(id);
        } catch (Exception e) {
            LOGGER.error("Not found status!");
        }
    }

    public void deleteStatus(Status status) {
        LOGGER.info("Deleting status");
        try {
            repository.delete(status);
        } catch (Exception e) {
            LOGGER.error("Not found status!");
        }
    }

    public List<Status> getStatuses() {
        LOGGER.info("Fetching status");
        return repository.findAll();
    }

    public Status getStatus(int id) {
        LOGGER.info("Fetching status by id");
        return repository.findById(id).orElse(null);
    }
}
