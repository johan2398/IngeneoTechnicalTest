/**
 * 
 */
package com.example.ingeneo_technical_test.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.ingeneo_technical_test.entity.User;

/**
 * @author Johan Casagua
 *
 */
public interface UserRepository extends JpaRepository<User, Long> {

	@Query("SELECT u FROM User u WHERE LOWER(u.userName) = LOWER(:userName) AND u.password = :password")
	User findByUsernameAndPassword(@Param("userName") String username, @Param("password") String password);
}
