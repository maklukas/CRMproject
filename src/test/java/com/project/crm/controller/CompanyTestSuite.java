package com.project.crm.controller;

import com.google.gson.Gson;
import com.project.crm.domain.Dto.CompanyDto;
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

@WebMvcTest(CompanyController.class)
@RunWith(SpringRunner.class)
@WithMockUser(username = "username", password = "password")
public class CompanyTestSuite {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CompanyController controller;

    @Test
    public void shouldCreateCompany() throws Exception {
        //given
        CompanyDto company = new CompanyDto(
                1,
                "Test company",
                "Test address",
                "TE154454566",
                null
        );
        doNothing().when(controller).createCompany(company);
        String context = new Gson().toJson(company);
        //when & then
        mockMvc.perform(post("/v1/companies").contentType(MediaType.APPLICATION_JSON).content(context))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldGetClients() throws Exception {
        //given
        List<CompanyDto> companies = new ArrayList<>();
        companies.add(new CompanyDto(
                1,
                "Test company",
                "Test address",
                "TE154454566",
                null
        ));
        when(controller.getCompanies()).thenReturn(companies);
        //when & then
        mockMvc.perform(get("/v1/companies").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].name", is("Test company")))
                .andExpect(jsonPath("$[0].address", is("Test address")))
                .andExpect(jsonPath("$[0].taxNumber", is("TE154454566")));
    }

    @Test
    public void shouldGetCompanyById() throws Exception {
        //given
        CompanyDto company = new CompanyDto(
                1,
                "Test company",
                "Test address",
                "TE154454566",
                null
        );
        int id = 1;
        when(controller.getCompanyById(1)).thenReturn(company);
        //when & then
        mockMvc.perform(get("/v1/companies/" + id).contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.name", is("Test company")))
                .andExpect(jsonPath("$.address", is("Test address")))
                .andExpect(jsonPath("$.taxNumber", is("TE154454566")));
    }

    @Test
    public void shouldGetCompaniesByFragment() throws Exception {
        //given
        List<CompanyDto> companies = new ArrayList<>();
        companies.add(new CompanyDto(
                1,
                "Test company",
                "Test address",
                "TE154454566",
                null
        ));
        String fragment = "comp";
        when(controller.getCompaniesByFragment(fragment)).thenReturn(companies);
        //when & then
        mockMvc.perform(get("/v1/companies/fragment/" + fragment).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].name", is("Test company")))
                .andExpect(jsonPath("$[0].address", is("Test address")))
                .andExpect(jsonPath("$[0].taxNumber", is("TE154454566")));
    }

    @Test
    public void shouldUpdateCompany() throws Exception {
        //given
        CompanyDto company = new CompanyDto(
                1,
                "Test company",
                "Test address",
                "TE154454566",
                null
        );
        doNothing().when(controller).updateCompany(company);
        String context = new Gson().toJson(company);
        //when & then
        mockMvc.perform(put("/v1/companies").contentType(MediaType.APPLICATION_JSON).content(context))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldDeleteCompany() throws Exception {
        //given
        CompanyDto company = new CompanyDto(
                1,
                "Test company",
                "Test address",
                "TE154454566",
                null
        );
        int id = 1;
        doNothing().when(controller).deleteCompany(id);
        //when & then
        mockMvc.perform(delete("/v1/companies/" + id).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

    }
}
