package com.project.crm.controller;

import com.project.crm.domain.Dto.InvestmentDto;
import com.project.crm.mapper.MapperConnected;
import com.project.crm.service.ServiceConnected;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/v1/investments")
@CrossOrigin("*")
public class InvestmentController {
    @Autowired
    private ServiceConnected service;

    @Autowired
    private MapperConnected mapper;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public void createInvestment(@RequestBody InvestmentDto investmentDto) {
        service.investment.createInvestment(mapper.investment.mapToInvestment(investmentDto));
    }

    @PatchMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public void updateInvestment(@RequestBody InvestmentDto investmentDto) {
        service.investment.updateInvestment(mapper.investment.mapToInvestment(investmentDto));
    }

    @DeleteMapping(path = "/{id}")
    public void deleteInvestment(@PathVariable int id) {
        service.investment.deleteInvestment(id);
    }

    @GetMapping
    public List<InvestmentDto> getInvestments() {
        return mapper.investment.mapToInvestmentDtoList(service.investment.getInvestments());
    }

    @GetMapping (path = "/{id}")
    public InvestmentDto getInvestmentById(@PathVariable int id) {
        return mapper.investment.mapToInvestmentDto(service.investment.getInvestmentById(id));
    }

    @GetMapping (path = "/fragment/{txt}")
    public List<InvestmentDto> getInvestmentByFragment(@PathVariable String txt) {
        return mapper.investment.mapToInvestmentDtoList(service.investment.getInvestmentByFragment(txt));
    }
}
