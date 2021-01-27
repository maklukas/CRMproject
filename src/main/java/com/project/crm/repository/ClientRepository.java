package com.project.crm.repository;

import com.project.crm.domain.Client;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ClientRepository extends CrudRepository<Client, Integer> {
    @Override
    List<Client> findAll();
}
