package com.project.crm.controller;

import com.project.crm.domain.User;
import com.project.crm.domain.Dto.UserDto;
import com.project.crm.mapper.MapperConnected;
import com.project.crm.service.ServiceConnected;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RequestMapping(path = "/v1/users")
@RestController
public class UserController {

    @Autowired
    private ServiceConnected userService;

    @Autowired
    private MapperConnected mapper;

    @PostMapping
    public void addNewUser (@ModelAttribute User user) {
        userService.user.createUser(user);
    }

    @GetMapping
    public List<UserDto> getUsers() {
        return mapper.user.mapToUserDtoList(userService.user.getUsers());
    }

    @GetMapping(path = "/{id}")
    public UserDto getUser(@PathVariable int id) {
        return mapper.user.mapToUserDto(userService.user.getUserById(id));
    }

    @GetMapping(path = "/fragment/{txt}")
    public List<UserDto> getUserByName(@PathVariable String txt) {
        return mapper.user.mapToUserDtoList(userService.user.getUserByFragment(txt));
    }

    @GetMapping(path = "/department/{departmentName}")
    public List<UserDto> getUserByDepartment(@PathVariable String departmentName) {
        return mapper.user.mapToUserDtoList(userService.user.getUserByDepartment(departmentName));
    }

    @GetMapping(path = "/role/{roleName}")
    public List<UserDto> getUserByRole(@PathVariable String roleName) {
        return mapper.user.mapToUserDtoList(userService.user.getUserByRole(roleName));
    }

    @DeleteMapping(path = "/{id}")
    public void deleteUser(@PathVariable int id) {
        userService.user.deleteUser(id);
    }

    @PutMapping
    public void updateUser(@ModelAttribute User user) {
        userService.user.updateUser(user);
    }
}
