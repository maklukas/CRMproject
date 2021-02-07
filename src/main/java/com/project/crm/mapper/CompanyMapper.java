package com.project.crm.mapper;

import com.project.crm.domain.Company;
import com.project.crm.domain.Dto.CompanyDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CompanyMapper {

    @Autowired
    private ClientMapper employeeMapper;

    @Autowired
    private InvestmentMapper investmentMapper;

    public Company mapToCompany(CompanyDto company) {
        return new Company(
                company.getId(),
                company.getName(),
                company.getAddress(),
                company.getTaxNumber(),
                employeeMapper.mapToClientList(company.getEmployees()),
                investmentMapper.mapToInvestmentList(company.getInvestments())
        );
    }

    public CompanyDto mapToCompanyDto(Company company) {
        return new CompanyDto(
                company.getId(),
                company.getName(),
                company.getAddress(),
                company.getTaxNumber(),
                employeeMapper.mapToClientDtoList(company.getEmployees()),
                investmentMapper.mapToInvestmentDtoList(company.getInvestments())
        );
    }

    public List<CompanyDto> mapToCompanyDtoList(List<Company> companies) {
        return companies.stream()
                .map(company -> new CompanyDto(
                        company.getId(),
                        company.getName(),
                        company.getAddress(),
                        company.getTaxNumber(),
                        employeeMapper.mapToClientDtoList(company.getEmployees()),
                        investmentMapper.mapToInvestmentDtoList(company.getInvestments())
                )).collect(Collectors.toList());
    }

    public List<Company> mapToCompanyList(List<CompanyDto> companies) {
        return companies.stream()
                .map(company -> new Company(
                        company.getId(),
                        company.getName(),
                        company.getAddress(),
                        company.getTaxNumber(),
                        employeeMapper.mapToClientList(company.getEmployees()),
                        investmentMapper.mapToInvestmentList(company.getInvestments())
                )).collect(Collectors.toList());
    }

}
