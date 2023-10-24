/**
 * 
 */
package com.example.ingeneo_technical_test.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ingeneo_technical_test.entity.Ship;

/**
 * @author Johan Casagua
 *
 */
public interface ShipRepository extends JpaRepository<Ship, Long> {

}
