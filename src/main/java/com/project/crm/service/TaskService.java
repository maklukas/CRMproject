package com.project.crm.service;

import com.project.crm.domain.Status;
import com.project.crm.domain.Task;
import com.project.crm.repository.StatusRepository;
import com.project.crm.repository.TaskRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaskService {
    private static Logger LOGGER = LoggerFactory.getLogger(TaskService.class);

    @Autowired
    private TaskRepository repository;

    @Autowired
    private StatusRepository statusRepository;

    public boolean createTask(Task task) {
        LOGGER.info("Adding task");
        Status status;
        try {
            if (task.getStatus() != null) {
                status = statusRepository.findByName(task.getStatus().getName()).orElse(task.getStatus());
                task.setStatus(status);
            }
            repository.save(task);
            return true;
        } catch (Exception e) {
            LOGGER.error("Cannot add task. " + e);
            return false;
        }
    }

    public boolean updateTask(Task task) {
        LOGGER.info("Updating task");
        Status status;
        try {
            if (task.getStatus() != null) {
                status = statusRepository.findByName(task.getStatus().getName()).orElse(task.getStatus());
                task.setStatus(status);
            }
            repository.save(task);
            return true;
        } catch (Exception e) {
            LOGGER.error("Cannot update task. "+ e);
            return false;
        }
    }

    public void deleteTask(int id) {
        LOGGER.info("Deleting task by id");
        try {
            repository.deleteById(id);
        } catch (Exception e) {
            LOGGER.error("Not found task!");
        }
    }

    public void deleteTask(Task task) {
        LOGGER.info("Deleting task");
        try {
            repository.delete(task);
        } catch (Exception e) {
            LOGGER.error("Not found task!");
        }
    }

    public List<Task> getTasks() {
        LOGGER.info("Fetching all tasks");
        return repository.findAll();
    }

    public Task getTaskById(int id) {
        LOGGER.info("Fetching task by id");
        return repository.findById(id).orElse(null);
    }

    public List<Task> getTaskByFragment(String txt) {
        LOGGER.info("Fetching tasks by text fragment");
        return repository.findAll().stream()
                .filter(task -> task.getTitle().contains(txt) || task.getDescription().contains(txt))
                .collect(Collectors.toList());
    }
}
