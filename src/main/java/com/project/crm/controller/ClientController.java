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

    @Autowired
    private ServiceConnected service;

    @Autowired
    private MapperConnected mapper;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public void createClient(@RequestBody ClientDto clientDto) {
        service.client.createClient(mapper.client.mapToClient(clientDto));
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public void updateClient(@RequestBody ClientDto clientDto) {
        service.client.updateClient(mapper.client.mapToClient(clientDto));
    }

    @DeleteMapping(path = "/{id}")
    public void deleteClient(@PathVariable int id) {
        service.client.deleteClient(id);
    }

    @GetMapping
    public List<ClientDto> getClients() {
        return mapper.client.mapToClientDtoList(service.client.getClients());
    }

    @GetMapping(path = "/{id}")
    public ClientDto getClientById(@PathVariable int id) {
        return mapper.client.mapToClientDto(service.client.getClientById(id));
    }

    @GetMapping(path = "/fragment/{txt}")
    public List<ClientDto> getClientByFragment(@PathVariable String txt) {
        return mapper.client.mapToClientDtoList(service.client.getClientByFragment(txt));
    }
}
