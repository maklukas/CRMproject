package com.project.crm.service;

import com.project.crm.domain.Company;
import com.project.crm.repository.CompanyRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

@Service
public class CompanyService {

    public static Logger LOGGER = LoggerFactory.getLogger(CompanyService.class);

    @Autowired
    CompanyRepository repository;

    public List<Company> getCompanies() {
        LOGGER.info("Fetching all companies");
        return repository.findAll();
    }

    public void createCompany(Company company) {
        LOGGER.info("Adding company");
        boolean result = repository.findAll().stream()
                .filter(company1 -> company1.getTaxNumber().equals(company.getTaxNumber()))
                .collect(toList()).size() == 0;
        if (result) {
            repository.save(company);
        } else {
            LOGGER.error("Client with the tax number already exists");
        }
    }

    public void updateCompany(Company company) {
        LOGGER.info("Updating company");
        boolean result = repository.findAll().stream()
                .filter(company1 -> company1.getTaxNumber().equals(company.getTaxNumber())
                        && company1.getId() != company.getId())
                .collect(toList()).size() == 0;
        if (result) {
            repository.save(company);
        } else {
            LOGGER.error("Client with the tax number already exists");
        }
    }

    public Company getCompanyById(int id) {
        LOGGER.info("Getting company by id");
        return repository.findById(id).orElse(null);
    }

    public List<Company> getCompanyByFragment(String txt) {
        LOGGER.info("Getting company by fragment");
        return repository.findAll().stream()
                .filter(company -> company.getAddress().contains(txt) || company.getName().contains(txt) || company.getTaxNumber().contains(txt))
                .collect(toList());
    }

    public void deleteCompany(int id) {
        LOGGER.info("Deleting company by id");
        try {
            repository.deleteById(id);
        } catch (Exception e) {
            LOGGER.error("Not found company!");
        }
    }

    public void deleteCompany(Company company) {
        LOGGER.info("Deleting company");
        try {
            repository.delete(company);
        } catch (Exception e) {
            LOGGER.error("Not found company!");
        }
    }
}
