package com.project.crm.domain;

import com.project.crm.repository.StatusRepository;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "tasks")
public class Task {

    public Task(String title, String description, LocalDateTime realizationTime, Status status) {
        this.title = title;
        this.description = description;
        this.creationTime = LocalDateTime.now();
        this.realizationTime = realizationTime;
        this.users = new ArrayList<>();
        this.status = status;
    }

    private static Logger LOGGER = LoggerFactory.getLogger(Task.class);

    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "task_id")
    private int id;

    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinTable(
            name = "join_task_user",
            joinColumns = {@JoinColumn(name = "task_id", referencedColumnName = "task_id")},
            inverseJoinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "user_id")}
    )
    @NotFound(action = NotFoundAction.IGNORE)
    private List<User> users;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @NotFound(action = NotFoundAction.IGNORE)
    @JoinColumn(name = "status_id")
    private Status status;

    private String title;

    private String description;

    private LocalDateTime creationTime;

    private LocalDateTime realizationTime;

    public void addUser(User user) {
        LOGGER.info("Creating user");
        users.add(user);
        user.getTasks().add(this);
    }

    public void removeUser(User user) {
        LOGGER.info("Removing user");
        users.remove(user);
        user.getTasks().remove(this);
    }

}
