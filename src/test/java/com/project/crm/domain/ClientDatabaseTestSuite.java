package com.project.crm.domain;

import com.project.crm.service.*;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ClientDatabaseTestSuite {

    @Autowired
    private ServiceConnected serviceConnected;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    public void shouldCreateClient() throws Throwable {
        //given
        Client client = new Client("Testowy", "Klient", 777444666);
        Company company = new Company("Company", "Addres testowy", "PL43413443434");
        Department department = new Department("Test Random");
        User user = new User("TestingUser", "password", "fn", "ln", department);
        Status status = new Status("TestingStatus");
        Investment investment = new Investment("Inwestycja", "bna", user, status);
        client.addCompany(company);
        client.addInvestment(investment);
        //when
        int assertClientSize = serviceConnected.client.getClients().size() + 1;
        int assertCompanySize = serviceConnected.company.getCompanies().size() + 1;
        int assertInvestmentsSize = serviceConnected.investment.getInvestments().size() + 1;
        int assertDepartmentSize = serviceConnected.department.getDepartments().size() + 1;
        int assertUsersSize = serviceConnected.user.getUsers().size() + 1;

        serviceConnected.client.createClient(client);
        //then
        Assert.assertEquals(assertClientSize, serviceConnected.client.getClients().size());
        Assert.assertEquals(assertCompanySize, serviceConnected.company.getCompanies().size());
        Assert.assertEquals(assertInvestmentsSize, serviceConnected.investment.getInvestments().size());
        Assert.assertEquals(assertDepartmentSize, serviceConnected.department.getDepartments().size());
        Assert.assertEquals(assertUsersSize, serviceConnected.user.getUsers().size());
        Assert.assertTrue(passwordEncoder.matches("password", serviceConnected.user.getUserById(user.getId()).getPassword()));
        //cleanup

        serviceConnected.client.deleteClient(client.getId());
        serviceConnected.company.deleteCompany(company.getId());
        serviceConnected.department.deleteDepartment(department.getId());
        serviceConnected.user.deleteUser(user.getId());
        serviceConnected.status.deleteStatus(status.getId());
        serviceConnected.investment.deleteInvestment(investment.getId());


    }

}
