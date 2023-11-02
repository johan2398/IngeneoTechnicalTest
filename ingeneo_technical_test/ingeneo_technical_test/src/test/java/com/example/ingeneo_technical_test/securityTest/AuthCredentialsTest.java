/**
 * 
 */
package com.example.ingeneo_technical_test.securityTest;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import com.example.ingeneo_technical_test.security.AuthCredentials;

/**
 * @author Johan Casagua
 *
 */
public class AuthCredentialsTest {

	@Test
    void testAuthCredentials() {
        // Arrange
        AuthCredentials authCredentials = new AuthCredentials();
        authCredentials.setEmail("test@example.com");
        authCredentials.setPassword("password123");

        // Act & Assert
        assertEquals("test@example.com", authCredentials.getEmail());
        assertEquals("password123", authCredentials.getPassword());
    }
	
	@Test
	void testAuthCredentialsWithNullValues() {
	    // Arrange
	    AuthCredentials authCredentials = new AuthCredentials();
	    authCredentials.setEmail(null);
	    authCredentials.setPassword(null);

	    // Act & Assert
	    assertNull(authCredentials.getEmail());
	    assertNull(authCredentials.getPassword());
	}
	
	@Test
	void testAuthCredentialsWithEmptyValues() {
	    // Arrange
	    AuthCredentials authCredentials = new AuthCredentials();
	    authCredentials.setEmail("");
	    authCredentials.setPassword("");

	    // Act & Assert
	    assertEquals("", authCredentials.getEmail());
	    assertEquals("", authCredentials.getPassword());
	}

	@Test
	void testAuthCredentialsWithValidValues() {
	    // Arrange
	    AuthCredentials authCredentials = new AuthCredentials();
	    authCredentials.setEmail("test@example.com");
	    authCredentials.setPassword("password123");

	    // Act & Assert
	    assertEquals("test@example.com", authCredentials.getEmail());
	    assertEquals("password123", authCredentials.getPassword());
	}

	@Test
	void testEqualsAndHashCode() {
	    // Arrange
	    AuthCredentials authCredentials1 = new AuthCredentials();
	    authCredentials1.setEmail("test@example.com");
	    authCredentials1.setPassword("password123");

	    AuthCredentials authCredentials2 = new AuthCredentials();
	    authCredentials2.setEmail("test@example.com");
	    authCredentials2.setPassword("password123");

	    // Act & Assert
	    assertEquals(authCredentials1, authCredentials2);
	    assertEquals(authCredentials1.hashCode(), authCredentials2.hashCode());
	}


}
