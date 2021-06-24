package com.example.demo.controllers;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import com.example.demo.dto.GetCarResponseDTO;
import com.example.demo.exception.ServiceException;
import com.example.demo.logger.services.LoggerService;
import com.example.demo.logger.services.LoggerServiceImpl;
import com.example.demo.repository.CarRowMapper;
import com.example.demo.services.GetPricesCarService;
import com.example.demo.services.GetPricesCarServiceImpl;

@SpringBootTest
public class getCarTest {
	@InjectMocks
	private CarController carController;

	@InjectMocks
	private GetPricesCarServiceImpl getPricesCarServiceImpl;

	@InjectMocks
	private LoggerServiceImpl loggerServiceImpl;

	@Mock
	private LoggerService LoggerServiceMock;

	@Mock
	private JdbcTemplate jdbcTemplate;

	@Mock
	private GetPricesCarService getPricesCarServiceMock;

	private MockMvc mockMvc;

	@BeforeEach
	public void init() throws ServiceException {
		mockMvc = MockMvcBuilders.standaloneSetup(carController).build();

		doNothing().when(LoggerServiceMock).addLog(Mockito.any(), Mockito.any());

		Mockito.when(jdbcTemplate.query(Mockito.anyString(), Mockito.any(CarRowMapper.class)))
				.thenReturn(UtilsTest.testCarList());

		List<GetCarResponseDTO> getCarResponseDTOList = getPricesCarServiceImpl.getCar("name eq Astra");

		Mockito.when(getPricesCarServiceMock.getCar("name eq Astra")).thenReturn(getCarResponseDTOList);
	}

	@Test
	public void getCar_OK_Test() throws Exception {

		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		params.add("filter", "name eq Astra");
		mockMvc.perform(MockMvcRequestBuilders.get("/coches").params(params))
				.andExpect(MockMvcResultMatchers.status().is(HttpStatus.OK.value()))
				.andExpect(jsonPath("$[0].idCar", is(1))).andExpect(jsonPath("$[0].idBrand", is(1)))
				.andExpect(jsonPath("$[0].colour", is("Red")));
	}

}
