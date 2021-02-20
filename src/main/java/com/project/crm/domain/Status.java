package com.project.crm.domain;

import com.sun.istack.NotNull;
import lombok.*;
import org.hibernate.annotations.NaturalId;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
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

    @NaturalId
    private String name;

    @OneToMany(mappedBy = "status")
    @NotFound(action = NotFoundAction.IGNORE)
    private List<Task> tasks;

    @OneToMany(mappedBy = "status")
    @NotFound(action = NotFoundAction.IGNORE)
    private List<Investment> investments;

}
