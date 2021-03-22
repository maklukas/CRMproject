package com.project.crm.repository;

import com.project.crm.domain.Role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RoleRepository extends CrudRepository<Role, Integer> {
    @Override
    List<Role> findAll();

    Optional<Role> findByName(String name);
}
