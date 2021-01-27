package com.project.crm.service;

import com.project.crm.domain.Client;
import com.project.crm.repository.ClientRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClientService {
    private static Logger LOGGER = LoggerFactory.getLogger(ClientService.class);

    @Autowired
    private ClientRepository repository;

    public void createClient(Client client) {
        LOGGER.info("Adding new client");
        repository.save(client);
    }

    public void deleteClient(int id) {
        LOGGER.info("Deleting client by id");
        repository.deleteById(id);
    }

    public void updateClient(Client client) {
        LOGGER.info("Updating client");
        repository.save(client);
    }

    public List<Client> getClients() {
        LOGGER.info("Fetching all clients");
        return repository.findAll();
    }

    public Client getClientById(int id) {
        LOGGER.info("Fetching client by Id");
        return repository.findById(id).orElse(null);
    }

    public List<Client> getClientByFragment(String txt) {
        LOGGER.info("Fetching client by text fragment");
        return repository.findAll().stream()
                .filter(client -> client.getFirstname().contains(txt)
                        || client.getLastname().contains(txt)
                        || String.valueOf(client.getPhoneNo()).contains(txt))
                .collect(Collectors.toList());
    }
}
