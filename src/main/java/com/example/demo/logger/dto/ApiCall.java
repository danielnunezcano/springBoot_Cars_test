package com.example.demo.logger.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ApiCall {
	
	  public String ip;
	  public Date date;
	  public Api service;

}
