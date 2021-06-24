package com.example.demo.dto.entity;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@JsonIgnoreProperties(ignoreUnknown = true) 
@NoArgsConstructor
public class PriceDTO {
	private Date startDate;
	private Date endDate;
	private Double value;
}
