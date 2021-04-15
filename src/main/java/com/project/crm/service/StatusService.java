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

    private StatusRepository repository;

    @Autowired
    public StatusService(StatusRepository repository) {
        this.repository = repository;
    }

    public Status createStatus(Status status) {
        LOGGER.info("Adding status");

        try {
            return getStatus(status);
        } catch (Exception e) {
            LOGGER.info("Cannot create status. " + e);
            return null;
        }
    }

    private Status getStatus(Status status) {
        if (getStatusByName(status.getName()) != null) {
            return getStatusByName(status.getName());
        } else {
            return repository.save(status);
        }
    }

    public boolean updateStatus(Status status) {
        LOGGER.info("Updating status");
        try {
            repository.save(status);
            return true;
        } catch (Exception e) {
            LOGGER.error("Status already exists." + e);
            return false;
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

    public Status getStatusByName(String name) {
        LOGGER.info("Fetching status by name");
        return repository.findByName(name).orElse(null);
    }
}
