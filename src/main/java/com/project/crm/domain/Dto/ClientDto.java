package com.project.crm.domain.Dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ClientDto {
    private int id;
    private String firstname;
    private String lastname;
    private int phoneNo;
    private List<CompanyDto> companies;
    private List<InvestmentDto> investments;
}
