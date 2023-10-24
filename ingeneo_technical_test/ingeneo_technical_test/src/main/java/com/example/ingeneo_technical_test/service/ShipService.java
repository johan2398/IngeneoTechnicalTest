/**
 * 
 */
package com.example.ingeneo_technical_test.service;

import java.util.List;

import com.example.ingeneo_technical_test.entity.dto.ShipDTO;

/**
 * @author Johan Casagua
 *
 */
public interface ShipService {
	public List<ShipDTO> listAllShips();
	
	public ShipDTO createShip(ShipDTO ShipDTO);
}
