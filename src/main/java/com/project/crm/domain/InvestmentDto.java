package com.project.crm.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class InvestmentDto {
    private int id;
    private String name;
    private String address;
    private User registeredBy;
    private Company company;
    private Status status;
}
