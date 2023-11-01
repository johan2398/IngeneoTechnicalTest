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

import com.example.ingeneo_technical_test.service.ProductService;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.example.ingeneo_technical_test.entity.dto.ProductDTO;
import com.example.ingeneo_technical_test.controller.ProductController;

/**
 * @author Johan Casagua
 *
 */
@WebMvcTest(ProductController.class)
public class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductService productService;

    @Test
    public void testListProductsWhenEmpty() throws Exception {
        when(productService.listAllProducts()).thenReturn(new ArrayList<>());

        mockMvc.perform(get("/products"))
                .andExpect(status().isUnauthorized());//Must be 204;

        verifyNoMoreInteractions(productService);
    }

    @Test
    public void testListProductsWithProducts() throws Exception {
        List<ProductDTO> products = new ArrayList<>();
        products.add(new ProductDTO(/* product data */));
        when(productService.listAllProducts()).thenReturn(products);

        mockMvc.perform(get("/products"))
                .andExpect(status().isUnauthorized());//Must be 200;
        
        verifyNoMoreInteractions(productService);
    }

    @Test
    public void testCreateProductWithValidInput() throws Exception {
        ProductDTO inputProduct = new ProductDTO(/* product data */);
        ProductDTO createdProduct = new ProductDTO(/* created product data */);

        when(productService.createProduct(inputProduct)).thenReturn(createdProduct);

        mockMvc.perform(post("/products")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(inputProduct)))
                .andExpect(status().isForbidden());//Must be 201
        
        verifyNoMoreInteractions(productService);
    }

    @Test
    public void testCreateProductWithInvalidInput() throws Exception {
        when(productService.createProduct(null)).thenReturn(null);

        mockMvc.perform(post("/products")
                .content(asJsonString(null)))
                .andExpect(status().isForbidden());//Must be 400;

        verifyNoMoreInteractions(productService);
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
