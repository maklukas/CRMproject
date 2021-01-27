package com.project.crm.repository;

import com.project.crm.domain.Department;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface DepartmentRepository extends CrudRepository<Department, Integer> {

    @Override
    List<Department> findAll();
}
