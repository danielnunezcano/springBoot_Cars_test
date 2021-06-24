package com.example.demo.mapper;

import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import com.example.demo.domain.PricesCars;
import com.example.demo.dto.GetPricesCarResponseDTO;
import com.example.demo.dto.entity.PriceDTO;

@Mapper
public interface PricesCarMapper {

	PricesCarMapper INSTANCE = Mappers.getMapper(PricesCarMapper.class);

	GetPricesCarResponseDTO pricesCarsTogetPricesCarResponseDTO(PricesCars request);
		
	PriceDTO toPriceDTO(PricesCars request);
	
	@AfterMapping
	default void updateResult(@MappingTarget GetPricesCarResponseDTO getPricesCarResponseDTO, PricesCars pricesCars) {
		getPricesCarResponseDTO.setPrice(toPriceDTO(pricesCars));
	}

}
