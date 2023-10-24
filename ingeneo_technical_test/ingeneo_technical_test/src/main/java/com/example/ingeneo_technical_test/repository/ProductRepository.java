/**
 * 
 */
package com.example.ingeneo_technical_test.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ingeneo_technical_test.entity.Product;

/**
 * @author Johan Casagua
 */
public interface ProductRepository extends JpaRepository<Product, Long> {

}
