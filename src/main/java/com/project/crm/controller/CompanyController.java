package com.project.crm.controller;

import com.project.crm.domain.Dto.CompanyDto;
import com.project.crm.mapper.MapperConnected;
import com.project.crm.service.ServiceConnected;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping(path = "/v1/companies")
public class CompanyController {

    private ServiceConnected service;
    private MapperConnected mapper;

    @Autowired
    public CompanyController(ServiceConnected service, MapperConnected mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public void createCompany(@RequestBody CompanyDto companyDto) {
        service.company.createCompany(mapper.company.mapToCompany(companyDto));
    }

    @GetMapping
    public List<CompanyDto> getCompanies() {
        return mapper.company.mapToCompanyDtoList(service.company.getCompanies());
    }

    @GetMapping(params = "id")
    public CompanyDto getCompanyById(@RequestParam int id) {
        return mapper.company.mapToCompanyDto(service.company.getCompanyById(id));
    }

    @GetMapping(params = "fragment")
    public List<CompanyDto> getCompaniesByFragment(@RequestParam String fragment) {
        return mapper.company.mapToCompanyDtoList(service.company.getCompanyByFragment(fragment));
    }

    //TODO add @PutMapping method

    @PatchMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public void updateCompany(@RequestBody CompanyDto companyDto) {
        service.company.updateCompany(mapper.company.mapToCompany(companyDto));
    }

    @DeleteMapping(params = "id")
    public void deleteCompany(@RequestParam int id) {
        service.company.deleteCompany(id);
    }
}
