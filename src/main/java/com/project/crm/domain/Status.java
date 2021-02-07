package com.project.crm.domain;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "statuses")
public class Status {

    public Status(String name) {
        this.name = name;
        this.tasks = new ArrayList<>();
        this.investments = new ArrayList<>();
    }

    @Id
    @GeneratedValue
    @Column(name = "status_id")
    @NotNull
    private int id;

    @Column(unique = true)
    private String name;

    @OneToMany(mappedBy = "status")
    @NotFound(action = NotFoundAction.IGNORE)
    private List<Task> tasks;

    @OneToMany(mappedBy = "status")
    @NotFound(action = NotFoundAction.IGNORE)
    private List<Investment> investments;

}
