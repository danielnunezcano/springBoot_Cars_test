package com.example.demo.exception;

import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHelper {
	private static final Logger LOGGER = LoggerFactory.getLogger(ExceptionHelper.class);

	@ExceptionHandler(value = { BindException.class })
	public ResponseEntity<ServiceExceptionData> handleBindException(BindException ex) {
		LOGGER.error("Invalid Input Exception: ", ex.getFieldError());
		ServiceExceptionData exception = new ServiceExceptionData();
		String errorMessage = ex.getBindingResult().getAllErrors().stream().map(ObjectError::getDefaultMessage)
				.collect(Collectors.toList()).toString();
		errorMessage = errorMessage.substring(1, errorMessage.length() - 1);
		exception.setCode(HttpStatus.BAD_REQUEST.value());
		exception.setDescription(errorMessage);
		return new ResponseEntity<ServiceExceptionData>(exception, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(value = { Exception.class })
	public ResponseEntity<ServiceExceptionData> handleException(Exception ex) {
		LOGGER.error("handleServiceException: ", ex.getMessage());
		ServiceExceptionData exception = new ServiceExceptionData();
		exception.setCode(HttpStatus.SERVICE_UNAVAILABLE.value());
		exception.setDescription(ex.getMessage());
		return new ResponseEntity<ServiceExceptionData>(exception, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(value = { ServiceException.class })
	public ResponseEntity<ServiceExceptionData> handleServiceException(ServiceException ex) {
		LOGGER.error("handleServiceException: ", ex.getData().getDescription());
		return new ResponseEntity<ServiceExceptionData>(ex.getData(), HttpStatus.valueOf(ex.getData().getCode()));
	}

}
