package com.project.crm.service;

import com.project.crm.domain.Status;
import com.project.crm.domain.Task;
import com.project.crm.domain.User;
import com.project.crm.repository.TaskRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaskService {
    private static Logger LOGGER = LoggerFactory.getLogger(TaskService.class);

    @Autowired
    private TaskRepository repository;

    @Autowired
    private ServiceConnected service;

    public boolean createTask(Task task) {
        LOGGER.info("Adding task");
        List<User> users = new ArrayList<>();

        try {
            task.setCreationTime(LocalDateTime.now());

            if (service.user.getUserFromSession() != null) {
                users.add(service.user.getUserFromSession());
                task.setUsers(users);
            } else if (task.getUsers().size() != 0) {
                users.addAll(service.user.getUsersByUsernames(task.getUsers().stream().map(User::getUsername).collect(Collectors.toList())));
                task.setUsers(users);
            }

            if (task.getStatus() != null) {
                task.setStatus(service.status.createStatus(task.getStatus()));
            } else {
                task.setStatus(service.status.createStatus(new Status("Active")));
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
                status = service.status.getStatusByName(task.getStatus().getName());
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

    public List<Task> getTasks4TheUser(String username) {
        LOGGER.info("Fetching tasks for the user");
        User user = service.user.getUserByUsername(username);
        return user.getTasks();
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
