/**
 * 
 */
package com.example.ingeneo_technical_test.entity.dto.converter;

import org.springframework.stereotype.Component;

import com.example.ingeneo_technical_test.entity.Client;
import com.example.ingeneo_technical_test.entity.dto.ClientDTO;

/**
 * @author Johan Casagua
 *
 */
@Component
public class ClientDTOConverter {

	public static ClientDTO convertToDTO(Client client) {
		ClientDTO clientDTO = new ClientDTO();
		clientDTO.setId(client.getId());
		clientDTO.setName(client.getName());
		clientDTO.setLastName(client.getLastName());
		clientDTO.setPassword(client.getPassword());
		clientDTO.setEmail(client.getEmail());
		clientDTO.setAddress(client.getAddress());
		clientDTO.setState(client.getState());
		clientDTO.setIdentification(client.getIdentification());
		clientDTO.setUsername(client.getUsername());
		return clientDTO;
	}
	
	public static Client convertToEntity(ClientDTO clientDTO) {
		Client client = new Client();
		client.setId(clientDTO.getId());
		client.setName(clientDTO.getName());
		client.setLastName(clientDTO.getLastName());
		client.setPassword(clientDTO.getPassword());
		client.setEmail(clientDTO.getEmail());
		client.setAddress(clientDTO.getAddress());
		client.setState(clientDTO.getState());
		client.setIdentification(clientDTO.getIdentification());
		client.setUsername(clientDTO.getUsername());
		return client;
	}
}
