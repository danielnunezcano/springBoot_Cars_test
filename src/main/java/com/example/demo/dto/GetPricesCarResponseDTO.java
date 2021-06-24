package com.example.demo.dto;

import com.example.demo.dto.entity.PriceDTO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@JsonIgnoreProperties(ignoreUnknown = true) 
@NoArgsConstructor
public class GetPricesCarResponseDTO {
	private Integer idCar;
	private Integer idBrand;
	private PriceDTO price;
}
