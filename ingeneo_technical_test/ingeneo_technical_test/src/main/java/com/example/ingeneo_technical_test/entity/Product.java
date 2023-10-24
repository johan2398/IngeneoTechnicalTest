package com.example.ingeneo_technical_test.entity;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Entity from table "PRODUCT"
 * @author Johan Casagua
 */
@Entity
@Table(name = "product")
@Data @AllArgsConstructor @NoArgsConstructor @Builder
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private Long id;
	
	private String name;
	
	private int quantity;
	
	private String description;
	
	private float price;
	
	private Long type;
	
	@Column(name="creation_date")
	private Date creationDate;
}
