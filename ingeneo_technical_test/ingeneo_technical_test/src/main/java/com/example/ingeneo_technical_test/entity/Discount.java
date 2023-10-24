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
 * Entity from table "DISCOUNT"
 * @author Johan Casagua
 */
@Entity
@Table(name = "DISCOUNT")
@Data @Builder
public class Discount {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private Long id;
	
	private String name;
	
	private String description;
	
	private String type;
	
	private float value;
	
	@Column(name="start_date")
	private Date startDate;
	
	@Column(name="end_date")
	private Date endDate;
	
	private Boolean active;

}
