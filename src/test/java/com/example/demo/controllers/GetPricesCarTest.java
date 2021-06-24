package com.example.demo.controllers;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import java.text.ParseException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import com.example.demo.dto.GetCarResponseDTO;
import com.example.demo.dto.GetPricesCarResponseDTO;
import com.example.demo.exception.ServiceException;
import com.example.demo.logger.dto.Api;
import com.example.demo.logger.repository.LoggerRepository;
import com.example.demo.logger.services.LoggerService;
import com.example.demo.logger.services.LoggerServiceImpl;
import com.example.demo.repository.PriceRepository;
import com.example.demo.services.GetPricesCarService;
import com.example.demo.services.GetPricesCarServiceImpl;

@SpringBootTest
public class GetPricesCarTest {
	@InjectMocks
	private CarController carController;

	@InjectMocks
	private GetPricesCarServiceImpl getPricesCarServiceImpl;

	@InjectMocks
	private LoggerServiceImpl loggerServiceImpl;
	
	@Mock
	private LoggerRepository loggerRepository;

	@Mock
	private LoggerService loggerServiceMock;

	@Mock
	private PriceRepository priceRepositoryMock;

	@Mock
	private GetPricesCarService getPricesCarServiceMock;

	private MockMvc mockMvc;

	@BeforeEach
	public void init() throws ServiceException, ParseException {
		mockMvc = MockMvcBuilders.standaloneSetup(carController).build();
		GetCarResponseDTO getCarResponseDTO = new GetCarResponseDTO();
		getCarResponseDTO.setIdCar(1);
		
		MockHttpServletRequest request = new MockHttpServletRequest();
		
		Mockito.when(loggerRepository.save(Mockito.any()))
			.thenReturn(null);
		
		loggerServiceImpl.addLog(Api.getPricesCar, request);
		
		doNothing().when(loggerServiceMock).addLog(Mockito.any(), Mockito.any());

	}

	@Test
	public void getCar_OK_Test() throws Exception {

		Mockito.when(priceRepositoryMock.fetchPrice(Mockito.any(), Mockito.any()))
				.thenReturn(UtilsTest.testPricesCars());

		GetPricesCarResponseDTO getPricesCarResponseDTO = getPricesCarServiceImpl
				.getCarPrice(UtilsTest.testGetPricesCarRequestDTO());

		Mockito.when(getPricesCarServiceMock.getCarPrice(Mockito.any())).thenReturn(getPricesCarResponseDTO);

		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		params.add("date", "2021-06-23");
		params.add("id", "1");
		mockMvc.perform(MockMvcRequestBuilders.get("/coches/search").params(params))
				.andExpect(MockMvcResultMatchers.status().is(HttpStatus.OK.value()))
				.andExpect(jsonPath("$.idCar", is(1))).andExpect(jsonPath("$.idBrand", is(1)))
				.andExpect(jsonPath("$.price.startDate", is(1624399200000L)))
				.andExpect(jsonPath("$.price.endDate", is(1624485600000L)))
				.andExpect(jsonPath("$.price.value", is(12345.67)));
	}

	@Test
	public void getCar_Without_result_Test() throws Exception {

		Mockito.when(priceRepositoryMock.fetchPrice(Mockito.any(), Mockito.any())).thenReturn(null);

		GetPricesCarResponseDTO getPricesCarResponseDTO = getPricesCarServiceImpl
				.getCarPrice(UtilsTest.testGetPricesCarRequestDTO());

		Mockito.when(getPricesCarServiceMock.getCarPrice(Mockito.any())).thenReturn(getPricesCarResponseDTO);

		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		params.add("date", "2021-06-23");
		params.add("id", "6");
		mockMvc.perform(
				MockMvcRequestBuilders.get("/coches/search").params(params).contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isNoContent());
	}

	@Test
	public void getCar_without_id_Test() throws Exception {
		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		params.add("date", "2021-06-23");
		mockMvc.perform(MockMvcRequestBuilders.get("/coches/search").params(params))
				.andExpect(MockMvcResultMatchers.status().is(HttpStatus.BAD_REQUEST.value()));
	}

	@Test
	public void getCar_without_date_Test() throws Exception {
		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		params.add("id", "1");
		mockMvc.perform(MockMvcRequestBuilders.get("/coches/search").params(params))
				.andExpect(MockMvcResultMatchers.status().is(HttpStatus.BAD_REQUEST.value()));
	}
}
