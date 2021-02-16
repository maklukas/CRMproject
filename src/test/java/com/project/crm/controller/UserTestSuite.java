package com.project.crm.controller;

import com.google.gson.Gson;
import com.project.crm.domain.Dto.DepartmentDto;
import com.project.crm.domain.Dto.UserDto;
import com.project.crm.domain.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
@WithMockUser(username = "user", password = "password")
public class UserTestSuite {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserController controller;

    @Test
    public void shouldCreateUser() throws Exception {
        //given
        User user = new User(
                1,
                "username",
                "password",
                "firTest",
                "laTest",
                null,
                new ArrayList<>(),
                new ArrayList<>());
        String context = new Gson().toJson(user);
        doNothing().when(controller).addNewUser(user);
        //when & then
        mockMvc.perform(post("/v1/users").contentType(MediaType.APPLICATION_JSON_VALUE).content(context))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldUpdateUser() throws Exception {
        //given
        User user = new User(
                1,
                "username",
                "password",
                "firTest",
                "laTest",
                null,
                new ArrayList<>(),
                new ArrayList<>());
        String context = new Gson().toJson(user);
        doNothing().when(controller).updateUser(user);
        //when & then
        mockMvc.perform(put("/v1/users").contentType(MediaType.APPLICATION_JSON_VALUE).content(context))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldDeleteUser() throws Exception {
        //given
        UserDto user = new UserDto(
                1,
                "username",
                "firTest",
                "laTest",
                null);
        int id = 1;
        doNothing().when(controller).deleteUser(id);
        //when & then
        mockMvc.perform(delete("/v1/users/" + id).contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldGetUserById() throws Exception {
        //given
        UserDto user = new UserDto(
                1,
                "userTest",
                "firTest",
                "laTest",
                null);
        int id = 1;
        when(controller.getUser(id)).thenReturn(user);
        //when & then
        mockMvc.perform(get("/v1/users/" + id).contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.username", is("userTest")))
                .andExpect(jsonPath("$.firstname", is("firTest")))
                .andExpect(jsonPath("$.lastname", is("laTest")));
    }

    @Test
    public void shouldGetUsers() throws Exception {
        //given
        List<UserDto> users = new ArrayList<>();
        users.add(new UserDto(
                1,
                "userTest",
                "firTest",
                "laTest",
                null));
        when(controller.getUsers()).thenReturn(users);
        //when & then
        mockMvc.perform(get("/v1/users").contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].username", is("userTest")))
                .andExpect(jsonPath("$[0].firstname", is("firTest")))
                .andExpect(jsonPath("$[0].lastname", is("laTest")));
    }

    @Test
    public void shouldGetUsersByFragment() throws Exception {
        //given
        List<UserDto> users = new ArrayList<>();
        users.add(new UserDto(
                1,
                "userTest",
                "firTest",
                "laTest",
                null));
        String fragment = "Tes";
        when(controller.getUserByName(fragment)).thenReturn(users);
        //when & then
        mockMvc.perform(get("/v1/users/fragment/" + fragment).contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].username", is("userTest")))
                .andExpect(jsonPath("$[0].firstname", is("firTest")))
                .andExpect(jsonPath("$[0].lastname", is("laTest")));
    }

    @Test
    public void shouldGetDepartmentsUsers() throws Exception {
        //given
        List<UserDto> users = new ArrayList<>();
        DepartmentDto department = new DepartmentDto(1, "DepTest");
        users.add(new UserDto(
                1,
                "userTest",
                "firTest",
                "laTest",
                department));
        String fragment = "DepTest";
        when(controller.getUserByDepartment(fragment)).thenReturn(users);
        //when & then
        mockMvc.perform(get("/v1/users/department/" + fragment).contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].username", is("userTest")))
                .andExpect(jsonPath("$[0].firstname", is("firTest")))
                .andExpect(jsonPath("$[0].lastname", is("laTest")))
                .andExpect(jsonPath("$[0].department.id", is(1)))
                .andExpect(jsonPath("$[0].department.name", is("DepTest")));
    }
}
