package com.example.ingeneo_technical_test.entity;


import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.example.ingeneo_technical_test.enumerations.WarehouseType;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Entity from table "WAREHOUSE"
 * @author Johan Casagua
 */
@Entity
@Table(name = "warehouse")
@Data @AllArgsConstructor @NoArgsConstructor @Builder
public class Warehouse {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private Long id;
	
	private String name;
	
	private String location;
	
	@Column(name="storage_capacity")
	private String storageCapacity;
	
	/**This field will contain the type of warehouse (PORT, STORE, etc.)*/
    private WarehouseType type; 
	
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "id_port")
	private Port port;
	
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "id_store")
	private Store store;
}
