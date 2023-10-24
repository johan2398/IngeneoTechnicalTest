package com.example.ingeneo_technical_test.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Builder;
import lombok.Data;

/**
 * Entity from table "STORE"
 * @author Johan Casagua
 */
@Entity
@Table(name = "STORE")
@Data @Builder
public class Store {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private Long id;
	
	/**Vehicle license plate (3 initial letters and 3 final numbers)*/
	@Column(name = "vehicle_plate")
    private String vehiclePlate;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_warehouse")
	private Warehouse warehouse;
}
