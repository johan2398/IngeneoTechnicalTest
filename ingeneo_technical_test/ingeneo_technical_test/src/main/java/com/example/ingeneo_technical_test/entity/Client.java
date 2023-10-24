package com.example.ingeneo_technical_test.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Builder;
import lombok.Data;
/**
 * Entity from table "CLIENT"
 * @author Johan Casagua
 */
@Entity
@Table(name = "CLIENT")
@Data @Builder
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
	
}
