package com.project.crm.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class CompanyDto {
    private int id;
    private String name;
    private String address;
    private String taxNumber;
}
