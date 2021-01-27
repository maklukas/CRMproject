package com.project.crm.service;

import com.project.crm.domain.Department;
import com.project.crm.repository.DepartmentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentService {

    private static Logger LOGGER = LoggerFactory.getLogger(DepartmentService.class);

    @Autowired
    private DepartmentRepository repository;

    public void createDepartment(Department department) {
        LOGGER.info("Adding department");
        repository.save(department);
    }

    public void updateDepartment(Department department) {
        LOGGER.info("Updating department");
        repository.save(department);
    }

    public void deleteDepartment(int id) {
        LOGGER.info("Deleting department by id");
        repository.deleteById(id);
    }

    public List<Department> getDepartments() {
        LOGGER.info("Fetching departments");
        return repository.findAll();
    }

    public Department getDepartment(int id) {
        LOGGER.info("Fetching department by id");
        return repository.findById(id).orElse(null);
    }


}
