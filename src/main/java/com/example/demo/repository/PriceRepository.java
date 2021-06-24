package com.example.demo.repository;

import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.domain.Price;
import com.example.demo.domain.PricesCars;

public interface PriceRepository extends JpaRepository<Price, Integer> {
	@Query("SELECT NEW com.example.demo.domain.PricesCars(c.id as idCard, b.id as idBrand, p.startDate, p.endDate, p.value) "
			+ "FROM Price p "
			+ "INNER JOIN Car as c ON p.idCar = c.id "
			+ "INNER JOIN Brand as b ON c.idBrand = b.id "
			+ "WHERE "
			+ "p.idCar = :idCar AND "
			+ "p.startDate < :date AND "
			+ "p.endDate  > :date")
	PricesCars fetchPrice(@Param("idCar") Integer idCar,@Param("date") Date date);
	
}
