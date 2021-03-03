package com.project.crm.controller;

import com.google.gson.Gson;
import com.project.crm.domain.Dto.StatusDto;
import com.project.crm.domain.Dto.TaskDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(TaskController.class)
@WithMockUser(username = "user", password = "password")
public class TaskTestSuite {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TaskController controller;

    @Test
    public void shouldCreateTask() throws Exception {
        //given
        LocalDateTime tera = LocalDateTime.now();
        TaskDto task = new TaskDto(
                1,
                new ArrayList<>(),
                null,
                "Test Title",
                "Test Description",
                null,
                null);
        String context = new Gson().toJson(task);
        doNothing().when(controller).createTask(task);
        //when & then
        mockMvc.perform(post("/v1/tasks").contentType(MediaType.APPLICATION_JSON_VALUE).content(context))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldUpdateTask() throws Exception {
        //given
        LocalDateTime tera = LocalDateTime.now();
        TaskDto task = new TaskDto(
                1,
                new ArrayList<>(),
                null,
                "Test Title",
                "Test Description",
                null,
                null);
        String context = new Gson().toJson(task);
        doNothing().when(controller).updateTask(task);
        //when & then
        mockMvc.perform(put("/v1/tasks").contentType(MediaType.APPLICATION_JSON_VALUE).content(context))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldDeleteTask() throws Exception {
        //given
        LocalDateTime tera = LocalDateTime.now();
        TaskDto task = new TaskDto(
                1,
                new ArrayList<>(),
                null,
                "Test Title",
                "Test Description",
                tera,
                tera.plusDays(1));
        int id = 1;
        doNothing().when(controller).deleteTask(id);
        //when & then
        mockMvc.perform(delete("/v1/tasks/" + id)
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldGetTaskById() throws Exception {
        //given
        StatusDto status = new StatusDto(1, "StTest");
        LocalDateTime tera = LocalDateTime.of(2021, 1, 12, 12,15);
        TaskDto task = new TaskDto(
                1,
                new ArrayList<>(),
                status,
                "Test Title",
                "Test Description",
                tera,
                tera.plusDays(1));
        int id = 1;
        when(controller.getTaskById(id)).thenReturn(task);
        //when & then
        mockMvc.perform(get("/v1/tasks/" + id)
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.title", is("Test Title")))
                .andExpect(jsonPath("$.description", is("Test Description")))
                .andExpect(jsonPath("$.status.id", is(1)))
                .andExpect(jsonPath("$.status.name", is("StTest")))
                .andExpect(jsonPath("$.creationTime", is("2021-01-12T12:15:00")))
                .andExpect(jsonPath("$.realizationTime", is("2021-01-13T12:15:00")));

    }

    @Test
    public void shouldGetTasks() throws Exception {
        //given
        LocalDateTime tera = LocalDateTime.now();
        List<TaskDto> tasks = new ArrayList<>();
        tasks.add(new TaskDto(
                1,
                new ArrayList<>(),
                null,
                "Test Title",
                "Test Description",
                tera,
                tera.plusDays(1)));
        when(controller.getTasks()).thenReturn(tasks);
        //when & then
        mockMvc.perform(get("/v1/tasks")
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].title", is("Test Title")))
                .andExpect(jsonPath("$[0].description", is("Test Description")));
//                .andExpect(jsonPath("$.creationTime", is("2021-02-10T17:32:30.0823494")))
//                .andExpect(jsonPath("$.realizationTime", is("2021-02-11T17:32:30.0823494")));
    }

    @Test
    public void shouldGetTasksByFragment() throws Exception {
        //given
        LocalDateTime tera = LocalDateTime.of(2021,1,12,12,15);
        List<TaskDto> tasks = new ArrayList<>();
        tasks.add(new TaskDto(
                1,
                new ArrayList<>(),
                null,
                "Test Title",
                "Test Description",
                tera,
                tera.plusDays(1)));
        String fragment = "Desc";
        when(controller.getTaskByFragment(fragment)).thenReturn(tasks);
        //when & then
        mockMvc.perform(get("/v1/tasks/fragment/" + fragment)
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].title", is("Test Title")))
                .andExpect(jsonPath("$[0].description", is("Test Description")))
                .andExpect(jsonPath("$[0].creationTime", is("2021-01-12T12:15:00")))
                .andExpect(jsonPath("$[0].realizationTime", is("2021-01-13T12:15:00")));
    }
}
