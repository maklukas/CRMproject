package com.project.crm.domain;

import com.sun.istack.NotNull;
import lombok.*;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "companies")
public class Company {

    public Company(String name, String address, String taxNumber) {
        this.name = name;
        this.address = address;
        this.taxNumber = taxNumber;
        this.employees = new ArrayList<>();
        this.investments = new ArrayList<>();
    }

    private static Logger LOGGER = LoggerFactory.getLogger(Company.class);

    @NotNull
    @Id
    @GeneratedValue
    @Column(name = "company_id")
    private int id;

    private String name;

    private String address;

    @Column(name = "tax_number")
    private String taxNumber;

    @ManyToMany(mappedBy = "companies")
    @NotFound(action = NotFoundAction.IGNORE)
    private List<Client> employees;

    @ManyToMany(mappedBy = "companies")
    @NotFound(action = NotFoundAction.IGNORE)
    private List<Investment> investments;

    public void addEmployee(Client employee) {
        LOGGER.info("Creating employee");
        employees.add(employee);
        employee.getCompanies().add(this);
    }

    public void removeEmployee(Client employee) {
        LOGGER.info("Removing employee");
        employees.remove(employee);
        employee.getCompanies().remove(this);
    }

    public void addInvestment(Investment investment) {
        LOGGER.info("Creating investment");
        investments.add(investment);
        investment.getCompanies().add(this);
    }

    public void removeInvestment(Investment investment) {
        LOGGER.info("Removing investment");
        investments.remove(investment);
        investment.getCompanies().remove(this);
    }

}
