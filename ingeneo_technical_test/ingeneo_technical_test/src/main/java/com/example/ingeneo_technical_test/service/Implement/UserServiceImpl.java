/**
 * 
 */
package com.example.ingeneo_technical_test.service.Implement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ingeneo_technical_test.entity.User;
import com.example.ingeneo_technical_test.entity.dto.ClientDTO;
import com.example.ingeneo_technical_test.entity.dto.UserDTO;
import com.example.ingeneo_technical_test.entity.dto.converter.ClientDTOConverter;
import com.example.ingeneo_technical_test.entity.dto.converter.UserDTOConverter;
import com.example.ingeneo_technical_test.repository.ClientRepository;
import com.example.ingeneo_technical_test.repository.UserRepository;
import com.example.ingeneo_technical_test.service.UserService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @author Johan Casagua
 *
 */
@Service
public class UserServiceImpl implements UserService{
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public UserDTO findByUsernameAndPassword(String userName, String password) {
		var userUnique = userRepository.findByUsernameAndPassword(userName, password);
		if(userUnique != null) {
			return UserDTOConverter.convertToDTO(userUnique);
		} else {
			return null;
		}
	}

	@Override
	public UserDTO createUser(UserDTO userDTO) {
		BCryptPasswordEncoder passSafety = new BCryptPasswordEncoder();
		userDTO.setPassword(passSafety.encode(userDTO.getPassword()));
		User userSaved = userRepository.save(UserDTOConverter.convertToEntity(userDTO));
		return UserDTOConverter.convertToDTO(userSaved);
	}
}
