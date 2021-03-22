package com.project.crm.repository;

import com.project.crm.domain.Investment;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InvestmentRepository extends CrudRepository<Investment, Integer> {
    @Override
    List<Investment> findAll();
}
