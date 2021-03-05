package com.project.crm.gui;

import com.project.crm.domain.Department;
import com.project.crm.domain.User;
import com.project.crm.repository.UserRepository;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

import java.util.List;

@Route("user")
public class UserGui extends VerticalLayout {

    private UserRepository userRepository;

    public UserGui(UserRepository repository) {
        List<User> users = repository.findAll();
        Grid<User> gridUser = new Grid<>(User.class);
        Grid<Department> gridDep = new Grid<>(Department.class);
        gridUser.setItems(users);
        add(gridUser);
        add(gridDep);
    }
}
