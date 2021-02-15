package com.project.crm.mapper;

import com.project.crm.domain.Client;
import com.project.crm.domain.Dto.ClientDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ClientMapper {

    @Autowired
    private ModelMapper modelMapper;

    public Client mapToClient(ClientDto client) {
        return modelMapper.map(client, Client.class);
    }

    public ClientDto mapToClientDto(Client client) {
//        modelMapper.typeMap(Client.class, ClientDto.class)
//                .addMappings(mapper -> {
//                    mapper.map(src -> src.getId(), Desti);
//                })
        return modelMapper.map(client, ClientDto.class);
    }

    public List<Client> mapToClientList(List<ClientDto> clients) {
        return clients.stream()
                .map(client -> modelMapper.map(client, Client.class))
                .collect(Collectors.toList());
    }

    public List<ClientDto> mapToClientDtoList(List<Client> clients) {
        return clients.stream()
                .map(client -> modelMapper.map(client, ClientDto.class))
                .collect(Collectors.toList());
    }
}
