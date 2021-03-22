package com.project.crm.domain;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.NaturalId;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity(name = "users")
public class User {

    public User(String username, String password, String firstname, String lastname) {
        this.username = username;
        this.password = password;
        this.firstname = firstname;
        this.lastname = lastname;
    }

    private static Logger LOGGER = LoggerFactory.getLogger(User.class);

    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private int id;

    @NotNull
    @NaturalId
    @Column(unique = true)
    private String username;

    @NotNull
    private String password;

    @Transient
    private String confirmPassword;

    private String firstname;

    private String lastname;

    @ManyToOne(cascade = CascadeType.MERGE)
    @NotFound(action = NotFoundAction.IGNORE)
    @JoinColumn(name = "department_id")
    private Department department;

    @ManyToMany(mappedBy = "users")
    @NotFound(action = NotFoundAction.IGNORE)
    private List<Task> tasks;

    public void addTask(Task task) {
        LOGGER.info("Create task");
        tasks.add(task);
        task.getUsers().add(this);
    }

    public void removeTask(Task task) {
        LOGGER.info("Removing task");
        tasks.remove(task);
        task.getUsers().remove(this);
    }

    @ManyToOne(cascade = CascadeType.MERGE)
    @NotFound(action = NotFoundAction.IGNORE)
    @JoinColumn(name = "role_id")
    private Role role;

    @ManyToOne(cascade = CascadeType.MERGE)
    @NotFound(action = NotFoundAction.IGNORE)
    @JoinColumn(name = "status_id")
    private Status status;

}


