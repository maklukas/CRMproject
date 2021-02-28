package com.project.crm.domain.Dto;

import com.project.crm.domain.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TaskDto {
    private int id;
    private List<UserDto> users;
    private Status status;
    private String title;
    private String description;
    private LocalDateTime creationTime;
    private LocalDateTime realizationTime;
}
