package com.example.demo.logger.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.demo.logger.dto.ApiCall;

public interface LoggerRepository extends MongoRepository<ApiCall, String> {

}
