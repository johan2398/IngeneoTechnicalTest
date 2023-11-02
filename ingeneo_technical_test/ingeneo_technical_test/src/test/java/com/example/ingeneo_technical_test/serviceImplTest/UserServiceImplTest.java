/**
 * 
 */
package com.example.ingeneo_technical_test.serviceImplTest;


import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.ingeneo_technical_test.repository.UserRepository;
import com.example.ingeneo_technical_test.service.Implement.UserServiceImpl;
import com.example.ingeneo_technical_test.entity.User;
import com.example.ingeneo_technical_test.entity.dto.UserDTO;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.*;
/**
 * @author Johan Casagua
 *
 */
@SpringBootTest
class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;

    @Test
    void testFindByUsernameAndPassword() {
        // Arrange
        String username = "testUser";
        String password = "testPassword";
        User user = new User();
        when(userRepository.findByUsernameAndPassword(username, password)).thenReturn(user);

        // Act
        UserDTO result = userService.findByUsernameAndPassword(username, password);

        // Assert
        assertNotNull(result);
        verify(userRepository, times(1)).findByUsernameAndPassword(username, password);
    }

    @Test
    void testFindByUsernameAndPasswordUserNotFound() {
        // Arrange
        String username = "nonExistentUser";
        String password = "testPassword";
        when(userRepository.findByUsernameAndPassword(username, password)).thenReturn(null);

        // Act
        UserDTO result = userService.findByUsernameAndPassword(username, password);

        // Assert
        assertNull(result);
        verify(userRepository, times(1)).findByUsernameAndPassword(username, password);
    }

    @Test
    void testCreateUser() {
        // Arrange
        UserDTO userDTO = new UserDTO();
        userDTO.setUserName("testUser");
        userDTO.setPassword("testPassword");
        when(userRepository.save(any(User.class))).thenReturn(new User());

        // Act
        UserDTO result = userService.createUser(userDTO);

        // Assert
        assertNotNull(result);
        verify(userRepository, times(1)).save(any(User.class));
    }
}

