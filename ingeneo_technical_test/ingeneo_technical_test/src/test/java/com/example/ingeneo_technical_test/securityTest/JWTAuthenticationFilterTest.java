/**
 * 
 */
package com.example.ingeneo_technical_test.securityTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.IOException;
import java.io.StringReader;
import java.util.Collections;

import com.example.ingeneo_technical_test.security.UserDetailsImpl;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.Map;
import java.util.HashMap;
import java.io.StringWriter;
import java.io.PrintWriter;
import java.io.BufferedReader;

import com.example.ingeneo_technical_test.DataSet;
import com.example.ingeneo_technical_test.security.AuthCredentials;
import com.example.ingeneo_technical_test.security.JWTAuthenticationFilter;
import com.fasterxml.jackson.databind.ObjectMapper;
/**
 * @author Johan Casagua
 *
 */
class JWTAuthenticationFilterTest {
	
    @InjectMocks
    private JWTAuthenticationFilter filter;

    @Mock
    private AuthenticationManager authenticationManager;

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @Mock
    private FilterChain chain;
	
	@Test
    void attemptAuthenticationTest() throws IOException {
        // Arrange
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        FilterChain chain = mock(FilterChain.class);

        AuthCredentials authCredentials = new AuthCredentials();
        authCredentials.setEmail("test@example.com");
        authCredentials.setPassword("password");

        String authCredentialsJson = new ObjectMapper().writeValueAsString(authCredentials);
        BufferedReader reader = new BufferedReader(new StringReader(authCredentialsJson));

        when(request.getReader()).thenReturn(reader);

        JWTAuthenticationFilter filter = new JWTAuthenticationFilter();
        filter.setAuthenticationManager(mock(AuthenticationManager.class));

        // Act
        Authentication authentication = filter.attemptAuthentication(request, response);

        // Assert
        assertNull(authentication);
    }

    @Test
    void successfulAuthenticationTest() throws IOException, ServletException {
        // Arrange
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        FilterChain chain = mock(FilterChain.class);
        UserDetailsImpl userDetails = new UserDetailsImpl(DataSet.getUserEntity());

        Authentication authResult = new UsernamePasswordAuthenticationToken(userDetails, null);

        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(writer);

        JWTAuthenticationFilter filter = new JWTAuthenticationFilter();
        filter.setAuthenticationManager(mock(AuthenticationManager.class));

        // Act
        filter.successfulAuthentication(request, response, chain, authResult);

        // Assert
        verify(response, times(1)).setContentType("application/json");
        verify(response, times(1)).setCharacterEncoding("UTF-8");
        verify(response, times(1)).addHeader(eq("Authorization"), anyString());
    }

}
