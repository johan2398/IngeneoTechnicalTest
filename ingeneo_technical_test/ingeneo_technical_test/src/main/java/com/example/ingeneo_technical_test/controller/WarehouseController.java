/**
 * 
 */
package com.example.ingeneo_technical_test.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ingeneo_technical_test.entity.dto.WarehouseDTO;
import com.example.ingeneo_technical_test.service.WarehouseService;

/**
 * @author Johan Casagua
 */
@RestController
@RequestMapping(value = "/warehouses")
public class WarehouseController {

	@Autowired
	WarehouseService warehouseService;
	
	@GetMapping
	public ResponseEntity<List<WarehouseDTO>> listWarehouse() {
		List<WarehouseDTO> warehouses = warehouseService.listAllWarehouse();
		
		if(warehouses.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(warehouses);
	}
	
	@PostMapping
    public ResponseEntity<WarehouseDTO> createWarehouse(@RequestBody WarehouseDTO warehouseDTO) {
        if (warehouseDTO == null) {
            return ResponseEntity.badRequest().build();
        }

        WarehouseDTO createdWarehouse = warehouseService.createWarehouse(warehouseDTO);

        if (createdWarehouse != null) {
        	// Returns a response with the DTO of the created client and the code 201 (CREATED)            
        	return ResponseEntity.status(HttpStatus.CREATED).body(createdWarehouse);
        } else {
        	// If the client could not be created, returns a 400 BAD REQUEST response code
        	return ResponseEntity.badRequest().build();
        }
    }
	
}
