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
 * Entity from table "WAREHOUSE"
 * @author Johan Casagua
 */
@Entity
@Table(name = "WAREHOUSE")
@Data @Builder
public class Warehouse {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private Long id;
	
	private String name;
	
	private String location;
	
	@Column(name="storage_capacity")
	private String storageCapactity;
}
