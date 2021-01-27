package com.project.crm.service;

import com.project.crm.domain.Investment;
import com.project.crm.repository.InvestmentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class InvestmentService {
    private static Logger LOGGER = LoggerFactory.getLogger(InvestmentService.class);

    @Autowired
    private InvestmentRepository repository;

    public void createInvestment(Investment investment) {
        LOGGER.info("Adding new investment");
        repository.save(investment);
    }

    public void updateInvestment(Investment investment) {
        LOGGER.info("Updating investment");
        repository.save(investment);
    }

    public void deleteInvestment(int id) {
        LOGGER.info("Deleting investment by id");
        repository.deleteById(id);
    }

    public List<Investment> getInvestments() {
        LOGGER.info("Fetching all investments");
        return repository.findAll();
    }

    public Investment getInvestmentById(int id) {
        LOGGER.info("Fetching investment by id");
        return repository.findById(id).orElse(null);
    }

    public List<Investment> getInvestmentByFragment(String txt) {
        LOGGER.info("Fetching investment by text fragment");
        return repository.findAll().stream()
                .filter(investment -> investment.getName().contains(txt)
                        || investment.getAddress().contains(txt))
                .collect(Collectors.toList());
    }
}
