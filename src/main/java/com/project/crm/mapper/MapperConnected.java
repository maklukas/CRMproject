package com.project.crm.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MapperConnected {

    @Autowired
    public ClientMapper client;

    @Autowired
    public CompanyMapper company;

    @Autowired
    public DepartmentMapper department;

    @Autowired
    public InvestmentMapper investment;

    @Autowired
    public StatusMapper status;

    @Autowired
    public TaskMapper task;

    @Autowired
    public UserMapper user;
}
