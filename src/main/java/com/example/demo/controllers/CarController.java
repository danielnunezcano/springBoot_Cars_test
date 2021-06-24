package com.example.demo.controllers;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.GetCarResponseDTO;
import com.example.demo.dto.GetPricesCarRequestDTO;
import com.example.demo.dto.GetPricesCarResponseDTO;
import com.example.demo.exception.ServiceException;
import com.example.demo.logger.dto.Api;
import com.example.demo.logger.services.LoggerService;
import com.example.demo.services.GetPricesCarService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/coches")
@Validated
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CarController {

	private final GetPricesCarService getPricesCarService;
	private final LoggerService loggerService;

	@RequestMapping(method = RequestMethod.GET, path = "/search")
	public ResponseEntity<GetPricesCarResponseDTO> getPricesCar(@Valid GetPricesCarRequestDTO input,
			HttpServletRequest request) throws ServiceException {
		loggerService.addLog(Api.getPricesCar, request);
		GetPricesCarResponseDTO output = getPricesCarService.getCarPrice(input);
		if(output != null) return ResponseEntity.status(HttpStatus.OK).body(output);
		else return ResponseEntity.status(HttpStatus.NO_CONTENT).body(output);
	}

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<GetCarResponseDTO>> getCar(String filter, HttpServletRequest request) throws ServiceException {
		loggerService.addLog(Api.getCar, request);
		return ResponseEntity.status(HttpStatus.OK).body(getPricesCarService.getCar(filter));
	}

	@RequestMapping(produces = "application/octet-stream", method = RequestMethod.GET, path = "/xls")
	public void getCarsXls(HttpServletRequest request, HttpServletResponse response) throws ServiceException, IOException {
		loggerService.addLog(Api.getCarsXls, request);
		response.setHeader("Content-Disposition", "attachment; filename=cars.xlsx");
		IOUtils.copy(getPricesCarService.getCarToXls(), response.getOutputStream());
	}

}
