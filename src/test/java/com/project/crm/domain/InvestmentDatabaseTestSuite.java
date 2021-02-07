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
public class InvestmentDatabaseTestSuite {

    @Autowired
    private ServiceConnected service;

    @Test
    public void shouldCreateInvestment() {
        //given
        Department department = new Department("Dział handlowy");
        User user = new User("TUName", "pass", "Fn", "Ln", department);
        Status status = new Status("NOWY");
        Investment investment = new Investment("Test investment", "Test Address", user, status);
        //when
        service.investment.createInvestment(investment);
        //then
        Assert.assertEquals(1, service.investment.getInvestments().size());
        Assert.assertEquals(1, service.department.getDepartments().size());
        Assert.assertEquals(1, service.user.getUsers().size());
        Assert.assertEquals(1, service.status.getStatuses().size());
        //cleanup
        service.investment.deleteInvestment(investment.getId());
        service.department.deleteDepartment(department.getId());
        service.user.deleteUser(user.getId());
        service.status.deleteStatus(status.getId());
    }
}
