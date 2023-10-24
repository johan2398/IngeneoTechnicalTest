/**
 * 
 */
package com.example.ingeneo_technical_test.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ingeneo_technical_test.entity.Client;

/**
 * @author Johan Casagua
 */
public interface ClientRepository extends JpaRepository<Client, Long> {

}
