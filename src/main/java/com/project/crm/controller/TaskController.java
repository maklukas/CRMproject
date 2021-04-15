package com.project.crm.controller;

import com.project.crm.domain.Dto.TaskDto;
import com.project.crm.mapper.MapperConnected;
import com.project.crm.service.ServiceConnected;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping(path = "/v1/tasks")
@CrossOrigin("*")
public class TaskController {

    private ServiceConnected service;
    private MapperConnected mapper;

    @Autowired
    public TaskController(ServiceConnected service, MapperConnected mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @PostMapping
    public void createTask(@ModelAttribute TaskDto taskDto, HttpServletResponse response) {
        service.task.createTask(mapper.task.mapToTask(taskDto));
        try {
            response.sendRedirect("/tasks");
        } catch (IOException e) {

        }
    }

//    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
//    public void createTask(@RequestBody TaskDto taskDto) {
//        service.task.createTask(mapper.task.mapToTask(taskDto));
//    }

    //TODO add @PutMapping method

    @PatchMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public void updateTask(@RequestBody TaskDto taskDto) {
        service.task.updateTask(mapper.task.mapToTask(taskDto));
    }

    @DeleteMapping(params = "id")
    public void deleteTask(@RequestParam int id) {
        service.task.deleteTask(id);
    }

    @GetMapping
    public List<TaskDto> getTasks() {
        return mapper.task.mapToTaskDtoList(service.task.getTasks());
    }

    @GetMapping(params = "id")
    public TaskDto getTaskById(@RequestParam int id) {
        return mapper.task.mapToTaskDto(service.task.getTaskById(id));
    }

    @GetMapping(params = "fragment")
    public List<TaskDto> getTaskByFragment(@RequestParam String fragment) {
        return mapper.task.mapToTaskDtoList(service.task.getTaskByFragment(fragment));
    }
}
