package com.project.crm.controller;

import com.google.gson.Gson;
import com.project.crm.domain.Dto.*;
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

import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(InvestmentController.class)
@WithMockUser(username = "user", password = "password")
public class InvestmentTestSuite {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private InvestmentController controller;

    @Test
    public void shouldCreateInvestment() throws Exception {
        //given
        InvestmentDto investment = new InvestmentDto(
                1,
                "Test Investment",
                "Test Address",
                new UserDto(),
                new ArrayList<>(),
                new ArrayList<>(),
                new StatusDto()
        );
        doNothing().when(controller).createInvestment(investment);
        String context = new Gson().toJson(investment);
        //when & then
        mockMvc.perform(post("/v1/investments").contentType(MediaType.APPLICATION_JSON).content(context))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldUpdateInvestment() throws Exception {
        //given
        InvestmentDto investment = new InvestmentDto(
                1,
                "Test Investment",
                "Test Address",
                new UserDto(),
                new ArrayList<>(),
                new ArrayList<>(),
                new StatusDto()
        );
        doNothing().when(controller).updateInvestment(investment);
        String context = new Gson().toJson(investment);
        //when & then
        mockMvc.perform(put("/v1/investments").contentType(MediaType.APPLICATION_JSON).content(context))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldDeleteInvestment() throws Exception {
        //given
        InvestmentDto investment = new InvestmentDto(
                1,
                "Test Investment",
                "Test Address",
                new UserDto(),
                new ArrayList<>(),
                new ArrayList<>(),
                new StatusDto()
        );
        int id = 1;
        doNothing().when(controller).deleteInvestment(id);
        //when & then
        mockMvc.perform(delete("/v1/investments/" + id).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldGetInvestments() throws Exception {
        //given
        DepartmentDto departmentDto = new DepartmentDto(1, "TestDep");
        RoleDto roleDto = new RoleDto(1, "USER");
        UserDto user = new UserDto(1, "user", "fn", "ln", departmentDto, roleDto);
        StatusDto status = new StatusDto(1, "StTest");
        List<InvestmentDto> investments = new ArrayList<>();
        investments.add(new InvestmentDto(
                1,
                "Test Investment",
                "Test Address",
                user,
                new ArrayList<>(),
                new ArrayList<>(),
                status
        ));

        when(controller.getInvestments()).thenReturn(investments);
        //when & then
        mockMvc.perform(get("/v1/investments").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].name", is("Test Investment")))
                .andExpect(jsonPath("$[0].address", is("Test Address")))
                .andExpect(jsonPath("$[0].registeredBy.id", is(1)))
                .andExpect(jsonPath("$[0].registeredBy.username", is("user")))
                .andExpect(jsonPath("$[0].registeredBy.firstname", is("fn")))
                .andExpect(jsonPath("$[0].registeredBy.department.id", is(1)))
                .andExpect(jsonPath("$[0].registeredBy.department.name", is("TestDep")))
                .andExpect(jsonPath("$[0].status.id", is(1)))
                .andExpect(jsonPath("$[0].status.name", is("StTest")));
    }

    @Test
    public void shouldGetInvestmentsByFragment() throws Exception {
        //given
        List<InvestmentDto> investments = new ArrayList<>();
        investments.add(new InvestmentDto(
                1,
                "Test Investment",
                "Test Address",
                null,
                new ArrayList<>(),
                new ArrayList<>(),
                null
        ));
        String fragment = "Inve";
        when(controller.getInvestmentByFragment(fragment)).thenReturn(investments);
        //when & then
        mockMvc.perform(get("/v1/investments/fragment/" + fragment).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].name", is("Test Investment")))
                .andExpect(jsonPath("$[0].address", is("Test Address")));
    }

    @Test
    public void shouldGetInvestmentsById() throws Exception {
        //given
        InvestmentDto investment = new InvestmentDto(
                1,
                "Test Investment",
                "Test Address",
                null,
                new ArrayList<>(),
                new ArrayList<>(),
                null
        );
        int id = 1;
        when(controller.getInvestmentById(id)).thenReturn(investment);
        //when & then
        mockMvc.perform(get("/v1/investments/" + id).contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.name", is("Test Investment")))
                .andExpect(jsonPath("$.address", is("Test Address")));

    }

}
