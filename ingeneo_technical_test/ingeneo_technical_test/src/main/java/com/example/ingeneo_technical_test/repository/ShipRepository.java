/**
 * 
 */
package com.example.ingeneo_technical_test.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.ingeneo_technical_test.entity.Ship;

/**
 * @author Johan Casagua
 *
 */
public interface ShipRepository extends JpaRepository<Ship, Long> {

	/*Query ships guide number or document identifier
	 * @param search
	 * @return
	 */
	@Query("SELECT s FROM Ship s INNER JOIN s.client c WHERE LOWER(c.identification) = LOWER(:search) OR LOWER(s.guideNumber) = LOWER(:search)")
	List<Ship> findByClientDocIdOrGuideNumberContainingIgnoreCase(@Param("search") String search);

	
	
}
