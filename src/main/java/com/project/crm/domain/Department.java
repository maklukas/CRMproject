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

    @Column(unique = true)
    private String name;

    @OneToMany(mappedBy = "department")
    @NotFound(action = NotFoundAction.IGNORE)
    private List<User> users;
}
