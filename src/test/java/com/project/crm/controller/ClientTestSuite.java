package com.project.crm.controller;

import com.google.gson.Gson;
import com.project.crm.domain.Dto.ClientDto;
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
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = ClientController.class)
@WithMockUser(username = "user", password = "password")
public class ClientTestSuite {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ClientController controller;

    @Test
    public void shouldCreateClient() throws Exception {
        //given
        ClientDto client = new ClientDto(
                1,
                "Lukasz",
                "Makuch",
                880700290,
                new ArrayList<>()
        );
        doNothing().when(controller).createClient(client);

        String gson = new Gson().toJson(client);
        mockMvc.perform(post("/v1/clients").contentType(MediaType.APPLICATION_JSON).content(gson))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldGetClients() throws Exception {
        //given
        List<ClientDto> clients = new ArrayList<>();
        clients.add(new ClientDto(
                1,
                "Lukasz",
                "Makuch",
                880700290,
                new ArrayList<>())
        );
        when(controller.getClients()).thenReturn(clients);
        //when & then
        mockMvc.perform(get("/v1/clients").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].firstname", is("Lukasz")))
                .andExpect(jsonPath("$[0].lastname", is("Makuch")))
                .andExpect(jsonPath("$[0].phoneNo", is(880700290)));
    }

    @Test
    public void shouldGetClient() throws Exception {
        //given
        ClientDto client = new ClientDto(
                1,
                "Lukasz",
                "Makuch",
                880700290,
                new ArrayList<>()
        );

        int id = 1;
        when(controller.getClientById(id)).thenReturn(client);
        //when & then
        mockMvc.perform(get("/v1/clients/"+id)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.firstname", is("Lukasz")))
                .andExpect(jsonPath("$.lastname", is("Makuch")))
                .andExpect(jsonPath("$.phoneNo", is(880700290)));
    }

    @Test
    public void shouldDeleteClient() throws Exception {
        //given
        ClientDto client = new ClientDto(
                1,
                "Lukasz",
                "Makuch",
                880700290,
                new ArrayList<>()
        );
        doNothing().when(controller).deleteClient(1);
        //when & then
        mockMvc.perform(delete("/v1/clients/1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    public void shouldUpdateClient() throws Exception {
        //given
        ClientDto client = new ClientDto(
                1,
                "Lukasz",
                "Makuch",
                880700290,
                new ArrayList<>()
        );
        doNothing().when(controller).updateClient(client);
        String gson = new Gson().toJson(client);
        //when & then
        mockMvc.perform(put("/v1/clients")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .characterEncoding("UTF-8").content(gson))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldGetClientByString() throws Exception {
        //given
        List<ClientDto> clients = new ArrayList<>();
        clients.add(new ClientDto(
                1,
                "Lukasz",
                "Makuch",
                880700290,
                new ArrayList<>())
        );
        String txt = "Mak";
        when(controller.getClientByFragment(txt)).thenReturn(clients);
        //when & then
        mockMvc.perform(get("/v1/clients/fragment/"+txt).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].firstname", is("Lukasz")))
                .andExpect(jsonPath("$[0].lastname", is("Makuch")))
                .andExpect(jsonPath("$[0].phoneNo", is(880700290)));
    }
}
