package com.example.ingeneo_technical_test.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Entity from table "PORT"
 * @author Johan Casagua
 */
@Entity
@Table(name = "port")
@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class Port{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private Long id;
	
	/**Fleet number (3 initial letters, 4 numbers and one letter at the end)*/
	@Column(name = "fleet_number")
    private String fleetNumber; 
}
