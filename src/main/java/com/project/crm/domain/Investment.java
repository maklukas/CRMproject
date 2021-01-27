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
@Entity(name = "investments")
public class Investment {
    @Id
    @NotNull
    @GeneratedValue
    @Column(name = "investment_id")
    private int id;
    private String name;
    private String address;
    private User registeredBy;
    private Company company;
    private Status status;
}
