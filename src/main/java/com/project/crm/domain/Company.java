package com.project.crm.domain;

import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "companies")
public class Company {
    @NotNull
    @Id
    @GeneratedValue
    @Column(name = "company_id")
    private int id;
    private String name;
    private String address;
    @Column(name = "tax_number")
    private String taxNumber;
}
