/**
 * 
 */
package com.example.ingeneo_technical_test.entity.dto.converter;

import org.springframework.stereotype.Component;

import com.example.ingeneo_technical_test.entity.Warehouse;
import com.example.ingeneo_technical_test.entity.dto.WarehouseDTO;

/**
 * @author Johan Casagua
 *
 */
@Component
public class WarehouseDTOConverter {

	public static WarehouseDTO convertToDTO(Warehouse warehouse) {
		WarehouseDTO warehouseDTO = new WarehouseDTO();
		warehouseDTO.setId(warehouse.getId());
		warehouseDTO.setLocation(warehouse.getLocation());
		warehouseDTO.setName(warehouse.getName());
		warehouseDTO.setStorageCapacity(warehouse.getStorageCapacity());
		warehouseDTO.setType(warehouse.getType());
		warehouseDTO.setPort(warehouse.getPort());
		warehouseDTO.setStore(warehouse.getStore());
		return warehouseDTO;
	}
	
	public static Warehouse convertToEntity(WarehouseDTO warehouseDTO) {
		Warehouse warehouse = new Warehouse();
		warehouse.setId(warehouseDTO.getId());
		warehouse.setLocation(warehouseDTO.getLocation());
		warehouse.setName(warehouseDTO.getName());
		warehouse.setStorageCapacity(warehouseDTO.getStorageCapacity());
		warehouse.setType(warehouseDTO.getType());
		warehouse.setPort(warehouseDTO.getPort());
		warehouse.setStore(warehouseDTO.getStore());
		return warehouse;
	}
}
