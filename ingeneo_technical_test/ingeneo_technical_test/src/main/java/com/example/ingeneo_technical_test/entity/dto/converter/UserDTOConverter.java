/**
 * 
 */
package com.example.ingeneo_technical_test.entity.dto.converter;

import org.springframework.stereotype.Component;

import com.example.ingeneo_technical_test.entity.Client;
import com.example.ingeneo_technical_test.entity.User;
import com.example.ingeneo_technical_test.entity.dto.ClientDTO;
import com.example.ingeneo_technical_test.entity.dto.UserDTO;

/**
 * @author Johan Casagua
 *
 */
@Component
public class UserDTOConverter {

	public static UserDTO convertToDTO(User user) {
		UserDTO userDTO = new UserDTO();
		userDTO.setId(user.getId());
		userDTO.setUserName(user.getUserName());
		userDTO.setPassword(user.getPassword());
		userDTO.setEmail(user.getEmail());
		return userDTO;
	}
	
	public static User convertToEntity(UserDTO userDTO) {
		User user = new User();
		user.setId(userDTO.getId());
		user.setUserName(userDTO.getUserName());
		user.setPassword(userDTO.getPassword());
		user.setEmail(userDTO.getEmail());
		return user;
	}
}
