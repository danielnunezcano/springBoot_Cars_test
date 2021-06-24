package com.example.demo.services;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

import com.example.demo.dto.GetCarResponseDTO;
import com.example.demo.dto.GetPricesCarRequestDTO;
import com.example.demo.dto.GetPricesCarResponseDTO;
import com.example.demo.exception.ServiceException;

public interface GetPricesCarService {
	public GetPricesCarResponseDTO getCarPrice(GetPricesCarRequestDTO request)  throws ServiceException;
	public List<GetCarResponseDTO> getCar(String filter)  throws ServiceException;
	public ByteArrayInputStream getCarToXls()  throws ServiceException, IOException;
}
