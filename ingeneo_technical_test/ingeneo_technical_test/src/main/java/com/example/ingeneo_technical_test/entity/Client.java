package com.example.ingeneo_technical_test.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.example.ingeneo_technical_test.enumerations.ClientState;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
/**
 * Entity from table "CLIENT"
 * @author Johan Casagua
 */
@Entity
@Table(name = "client")
@Data @AllArgsConstructor @NoArgsConstructor @Builder
public class Client {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private Long id;
	
	private String name;
	
	@Column(name="last_name")
	private String lastName;
	
	private String password;
	
	private String email;
	
	private String address;
	
	private ClientState state;
	
	private String identification;
	
	private String username;
}
