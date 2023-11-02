/**
 * 
 */
package com.example.ingeneo_technical_test.service.Implement;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ingeneo_technical_test.entity.Warehouse;
import com.example.ingeneo_technical_test.entity.dto.WarehouseDTO;
import com.example.ingeneo_technical_test.entity.dto.converter.WarehouseDTOConverter;
import com.example.ingeneo_technical_test.repository.WarehouseRepository;
import com.example.ingeneo_technical_test.service.WarehouseService;

/**
 * @author Johan Casagua
 *
 */
@Service
public class WarehouseServiceImpl implements WarehouseService{

	@Autowired
	private WarehouseRepository warehouseRepository;
	
	@Override
	public List<WarehouseDTO> listAllWarehouse() {
		List<Warehouse> warehouses = warehouseRepository.findAll();
		return warehouses.stream()
				.map(WarehouseDTOConverter::convertToDTO)
				.collect(Collectors.toList());
	}

	@Override
	public WarehouseDTO createWarehouse(WarehouseDTO warehouseDTO) {
		Warehouse warehouse = WarehouseDTOConverter.convertToEntity(warehouseDTO);
		Warehouse savedWarehouse = warehouseRepository.save(warehouse);
	    return WarehouseDTOConverter.convertToDTO(savedWarehouse);
	}

}
