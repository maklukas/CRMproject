package com.project.crm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServiceConnected {

    @Autowired
    public ClientService client;

    @Autowired
    public CompanyService company;

    @Autowired
    public InvestmentService investment;

    @Autowired
    public UserService user;

    @Autowired
    public DepartmentService department;

    @Autowired
    public StatusService status;

}
