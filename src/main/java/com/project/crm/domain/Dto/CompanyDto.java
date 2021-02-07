package com.project.crm.domain.Dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class CompanyDto {
    private int id;
    private String name;
    private String address;
    private String taxNumber;
    private List<ClientDto> employees;
    private List<InvestmentDto> investments;
}
