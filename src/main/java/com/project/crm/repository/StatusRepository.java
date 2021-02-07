package com.project.crm.repository;

import com.project.crm.domain.Status;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface StatusRepository extends CrudRepository<Status, Integer> {
    @Override
    List<Status> findAll();
    Optional<Status> findByName(String name);
}
