package com.project.crm.controller;

import com.google.gson.Gson;
import com.project.crm.domain.Dto.DepartmentDto;
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
@WebMvcTest(DepartmentController.class)
@WithMockUser(username = "user", password = "password")
public class DepartmentTestSuite {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DepartmentController controller;

    @Test
    public void shouldCreateDepartment() throws Exception {
        //given
        DepartmentDto department = new DepartmentDto(1, "NewTest", new ArrayList<>());
        doNothing().when(controller).createDepartment(department);
        String context = new Gson().toJson(department);
        //when & then
        mockMvc.perform(post("/v1/departments").contentType(MediaType.APPLICATION_JSON).content(context))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldUpdateDepartment() throws Exception {
        //given
        DepartmentDto department = new DepartmentDto(1, "NewTest", new ArrayList<>());
        doNothing().when(controller).updateDepartment(department);
        String context = new Gson().toJson(department);
        //when & then
        mockMvc.perform(put("/v1/departments").contentType(MediaType.APPLICATION_JSON).content(context))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldDeleteDepartment() throws Exception {
        //given
        DepartmentDto department = new DepartmentDto(1, "NewTest", new ArrayList<>());
        int id = 1;
        doNothing().when(controller).deleteDepartment(id);
        //when & then
        mockMvc.perform(delete("/v1/departments/" + id).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldGetDepartments() throws Exception {
        //given
        List<DepartmentDto> departments = new ArrayList<>();
        departments.add(new DepartmentDto(1, "NewTest", new ArrayList<>()));
        when(controller.getDepartments()).thenReturn(departments);
        //when & then
        mockMvc.perform(get("/v1/departments").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].name", is("NewTest")));
    }

    @Test
    public void shouldGetDepartment() throws Exception {
        //given
        DepartmentDto department = new DepartmentDto(1, "NewTest", new ArrayList<>());
        int id = 1;
        when(controller.getDepartment(id)).thenReturn(department);
        //when & then
        mockMvc.perform(get("/v1/departments/" + id).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.name", is("NewTest")));
    }



}
