/**
 * 
 */
package com.example.ingeneo_technical_test.entityTest;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.example.ingeneo_technical_test.entity.UserLoginRequest;

import org.junit.jupiter.api.Test;
/**
 * @author Johan Casagua
 *
 */
public class UserLoginTest {
	@Test
    void testUserLoginRequestAttributes() {
        // Arrange
        UserLoginRequest userLoginRequest = new UserLoginRequest();
        userLoginRequest.setUsername("testuser");
        userLoginRequest.setPassword("password123");

        // Act & Assert
        assertEquals("testuser", userLoginRequest.getUsername());
        assertEquals("password123", userLoginRequest.getPassword());
    }
}
