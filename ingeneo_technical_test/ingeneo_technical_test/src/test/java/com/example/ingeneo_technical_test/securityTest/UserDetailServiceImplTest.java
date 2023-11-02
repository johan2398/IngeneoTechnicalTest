/**
 * 
 */
package com.example.ingeneo_technical_test.securityTest;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.example.ingeneo_technical_test.security.UserDetailsImpl;
import com.example.ingeneo_technical_test.security.UserDetailServiceImpl;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.example.ingeneo_technical_test.entity.User;
import com.example.ingeneo_technical_test.repository.UserRepository;
/**
 * @author Johan Casagua
 *
 */
class UserDetailServiceImplTest {
	

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserDetailServiceImpl userDetailService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }
	
	@Test
    void testLoadUserByUsernameWhenUserExists() {
        // Arrange
        UserRepository userRepository = mock(UserRepository.class);
        UserDetailServiceImpl userDetailService = new UserDetailServiceImpl();
        userDetailService.userRepository = userRepository;

        User user = new User();
        user.setEmail("test@example.com");
        user.setPassword("password");

        when(userRepository.findOneByEmail("test@example.com")).thenReturn(Optional.of(user));

        // Act
        UserDetails userDetails = userDetailService.loadUserByUsername("test@example.com");

        // Assert
        assertNotNull(userDetails);
        assertEquals(user.getEmail(), userDetails.getUsername());
        assertEquals(user.getPassword(), userDetails.getPassword());
    }

    @Test
    void testLoadUserByUsernameWhenUserDoesNotExist() {
        // Arrange
        UserRepository userRepository = mock(UserRepository.class);
        UserDetailServiceImpl userDetailService = new UserDetailServiceImpl();
        userDetailService.userRepository = userRepository;

        when(userRepository.findOneByEmail("nonexistent@example.com")).thenReturn(Optional.empty());

        // Act and Assert
        assertThrows(UsernameNotFoundException.class, () -> {
            userDetailService.loadUserByUsername("nonexistent@example.com");
        });
    }
}
