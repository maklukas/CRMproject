package com.project.crm.mapper;

import com.project.crm.domain.Task;
import com.project.crm.domain.Dto.TaskDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class TaskMapper {

    @Autowired
    private ModelMapper modelMapper;

    public Task mapToTask(TaskDto task) {
        return modelMapper.map(task, Task.class);
    }

    public TaskDto mapToTaskDto(Task task) {
        return modelMapper.map(task, TaskDto.class);
    }

    public List<Task> mapToTaskList(List<TaskDto> tasks) {
        return tasks.stream()
                .map(task -> modelMapper.map(task, Task.class))
                .collect(Collectors.toList());
    }

    public List<TaskDto> mapToTaskDtoList(List<Task> tasks) {
        return tasks.stream()
                .map(task -> modelMapper.map(task, TaskDto.class))
                .collect(Collectors.toList());
    }
}
