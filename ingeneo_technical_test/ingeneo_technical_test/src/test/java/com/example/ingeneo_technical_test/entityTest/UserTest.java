/**
 * 
 */
package com.example.ingeneo_technical_test.entityTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.example.ingeneo_technical_test.entity.User;
/**
 * @author Johan Casagua
 *
 */
public class UserTest {
	@Test
    void testUserAttributes() {
        // Arrange
        User user = new User();
        user.setId(1L);
        user.setUserName("testuser");
        user.setPassword("password123");
        user.setEmail("testuser@example.com");
        user.setName("Test User");

        // Act & Assert
        assertEquals(1L, user.getId());
        assertEquals("testuser", user.getUserName());
        assertEquals("password123", user.getPassword());
        assertEquals("testuser@example.com", user.getEmail());
        assertEquals("Test User", user.getName());
    }

    @Test
    void testUserBuilder() {
        // Arrange & Act
        User user = User.builder()
            .id(1L)
            .userName("testuser")
            .password("password123")
            .email("testuser@example.com")
            .name("Test User")
            .build();

        // Assert
        assertEquals(1L, user.getId());
        assertEquals("testuser", user.getUserName());
        assertEquals("password123", user.getPassword());
        assertEquals("testuser@example.com", user.getEmail());
        assertEquals("Test User", user.getName());
    }
}
