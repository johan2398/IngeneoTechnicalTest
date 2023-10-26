/**
 * 
 */
package com.example.ingeneo_technical_test.service.Implement;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ingeneo_technical_test.entity.Client;
import com.example.ingeneo_technical_test.entity.Ship;
import com.example.ingeneo_technical_test.entity.dto.ShipDTO;
import com.example.ingeneo_technical_test.entity.dto.converter.ShipDTOConverter;
import com.example.ingeneo_technical_test.repository.ClientRepository;
import com.example.ingeneo_technical_test.repository.ShipRepository;
import com.example.ingeneo_technical_test.service.ShipService;

/**
 * @author Johan Casagua
 *
 */
@Service
public class ShipServiceImpl implements ShipService{

	@Autowired
	ShipRepository shipRepository;
	
	@Autowired
	ClientRepository clientRepository;
	
	@Override
	public List<ShipDTO> listAllShips() {
		List<Ship> ships = shipRepository.findAll();
		return ships.stream()
				.map(ShipDTOConverter::convertToDTO)
				.collect(Collectors.toList());
	}

	@Override
	public ShipDTO createShip(ShipDTO ShipDTO) {
		Ship ship = ShipDTOConverter.convertToEntity(ShipDTO);
		Client client = clientRepository.findById(ship.getClient().getId()).orElse(null);
		ship.setClient(client);
		Ship savedShip = shipRepository.save(ship);
	    return ShipDTOConverter.convertToDTO(savedShip);
	}

	@Override
	public List<ShipDTO> filterByDocIdClientOrGuideNumber(String search) {
		List<Ship> ships = shipRepository.findByClientDocIdOrGuideNumberContainingIgnoreCase(search);
		return ships.stream()
				.map(ShipDTOConverter::convertToDTO)
				.collect(Collectors.toList());
	}

}
