package com.project.crm.domain;

import com.project.crm.securingweb.PassEncryptor;
import com.project.crm.service.*;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ClientDatabaseTestSuite {

    @Autowired
    private ServiceConnected serviceConnected;

    @Test
    public void shouldCreateClient() throws Throwable {
        //given
        Client client = new Client("Testowy", "Klient", 777444666);
        Company company = new Company("Company", "Addres testowy", "PL443443434");
        Department department = new Department("Dział sprzedaży");
        User user = new User("username", "password", "fn", "ln", department);
        Status status = new Status("Nowy");
        Investment investment = new Investment("Inwestycja", "bna", user, status);
        client.addCompany(company);
        client.addInvestment(investment);
        //when
        serviceConnected.client.createClient(client);
        //then
        Assert.assertEquals(1, serviceConnected.client.getClients().size());
        Assert.assertEquals(1, serviceConnected.company.getCompanies().size());
        Assert.assertEquals(1, serviceConnected.investment.getInvestments().size());
        Assert.assertEquals(1, serviceConnected.department.getDepartments().size());
        Assert.assertEquals(1, serviceConnected.user.getUsers().size());
        Assert.assertTrue(new PassEncryptor().passwordEncoder().matches("password", serviceConnected.user.getUserById(user.getId()).getPassword()));
        //cleanup

        serviceConnected.client.deleteClient(client.getId());
        serviceConnected.company.deleteCompany(company.getId());
        serviceConnected.department.deleteDepartment(department.getId());
        serviceConnected.user.deleteUser(user.getId());
        serviceConnected.status.deleteStatus(status.getId());
        serviceConnected.investment.deleteInvestment(investment.getId());


    }

}
