package com.example.demo.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@JsonIgnoreProperties(ignoreUnknown = true) 
@NoArgsConstructor
@AllArgsConstructor
public class GetCarResponseDTO {
	private Integer idCar;
	private Integer idBrand;
	private String colour;
}
