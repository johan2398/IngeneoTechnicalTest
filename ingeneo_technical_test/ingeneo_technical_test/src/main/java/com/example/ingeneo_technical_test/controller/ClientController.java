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

import com.example.ingeneo_technical_test.entity.dto.ClientDTO;
import com.example.ingeneo_technical_test.entity.dto.ShipDTO;
import com.example.ingeneo_technical_test.service.ClientService;

/**
 * @author Johan Casagua
 */
@RestController
@RequestMapping(value = "/clients")
public class ClientController {
	
	@Autowired
	private ClientService clientService;
	
	@GetMapping
	public ResponseEntity<List<ClientDTO>> listClient() {
		List<ClientDTO> clients = clientService.listAllClients();
		
		if(clients.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(clients);
	}
	@PostMapping
    public ResponseEntity<ClientDTO> createClient(@RequestBody ClientDTO clientDTO) {
        if (clientDTO == null) {
            return ResponseEntity.badRequest().build();
        }

        ClientDTO createdClient = clientService.createClient(clientDTO);

        if (createdClient != null) {
        	// Returns a response with the DTO of the created client and the code 201 (CREATED)            
        	return ResponseEntity.status(HttpStatus.CREATED).body(createdClient);
        } else {
        	// If the client could not be created, returns a 400 BAD REQUEST response code
        	return ResponseEntity.badRequest().build();
        }
    }
	
	
}
