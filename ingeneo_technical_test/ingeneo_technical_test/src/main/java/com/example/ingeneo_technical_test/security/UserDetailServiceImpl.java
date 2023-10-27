/**
 * 
 */
package com.example.ingeneo_technical_test.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.ingeneo_technical_test.entity.User;
import com.example.ingeneo_technical_test.repository.UserRepository;

/**
 * @author Johan Casagua
 *
 */
@Service
public class UserDetailServiceImpl implements UserDetailsService{

	@Autowired
	UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		User user = userRepository
			.findOneByEmail(email)
			.orElseThrow(() -> new UsernameNotFoundException("El usuario con email " + email + " no existe"));
		return new UserDetailsImpl(user);
	}
	
}
