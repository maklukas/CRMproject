package com.project.crm.controller;

import com.project.crm.domain.Dto.*;
import com.project.crm.mapper.MapperConnected;
import com.project.crm.service.ServiceConnected;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.List;

@Controller
public class CommonController {

    @Autowired
    private ServiceConnected service;

    @Autowired
    private MapperConnected mapper;

    @GetMapping("")
    public String viewHomePage(ModelMap model) {
        model.addAttribute("Page", "Home - CROMPROJ");
        return hasLogged();
    }

    @GetMapping("/login")
    public String showLoginPage(ModelMap model) {
        return hasLogged();
    }

    @GetMapping("/users")
    public String getUsers(ModelMap model) {
        List<UserDto> users = mapper.user.mapToUserDtoList(service.user.getUsers());
        model.addAttribute("users", users);
        return "users";
    }

    @GetMapping("/tasks")
    public String getTasks(ModelMap model) {
        UserDetails userObject = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = userObject.getUsername();
        List<TaskDto> tasks = mapper.task.mapToTaskDtoList(service.task.getTasks4TheUser(username));
        model.addAttribute("tasks", tasks);
        model.addAttribute("formatDateTime", DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM));
        return "tasks";
    }

    @GetMapping("/clients")
    public String getClients(ModelMap model) {
        List<ClientDto> clients = mapper.client.mapToClientDtoList(service.client.getClients());
        model.addAttribute("clients", clients);
        return "clients";
    }

    @GetMapping("/companies")
    public String getCompanies(ModelMap model) {
        List<CompanyDto> companies = mapper.company.mapToCompanyDtoList(service.company.getCompanies());
        model.addAttribute("companies", companies);
        return "companies";
    }

    @GetMapping("/investments")
    public String getInvestments(ModelMap model) {
        List<InvestmentDto> investments = mapper.investment.mapToInvestmentDtoList(service.investment.getInvestments());
        model.addAttribute("investments", investments);
        return "investments";
    }

    private String hasLogged() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {
            return "login";
        }
        return "redirect:/tasks";
    }

}
