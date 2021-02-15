package com.project.crm.mapper;

import com.google.gson.Gson;
import com.project.crm.domain.*;
import com.project.crm.domain.Dto.*;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MapperTestSuite {

    @Autowired
    private MapperConnected mapper;

    @Test
    public void shouldMapClient() {
        //given
        Company company = new Company("NameT", "AddressT", "taxNO");
        User user = new User("Userman", "pasman", "fnT", "lnT", null);
        Investment investment = new Investment("ITest", "IAddre", user, null);

        List<Company> companies = new ArrayList<>();
        companies.add(company);
        List<Investment> investments = new ArrayList<>();
        investments.add(investment);

        Client client = new Client(1, "Lukasz", "Makuch", 880700290, companies, investments);
        //when
        ClientDto clientDto = mapper.client.mapToClientDto(client);

        System.out.println(new Gson().toJson(clientDto));
        //then
        Assert.assertEquals(client.getId(), clientDto.getId());
        Assert.assertEquals(client.getInvestments().get(0).getName(), clientDto.getInvestments().get(0).getName());
        Assert.assertEquals(client.getCompanies().get(0).getName(), clientDto.getCompanies().get(0).getName());
        Assert.assertEquals(client.getFirstname(), clientDto.getFirstname());
        Assert.assertEquals(client.getLastname(), clientDto.getLastname());
        Assert.assertEquals(client.getPhoneNo(), clientDto.getPhoneNo());
    }

    @Test
    public void shouldMapClientDto() {
        //given
        CompanyDto company = new CompanyDto(1, "NameT", "AddressT", "taxNO", new ArrayList<>(), new ArrayList<>());
        UserDto user = new UserDto(1, "Userman", "fnT", "lnT", null, new ArrayList<>(), new ArrayList<>());
        InvestmentDto investment = new InvestmentDto(1, "ITest", "IAddre", user, new ArrayList<>(), new ArrayList<>(), null);

        List<CompanyDto> companies = new ArrayList<>();
        List<InvestmentDto> investments = new ArrayList<>();
        companies.add(company);
        investments.add(investment);

        ClientDto clientDto = new ClientDto(1, "Lukasz", "Makuch", 880700290, companies, investments);
        //when
        Client client = mapper.client.mapToClient(clientDto);

        //then
        Assert.assertEquals(client.getId(), clientDto.getId());
        Assert.assertEquals(client.getInvestments().get(0).getName(), clientDto.getInvestments().get(0).getName());
        Assert.assertEquals(client.getCompanies().get(0).getName(), clientDto.getCompanies().get(0).getName());
        Assert.assertEquals(client.getFirstname(), clientDto.getFirstname());
        Assert.assertEquals(client.getLastname(), clientDto.getLastname());
        Assert.assertEquals(client.getPhoneNo(), clientDto.getPhoneNo());
    }
}
