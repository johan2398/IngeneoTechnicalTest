/**
 * 
 */
package com.example.ingeneo_technical_test.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
}
