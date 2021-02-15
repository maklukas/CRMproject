package com.project.crm.domain.Dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ClientDto {
    private int id;
    private String firstname;
    private String lastname;
    private int phoneNo;
    private List<CompanyDto> companies;
}
