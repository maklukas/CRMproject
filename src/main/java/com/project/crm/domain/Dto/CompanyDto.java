package com.project.crm.domain.Dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CompanyDto {
    private int id;
    private String name;
    private String address;
    private String taxNumber;
    private StatusDto status;
}
