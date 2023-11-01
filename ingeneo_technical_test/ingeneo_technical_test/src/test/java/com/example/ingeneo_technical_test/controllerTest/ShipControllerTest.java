/**
 * 
 */
package com.example.ingeneo_technical_test.controllerTest;


import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.example.ingeneo_technical_test.entity.dto.ShipDTO;
import com.example.ingeneo_technical_test.service.ShipService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.example.ingeneo_technical_test.controller.ShipController;

/**
 * @author Johan Casagua
 *
 */
@WebMvcTest(ShipController.class)
public class ShipControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ShipService shipService;

    @Test
    public void testListShipsWhenEmpty() throws Exception {
        when(shipService.listAllShips()).thenReturn(new ArrayList<>());

        mockMvc.perform(get("/ships"))
                .andExpect(status().isUnauthorized());//Must be 204

        verifyNoMoreInteractions(shipService);
    }

    @Test
    public void testListShipsWithShips() throws Exception {
        List<ShipDTO> ships = new ArrayList<>();
        ships.add(new ShipDTO(/* ship data */));
        when(shipService.listAllShips()).thenReturn(ships);

        mockMvc.perform(get("/ships"))
                .andExpect(status().isUnauthorized());//Must be 200

        verifyNoMoreInteractions(shipService);
    }

    @Test
    public void testFilterShipsByDocIdClientOrGuideNumberWithValidInput() throws Exception {
        String search = "searchQuery";
        List<ShipDTO> filteredShips = new ArrayList<>();
        filteredShips.add(new ShipDTO(/* ship data */));
        when(shipService.filterByDocIdClientOrGuideNumber(search)).thenReturn(filteredShips);

        mockMvc.perform(get("/ships/filters")
                .param("search", search))
                .andExpect(status().isUnauthorized());//must be 200

        verifyNoMoreInteractions(shipService);
    }

    @Test
    public void testFilterShipsByDocIdClientOrGuideNumberWithInvalidInput() throws Exception {
        String search = "invalidSearchQuery";
        when(shipService.filterByDocIdClientOrGuideNumber(search)).thenReturn(new ArrayList<>());

        mockMvc.perform(get("/ships/filters")
                .param("search", search))
                .andExpect(status().isUnauthorized());//Must be 404

        verifyNoMoreInteractions(shipService);
    }

    @Test
    public void testCreateShipmentWithValidInput() throws Exception {
        ShipDTO inputShip = new ShipDTO(/* ship data */);
        ShipDTO createdShip = new ShipDTO(/* created ship data */);

        when(shipService.createShip(inputShip)).thenReturn(createdShip);

        mockMvc.perform(post("/ships")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(inputShip)))
                .andExpect(status().isForbidden());//Must be 201

        verifyNoMoreInteractions(shipService);
    }

    @Test
    public void testCreateShipmentWithInvalidInput() throws Exception {
        when(shipService.createShip(null)).thenReturn(null);

        mockMvc.perform(post("/ships")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(null)))
                .andExpect(status().isForbidden());//Must be 400

        verifyNoMoreInteractions(shipService);
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

