package com.example.demo.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.example.demo.domain.Car;
import com.example.demo.dto.GetCarResponseDTO;

@Mapper
public interface CarMapper {

	CarMapper INSTANCE = Mappers.getMapper(CarMapper.class);
	
	@Mapping(target = "idCar", source = "id")
	GetCarResponseDTO carsToGetCarResponseDTO(Car request);
	
	List<GetCarResponseDTO> carsToGetCarResponseDTOs(List<Car> request);
		
}
