package com.example.ingeneo_technical_test.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ingeneo_technical_test.entity.Warehouse;

/**
 * @author Johan Casagua
 */
public interface WarehouseRepository extends JpaRepository<Warehouse, Long> {

}
