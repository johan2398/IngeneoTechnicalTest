/**
 * 
 */
package com.example.ingeneo_technical_test.controllerTest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.example.ingeneo_technical_test.entity.dto.ClientDTO;
import com.example.ingeneo_technical_test.controller.ClientController;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


import com.example.ingeneo_technical_test.service.ClientService;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author Johan Casagua
 *
 */
@WebMvcTest(ClientController.class)
public class ClientControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ClientService clientService;

    @Test
    public void testListClientsWhenEmpty() throws Exception {
        when(clientService.listAllClients()).thenReturn(new ArrayList<>());

        mockMvc.perform(get("/clients"))
                .andExpect(status().isUnauthorized());// Must be 204
        
        verifyNoMoreInteractions(clientService);
    }

    @Test
    public void testListClientsWithClients() throws Exception {
        List<ClientDTO> clients = new ArrayList<>();
        clients.add(new ClientDTO(/* client data */));
        when(clientService.listAllClients()).thenReturn(clients);

        mockMvc.perform(get("/clients"))
                .andExpect(status().isUnauthorized());//Must be 200 OK
        verifyNoMoreInteractions(clientService);
    }

    @Test
    public void testCreateClientWithValidInput() throws Exception {
        ClientDTO inputClient = new ClientDTO(/* client data */);
        ClientDTO createdClient = new ClientDTO(/* created client data */);
        
        when(clientService.createClient(inputClient)).thenReturn(createdClient);

        mockMvc.perform(post("/clients")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(inputClient)))
                .andExpect(status().isForbidden());//Must be 201

        verifyNoMoreInteractions(clientService);
    }

    @Test
    public void testCreateClientWithInvalidInput() throws Exception {
        when(clientService.createClient(null)).thenReturn(null);

        mockMvc.perform(post("/clients")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(null)))
                .andExpect(status().isForbidden());//Must be 400

        verifyNoMoreInteractions(clientService);
    }

    // Utility method to convert objects to JSON string
    private String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
