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
public class InvestmentDto {
    private int id;
    private String name;
    private String address;
    private UserDto registeredBy;
    private List<CompanyDto> companies;
    private List<ClientDto> clients;
    private StatusDto status;
}
