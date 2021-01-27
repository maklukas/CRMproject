package com.project.crm.mapper;

import com.project.crm.domain.Client;
import com.project.crm.domain.ClientDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ClientMapper {
    public Client mapToClient(ClientDto clientDto) {
        return new Client(
                clientDto.getId(),
                clientDto.getFirstname(),
                clientDto.getLastname(),
                clientDto.getPhoneNo(),
                clientDto.getCompany()
        );
    }
    public ClientDto mapToClientDto(Client client) {
        return new ClientDto(
                client.getId(),
                client.getFirstname(),
                client.getLastname(),
                client.getPhoneNo(),
                client.getCompany()
        );
    }
    public List<ClientDto>mapToClientDtoList(List<Client> clientList) {
        return clientList.stream()
                .map(client -> new ClientDto(
                        client.getId(),
                        client.getFirstname(),
                        client.getLastname(),
                        client.getPhoneNo(),
                        client.getCompany()
                ))
                .collect(Collectors.toList());
    }
}
