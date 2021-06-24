package com.example.demo.domain;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@JsonIgnoreProperties(ignoreUnknown = true) 
@NoArgsConstructor
@AllArgsConstructor
@Data
public class PricesCars {
	private Integer idCar;
	private Integer idBrand;
	private Date startDate;
	private Date endDate;
	private Double value;
}
