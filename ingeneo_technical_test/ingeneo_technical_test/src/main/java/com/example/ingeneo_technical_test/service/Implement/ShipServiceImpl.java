/**
 * 
 */
package com.example.ingeneo_technical_test.service.Implement;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.UUID;
import java.util.Random;
import java.security.SecureRandom;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ingeneo_technical_test.entity.Client;
import com.example.ingeneo_technical_test.entity.Ship;
import com.example.ingeneo_technical_test.entity.Store;
import com.example.ingeneo_technical_test.entity.Port;
import com.example.ingeneo_technical_test.entity.dto.ShipDTO;
import com.example.ingeneo_technical_test.entity.dto.converter.ClientDTOConverter;
import com.example.ingeneo_technical_test.entity.dto.converter.ShipDTOConverter;
import com.example.ingeneo_technical_test.enumerations.ShipState;
import com.example.ingeneo_technical_test.enumerations.WarehouseType;
import com.example.ingeneo_technical_test.repository.ClientRepository;
import com.example.ingeneo_technical_test.repository.ShipRepository;
import com.example.ingeneo_technical_test.service.ClientService;
import com.example.ingeneo_technical_test.service.ShipService;

/**
 * @author Johan Casagua
 *
 */
@Service
public class ShipServiceImpl implements ShipService{

	private static SecureRandom random = new SecureRandom();
	
	@Autowired
	ShipRepository shipRepository;
	
	@Autowired
	ClientRepository clientRepository;
	
	@Autowired
	ClientService clientService;
	
	@Override
	public List<ShipDTO> listAllShips() {
		List<Ship> ships = shipRepository.findAll();
		return ships.stream()
				.map(ShipDTOConverter::convertToDTO)
				.collect(Collectors.toList());
	}

	@Override
	public List<ShipDTO> filterByDocIdClientOrGuideNumber(String search) {
		List<Ship> ships = shipRepository.findByClientDocIdOrGuideNumberContainingIgnoreCase(search);
		return ships.stream()
				.map(ShipDTOConverter::convertToDTO)
				.collect(Collectors.toList());
	}
	
