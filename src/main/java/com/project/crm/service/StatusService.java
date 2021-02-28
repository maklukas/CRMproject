package com.project.crm.service;

import com.project.crm.domain.Status;
import com.project.crm.repository.StatusRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
public class StatusService {

    private static Logger LOGGER = LoggerFactory.getLogger(StatusService.class);

    @Autowired
    private StatusRepository repository;

    public boolean createStatus(Status status) {
        LOGGER.info("Adding status");

        try {
            repository.save(status);
            return true;
        } catch (Exception e) {
            LOGGER.error("Status already exists." + e);
            return false;
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
}
