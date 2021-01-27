package com.project.crm.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ClientDto {
    private int id;
    private String firstname;
    private String lastname;
    private int phoneNo;
    private Company company;
}
