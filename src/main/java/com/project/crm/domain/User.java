package com.project.crm.domain;

import com.sun.istack.NotNull;
import lombok.*;
import org.hibernate.annotations.NaturalId;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@Entity(name = "users")
public class User {

    public User(int id, String username, String password, String firstname, String lastname, Department department, List<Investment> investments, List<Task> tasks, Role role) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.firstname = firstname;
        this.lastname = lastname;
        this.department = department;
        this.investments = investments;
        this.tasks = tasks;
        this.role = role;
    }

    public User(String username, String password, String firstname, String lastname, Department department, Role role) {
        this.username = username;
        this.password = password;
        this.firstname = firstname;
        this.lastname = lastname;
        this.department = department;
        this.tasks = new ArrayList<>();
        this.investments = new ArrayList<>();
        this.role = role;
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

    private String firstname;

    private String lastname;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @NotFound(action = NotFoundAction.IGNORE)
    @JoinColumn(name = "department_id")
    private Department department;

    @OneToMany(mappedBy = "registeredBy")
    @NotFound(action = NotFoundAction.IGNORE)
    private List<Investment> investments;

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

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @NotFound(action = NotFoundAction.IGNORE)
    @JoinColumn(name = "role_id")
    private Role role;

}


