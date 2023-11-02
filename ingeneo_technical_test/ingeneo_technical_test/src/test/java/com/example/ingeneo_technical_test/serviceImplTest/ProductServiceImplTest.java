/**
 * 
 */
package com.example.ingeneo_technical_test.serviceImplTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.ingeneo_technical_test.repository.ProductRepository;
import com.example.ingeneo_technical_test.service.Implement.ProductServiceImpl;
import com.example.ingeneo_technical_test.DataSet;
import com.example.ingeneo_technical_test.entity.Product;
import com.example.ingeneo_technical_test.entity.dto.ProductDTO;
import com.example.ingeneo_technical_test.entity.dto.converter.ProductDTOConverter;
/**
 * @author Johan Casagua
 *
 */
class ProductServiceImplTest {
	@Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductServiceImpl productService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testListAllProducts() {
        // Arrange
        List<Product> products = new ArrayList<>();
        products.add(ProductDTOConverter.convertToEntity(DataSet.getProduct()));
        products.add(ProductDTOConverter.convertToEntity(DataSet.getProduct()));
        when(productRepository.findAll()).thenReturn(products);

        // Act
        List<ProductDTO> productDTOs = productService.listAllProducts();

        // Assert
        assertEquals(2, productDTOs.size());
        verify(productRepository, times(1)).findAll();
    }

    @Test
    void testCreateProduct() {
        // Arrange
        ProductDTO productDTO = DataSet.getProduct();
        Product product = ProductDTOConverter.convertToEntity(productDTO);
        when(productRepository.save(any(Product.class))).thenReturn(product);

        // Act
        ProductDTO savedProductDTO = productService.createProduct(productDTO);

        // Assert
        assertEquals(productDTO.getName(), savedProductDTO.getName());
        verify(productRepository, times(1)).save(any(Product.class));
    }
}
