package com.project.crm.controller;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CommonController {

    @GetMapping("")
    public String viewHomePage(ModelMap model) {
        model.addAttribute("Page", "Home - CROMPROJ");
        return hasLogged();
    }

    @GetMapping("/login")
    public String showLoginPage(ModelMap model) {
        return hasLogged();
    }

    private String hasLogged() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {
            return "login";
        }
        return "redirect:/hello";
    }
}
