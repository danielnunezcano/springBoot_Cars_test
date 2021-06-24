package com.example.demo.dto;

import java.util.Date;

import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@JsonIgnoreProperties(ignoreUnknown = true) 
@NoArgsConstructor
public class GetPricesCarRequestDTO  {
	@NotNull(message="Date is null")
	@DateTimeFormat(pattern = "yyyy-MM-dd") 
	private Date date;
	@NotNull(message="Id is null")
	private Integer id;
}

