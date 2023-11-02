/**
 * 
 */
package com.example.ingeneo_technical_test.serviceImplTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.example.ingeneo_technical_test.entity.Client;
import com.example.ingeneo_technical_test.entity.dto.ClientDTO;
import com.example.ingeneo_technical_test.entity.dto.converter.ClientDTOConverter;
import com.example.ingeneo_technical_test.repository.ClientRepository;
import com.example.ingeneo_technical_test.service.Implement.ClientServiceImpl;

import com.example.ingeneo_technical_test.DataSet;

/**
 * @author Johan Casagua
 *
 */
class ClientServiceImplTest {

	@Mock
    private ClientRepository clientRepository;

    @InjectMocks
    private ClientServiceImpl clientService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testListAllClients() {
        // Arrange
        List<Client> clients = new ArrayList<>();
        clients.add(ClientDTOConverter.convertToEntity(DataSet.getClient()));
        clients.add(ClientDTOConverter.convertToEntity(DataSet.getClient()));
        when(clientRepository.findAll()).thenReturn(clients);

        // Act
        List<ClientDTO> clientDTOs = clientService.listAllClients();

        // Assert
        assertEquals(2, clientDTOs.size());
        verify(clientRepository, times(1)).findAll();
    }

    @Test
    void testCreateClient() {
        // Arrange
        ClientDTO clientDTO = DataSet.getClient();
        when(clientRepository.save(any(Client.class))).thenReturn(new Client());
        // Act
        ClientDTO savedClientDTO = clientService.createClient(clientDTO);

        // Assert
        assertNotNull(savedClientDTO);
        verify(clientRepository, times(1)).save(any(Client.class));
    }
}
