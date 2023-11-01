/**
 * 
 */
package com.example.ingeneo_technical_test.controller;

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
import com.example.ingeneo_technical_test.entity.dto.UserDTO;
import com.example.ingeneo_technical_test.service.UserService;

/**
 * @author Johan Casagua
 *
 */
@RestController
@RequestMapping(value = "/users")
public class UserController {
	@Autowired
	private UserService userService;
	
	@GetMapping("/usernames")
	public ResponseEntity<UserDTO> findByUsernameAndPassword(@RequestParam String userName, @RequestParam String password) {
        UserDTO userFound = userService.findByUsernameAndPassword(userName, password);

        if (userFound == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(userFound);
	}
	
	@PostMapping
    public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO userDTO) {
        if (userDTO == null) {
            return ResponseEntity.badRequest().build();
        }

        UserDTO createdUser = userService.createUser(userDTO);

        if (createdUser != null) {
        	// Returns a response with the DTO of the created client and the code 201 (CREATED)            
        	return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
        } else {
        	// If the client could not be created, returns a 400 BAD REQUEST response code
        	return ResponseEntity.badRequest().build();
        }
    }
}
