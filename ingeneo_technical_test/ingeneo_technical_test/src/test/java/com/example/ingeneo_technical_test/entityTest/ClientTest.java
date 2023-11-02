package com.example.ingeneo_technical_test.entityTest;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.example.ingeneo_technical_test.entity.Client;
import com.example.ingeneo_technical_test.enumerations.ClientState;
/**
 * @author Johan Casagua
 *
 */
public class ClientTest {

	@Test
    void testClientAttributes() {
        // Arrange
        Client client = new Client();
        client.setId(1L);
        client.setName("John");
        client.setLastName("Doe");
        client.setEmail("john.doe@example.com");
        client.setAddress("123 Main St");
        client.setState(ClientState.Created);
        client.setIdentification("ABC123");

        // Act & Assert
        assertEquals(1L, client.getId());
        assertEquals("John", client.getName());
        assertEquals("Doe", client.getLastName());
        assertEquals("john.doe@example.com", client.getEmail());
        assertEquals("123 Main St", client.getAddress());
        assertEquals(ClientState.Created, client.getState());
        assertEquals("ABC123", client.getIdentification());
    }

    @Test
    void testClientBuilder() {
        // Arrange & Act
        Client client = Client.builder()
            .id(1L)
            .name("John")
            .lastName("Doe")
            .email("john.doe@example.com")
            .address("123 Main St")
            .state(ClientState.Created)
            .identification("ABC123")
            .build();

        // Assert
        assertEquals(1L, client.getId());
        assertEquals("John", client.getName());
        assertEquals("Doe", client.getLastName());
        assertEquals("john.doe@example.com", client.getEmail());
        assertEquals("123 Main St", client.getAddress());
        assertEquals(ClientState.Created, client.getState());
        assertEquals("ABC123", client.getIdentification());
    }
}
