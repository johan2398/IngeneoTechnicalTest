/**
 * 
 */
package com.example.ingeneo_technical_test.entity.dto.converter;

import org.springframework.stereotype.Component;

import com.example.ingeneo_technical_test.entity.Ship;
import com.example.ingeneo_technical_test.entity.dto.ShipDTO;

/**
 * @author Johan Casagua
 *
 */
@Component
public class ShipDTOConverter {

	public static ShipDTO convertToDTO(Ship ship) {
	    ShipDTO shipDTO = new ShipDTO();
	    shipDTO.setId(ship.getId());
	    shipDTO.setProduct(ship.getProduct());
	    shipDTO.setRegistryDate(ship.getRegistryDate());
	    shipDTO.setDeliveryDate(ship.getDeliveryDate());
	    shipDTO.setShippingPrice(ship.getShippingPrice());
	    shipDTO.setGuideNumber(ship.getGuideNumber());
	    shipDTO.setClient(ship.getClient());
	    shipDTO.setWarehouse(ship.getWarehouse());
	    shipDTO.setDiscount(ship.getDiscount());
	    return shipDTO;
	}

	public static Ship convertToEntity(ShipDTO shipDTO) {
	    Ship ship = new Ship();
	    ship.setId(shipDTO.getId());
	    ship.setProduct(shipDTO.getProduct());
	    ship.setRegistryDate(shipDTO.getRegistryDate());
	    ship.setDeliveryDate(shipDTO.getDeliveryDate());
	    ship.setShippingPrice(shipDTO.getShippingPrice());
	    ship.setGuideNumber(shipDTO.getGuideNumber());
	    ship.setClient(shipDTO.getClient());
	    ship.setWarehouse(shipDTO.getWarehouse());
	    ship.setDiscount(shipDTO.getDiscount());
	    return ship;
	}

}
