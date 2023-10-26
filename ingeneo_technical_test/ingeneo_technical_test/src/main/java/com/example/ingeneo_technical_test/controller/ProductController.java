/**
 * 
 */
package com.example.ingeneo_technical_test.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ingeneo_technical_test.entity.dto.ProductDTO;
import com.example.ingeneo_technical_test.service.ProductService;

/**
 * @author Johan Casagua
 */
@RestController
@RequestMapping(value = "/products")
public class ProductController {

	@Autowired
	private ProductService productService;
	
	@GetMapping
	public ResponseEntity<List<ProductDTO>> listProducts() {
		List<ProductDTO> product = productService.listAllProducts();
		
		if(product.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(product);
	}
	
	@PostMapping
    public ResponseEntity<ProductDTO> createProduct(@RequestBody ProductDTO productDTO) {
        if (productDTO == null) {
            return ResponseEntity.badRequest().build();
        }

        ProductDTO createdProduct = productService.createProduct(productDTO);

        if (createdProduct != null) {
        	// Returns a response with the DTO of the created product and the code 201 (CREATED)            
        	return ResponseEntity.status(HttpStatus.CREATED).body(createdProduct);
        } else {
        	// If the product could not be created, returns a 400 BAD REQUEST response code
        	return ResponseEntity.badRequest().build();
        }
    }
	
}
