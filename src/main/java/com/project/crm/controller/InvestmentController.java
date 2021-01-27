package com.project.crm.controller;

import com.project.crm.domain.InvestmentDto;
import com.project.crm.mapper.InvestmentMapper;
import com.project.crm.service.InvestmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/v1/investments")
@CrossOrigin("*")
public class InvestmentController {
    @Autowired
    private InvestmentService service;

    @Autowired
    private InvestmentMapper mapper;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public void createInvestment(@RequestBody InvestmentDto investmentDto) {
        service.createInvestment(mapper.mapToInvestment(investmentDto));
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public void updateInvestment(@RequestBody InvestmentDto investmentDto) {
        service.updateInvestment(mapper.mapToInvestment(investmentDto));
    }

    @DeleteMapping(path = "/{id}")
    public void deleteInvestment(@PathVariable int id) {
        service.deleteInvestment(id);
    }

    @GetMapping
    public List<InvestmentDto> getInvestments() {
        return mapper.mapToInvestmentDtoList(service.getInvestments());
    }

    @GetMapping (path = "/{id}")
    public InvestmentDto getInvestmentById(@PathVariable int id) {
        return mapper.mapToInvestmentDto(service.getInvestmentById(id));
    }

    @GetMapping (path = "/fragment/{txt}")
    public List<InvestmentDto> getInvestmentByFragment(@PathVariable String txt) {
        return mapper.mapToInvestmentDtoList(service.getInvestmentByFragment(txt));
    }
}
