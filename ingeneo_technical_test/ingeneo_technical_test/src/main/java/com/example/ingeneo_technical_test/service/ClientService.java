/**
 * 
 */
package com.example.ingeneo_technical_test.service;
import java.util.List;

import com.example.ingeneo_technical_test.entity.dto.ClientDTO;

/**
 * @author Johan Casagua
 *
 */
public interface ClientService {

	public List<ClientDTO> listAllClients();
	
	public ClientDTO createClient(ClientDTO client);
	
}
