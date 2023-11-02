/**
 * 
 */
package com.example.ingeneo_technical_test.securityTest;


import com.example.ingeneo_technical_test.entity.User;
import com.example.ingeneo_technical_test.security.UserDetailsImpl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

/**
 * @author Johan Casagua
 *
 */
public class UserDetailsImplTest {
	@Mock
    private User mockUser;

    @InjectMocks
    private UserDetailsImpl userDetails;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testGetUsername() {
        // Arrange
        when(mockUser.getEmail()).thenReturn("test@example.com");

        // Act & Assert
        assertEquals("test@example.com", userDetails.getUsername());
    }

    @Test
    void testGetPassword() {
        // Arrange
        when(mockUser.getPassword()).thenReturn("password123");

        // Act & Assert
        assertEquals("password123", userDetails.getPassword());
    }

    @Test
    void testGetName() {
        // Arrange
        when(mockUser.getName()).thenReturn("Test User");

        // Act & Assert
        assertEquals("Test User", userDetails.getName());
    }

    @Test
    void testGetAuthorities() {
        // Act & Assert
        assertEquals(Collections.emptyList(), userDetails.getAuthorities());
    }
}
