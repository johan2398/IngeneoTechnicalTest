/**
 * 
 */
package com.example.ingeneo_technical_test.entity.dto;

import java.util.Date;

import com.example.ingeneo_technical_test.entity.Client;
import com.example.ingeneo_technical_test.entity.Discount;
import com.example.ingeneo_technical_test.entity.Product;
import com.example.ingeneo_technical_test.entity.Warehouse;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Johan Casagua
 *
 */
@Data @AllArgsConstructor @NoArgsConstructor @Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ShipDTO {
	private Long id;
	private Product product;
	private Date registryDate;
	private Date deliveryDate;
	private float shippingPrice;
	private String guideNumber;
	private Client client;
	private Warehouse warehouse;
	private Discount discount;
}
