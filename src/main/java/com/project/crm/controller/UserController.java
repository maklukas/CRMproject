package com.project.crm.controller;

import com.project.crm.domain.Dto.UserDto;
import com.project.crm.domain.User;
import com.project.crm.mapper.MapperConnected;
import com.project.crm.service.ServiceConnected;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@CrossOrigin("*")
@RequestMapping(path = "/v1/users")
@RestController
public class UserController {

    private ServiceConnected userService;
    private MapperConnected mapper;

    @Autowired
    public UserController(ServiceConnected userService, MapperConnected mapper) {
        this.userService = userService;
        this.mapper = mapper;
    }

    @PostMapping
    public void addNewUser (@ModelAttribute User user, HttpServletResponse response) throws IOException {
        userService.user.createUser(user);
        response.sendRedirect("/hello");
    }

    @GetMapping
    public List<UserDto> getUsers() {
        return mapper.user.mapToUserDtoList(userService.user.getUsers());
    }

    @GetMapping(params = "id")
    public UserDto getUser(@RequestParam int id) {
        return mapper.user.mapToUserDto(userService.user.getUserById(id));
    }

    @GetMapping(params = "fragment")
    public List<UserDto> getUserByName(@RequestParam String fragment) {
        return mapper.user.mapToUserDtoList(userService.user.getUserByFragment(fragment));
    }

    @GetMapping(params = "department")
    public List<UserDto> getUserByDepartment(@RequestParam String department) {
        return mapper.user.mapToUserDtoList(userService.user.getUserByDepartment(department));
    }

    @GetMapping(params = "role}")
    public List<UserDto> getUserByRole(@RequestParam String role) {
        return mapper.user.mapToUserDtoList(userService.user.getUserByRole(role));
    }

    @DeleteMapping(params = "id")
    public void deleteUser(@RequestParam int id) {
        userService.user.deleteUser(id);
    }

    //TODO add @PutMapping method

    @PatchMapping
    public void updateUser(@ModelAttribute User user) {
        userService.user.updateUser(user);
    }
}
