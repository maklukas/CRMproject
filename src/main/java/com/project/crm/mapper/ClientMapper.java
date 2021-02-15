package com.project.crm.mapper;

import com.project.crm.domain.Client;
import com.project.crm.domain.Dto.ClientDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ClientMapper {

    private static Logger LOGGER = LoggerFactory.getLogger(ClientMapper.class);

    @Autowired
    private MapperConnected mapper;

    public Client mapToClient(ClientDto client) {
        if (client == null) {
            LOGGER.error("Mapped filed");
            return new Client();
        } else {
            return new Client(
                    client.getId(),
                    client.getFirstname(),
                    client.getLastname(),
                    client.getPhoneNo(),
                    mapper.company.mapToCompanyList(client.getCompanies()),
                    mapper.investment.mapToInvestmentList(client.getInvestments())
            );
        }
    }

    public ClientDto mapToClientDto(Client client) {
        if (client == null) {
            LOGGER.error("Mapped filed");
            return new ClientDto();
        } else {
            return new ClientDto(
                    client.getId(),
                    client.getFirstname(),
                    client.getLastname(),
                    client.getPhoneNo(),
                    mapper.company.mapToCompanyDtoList(client.getCompanies()),
                    mapper.investment.mapToInvestmentDtoList(client.getInvestments())
            );
        }
    }

    public List<ClientDto> mapToClientDtoList(List<Client> clients) {
        if (clients == null) {
            LOGGER.error("Mapped filed");
            return new ArrayList<>();
        } else {
            return clients.stream()
                    .map(client -> new ClientDto(
                            client.getId(),
                            client.getFirstname(),
                            client.getLastname(),
                            client.getPhoneNo(),
                            mapper.company.mapToCompanyDtoList(client.getCompanies()),
                            mapper.investment.mapToInvestmentDtoList(client.getInvestments())
                    )).collect(Collectors.toList());
        }
    }


    public List<Client> mapToClientList(List<ClientDto> clients) {
        if (clients == null) {
            LOGGER.error("Mapped filed");
            return new ArrayList<>();
        } else {
            return clients.stream()
                    .map(client -> new Client(
                            client.getId(),
                            client.getFirstname(),
                            client.getLastname(),
                            client.getPhoneNo(),
                            mapper.company.mapToCompanyList(client.getCompanies()),
                            mapper.investment.mapToInvestmentList(client.getInvestments())
                    )).collect(Collectors.toList());
        }
    }

}
