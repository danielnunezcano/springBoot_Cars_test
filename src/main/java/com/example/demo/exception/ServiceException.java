package com.example.demo.exception;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true) 
@NoArgsConstructor
public class ServiceException extends Exception { 
	
	private static final long serialVersionUID = -116999120150917483L;
	
	private ServiceExceptionData data;

	public ServiceException(Integer code, String description) {
        super(description);
        ServiceExceptionData data = new ServiceExceptionData(code, description);
        this.data = data;
    }	
	
}
