package com.project.crm.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("login");
        registry.addViewController("/tasks").setViewName("tasks");
        registry.addViewController("/users").setViewName("users");
        registry.addViewController("/companies").setViewName("companies");
        registry.addViewController("/investments").setViewName("investments");
        registry.addViewController("/clients").setViewName("clients");
        registry.addViewController("/login").setViewName("login");
    }

}
