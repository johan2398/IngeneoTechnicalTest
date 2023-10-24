/**
 * 
 */
package com.example.ingeneo_technical_test.entity.dto.converter;

import org.springframework.stereotype.Component;

import com.example.ingeneo_technical_test.entity.Product;
import com.example.ingeneo_technical_test.entity.dto.ProductDTO;

/**
 * @author Johan Casagua
 *
 */
@Component
public class ProductDTOConverter {

	public static ProductDTO convertToDTO(Product client) {
		ProductDTO productDTO = new ProductDTO();
		productDTO.setId(client.getId());
		productDTO.setType(client.getType());
		productDTO.setDescription(client.getDescription());
		productDTO.setCreationDate(client.getCreationDate());
		return productDTO;
	}
	
	public static Product convertToEntity(ProductDTO productDTO) {
		Product product = new Product();
		product.setId(productDTO.getId());
		product.setType(productDTO.getType());
		product.setDescription(productDTO.getDescription());
		product.setCreationDate(productDTO.getCreationDate());
		return product;
	}
}
