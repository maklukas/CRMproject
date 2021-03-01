package com.project.crm.service;

import com.project.crm.domain.Department;
import com.project.crm.domain.Role;
import com.project.crm.domain.User;
import com.project.crm.repository.DepartmentRepository;
import com.project.crm.repository.RoleRepository;
import com.project.crm.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

@Service
public class UserService implements UserDetailsService {

    private static Logger LOGGER = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserRepository repository;

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public boolean createUser(User user) {
        LOGGER.info("Saving new user");
        Department department;
        Role role;
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        try {
            if (!passwordEncoder.matches(user.getConfirmPassword(), user.getPassword())) {
                LOGGER.error("Password and confirmed password are different");
                return false;
            } else {
                if (user.getDepartment() != null) {
                    department = departmentRepository.findByName(user.getDepartment().getName()).orElse(user.getDepartment());
                    user.setDepartment(department);
                }
                if (user.getRole() != null) {
                    role = roleRepository.findByName(user.getRole().getName()).orElse(user.getRole());
                } else {
                    role = roleRepository.findByName("USER").orElse(new Role("USER"));
                }
                user.setRole(role);
                repository.save(user);
                return true;
            }
        } catch (Exception e) {
            LOGGER.error("Cannot add new user. " + e);
            return false;
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

    public List<User> getUserByRole(String roleName) {
        LOGGER.info("Getting users by role");
        List<User> users = repository.findAll()
                .stream()
                .filter(user -> user.getRole().getName().equals(roleName))
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

    public boolean updateUser(User user) {
        LOGGER.info("Updating user");
        Department department;
        Role role;
        try {
            if (user.getPassword() != null) {
                user.setPassword(passwordEncoder.encode(user.getPassword()));
            }
            if (user.getDepartment() != null) {
                department = departmentRepository.findByName(user.getDepartment().getName()).orElse(user.getDepartment());
                user.setDepartment(department);
            }
            if (user.getRole() != null) {
                role = roleRepository.findByName(user.getRole().getName()).orElse(user.getRole());
                user.setRole(role);
            }
            repository.save(user);
            return true;
        } catch (Exception e) {
            LOGGER.error("Cannot update user. " + e);
            return false;
        }
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> optionalUser = repository.findByUsername(username);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();

            return org.springframework.security.core.userdetails.User.builder()
                    .username(user.getUsername())
                    .password(user.getPassword())
                    .roles(user.getRole().getName())
                    .build();
        } else {
            throw new UsernameNotFoundException("User Name is not Found");
        }
    }
}
