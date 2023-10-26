package com.example.ingeneo_technical_test.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ingeneo_technical_test.entity.Client;

/**
 * @author Johan Casagua
 */
public interface ClientRepository extends JpaRepository<Client, Long> {

}
