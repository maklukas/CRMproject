package com.project.crm.controller;

import com.project.crm.domain.Dto.CompanyDto;
import com.project.crm.mapper.CompanyMapper;
import com.project.crm.mapper.MapperConnected;
import com.project.crm.service.CompanyService;
import com.project.crm.service.ServiceConnected;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping(path = "/v1/companies")
public class CompanyController {

    @Autowired
    private ServiceConnected service;

    @Autowired
    private MapperConnected mapper;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public void createCompany(@RequestBody CompanyDto companyDto) {
        service.company.createCompany(mapper.company.mapToCompany(companyDto));
    }

    @GetMapping
    public List<CompanyDto> getCompanies() {
        return mapper.company.mapToCompanyDtoList(service.company.getCompanies());
    }

    @GetMapping(path = "/{id}")
    public CompanyDto getCompanyById(@PathVariable int id) {
        return mapper.company.mapToCompanyDto(service.company.getCompanyById(id));
    }

    @GetMapping(path = "/fragment/{txt}")
    public List<CompanyDto> getCompaniesByFragment(@PathVariable String txt) {
        return mapper.company.mapToCompanyDtoList(service.company.getCompanyByFragment(txt));
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public void updateCompany(@RequestBody CompanyDto companyDto) {
        service.company.updateCompany(mapper.company.mapToCompany(companyDto));
    }

    @DeleteMapping(path = "/{id}")
    public void deleteCompany(@PathVariable int id) {
        service.company.deleteCompany(id);
    }
}
