package com.project.crm.domain;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "clients")
public class Client {
    @Id
    @NotNull
    @GeneratedValue
    @Column(name = "client_id")
    private int id;
    private String firstname;
    private String lastname;
    @Column(name = "phone_no")
    private int phoneNo;
    private Company company;
}
