package com.project.crm.controller;

import com.project.crm.domain.Dto.UserDto;
import com.project.crm.mapper.MapperConnected;
import com.project.crm.service.ServiceConnected;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

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

    private String hasLogged() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {
            return "login";
        }
        return "redirect:/hello";
    }

}
