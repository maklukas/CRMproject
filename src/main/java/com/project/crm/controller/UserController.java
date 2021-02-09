package com.project.crm.controller;

import com.project.crm.domain.User;
import com.project.crm.domain.Dto.UserDto;
import com.project.crm.mapper.MapperConnected;
import com.project.crm.mapper.UserMapper;
import com.project.crm.service.ServiceConnected;
import com.project.crm.service.UserService;
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

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public void addNewUser (@RequestBody User user) {
        userService.user.createUser(user);
    }

    @GetMapping
    public List<UserDto> getUsers () {
        List<UserDto> users = mapper.user.mapToUserDtoList(userService.user.getUsers());
        return users;
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
    public List<UserDto> getUserByDepartmentId(@PathVariable String departmentName) {
        return mapper.user.mapToUserDtoList(userService.user.getUserByDepartmentId(departmentName));
    }

    @DeleteMapping(path = "{/id}")
    public void deleteUser(@PathVariable int id) {
        userService.user.deleteUser(id);
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public void updateUser(@RequestBody User user) {
        userService.user.updateUser(user);
    }
}
