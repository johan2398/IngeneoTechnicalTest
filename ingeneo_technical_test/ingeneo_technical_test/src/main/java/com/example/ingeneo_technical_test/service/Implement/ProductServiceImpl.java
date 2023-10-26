/**
 * 
 */
package com.example.ingeneo_technical_test.service.Implement;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.time.LocalDate;
import java.time.ZoneId;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ingeneo_technical_test.entity.Product;
import com.example.ingeneo_technical_test.entity.dto.ProductDTO;
import com.example.ingeneo_technical_test.entity.dto.converter.ProductDTOConverter;
import com.example.ingeneo_technical_test.repository.ProductRepository;
import com.example.ingeneo_technical_test.service.ProductService;

/**
 * @author Johan Casagua
 *
 */
@Service
public class ProductServiceImpl implements ProductService{

	@Autowired
	private ProductRepository productRepository;
	
	@Override
	public List<ProductDTO> listAllProducts() {
		List<Product> products = productRepository.findAll();
		return products.stream()
				.map(ProductDTOConverter::convertToDTO)
				.collect(Collectors.toList());
	}

	@Override
	public ProductDTO createProduct(ProductDTO productDTO) {
		Product product = ProductDTOConverter.convertToEntity(productDTO);
		LocalDate currentDate = LocalDate.now();
		Date date = Date.from(currentDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
		product.setCreationDate(date);
		Product savedProduct = productRepository.save(product);
	    return ProductDTOConverter.convertToDTO(savedProduct);
	}

}
