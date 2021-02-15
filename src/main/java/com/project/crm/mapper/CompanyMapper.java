package com.project.crm.mapper;

import com.project.crm.domain.Company;
import com.project.crm.domain.Dto.CompanyDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class CompanyMapper {

    private static Logger LOGGER = LoggerFactory.getLogger(CompanyMapper.class);

    @Autowired
    private MapperConnected mapper;

    public Company mapToCompany(CompanyDto company) {
        if (company == null) {
            LOGGER.error("Mapping filed..");
            return new Company();
        } else {
            return new Company(
                    company.getId(),
                    company.getName(),
                    company.getAddress(),
                    company.getTaxNumber(),
                    mapper.client.mapToClientList(company.getEmployees()),
                    mapper.investment.mapToInvestmentList(company.getInvestments())
            );
        }
    }

    public CompanyDto mapToCompanyDto(Company company) {
        if (company == null) {
            LOGGER.error("Mapping filed..");
            return new CompanyDto();
        } else {
            return new CompanyDto(
                    company.getId(),
                    company.getName(),
                    company.getAddress(),
                    company.getTaxNumber(),
                    mapper.client.mapToClientDtoList(company.getEmployees()),
                    mapper.investment.mapToInvestmentDtoList(company.getInvestments())
            );
        }
    }

    public List<CompanyDto> mapToCompanyDtoList(List<Company> companies) {
        if (companies == null) {
            LOGGER.error("Mapping filed..");
            return new ArrayList<>();
        } else {
            return companies.stream()
                    .map(company -> new CompanyDto(
                            company.getId(),
                            company.getName(),
                            company.getAddress(),
                            company.getTaxNumber(),
                            mapper.client.mapToClientDtoList(company.getEmployees()),
                            mapper.investment.mapToInvestmentDtoList(company.getInvestments())
                    )).collect(Collectors.toList());
        }
    }

    public List<Company> mapToCompanyList(List<CompanyDto> companies) {
        if (companies == null) {
            LOGGER.error("Mapping filed..");
            return new ArrayList<>();
        } else {
            return companies.stream()
                    .map(company -> new Company(
                            company.getId(),
                            company.getName(),
                            company.getAddress(),
                            company.getTaxNumber(),
                            mapper.client.mapToClientList(company.getEmployees()),
                            mapper.investment.mapToInvestmentList(company.getInvestments())
                    )).collect(Collectors.toList());
        }
    }

}
