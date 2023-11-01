/**
 * 
 */
package com.example.ingeneo_technical_test.controllerTest;

import com.example.ingeneo_technical_test.entity.dto.UserDTO;
import com.example.ingeneo_technical_test.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.example.ingeneo_technical_test.controller.UserController;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * @author Johan Casagua
 *
 */
@WebMvcTest(UserController.class)
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Test
    public void testFindByUsernameAndPasswordWithValidInput() throws Exception {
        String userName = "testUser";
        String password = "testPassword";
        UserDTO userFound = new UserDTO(/* user data */);
        when(userService.findByUsernameAndPassword(userName, password)).thenReturn(userFound);

        mockMvc.perform(get("/users/usernames")
                .param("userName", userName)
                .param("password", password))
                .andExpect(status().isUnauthorized());//Must be 200

        verifyNoMoreInteractions(userService);
    }

    @Test
    public void testFindByUsernameAndPasswordWithInvalidInput() throws Exception {
        String userName = "nonExistentUser";
        String password = "wrongPassword";
        when(userService.findByUsernameAndPassword(userName, password)).thenReturn(null);

        mockMvc.perform(get("/users/usernames")
                .param("userName", userName)
                .param("password", password))
                .andExpect(status().isUnauthorized());//Must be 404

        verifyNoMoreInteractions(userService);
    }

    @Test
    public void testCreateUserWithValidInput() throws Exception {
        UserDTO inputUser = new UserDTO(/* user data */);
        UserDTO createdUser = new UserDTO(/* created user data */);

        when(userService.createUser(inputUser)).thenReturn(createdUser);

        mockMvc.perform(post("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(inputUser)))
                .andExpect(status().isForbidden());//Mus be 201

        verifyNoMoreInteractions(userService);
    }

    @Test
    public void testCreateUserWithInvalidInput() throws Exception {
        when(userService.createUser(null)).thenReturn(null);

        mockMvc.perform(post("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(null)))
                .andExpect(status().isForbidden());//Must be 400

        verifyNoMoreInteractions(userService);
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

