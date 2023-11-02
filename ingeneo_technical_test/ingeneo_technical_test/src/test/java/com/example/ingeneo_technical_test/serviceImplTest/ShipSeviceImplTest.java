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
import com.example.ingeneo_technical_test.entity.dto.ClientDTO;
import com.example.ingeneo_technical_test.entity.dto.ShipDTO;
import com.example.ingeneo_technical_test.DataSet;
import com.example.ingeneo_technical_test.entity.Client;
import com.example.ingeneo_technical_test.entity.Ship;

import com.example.ingeneo_technical_test.entity.dto.converter.ClientDTOConverter;
import com.example.ingeneo_technical_test.enumerations.ShipState;
import com.example.ingeneo_technical_test.repository.ClientRepository;
import com.example.ingeneo_technical_test.repository.ShipRepository;
import com.example.ingeneo_technical_test.service.ClientService;
import com.example.ingeneo_technical_test.service.Implement.ShipServiceImpl;

/**
 * @author Johan Casagua
 *
 */
class ShipSeviceImplTest {
	@Mock
    private ShipRepository shipRepository;

    @Mock
    private ClientRepository clientRepository;

    @Mock
    private ClientService clientService;

    @InjectMocks
    private ShipServiceImpl shipService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testListAllShips() {
        // Arrange
        List<Ship> ships = new ArrayList<>();
        ships.add(new Ship());
        ships.add(new Ship());
        when(shipRepository.findAll()).thenReturn(ships);

        // Act
        List<ShipDTO> shipDTOs = shipService.listAllShips();

        // Assert
        assertEquals(2, shipDTOs.size());
        verify(shipRepository, times(1)).findAll();
    }

    @Test
    void testFilterByDocIdClientOrGuideNumber() {
        // Arrange
        String search = "search";
        List<Ship> ships = new ArrayList<>();
        ships.add(new Ship());
        when(shipRepository.findByClientDocIdOrGuideNumberContainingIgnoreCase(search)).thenReturn(ships);

        // Act
        List<ShipDTO> shipDTOs = shipService.filterByDocIdClientOrGuideNumber(search);

        // Assert
        assertEquals(1, shipDTOs.size());
        verify(shipRepository, times(1)).findByClientDocIdOrGuideNumberContainingIgnoreCase(search);
    }

    @Test
    void testCreateShip() {
        // Arrange
        ShipDTO shipDTO = DataSet.getShip();
        ClientDTO clientDTO = new ClientDTO();
        shipDTO.setClient(ClientDTOConverter.convertToEntity(clientDTO));
        when(clientRepository.findByIdentification(any())).thenReturn(new Client());
        when(clientService.createClient(any())).thenReturn(new ClientDTO());
        when(shipRepository.save(any())).thenReturn(new Ship());

        // Act
        ShipDTO savedShipDTO = shipService.createShip(shipDTO);

        // Assert
        assertNotNull(savedShipDTO);
        verify(shipRepository, times(1)).save(any(Ship.class));
    }
}
