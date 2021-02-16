package com.project.crm.controller;

import com.google.gson.Gson;
import com.project.crm.domain.Dto.StatusDto;
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
@WebMvcTest(StatusController.class)
@WithMockUser(username = "user", password = "password")
public class StatusTestSuite {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private StatusController controller;

    @Test
    public void shouldCreateStatus() throws Exception {
        //given
        StatusDto status = new StatusDto(1, "NewStatus");
        doNothing().when(controller).createStatus(status);
        String context = new Gson().toJson(status);
        //when & then
        mockMvc.perform(post("/v1/statuses").contentType(MediaType.APPLICATION_JSON).content(context))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldUpdateStatus() throws Exception {
        //given
        StatusDto status = new StatusDto(1, "NewStatus");
        doNothing().when(controller).updateStatus(status);
        String context = new Gson().toJson(status);
        //when & then
        mockMvc.perform(put("/v1/statuses").contentType(MediaType.APPLICATION_JSON).content(context))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldDeleteStatus() throws Exception {
        //given
        StatusDto status = new StatusDto(1, "NewStatus");
        int id = 1;
        doNothing().when(controller).deleteStatus(id);
        //when & then
        mockMvc.perform(delete("/v1/statuses/" + id)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldGetStatuses() throws Exception {
        //given
        List<StatusDto> statuses = new ArrayList<>();
        statuses.add(new StatusDto(1, "NewStatus"));
        when(controller.getStatuses()).thenReturn(statuses);
        //when & then
        mockMvc.perform(get("/v1/statuses")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].name", is("NewStatus")));
    }

    @Test
    public void shouldGetStatus() throws Exception {
        //given
        StatusDto status = new StatusDto(1, "NewStatus");
        int id = 1;
        when(controller.getStatus(id)).thenReturn(status);
        //when & then
        mockMvc.perform(get("/v1/statuses/" + id)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.name", is("NewStatus")));
    }
}
