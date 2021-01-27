package com.project.crm.repository;

import com.project.crm.domain.Status;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface StatusRepository extends CrudRepository<Status, Integer> {
    @Override
    List<Status> findAll();
}
