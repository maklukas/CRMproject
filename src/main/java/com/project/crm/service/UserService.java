package com.project.crm.service;

import com.project.crm.domain.Department;
import com.project.crm.domain.User;
import com.project.crm.repository.DepartmentRepository;
import com.project.crm.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
public class UserService {

    private static Logger LOGGER = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserRepository repository;

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public void createUser(User user) {
        LOGGER.info("Saving new user");

        List<Department> departments = departmentRepository.findAll().stream()
                .filter(d -> d.getName().equals(user.getDepartment().getName()))
                .collect(toList());

        try {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            if (departments.size() != 0) {
                user.setDepartment(departments.get(0));
            }
            repository.save(user);
        } catch (Exception e) {
            LOGGER.error("User with the username already exists." + e);
        }
    }

    public List<User> getUsers() {
        LOGGER.info("Fetching users");
        return repository.findAll();
    }

    public User getUserById(int id) {
        LOGGER.info("Getting user by id");
        User user = repository.findById(id).orElse(null);
        return user;
    }

    public List<User> getUserByFragment(String txt) {
        LOGGER.info("Getting users by text fragment");
        return repository.findAll()
                .stream().filter(user -> user.getFirstname().contains(txt)
                        || user.getLastname().contains(txt)
                        || user.getUsername().contains(txt))
                .collect(toList());
    }

    public List<User> getUserByDepartment(String departmentName) {
        LOGGER.info("Getting users by department");
        List<User> users = repository.findAll()
                .stream()
                .filter(user -> user.getDepartment().getName().equals(departmentName))
                .collect(toList());
        return users;
    }

    public void deleteUser(int id) {
        LOGGER.info("Deleting user via id");
        try {
            repository.deleteById(id);
        } catch (Exception e) {
            LOGGER.error("Not found user!");
        }
    }

    public void deleteUser(User user) {
        LOGGER.info("Deleting user");
        try {
            repository.delete(user);
        } catch (Exception e) {
            LOGGER.error("Not found user!");
        }
    }

    public void updateUser(User user) {
        LOGGER.info("Updating user");

        List<Department> departments = departmentRepository.findAll().stream()
                .filter(d -> d.getName().equals(user.getDepartment().getName()))
                .collect(toList());

        try {
            if (user.getPassword() != null) {
                user.setPassword(passwordEncoder.encode(user.getPassword()));
            }
            if (departments.size() != 0) {
                user.setDepartment(departments.get(0));
            }
            repository.save(user);
        } catch (Exception e) {
            LOGGER.error("User with the username already exists." + e);
        }
    }
}
