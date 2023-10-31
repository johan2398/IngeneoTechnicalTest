/**
 * 
 */
package com.example.ingeneo_technical_test.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Johan Casagua
 *
 */
@Data @AllArgsConstructor @NoArgsConstructor @Builder
public class UserLoginRequest {
	private String username;
    private String password;
}
