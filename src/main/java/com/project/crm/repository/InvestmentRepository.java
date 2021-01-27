package com.project.crm.repository;

import com.project.crm.domain.Investment;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface InvestmentRepository extends CrudRepository<Investment, Integer> {
    @Override
    List<Investment> findAll();
}
