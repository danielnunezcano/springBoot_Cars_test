package com.example.demo.services;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.example.demo.domain.Car;
import com.example.demo.domain.PricesCars;
import com.example.demo.dto.GetCarResponseDTO;
import com.example.demo.dto.GetPricesCarRequestDTO;
import com.example.demo.dto.GetPricesCarResponseDTO;
import com.example.demo.exception.ServiceException;
import com.example.demo.mapper.CarMapper;
import com.example.demo.mapper.PricesCarMapper;
import com.example.demo.repository.CarRepository;
import com.example.demo.repository.CarRowMapper;
import com.example.demo.repository.PriceRepository;
import com.example.demo.xls.ToXls;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class GetPricesCarServiceImpl implements GetPricesCarService {

	private final PriceRepository priceRepository;
	private final CarRepository carRepository;
	private final JdbcTemplate jdbcTemplate;

	@Override
	public GetPricesCarResponseDTO getCarPrice(GetPricesCarRequestDTO request) throws ServiceException {

		PricesCars result = priceRepository.fetchPrice(request.getId(), request.getDate());

		return PricesCarMapper.INSTANCE.pricesCarsTogetPricesCarResponseDTO(result);
	}

	@Override
	public List<GetCarResponseDTO> getCar(String filter) throws ServiceException {

		filter = filter.replace("eq", "=");

		String query = "SELECT * FROM car";

		if (!filter.isEmpty())
			query = query.concat(" WHERE ").concat(filter);

		List<Car> result = jdbcTemplate.query(query, new CarRowMapper());

		return CarMapper.INSTANCE.carsToGetCarResponseDTOs(result);
	}

	@Override
	public ByteArrayInputStream getCarToXls() throws ServiceException, IOException {
		return ToXls.toXls(carRepository.findAll());
	}

}
