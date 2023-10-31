/**
 * 
 */
package com.example.ingeneo_technical_test.entity.dto;

import javax.persistence.Column;

import com.example.ingeneo_technical_test.enumerations.ClientState;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Johan Casagua
 *
 */
@Data @AllArgsConstructor @NoArgsConstructor @Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDTO {
	private Long id;
	private String userName;
	private String password;
	private String email;
	private String name;
}
