package com.project.crm.controller;

import com.project.crm.domain.Dto.CompanyDto;
import com.project.crm.mapper.CompanyMapper;
import com.project.crm.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping(path = "/v1/companies")
public class CompanyController {

    @Autowired
    private CompanyService service;

    @Autowired
    private CompanyMapper mapper;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public void createCompany(@RequestBody CompanyDto companyDto) {
        service.createCompany(mapper.mapToCompany(companyDto));
    }

    @GetMapping
    public List<CompanyDto> getCompanies() {
        return mapper.mapToCompanyDtoList(service.getCompanies());
    }

    @GetMapping(path = "/{id}")
    public CompanyDto getCompanyById(@PathVariable int id) {
        return mapper.mapToCompanyDto(service.getCompanyById(id));
    }

    @GetMapping(path = "/fragment/{txt}")
    public List<CompanyDto> getCompaniesByFragment(@PathVariable String txt) {
        return mapper.mapToCompanyDtoList(service.getCompanyByFragment(txt));
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public void updateCompany(@RequestBody CompanyDto companyDto) {
        service.updateCompany(mapper.mapToCompany(companyDto));
    }

    @DeleteMapping(path = "/{id}")
    public void deleteCompany(@PathVariable int id) {
        service.deleteCompany(id);
    }
}
