package com.example.demo.exception;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true) 
@NoArgsConstructor
public class ServiceExceptionData {
	private Integer code;
	private String description;

	public ServiceExceptionData(Integer code, String description) {
        this.code = code;
        this.description = description;
    }
}
