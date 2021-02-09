package com.project.crm.controller;

import com.project.crm.domain.Dto.TaskDto;
import com.project.crm.mapper.MapperConnected;
import com.project.crm.mapper.TaskMapper;
import com.project.crm.service.ServiceConnected;
import com.project.crm.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/v1/tasks")
@CrossOrigin("*")
public class TaskController {

    @Autowired
    private ServiceConnected service;

    @Autowired
    private MapperConnected mapper;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public void createTask(@RequestBody TaskDto taskDto) {
        service.task.createTask(mapper.task.mapToTask(taskDto));
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public void updateTask(@RequestBody TaskDto taskDto) {
        service.task.createTask(mapper.task.mapToTask(taskDto));
    }

    @DeleteMapping(path = "/{id}")
    public void deleteTask(@PathVariable int id) {
        service.task.deleteTask(id);
    }

    @GetMapping
    public List<TaskDto> getTasks() {
        return mapper.task.mapToTaskDtoList(service.task.getTasks());
    }

    @GetMapping(path = "/v1/{id}")
    public TaskDto getTaskById(@PathVariable int id) {
        return mapper.task.mapToTaskDto(service.task.getTaskById(id));
    }

    @GetMapping(path = "/v1/fragment/{txt}")
    public List<TaskDto> getTaskByFragment(@PathVariable String txt) {
        return mapper.task.mapToTaskDtoList(service.task.getTaskByFragment(txt));
    }
}
