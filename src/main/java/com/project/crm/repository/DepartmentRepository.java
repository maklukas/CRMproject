package com.project.crm.repository;

import com.project.crm.domain.Department;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DepartmentRepository extends CrudRepository<Department, Integer> {

    @Override
    List<Department> findAll();

    Optional<Department> findByName(String name);
}
