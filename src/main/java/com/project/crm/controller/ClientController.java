package com.project.crm.controller;

import com.project.crm.domain.ClientDto;
import com.project.crm.mapper.ClientMapper;
import com.project.crm.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/v1/clients")
@CrossOrigin("*")
public class ClientController {
    @Autowired
    ClientService service;

    @Autowired
    ClientMapper mapper;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public void createClient(@RequestBody ClientDto clientDto) {
        service.createClient(mapper.mapToClient(clientDto));
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public void updateClient(@RequestBody ClientDto clientDto) {
        service.updateClient(mapper.mapToClient(clientDto));
    }

    @DeleteMapping(path = "/{id}")
    public void deleteClient(@PathVariable int id) {
        service.deleteClient(id);
    }

    @GetMapping
    public List<ClientDto> getClients() {
        return mapper.mapToClientDtoList(service.getClients());
    }

    @GetMapping(path = "/{id}")
    public ClientDto getClientById(@PathVariable int id) {
        return mapper.mapToClientDto(service.getClientById(id));
    }

    @GetMapping(path = "/fragment/{txt}")
    public List<ClientDto> getClientByFragment(@PathVariable String txt) {
        return mapper.mapToClientDtoList(service.getClientByFragment(txt));
    }
}
