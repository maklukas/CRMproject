package com.project.crm.domain;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity(name = "tasks")
public class Task {

    public Task(String title, String description, LocalDateTime realizationTime) {
        this.title = title;
        this.description = description;
        this.creationTime = LocalDateTime.now();
        this.realizationTime = realizationTime;
    }

    private static Logger LOGGER = LoggerFactory.getLogger(Task.class);

    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "task_id")
    private int id;

    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(
            name = "join_task_user",
            joinColumns = {@JoinColumn(name = "task_id", referencedColumnName = "task_id")},
            inverseJoinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "user_id")}
    )
    @NotFound(action = NotFoundAction.IGNORE)
    private List<User> users;

    @ManyToOne(cascade = CascadeType.MERGE)
    @NotFound(action = NotFoundAction.IGNORE)
    @JoinColumn(name = "status_id")
    private Status status;

    private String title;

    private String description;

    private LocalDateTime creationTime;

    private LocalDateTime realizationTime;

}
