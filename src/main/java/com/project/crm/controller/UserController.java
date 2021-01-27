package com.project.crm.controller;

import com.project.crm.domain.User;
import com.project.crm.domain.UserDto;
import com.project.crm.mapper.UserMapper;
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
    private UserService userService;

    @Autowired
    private UserMapper mapper;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public void addNewUser (@RequestBody User user) {
        userService.createUser(user);
    }

    @GetMapping
    public List<UserDto> getUsers () {
        List<UserDto> users = mapper.mapToUserDtoList(userService.getUsers());
        return users;
    }

    @GetMapping(path = "/{id}")
    public UserDto getUser(@PathVariable int id) {
        return mapper.mapToUserDto(userService.getUserById(id));
    }

    @GetMapping(path = "/fragment/{txt}")
    public List<UserDto> getUserByName(@PathVariable String txt) {
        return mapper.mapToUserDtoList(userService.getUserByFragment(txt));
    }

    @GetMapping(path = "/department/{department_id}")
    public List<UserDto> getUserByDepartmentId(@PathVariable int department_id) {
        return mapper.mapToUserDtoList(userService.getUserByDepartmentId(department_id));
    }

    @DeleteMapping(path = "{/id}")
    public void deleteUser(@PathVariable int id) {
        userService.deleteUser(id);
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public void updateUser(@RequestBody User user) {
        userService.updateUser(user);
    }
}
