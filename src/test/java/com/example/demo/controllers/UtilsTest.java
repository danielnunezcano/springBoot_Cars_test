package com.example.demo.controllers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.example.demo.domain.Car;
import com.example.demo.domain.PricesCars;
import com.example.demo.dto.GetPricesCarRequestDTO;

public class UtilsTest {

	public static List<Car> testCarList() {
		List<Car> output = new ArrayList<Car>();
		output.add(new Car(1, "C1", "Red", 1));
		output.add(new Car(2, "C3", "Blue", 1));
		output.add(new Car(3, "Astra", "Black", 2));
		output.add(new Car(4, "Omega", "White", 2));
		return output;
	}

	public static Car testCar() {
		Car output = new Car();
		output.setId(1);
		output.setName("Astra");
		output.setColour("Blue");
		return output;
	}

	public static PricesCars testPricesCars() {
		PricesCars output = new PricesCars();
		output.setIdCar(1);
		output.setIdBrand(1);
		output.setStartDate(new Date(1624399200000L));
		output.setEndDate(new Date(1624485600000L));
		output.setValue(12345.67);
		return output;
	}

	public static GetPricesCarRequestDTO testGetPricesCarRequestDTO() throws ParseException {
		GetPricesCarRequestDTO output = new GetPricesCarRequestDTO();
		output.setId(1);
		output.setDate(new SimpleDateFormat("yyyy-MM-dd").parse("2021-06-23"));
		return output;
	}

}
