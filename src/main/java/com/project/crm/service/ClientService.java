package com.project.crm.service;

import com.project.crm.domain.Client;
import com.project.crm.domain.Status;
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

    @Autowired
    private ServiceConnected service;

    public boolean createClient(Client client) {
        LOGGER.info("Adding new client");
        try {
            if (client.getStatus() != null) {
                client.setStatus(service.status.createStatus(client.getStatus()));
            } else {
                client.setStatus(service.status.createStatus(new Status("Active")));
            }
            repository.save(client);
            return true;
        } catch (Exception e) {
            LOGGER.error("Cannot create client " + e);
            return false;
        }
    }

    public void deleteClient(int id) {
        LOGGER.info("Deleting client by id");
        try {
            repository.deleteById(id);
        } catch (Exception e) {
            LOGGER.error("Not found client!");
        }
    }

    public void deleteClient(Client client) {
        LOGGER.info("Deleting client");
        try {
            repository.delete(client);
        } catch (Exception e) {
            LOGGER.error("Not found client!");
        }
    }

    public boolean updateClient(Client client) {
        LOGGER.info("Updating client");
        try {
            repository.save(client);
            return true;
        } catch (Exception e) {
            LOGGER.error("Cannot update client. " + e);
            return false;
        }
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
