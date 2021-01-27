package com.project.crm.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class TaskDto {
    private int id;
    private User user;
    private Status status;
    private String title;
    private String description;
    private LocalDateTime creationTime;
    private LocalDateTime realizationTime;
}
