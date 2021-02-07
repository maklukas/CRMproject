package com.project.crm.domain.Dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class TaskDto {
    private int id;
    private List<UserDto> users;
    private StatusDto status;
    private String title;
    private String description;
    private LocalDateTime creationTime;
    private LocalDateTime realizationTime;
}
