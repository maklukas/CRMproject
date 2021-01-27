package com.project.crm.mapper;

import com.project.crm.domain.Company;
import com.project.crm.domain.CompanyDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CompanyMapper {

    public CompanyDto mapToCompanyDto(Company company) {
        return new CompanyDto(
                company.getId(),
                company.getName(),
                company.getAddress(),
                company.getTaxNumber()
        );
    }

    public Company mapToCompany(CompanyDto companyDto) {
        return new Company(
                companyDto.getId(),
                companyDto.getName(),
                companyDto.getAddress(),
                companyDto.getTaxNumber()
        );
    }

    public List<CompanyDto> mapToCompanyDtoList(List<Company> companies) {
               return companies.stream()
                .map(company -> new CompanyDto(
                        company.getId(),
                        company.getName(),
                        company.getAddress(),
                        company.getTaxNumber()))
                .collect(Collectors.toList());
    }
}
