package com.project.crm.domain;

import com.project.crm.service.ServiceConnected;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CompanyDatabaseTestSuite {

    @Autowired
    ServiceConnected service;

    @Test
    public void shouldCreateCompany() {
        //given
        Company company = new Company("Test Company SA", "Test Addres 33", "TX1234555");
        //when
        service.company.createCompany(company);
        //then
        Assert.assertEquals(1, service.company.getCompanies().size());
        //cleanUp
        service.company.deleteCompany(company.getId());
    }
}
