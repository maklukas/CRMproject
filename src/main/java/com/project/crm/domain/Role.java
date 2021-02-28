package com.project.crm.domain;

import com.sun.istack.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.NaturalId;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@Entity(name = "roles")
public class Role {

    public Role(String name) {
        this.name = name;
    }

    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id")
    private int id;

    @NaturalId
    @Column(unique = true)
    private String name;

    @OneToMany(mappedBy = "role")
    @NotFound(action = NotFoundAction.IGNORE)
    private List<User> users;
}
