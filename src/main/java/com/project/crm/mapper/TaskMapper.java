package com.project.crm.mapper;

import com.project.crm.domain.Task;
import com.project.crm.domain.Dto.TaskDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class TaskMapper {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private StatusMapper statusMapper;

    public Task mapToTask(TaskDto task) {
        return new Task(
                task.getId(),
                userMapper.mapToUserList(task.getUsers()),
                statusMapper.mapToStatus(task.getStatus()),
                task.getTitle(),
                task.getDescription(),
                task.getCreationTime(),
                task.getRealizationTime()
        );
    }

    public TaskDto mapToTaskDto(Task task) {
        return new TaskDto(
                task.getId(),
                userMapper.mapToUserDtoList(task.getUsers()),
                statusMapper.mapToStatusDto(task.getStatus()),
                task.getTitle(),
                task.getDescription(),
                task.getCreationTime(),
                task.getRealizationTime()
        );
    }

    public List<TaskDto> mapToTaskDtoList(List<Task> tasks) {
        return tasks.stream()
                .map(task -> new TaskDto(
                        task.getId(),
                        userMapper.mapToUserDtoList(task.getUsers()),
                        statusMapper.mapToStatusDto(task.getStatus()),
                        task.getTitle(),
                        task.getDescription(),
                        task.getCreationTime(),
                        task.getRealizationTime()
                )).collect(Collectors.toList());
    }

    public List<Task> mapToTaskList(List<TaskDto> tasks) {
        return tasks.stream()
                .map(task -> new Task(
                        task.getId(),
                        userMapper.mapToUserList(task.getUsers()),
                        statusMapper.mapToStatus(task.getStatus()),
                        task.getTitle(),
                        task.getDescription(),
                        task.getCreationTime(),
                        task.getRealizationTime()
                )).collect(Collectors.toList());
    }
}
