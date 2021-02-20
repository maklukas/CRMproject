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
@Entity(name = "departments")
public class Department {

    public Department(String name) {
        this.name = name;
        this.users = new ArrayList<>();
    }

    @Id
    @NotNull
    @GeneratedValue
    @Column(name = "department_id")
    private int id;

    @NaturalId
    private String name;

    @OneToMany(mappedBy = "department")
    @NotFound(action = NotFoundAction.IGNORE)
    private List<User> users;
}
