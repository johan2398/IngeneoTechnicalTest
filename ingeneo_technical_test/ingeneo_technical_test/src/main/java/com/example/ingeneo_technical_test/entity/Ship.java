package com.example.ingeneo_technical_test.entity;


import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.example.ingeneo_technical_test.enumerations.ShipState;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Entity from table "SHIP"
 * @author Johan Casagua
 */
@Entity
@Table(name = "ship")
@Data @AllArgsConstructor @NoArgsConstructor @Builder
public class Ship {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private Long id;
	
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "id_product")
	private Product product;
	
	@Column(name="registry_date")
	private Date registryDate;
	
	@Column(name="delivery_date")
	private Date deliveryDate;
	
	/**Land logistic -> 20000 & maritime logistic -> 50000*/
	@Column(name="shipping_price")
	private float shippingPrice;
	
	/**(10-digit alphanumeric unique number)*/
	@Column(name="guide_number",unique = true)
	private String guideNumber;
	
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "id_client")
	private Client client;
	
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "id_warehouse")
	private Warehouse warehouse;
	
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "id_discount")
	private Discount discount;
	
	private ShipState state;
	
	@Column(name="shipping_price_with_discount")
	private float shippingPriceWithDiscount;
}
