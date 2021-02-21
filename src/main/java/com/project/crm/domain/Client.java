package com.project.crm.domain;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
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
@Entity(name = "clients")
public class Client {
    public Client(String firstname, String lastname, int phoneNo) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.phoneNo = phoneNo;
        this.companies = new ArrayList<>();
        this.investments = new ArrayList<>();
    }

    private static Logger LOGGER = LoggerFactory.getLogger(Client.class);

    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "client_id")
    private int id;

    private String firstname;

    private String lastname;

    @Column(name = "phone_no")
    private int phoneNo;

    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinTable(
            name = "join_client_company",
            joinColumns = {@JoinColumn(name = "client_id", referencedColumnName = "client_id")},
            inverseJoinColumns = {@JoinColumn(name = "company_id", referencedColumnName = "company_id")}
    )
    @NotFound(action = NotFoundAction.IGNORE)
    private List<Company> companies;

    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinTable(
            name = "join_client_investment",
            joinColumns = {@JoinColumn(name = "client_id", referencedColumnName = "client_id")},
            inverseJoinColumns = {@JoinColumn(name = "investment_id", referencedColumnName = "investment_id")}
    )
    @NotFound(action = NotFoundAction.IGNORE)
    private List<Investment> investments;

    public void addCompany(Company company) {
        LOGGER.info("Creating company");
        this.companies.add(company);
        company.getEmployees().add(this);
    }

    public void removeCompany(Company company) {
        LOGGER.info("Removing company");
        this.companies.remove(company);
        company.getEmployees().remove(this);
    }

    public void addInvestment(Investment investment) {
        LOGGER.info("Creating investment");
        this.investments.add(investment);
        investment.getClients().add(this);
    }

    public void removeInvestment(Investment investment) {
        LOGGER.info("Removing investment");
        this.companies.remove(investment);
        investment.getClients().remove(this);
    }

}
