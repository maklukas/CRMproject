package com.project.crm.mapper;


import com.project.crm.domain.Company;
import com.project.crm.domain.Dto.CompanyDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CompanyMapper {

    @Autowired
    private ModelMapper modelMapper;

    public Company mapToCompany(CompanyDto company) {
        return modelMapper.map(company, Company.class);
    }

    public CompanyDto mapToCompanyDto(Company company) {
        return modelMapper.map(company, CompanyDto.class);
    }

    public List<Company> mapToCompanyList(List<CompanyDto> companies) {
        return companies.stream()
                .map(company -> modelMapper.map(company, Company.class))
                .collect(Collectors.toList());
    }

    public List<CompanyDto> mapToCompanyDtoList(List<Company> companies) {
        return companies.stream()
                .map(company -> modelMapper.map(company, CompanyDto.class))
                .collect(Collectors.toList());
    }

}
