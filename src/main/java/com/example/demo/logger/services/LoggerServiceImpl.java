package com.example.demo.logger.services;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.logger.dto.Api;
import com.example.demo.logger.dto.ApiCall;
import com.example.demo.logger.repository.LoggerRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class LoggerServiceImpl implements LoggerService{

	private final LoggerRepository loggerRepository;
	
	@Override
	public void addLog(Api api, HttpServletRequest request) {
		
		ApiCall apiCall = new ApiCall(request.getRemoteAddr(),new Date(), api);
		
		loggerRepository.save(apiCall);
	}
}
