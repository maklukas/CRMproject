package com.project.crm.repository;

import com.project.crm.domain.Company;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CompanyRepository extends CrudRepository<Company, Integer> {

    @Override
    List<Company> findAll();
}
