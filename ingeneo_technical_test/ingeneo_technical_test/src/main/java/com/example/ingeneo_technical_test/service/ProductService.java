/**
 * 
 */
package com.example.ingeneo_technical_test.service;

import java.util.List;

import com.example.ingeneo_technical_test.entity.dto.ProductDTO;

/**
 * @author Johan Casagua
 *
 */
public interface ProductService {

	public List<ProductDTO> listAllProducts();
	
	public ProductDTO createProduct(ProductDTO product);
}
