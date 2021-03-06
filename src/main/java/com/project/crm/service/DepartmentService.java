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

    private DepartmentRepository repository;

    @Autowired
    public DepartmentService(DepartmentRepository repository) {
        this.repository = repository;
    }

    public Department createDepartment(Department department) {
        LOGGER.info("Adding department");
        try {
            return getDepartment(department);
        } catch (Exception e) {
            LOGGER.error("Cannot create department. " + e);
            return null;
        }
    }

    private Department getDepartment(Department department) {
        if (getDepartmentByName(department.getName()) != null) {
            return getDepartmentByName(department.getName());
        } else {
            return repository.save(department);
        }
    }

    public boolean updateDepartment(Department department) {
        LOGGER.info("Updating department");
        try {
            repository.save(department);
            return true;
        } catch (Exception e) {
            LOGGER.error("Department already exists. " + e);
            return false;
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

    public Department getDepartmentByName(String name) {
        LOGGER.info("Fetching department by name");
        return repository.findByName(name).orElse(null);
    }


}
