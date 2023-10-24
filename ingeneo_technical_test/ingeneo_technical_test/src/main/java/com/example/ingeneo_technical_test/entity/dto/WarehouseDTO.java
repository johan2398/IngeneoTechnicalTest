package com.example.ingeneo_technical_test.entity.dto;

import com.example.ingeneo_technical_test.entity.Port;
import com.example.ingeneo_technical_test.entity.Store;
import com.example.ingeneo_technical_test.enumerations.WarehouseType;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Johan Casagua
 */
@Data @AllArgsConstructor @NoArgsConstructor @Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class WarehouseDTO {
	private Long id;
	private String name;
	private String location;
	private String storageCapacity;
    private WarehouseType type; 
	private Port port;
	private Store store;
}
