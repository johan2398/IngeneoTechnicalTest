/**
 * 
 */
package com.example.ingeneo_technical_test.serviceImplTest;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.ingeneo_technical_test.repository.WarehouseRepository;
import com.example.ingeneo_technical_test.service.Implement.WarehouseServiceImpl;
import com.example.ingeneo_technical_test.DataSet;
import com.example.ingeneo_technical_test.entity.Warehouse;
import com.example.ingeneo_technical_test.entity.dto.WarehouseDTO;
import com.example.ingeneo_technical_test.entity.dto.converter.WarehouseDTOConverter;
/**
 * @author Johan Casagua
 *
 */
class WarehouseServiceImplTest {
	@Mock
    private WarehouseRepository warehouseRepository;

    @InjectMocks
    private WarehouseServiceImpl warehouseService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testListAllWarehouse() {
        // Arrange
        List<Warehouse> warehouses = new ArrayList<>();
        warehouses.add(WarehouseDTOConverter.convertToEntity(DataSet.getWarehouse()));
        warehouses.add(WarehouseDTOConverter.convertToEntity(DataSet.getWarehouse()));
        when(warehouseRepository.findAll()).thenReturn(warehouses);

        // Act
        List<WarehouseDTO> warehouseDTOs = warehouseService.listAllWarehouse();

        // Assert
        assertEquals(2, warehouseDTOs.size());
        verify(warehouseRepository, times(1)).findAll();
    }

    @Test
    void testCreateWarehouse() {
        // Arrange
        WarehouseDTO warehouseDTO = DataSet.getWarehouse();
        Warehouse warehouse = WarehouseDTOConverter.convertToEntity(warehouseDTO);
        when(warehouseRepository.save(any(Warehouse.class))).thenReturn(warehouse);

        // Act
        WarehouseDTO savedWarehouseDTO = warehouseService.createWarehouse(warehouseDTO);

        // Assert
        assertEquals(warehouseDTO.getName(), savedWarehouseDTO.getName());
        verify(warehouseRepository, times(1)).save(any(Warehouse.class));
    }
}
