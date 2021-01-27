package com.project.crm.domain;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "tasks")
public class Task {
    @Id
    @NotNull
    @GeneratedValue
    @Column(name = "task_id")
    private int id;
    private User user;
    private Status status;
    private String title;
    private String description;
    private LocalDateTime creationTime;
    private LocalDateTime realizationTime;
}
