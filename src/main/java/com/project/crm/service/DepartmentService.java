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

        boolean result = repository.findAll().stream()
                .filter(department1 -> department1.getName().equals(department.getName()))
                .count() == 0;

        if (result) {
            repository.save(department);
        } else {
            LOGGER.error("Department already exists");
        }
    }

    public void updateDepartment(Department department) {
        LOGGER.info("Updating department");
        boolean result = repository.findAll().stream()
                .filter(department1 -> department1.getName().equals(department.getName()))
                .count() == 0;

        if (result) {
            repository.save(department);
        } else {
            LOGGER.error("Department already exists");
        }
    }

    public void deleteDepartment(int id) {
        LOGGER.info("Deleting department by id");
        try {
            repository.deleteById(id);
        } catch (Exception e) {
            LOGGER.error("Not found department!");
        }
    }

    public void deleteDepartment(Department department) {
        LOGGER.info("Deleting department");
        try {
            repository.delete(department);
        } catch (Exception e) {
            LOGGER.info("Not found department!");
        }
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
