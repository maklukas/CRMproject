package com.project.crm.domain;

import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Getter
@Entity(name = "users")
@NoArgsConstructor
@AllArgsConstructor
@Setter
public class User {

    @Id
    @NotNull
    @GeneratedValue
    @Column(name = "user_id")
    private int id;

    @NotNull
    @Column(unique = true)
    private String username;
    @NotNull
    private String password;
    private String firstname;
    private String lastname;
    private Department department;

}
