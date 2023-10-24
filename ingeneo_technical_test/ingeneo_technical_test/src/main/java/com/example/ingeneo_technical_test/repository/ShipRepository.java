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

	/*Query ships with a partial or complete guide number
	 * @param partialGuideNumber
	 * @return
	 */
	@Query("SELECT s FROM Ship s WHERE LOWER(s.guideNumber) LIKE LOWER(CONCAT('%', :partialGuideNumber, '%'))")
    List<Ship> findByNumberGuideContainingIgnoreCase(@Param("partialGuideNumber") String partialGuideNumber);
	
	@Query("SELECT s FROM Ship s INNER JOIN s.client c WHERE LOWER(c.identification) LIKE LOWER(CONCAT('%', :partialDocId, '%'))")
	List<Ship> findByClientDocIdContainingIgnoreCase(@Param("partialDocId") String partialDocId);

	
}
