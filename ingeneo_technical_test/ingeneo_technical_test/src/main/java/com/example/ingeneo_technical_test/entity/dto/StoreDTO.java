package com.example.ingeneo_technical_test.entity.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Johan Casagua
 *
 */
@Data @AllArgsConstructor @NoArgsConstructor @Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class StoreDTO {
	private Long id;
    private String vehiclePlate;
}
