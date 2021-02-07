package com.project.crm.mapper;

import com.project.crm.domain.Client;
import com.project.crm.domain.Dto.ClientDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ClientMapper {

    @Autowired
    private CompanyMapper companyMapper;

    @Autowired
    private InvestmentMapper investmentMapper;

    public Client mapToClient(ClientDto client) {
        return new Client(
                client.getId(),
                client.getFirstname(),
                client.getLastname(),
                client.getPhoneNo(),
                companyMapper.mapToCompanyList(client.getCompanies()),
                investmentMapper.mapToInvestmentList(client.getInvestments())
        );
    }

    public ClientDto mapToClientDto(Client client) {
        return new ClientDto(
                client.getId(),
                client.getFirstname(),
                client.getLastname(),
                client.getPhoneNo(),
                companyMapper.mapToCompanyDtoList(client.getCompanies()),
                investmentMapper.mapToInvestmentDtoList(client.getInvestments())
        );
    }

    public List<ClientDto> mapToClientDtoList(List<Client> clients) {
        return clients.stream()
                .map(client -> new ClientDto(
                        client.getId(),
                        client.getFirstname(),
                        client.getLastname(),
                        client.getPhoneNo(),
                        companyMapper.mapToCompanyDtoList(client.getCompanies()),
                        investmentMapper.mapToInvestmentDtoList(client.getInvestments())
                )).collect(Collectors.toList());
    }

    public List<Client> mapToClientList(List<ClientDto> clients) {
        return clients.stream()
                .map(client -> new Client(
                        client.getId(),
                        client.getFirstname(),
                        client.getLastname(),
                        client.getPhoneNo(),
                        companyMapper.mapToCompanyList(client.getCompanies()),
                        investmentMapper.mapToInvestmentList(client.getInvestments())
                )).collect(Collectors.toList());
    }

}
