package com.project.crm.service;

import com.project.crm.domain.Role;
import com.project.crm.domain.Status;
import com.project.crm.domain.User;
import com.project.crm.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

@Service
public class UserService implements UserDetailsService {

    private static Logger LOGGER = LoggerFactory.getLogger(UserService.class);

    private UserRepository repository;
    private ServiceConnected service;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository repository, ServiceConnected service, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.service = service;
        this.passwordEncoder = passwordEncoder;
    }

    public boolean createUser(User user) {
        LOGGER.info("Saving new user");
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        try {
            decorate(user);
            if (checkConfirmedPassword(user)) {
                repository.save(user);
                setAuthentication(user);
                return true;
            }
            return false;
        } catch (Exception e) {
            LOGGER.error("Cannot add new user. " + e);
            return false;
        }
    }

    private void decorate(User user) {
        setDepartment(user);
        setStatus(user);
        setRole(user);
    }

    private boolean checkConfirmedPassword(User user) {
        if (!passwordEncoder.matches(user.getConfirmPassword(), user.getPassword())) {
            LOGGER.error("Password and confirmed password are different");
            return false;
        }
        return true;

    }

    private void setAuthentication(User user) {
        UserDetails newUser = loadUserByUsername(user.getUsername());
        UsernamePasswordAuthenticationToken authentication =
                new UsernamePasswordAuthenticationToken(
                        newUser,
                        null,
                        newUser.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    private void setRole(User user) {
        if (user.getRole() != null) {
            user.setRole(service.role.createRole(user.getRole()));
        } else {
            user.setRole(service.role.createRole(new Role("USER")));
        }
    }

    private void setStatus(User user) {
        if (user.getStatus() != null) {
            user.setStatus(service.status.createStatus(user.getStatus()));
        } else {
            user.setStatus(service.status.createStatus(new Status("Active")));
        }
    }

    private void setDepartment(User user) {
        if (user.getDepartment() != null) {
            user.setDepartment(
                    service.department.createDepartment(user.getDepartment()));
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
        Role role;
        try {
            setPassword(user);
            setDepartment(user);
            if (user.getStatus() != null) {
                try {
                    service.status.createStatus(user.getStatus());
                } catch (Exception e) {
                    LOGGER.info("Fetching status");
                } finally {
                    user.setStatus(service.status.getStatusByName(user.getStatus().getName()));
                }
            } else {
                try {
                    service.status.createStatus(new Status("Active"));
                } catch (Exception e) {
                    LOGGER.info("Fetching status");
                } finally {
                    user.setStatus(service.status.getStatusByName("Active"));
                }
            }

            if (user.getRole() != null) {
                try {
                    service.role.createRole(user.getRole());
                } catch (Exception e) {
                    LOGGER.info("Fetching role");
                } finally {
                    user.setRole(service.role.getRoleByName(user.getRole().getName()));
                }
            } else {
                try {
                    service.role.createRole(new Role("USER"));
                } catch (Exception e) {
                    LOGGER.info("Fetching role");
                } finally {
                    user.setRole(service.role.getRoleByName("USER"));
                }
            }
            repository.save(user);
            return true;
        } catch (Exception e) {
            LOGGER.error("Cannot update user. " + e);
            return false;
        }
    }

    private void setPassword(User user) {
        if (user.getPassword() != null) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        }
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> optionalUser = repository.findByUsername(username);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            return buildUser(user, buildUserAuthority(user.getRole()));
        } else {
            throw new UsernameNotFoundException("User Name is not Found");
        }
    }

    private org.springframework.security.core.userdetails.User buildUser(User user, List<GrantedAuthority> authorities) {
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorities);
    }

    private List<GrantedAuthority> buildUserAuthority(Role role) {
        List<GrantedAuthority> roles = new ArrayList<>();
        roles.add(new SimpleGrantedAuthority(role.getName()));
        return roles;

    }

    public User getUserByUsername(String username) {
        Optional<User> optionalUser = repository.findByUsername(username);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            return user;
        } else {
            throw new UsernameNotFoundException("User Name is not Found");
        }
    }

    public List<User> getUsersByUsernames(List<String> usernames) {
        return usernames.stream()
                .filter(username -> repository.findByUsername(username).isPresent())
                .map(u -> repository.findByUsername(u).get())
                .collect(Collectors.toList());
    }

    public User getUserFromSession() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            return service.user.getUserByUsername(((UserDetails) principal).getUsername());
        } else {
            return null;
        }
    }

}
