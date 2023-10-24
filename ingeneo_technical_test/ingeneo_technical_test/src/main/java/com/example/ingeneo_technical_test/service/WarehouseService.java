/**
 * 
 */
package com.example.ingeneo_technical_test.service;

import java.util.List;

import com.example.ingeneo_technical_test.entity.dto.WarehouseDTO;

/**
 * @author Johan Casagua
 *
 */
public interface WarehouseService {

	public List<WarehouseDTO> listAllWarehouse();
	
	public WarehouseDTO createWarehouse(WarehouseDTO warehouse);
}
