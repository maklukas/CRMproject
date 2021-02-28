package com.project.crm.service;

import com.project.crm.domain.Role;
import com.project.crm.repository.RoleRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService {

    private static Logger LOGGER = LoggerFactory.getLogger(RoleService.class);

    @Autowired
    private RoleRepository repository;

    public boolean createRole(Role role) {
        LOGGER.info("Creating Role");
        try {
            repository.save(role);
            return true;
        } catch (Exception e) {
            LOGGER.error("Cannot create Role. " + e);
            return false;
        }
    }

    public boolean updateRole(Role role) {
        LOGGER.info("Updating role");
        try {
            repository.save(role);
            return true;
        } catch (Exception e) {
            LOGGER.error("Role already exists. " + e);
            return false;
        }
    }

    public void deleteRole(int id) {
        LOGGER.info("Deleting role by id");
        try {
            repository.deleteById(id);
        } catch (Exception e) {
            LOGGER.error("Not found Role. " + e);
        }
    }

    public void deleteRole(Role role) {
        LOGGER.info("Deleting role");
        try {
            repository.delete(role);
        } catch (Exception e) {
            LOGGER.info("Not found role. " + e);
        }
    }

    public List<Role> getRoles() {
        LOGGER.info("Fetching roles");
        return repository.findAll();
    }

    public Role getRole(int id) {
        LOGGER.info("Fetching role by id");
        return repository.findById(id).orElse(null);
    }
}
