/**
 * 
 */
package com.example.ingeneo_technical_test.service.Implement;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ingeneo_technical_test.entity.Client;
import com.example.ingeneo_technical_test.entity.dto.ClientDTO;
import com.example.ingeneo_technical_test.entity.dto.converter.ClientDTOConverter;
import com.example.ingeneo_technical_test.enumerations.ClientState;
import com.example.ingeneo_technical_test.repository.ClientRepository;
import com.example.ingeneo_technical_test.service.ClientService;

/**
 * @author Johan Casagua
 *
 */
@Service
public class ClientServiceImpl implements ClientService{

	@Autowired
	private ClientRepository clientRepository;
	
	@Override
	public List<ClientDTO> listAllClients() {
		List<Client> clients = clientRepository.findAll();
		return clients.stream()
				.map(ClientDTOConverter::convertToDTO)
				.collect(Collectors.toList());
	}

	@Override
	public ClientDTO createClient(ClientDTO clientDTO) {
		Client client = ClientDTOConverter.convertToEntity(clientDTO);
		client.setState(ClientState.Created);
		Client savedClient = clientRepository.save(client);
	    return ClientDTOConverter.convertToDTO(savedClient);
	}

}
