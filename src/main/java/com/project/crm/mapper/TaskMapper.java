package com.project.crm.mapper;

import com.project.crm.domain.Task;
import com.project.crm.domain.Dto.TaskDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class TaskMapper {

    private static Logger LOGGER = LoggerFactory.getLogger(TaskMapper.class);

    @Autowired
    private MapperConnected mapper;

    public Task mapToTask(TaskDto task) {
        if (task == null) {
            LOGGER.error("Mapping filed..");
            return new Task();
        } else {
            return new Task(
                    task.getId(),
                    mapper.user.mapToUserList(task.getUsers()),
                    mapper.status.mapToStatus(task.getStatus()),
                    task.getTitle(),
                    task.getDescription(),
                    task.getCreationTime(),
                    task.getRealizationTime()
            );
        }
    }

    public TaskDto mapToTaskDto(Task task) {
        if (task == null) {
            LOGGER.error("Mapping filed..");
            return new TaskDto();
        } else {
            return new TaskDto(
                    task.getId(),
                    mapper.user.mapToUserDtoList(task.getUsers()),
                    mapper.status.mapToStatusDto(task.getStatus()),
                    task.getTitle(),
                    task.getDescription(),
                    task.getCreationTime(),
                    task.getRealizationTime()
            );
        }
    }

    public List<TaskDto> mapToTaskDtoList(List<Task> tasks) {
        if (tasks == null) {
            LOGGER.error("Mapping filed..");
            return new ArrayList<>();
        } else {
            return tasks.stream()
                    .map(task -> new TaskDto(
                            task.getId(),
                            mapper.user.mapToUserDtoList(task.getUsers()),
                            mapper.status.mapToStatusDto(task.getStatus()),
                            task.getTitle(),
                            task.getDescription(),
                            task.getCreationTime(),
                            task.getRealizationTime()
                    )).collect(Collectors.toList());
        }
    }

    public List<Task> mapToTaskList(List<TaskDto> tasks) {
        if (tasks == null) {
            LOGGER.error("Mapping filed..");
            return new ArrayList<>();
        } else {
            return tasks.stream()
                    .map(task -> new Task(
                            task.getId(),
                            mapper.user.mapToUserList(task.getUsers()),
                            mapper.status.mapToStatus(task.getStatus()),
                            task.getTitle(),
                            task.getDescription(),
                            task.getCreationTime(),
                            task.getRealizationTime()
                    )).collect(Collectors.toList());
        }
    }
}
