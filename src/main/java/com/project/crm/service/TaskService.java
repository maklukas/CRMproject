package com.project.crm.service;

import com.project.crm.domain.Task;
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

    public void createTask(Task task) {
        LOGGER.info("Adding task");
        repository.save(task);
    }

    public void deleteTask(int id) {
        LOGGER.info("Deleting task by id");
        repository.deleteById(id);
    }

    public void updateTask(Task task) {
        LOGGER.info("Updating task");
        repository.save(task);
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
