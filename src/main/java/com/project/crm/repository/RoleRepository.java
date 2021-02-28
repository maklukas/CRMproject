package com.project.crm.repository;

import com.project.crm.domain.Role;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface RoleRepository extends CrudRepository<Role, Integer> {
    @Override
    List<Role> findAll();

    Optional<Role> findByName(String name);
}
