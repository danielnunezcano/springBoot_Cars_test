package com.example.demo.repository;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.example.demo.domain.Car;

public class CarRowMapper implements RowMapper<Car> {
	@Override
	public Car mapRow(ResultSet rs, int rowNum) throws SQLException {
		return new Car(rs.getInt("id"), rs.getString("name"), rs.getString("colour"), rs.getInt("id_brand"));
	}
}
