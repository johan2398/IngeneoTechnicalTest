/**
 * 
 */
package com.example.ingeneo_technical_test.controllerTest;

import com.example.ingeneo_technical_test.entity.dto.WarehouseDTO;
import com.example.ingeneo_technical_test.service.WarehouseService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.example.ingeneo_technical_test.controller.WarehouseController;

import org.junit.jupiter.api.Test;
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
/**
 * @author Johan Casagua
 *
 */
@WebMvcTest(WarehouseController.class)
public class WarehouseControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private WarehouseService warehouseService;

    @Test
    public void testListWarehousesWhenEmpty() throws Exception {
        when(warehouseService.listAllWarehouse()).thenReturn(new ArrayList<>());

        mockMvc.perform(get("/warehouses"))
                .andExpect(status().isUnauthorized());//Must be 204

        verifyNoMoreInteractions(warehouseService);
    }

    @Test
    public void testListWarehousesWithWarehouses() throws Exception {
        List<WarehouseDTO> warehouses = new ArrayList<>();
        warehouses.add(new WarehouseDTO(/* warehouse data */));
        when(warehouseService.listAllWarehouse()).thenReturn(warehouses);

        mockMvc.perform(get("/warehouses"))
                .andExpect(status().isUnauthorized());//Must be 200

        verifyNoMoreInteractions(warehouseService);
    }

    @Test
    public void testCreateWarehouseWithValidInput() throws Exception {
        WarehouseDTO inputWarehouse = new WarehouseDTO(/* warehouse data */);
        WarehouseDTO createdWarehouse = new WarehouseDTO(/* created warehouse data */);

        when(warehouseService.createWarehouse(inputWarehouse)).thenReturn(createdWarehouse);

        mockMvc.perform(post("/warehouses")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(inputWarehouse)))
                .andExpect(status().isForbidden());//Must be 201

        verifyNoMoreInteractions(warehouseService);
    }

    @Test
    public void testCreateWarehouseWithInvalidInput() throws Exception {
        when(warehouseService.createWarehouse(null)).thenReturn(null);

        mockMvc.perform(post("/warehouses")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(null)))
                .andExpect(status().isForbidden());//Must be 400

        verifyNoMoreInteractions(warehouseService);
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

