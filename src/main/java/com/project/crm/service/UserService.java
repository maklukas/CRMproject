package com.project.crm.service;

import com.project.crm.domain.User;
import com.project.crm.repository.UserRepository;
import com.project.crm.securingweb.PassEncryptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    private static Logger LOGGER = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserRepository repository;

    @Autowired
    private PassEncryptor passEncryptor;

    public User createUser(User user) {
        LOGGER.info("Saving new user");
        user.setPassword(passEncryptor.passwordEncoder().encode(user.getPassword()));
        return repository.save(user);
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
        List<User> users = repository.findAll()
                .stream().filter(user -> user.getFirstname().contains(txt) || user.getLastname().contains(txt) || user.getUsername().contains(txt))
                .collect(Collectors.toList());
        return users;
    }

    public List<User> getUserByDepartmentId(String departmentName) {
        LOGGER.info("Getting users by department");
        List<User> users = repository.findAll()
                .stream()
                .filter(user -> user.getDepartment().getName() == departmentName)
                .collect(Collectors.toList());
        return users;
    }

    public void deleteUser(int id) {
        LOGGER.info("Deleting user via id");
        repository.deleteById(id);
    }

    public void deleteUser(User user) {
        LOGGER.info("Deleting user");
        repository.delete(user);
    }

    public User updateUser(User user) {
        LOGGER.info("Updating user");
        user.setPassword(passEncryptor.passwordEncoder().encode(user.getPassword()));
        return repository.save(user);
    }


}
