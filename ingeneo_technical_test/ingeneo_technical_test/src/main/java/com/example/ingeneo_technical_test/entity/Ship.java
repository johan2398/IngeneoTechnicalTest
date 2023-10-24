package com.example.ingeneo_technical_test.entity;


import java.util.Date;

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
 * Entity from table "SHIP"
 * @author Johan Casagua
 */
@Entity
@Table(name = "SHIP")
@Data @Builder
public class Ship {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private Long id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "product_type")
	private Product productType;
	
	@Column(name="product_quantity")
	private int productQuantity;
	
	@Column(name="registry_date")
	private Date registryDate;
	
	@Column(name="delivery_date")
	private Date deliveryDate;
	
	@Column(name="shipping_price")
	private float shippingPrice;
	
	/**(10-digit alphanumeric unique number)*/
	@Column(name="guide_number")
	private String guideNumber;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_client")
	private Client client;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_warehouse")
	private Warehouse warehouse;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_discount")
	private Discount discount;
}
