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

	public static ProductDTO convertToDTO(Product product) {
		ProductDTO productDTO = new ProductDTO();
		productDTO.setId(product.getId());
		productDTO.setName(product.getName());
		productDTO.setQuantity(product.getQuantity());
		productDTO.setDescription(product.getDescription());
		productDTO.setPrice(product.getPrice());
		productDTO.setType(product.getType());
		productDTO.setCreationDate(product.getCreationDate());
		return productDTO;
	}
	
	public static Product convertToEntity(ProductDTO productDTO) {
		Product product = new Product();
		product.setId(productDTO.getId());
		product.setName(productDTO.getName());
		product.setQuantity(productDTO.getQuantity());
		product.setDescription(productDTO.getDescription());
		product.setPrice(productDTO.getPrice());
		product.setType(productDTO.getType());
		product.setCreationDate(productDTO.getCreationDate());
		return product;
	}
}
