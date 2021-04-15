package com.project.crm.controller;

import com.project.crm.domain.Dto.ClientDto;
import com.project.crm.mapper.MapperConnected;
import com.project.crm.service.ServiceConnected;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/v1/clients")
@CrossOrigin("*")
public class ClientController {

    private ServiceConnected service;
    private MapperConnected mapper;

    @Autowired
    public ClientController(ServiceConnected service, MapperConnected mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public void createClient(@RequestBody ClientDto clientDto) {
        service.client.createClient(mapper.client.mapToClient(clientDto));
    }

   //TODO add @PutMapping method

    @PatchMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public void updateClient(@RequestBody ClientDto clientDto) {
        service.client.updateClient(mapper.client.mapToClient(clientDto));
    }

    @DeleteMapping(params = "id")
    public void deleteClient(@RequestParam int id) {
        service.client.deleteClient(id);
    }

    @GetMapping
    public List<ClientDto> getClients() {
        return mapper.client.mapToClientDtoList(service.client.getClients());
    }

    @GetMapping(params = "id")
    public ClientDto getClientById(@RequestParam int id) {
        return mapper.client.mapToClientDto(service.client.getClientById(id));
    }

    @GetMapping(params = "fragment")
    public List<ClientDto> getClientByFragment(@RequestParam String fragment) {
        return mapper.client.mapToClientDtoList(service.client.getClientByFragment(fragment));
    }
}
