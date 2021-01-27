package com.project.crm.mapper;

import com.project.crm.domain.Task;
import com.project.crm.domain.TaskDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Component
public class TaskMapper {
    public Task mapToTask(TaskDto taskDto) {
        return new Task(
                taskDto.getId(),
                taskDto.getUser(),
                taskDto.getStatus(),
                taskDto.getTitle(),
                taskDto.getDescription(),
                taskDto.getCreationTime(),
                taskDto.getRealizationTime()
        );
    }
    public TaskDto mapToTaskDto(Task task) {
        return new TaskDto(
                task.getId(),
                task.getUser(),
                task.getStatus(),
                task.getTitle(),
                task.getDescription(),
                task.getCreationTime(),
                task.getRealizationTime()
        );
    }

    public List<TaskDto> mapToTaskDtoList(List<Task> taskList) {
        return taskList.stream()
                .map(task -> new TaskDto(
                        task.getId(),
                        task.getUser(),
                        task.getStatus(),
                        task.getTitle(),
                        task.getDescription(),
                        task.getCreationTime(),
                        task.getRealizationTime()
                ))
                .collect(Collectors.toList());

    }
}
