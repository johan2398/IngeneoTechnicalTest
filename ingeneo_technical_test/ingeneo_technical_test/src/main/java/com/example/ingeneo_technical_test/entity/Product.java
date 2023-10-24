package com.example.ingeneo_technical_test.entity;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Builder;
import lombok.Data;

/**
 * Entity from table "PRODUCT"
 * @author Johan Casagua
 */
@Entity
@Table(name = "PRODUCT")
@Data @Builder
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private Long id;
	
	private String description;
	
	private Long type;
	
	@Column(name="creation_date")
	private Date creationDate;
}
