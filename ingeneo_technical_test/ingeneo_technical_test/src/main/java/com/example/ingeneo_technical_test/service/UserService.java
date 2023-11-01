/**
 * 
 */
package com.example.ingeneo_technical_test.service;

import com.example.ingeneo_technical_test.entity.dto.UserDTO;

/**
 * @author Johan Casagua
 *
 */
public interface UserService {

	public UserDTO findByUsernameAndPassword(String userName, String password);

	public UserDTO createUser(UserDTO userDTO);
}
