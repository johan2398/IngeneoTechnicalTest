/**
 * 
 */
package com.example.ingeneo_technical_test.security;

import java.io.IOException;
import java.util.Collections;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.Map;
import java.util.HashMap;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author Johan Casagua
 *
 */
public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter{
	
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, 
												HttpServletResponse response) throws AuthenticationException {
		
		AuthCredentials authCredentials = new AuthCredentials();
		
		try {
			authCredentials = new ObjectMapper().readValue(request.getReader(), AuthCredentials.class);
		} catch (IOException e) {
		}
		
		UsernamePasswordAuthenticationToken userNamePAT = new UsernamePasswordAuthenticationToken(
				authCredentials.getEmail(),
				authCredentials.getPassword(),
				Collections.emptyList()
		);
		
		return getAuthenticationManager().authenticate(userNamePAT);
	}
	
	@Override
	protected void successfulAuthentication(HttpServletRequest request,
											HttpServletResponse response,
											FilterChain chain,
											Authentication authResult) throws IOException, ServletException {
		UserDetailsImpl userDetails = (UserDetailsImpl) authResult.getPrincipal();
		String token = TokenUtils.createToken(userDetails.getName(), userDetails.getUsername());
		
		 // Agregar el token al cuerpo de la respuesta
	    Map<String, Object> responseBody = new HashMap<>();
	    responseBody.put("token", token);

	    // Convertir el cuerpo de respuesta a formato JSON
	    ObjectMapper objectMapper = new ObjectMapper();
	    String responseBodyJson = objectMapper.writeValueAsString(responseBody);

	    // Establecer el cuerpo de la respuesta y los encabezados necesarios
	    response.setContentType("application/json");
	    response.setCharacterEncoding("UTF-8");
		response.addHeader("Authorization", "Bearer " + token);
		response.getWriter().write(responseBodyJson);
		response.getWriter().flush();
		super.successfulAuthentication(request, response, chain, authResult);
	}

}
