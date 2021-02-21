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
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "investments")
public class Investment {

    public Investment(String name, String address, User registeredBy, Status status) {
        this.name = name;
        this.address = address;
        this.registeredBy = registeredBy;
        this.companies = new ArrayList<>();
        this.clients = new ArrayList<>();
        this.status = status;
    }

    private static Logger LOGGER = LoggerFactory.getLogger(Investment.class);

    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "investment_id")
    private int id;

    private String name;

    private String address;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @NotFound(action = NotFoundAction.IGNORE)
    @JoinColumn(name = "user_id")
    private User registeredBy;

    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinTable(
            name = "join_investment_company",
            joinColumns = {@JoinColumn(name = "investment_id", referencedColumnName = "investment_id")},
            inverseJoinColumns = {@JoinColumn(name = "company_id", referencedColumnName = "company_id")}
    )
    @NotFound(action = NotFoundAction.IGNORE)
    private List<Company> companies;

    @ManyToMany(mappedBy = "investments")
    @NotFound(action = NotFoundAction.IGNORE)
    private List<Client> clients;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @NotFound(action = NotFoundAction.IGNORE)
    @JoinColumn(name = "status_id")
    private Status status;

    public void addCompany(Company company) {
        LOGGER.info("Creating company");
        companies.add(company);
        company.getInvestments().add(this);
    }

    public void removeCompany(Company company) {
        LOGGER.info("Removing company");
        companies.remove(company);
        company.getInvestments().remove(this);
    }

    public void addClient(Client client) {
        LOGGER.info("Creating client");
        clients.add(client);
        client.getInvestments().add(this);
    }

    public void removeClient(Client client) {
        LOGGER.info("Removing client");
        clients.remove(client);
        client.getInvestments().remove(this);
    }

}
