/**
 * 
 */
package com.example.ingeneo_technical_test.entity.dto;

import com.example.ingeneo_technical_test.enumerations.ClientState;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Johan Casagua
 */
@Data @AllArgsConstructor @NoArgsConstructor @Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ClientDTO {

	private Long id;
	private String name;
	private String lastName;
	private String email;
	private String address;
	private ClientState state;
	private String identification;
}
