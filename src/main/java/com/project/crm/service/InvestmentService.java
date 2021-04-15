package com.project.crm.service;

import com.project.crm.domain.Investment;
import com.project.crm.domain.Status;
import com.project.crm.domain.User;
import com.project.crm.repository.InvestmentRepository;
import com.project.crm.repository.StatusRepository;
import com.project.crm.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class InvestmentService {
    private static Logger LOGGER = LoggerFactory.getLogger(InvestmentService.class);

    private InvestmentRepository repository;
    private PasswordEncoder passwordEncoder;
    private StatusRepository statusRepository;
    private UserRepository userRepository;

    @Autowired
    public InvestmentService(InvestmentRepository repository, PasswordEncoder passwordEncoder, StatusRepository statusRepository, UserRepository userRepository) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
        this.statusRepository = statusRepository;
        this.userRepository = userRepository;
    }

    public boolean createInvestment(Investment investment) {
        LOGGER.info("Adding new investment");
        try {
            decore(investment);
            repository.save(investment);
            return true;
        } catch (Exception e) {
            LOGGER.error("Cannot create investment. " + e);
            return false;
        }
    }

    private void decore(Investment investment) {
        encodeUserPass(investment);
        setStatus(investment);
        setRegisterByUser(investment);
    }

    private void setStatus(Investment investment) {
        Status status;
        if (investment.getStatus() != null) {
            status = statusRepository.findByName(investment.getStatus().getName()).orElse(investment.getStatus());
            investment.setStatus(status);
        }
    }

    private void encodeUserPass(Investment investment) {
        investment.getRegisteredBy().setPassword(passwordEncoder.encode(investment.getRegisteredBy().getPassword()));
    }

    public boolean updateInvestment(Investment investment) {
        LOGGER.info("Updating investment");
        try {
            decore(investment);
            repository.save(investment);
            return true;
        } catch (Exception e) {
            LOGGER.error("Cannot update investment. " + e);
            return false;
        }
    }

    private void setRegisterByUser(Investment investment) {
        User registeredBy;
        if (investment.getRegisteredBy() != null) {
            registeredBy = userRepository.findByUsername(investment.getRegisteredBy().getUsername()).orElse(investment.getRegisteredBy());
            investment.setRegisteredBy(registeredBy);
        }
    }

    public void deleteInvestment(int id) {
        LOGGER.info("Deleting investment by id");
        try {
            repository.deleteById(id);
        } catch (Exception e) {
            LOGGER.error("Not found investment!");
        }
    }

    public void deleteInvestment(Investment investment) {
        LOGGER.info("Deleting investment");
        try {
            repository.delete(investment);
        } catch (Exception e) {
            LOGGER.error("Not found investment!");
        }
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
