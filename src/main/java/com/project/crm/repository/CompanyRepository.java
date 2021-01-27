package com.project.crm.repository;

import com.project.crm.domain.Company;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CompanyRepository extends CrudRepository<Company, Integer> {

    @Override
    List<Company> findAll();
}
