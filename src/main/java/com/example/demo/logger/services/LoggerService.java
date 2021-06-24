package com.example.demo.logger.services;

import javax.servlet.http.HttpServletRequest;

import com.example.demo.logger.dto.Api;

public interface LoggerService {
	public void addLog(Api apiCall, HttpServletRequest request);
}
