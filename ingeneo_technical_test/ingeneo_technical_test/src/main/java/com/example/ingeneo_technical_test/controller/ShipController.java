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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.ingeneo_technical_test.entity.dto.ShipDTO;
import com.example.ingeneo_technical_test.service.ShipService;

/**
 * @author Johan Casagua
 *
 */
@RestController
@RequestMapping(value = "/ships")
public class ShipController {

	@Autowired
	ShipService shipService;
	
	@GetMapping
	public ResponseEntity<List<ShipDTO>> listShips() {
		List<ShipDTO> ships = shipService.listAllShips();
		
		if(ships.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(ships);
	}
	
	@GetMapping("/filters")
	public ResponseEntity<List<ShipDTO>> filterByDocIdClientOrGuideNumber(@RequestParam String search) {
        List<ShipDTO> filteredShips = shipService.filterByDocIdClientOrGuideNumber(search);

        if (filteredShips.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(filteredShips);
    }
	
	@PostMapping
    public ResponseEntity<ShipDTO> createShipment(@RequestBody ShipDTO shipDTO) {
        if (shipDTO == null) {
            return ResponseEntity.badRequest().build();
        }

        ShipDTO createdShip = shipService.createShip(shipDTO);

        if (createdShip != null) {
        	// Returns a response with the DTO of the created client and the code 201 (CREATED)            
        	return ResponseEntity.status(HttpStatus.CREATED).body(createdShip);
        } else {
        	// If the client could not be created, returns a 400 BAD REQUEST response code
        	return ResponseEntity.badRequest().build();
        }
    }
	
}