	@Override
	public ShipDTO createShip(ShipDTO ShipDTO) {
		//Create the guide number
		// Generate a random UUID
        UUID uuid = UUID.randomUUID();
        // Convert the UUID to a string and take the first 10 characters
        String shortUuid = uuid.toString().replace("-", "").substring(0, 10);
        ShipDTO.setGuideNumber(shortUuid.toUpperCase());
        
        /**
         * Create pricing 
         * Land logistic -> 20000 & maritime logistic -> 50000
         */
        if(ShipDTO.getWarehouse().getType() == WarehouseType.STORE) {
        	ShipDTO.setShippingPrice(20000);
        } else if (ShipDTO.getWarehouse().getType() == WarehouseType.PORT){
        	ShipDTO.setShippingPrice(50000);
        }
        /**
         * Query discount
         * If there are more than 10 products apply discount
         * 5% for land and 3% for maritime
         * Save both prices
         */
//        if(ShipDTO.getProduct().getQuantity() > 10) { 
//        	float price = ShipDTO.getShippingPrice();
//        	if(ShipDTO.getWarehouse().getType() == WarehouseType.STORE) {
//        		float discount = (float)(price - (price * 0.05));
//        		ShipDTO.setShippingPriceWithDiscount(discount);
//        	} else if(ShipDTO.getWarehouse().getType() == WarehouseType.PORT){
//        		float discount = (float)(price - (price * 0.03));
//        		ShipDTO.setShippingPriceWithDiscount(discount);
//        	}
//        }
        if(ShipDTO.getProduct().getQuantity() > 10) { 
            float originalPrice = ShipDTO.getShippingPrice();
            float discountRate = 0.05f; // Por defecto, se aplica un descuento del 5%

            if (ShipDTO.getWarehouse().getType() == WarehouseType.PORT) {
                discountRate = 0.03f; // Si el almacén es de tipo PORT, se aplica un descuento del 3%
            }

            float discount = originalPrice * discountRate;
            float discountedPrice = originalPrice - discount;
            
            ShipDTO.setShippingPriceWithDiscount(discountedPrice);
        } else {
        	ShipDTO.setShippingPriceWithDiscount(ShipDTO.getShippingPrice());
        }

        
        /**
         * Create registry date
         */
        LocalDate currentDate = LocalDate.now();
		Date date = Date.from(currentDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
		ShipDTO.setRegistryDate(date);
		
		/*
		 * Create delivery date
		 */
		LocalDate deliveryDate = null;
		if(ShipDTO.getWarehouse().getType() == WarehouseType.STORE) {
			deliveryDate = currentDate.plusDays(3);
        } else if (ShipDTO.getWarehouse().getType() == WarehouseType.PORT){
        	deliveryDate = currentDate.plusDays(8);
        }
        Date deliveryDateAsDate = Date.from(deliveryDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
		ShipDTO.setDeliveryDate(deliveryDateAsDate);
        
		/**
		 * Search client if doesn't exist creating
		 */
		var clientSearched = clientRepository.findByIdentification(ShipDTO.getClient().getIdentification());
		if(clientSearched != null) { //If exist
			var clientConvertDTO = ClientDTOConverter.convertToDTO(clientSearched);
			ShipDTO.getClient().setId(clientConvertDTO.getId());
			ShipDTO.getClient().setName(clientConvertDTO.getName());
			ShipDTO.getClient().setLastName(clientConvertDTO.getLastName());
			ShipDTO.getClient().setEmail(clientConvertDTO.getEmail());
			ShipDTO.getClient().setAddress(clientConvertDTO.getAddress());
			ShipDTO.getClient().setState(clientConvertDTO.getState());
			ShipDTO.getClient().setIdentification(clientConvertDTO.getIdentification());
		} else { //If doesn't exist creating
			clientService.createClient(ClientDTOConverter.convertToDTO(ShipDTO.getClient()));
		}
		
		/**
		 * Save the logistic type
		 */
		//Create the guide number
		// Generate a random UUID
		UUID uuidWarehouse = UUID.randomUUID();
        String uuidPart = uuid.toString().substring(0, 4);
        int randNum = Math.abs(uuidPart.hashCode() % 9000) + 1000;
		String shortuuidWarehouse = Integer.toString(randNum).replace("-", "");
		
		ShipDTO.getWarehouse().setName(ShipDTO.getWarehouse().getType().toString());
		if(ShipDTO.getWarehouse().getType() == WarehouseType.STORE) {
			ShipDTO.getWarehouse().setStore(new Store());
			ShipDTO.getWarehouse().getStore().setVehiclePlate(generateRandomLetters() + shortuuidWarehouse.substring(0, 3));;
		} else if(ShipDTO.getWarehouse().getType() == WarehouseType.PORT) {
			ShipDTO.getWarehouse().setPort(new Port());
			ShipDTO.getWarehouse().getPort()
			.setFleetNumber(generateRandomLetters() + shortuuidWarehouse +  generateRandomLetters().substring(0,1));
		}
		
		/**
		 * Save state
		 */
		ShipDTO.setState(ShipState.REGISTRIED);
		
		//Save Shipment
		Ship ship = ShipDTOConverter.convertToEntity(ShipDTO);
		Ship savedShip = shipRepository.save(ship);
	    return ShipDTOConverter.convertToDTO(savedShip);
	}
	
    public static String generateRandomLetters() {
        String caracteres = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        StringBuilder sb = new StringBuilder();

        // Generar 3 random letters
        for (int i = 0; i < 3; i++) {
            int index = random.nextInt(caracteres.length());
            char letra = caracteres.charAt(index);
            sb.append(letra);
        }

        return sb.toString();
    }

}
