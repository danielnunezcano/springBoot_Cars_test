package com.example.demo.controllers;

import static org.mockito.Mockito.doNothing;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.example.demo.exception.ServiceException;
import com.example.demo.logger.services.LoggerService;
import com.example.demo.logger.services.LoggerServiceImpl;
import com.example.demo.repository.CarRepository;
import com.example.demo.services.GetPricesCarService;
import com.example.demo.services.GetPricesCarServiceImpl;

@SpringBootTest
public class getCarsXlsTest {
	@InjectMocks
	private CarController carController;

	@InjectMocks
	private GetPricesCarServiceImpl getPricesCarServiceImpl;

	@InjectMocks
	private LoggerServiceImpl loggerServiceImpl;

	@Mock
	private LoggerService LoggerServiceMock;

	@Mock
	private CarRepository carRepositoryMock;

	@Mock
	private GetPricesCarService getPricesCarServiceMock;

	private MockMvc mockMvc;

	@BeforeEach
	public void init() throws ServiceException, IOException {
		mockMvc = MockMvcBuilders.standaloneSetup(carController).build();

		doNothing().when(LoggerServiceMock).addLog(Mockito.any(), Mockito.any());

		Mockito.when(carRepositoryMock.findAll()).thenReturn(UtilsTest.testCarList());

		ByteArrayInputStream byteArrayInputStream = getPricesCarServiceImpl.getCarToXls();

		Mockito.when(getPricesCarServiceMock.getCarToXls()).thenReturn(byteArrayInputStream);
	}

	@Test
	public void getCarsXls_OK_Test() throws Exception {

		Mockito.when(carRepositoryMock.findAll()).thenReturn(UtilsTest.testCarList());

		ByteArrayInputStream byteArrayInputStream = getPricesCarServiceImpl.getCarToXls();

		Mockito.when(getPricesCarServiceMock.getCarToXls()).thenReturn(byteArrayInputStream);

		mockMvc.perform(MockMvcRequestBuilders.get("/coches/xls"))
				.andExpect(MockMvcResultMatchers.status().is(HttpStatus.OK.value()));
	}

}
