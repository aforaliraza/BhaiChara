package com.techpp.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.techpp.modal.InterCityRides;

public class InterCityDriverRowMapper implements RowMapper<InterCityRides> {

	@Override
	public InterCityRides mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		InterCityRides interCityRides = new InterCityRides();
		  interCityRides.setMobileNumber(rs.getString("mobile_no"));
		    interCityRides.setSource(rs.getString("source"));
		    interCityRides.setDestination(rs.getString("destination"));
		    interCityRides.setCarType(rs.getString("car_type"));
		    interCityRides.setCarNumber(rs.getString("car_number"));
		    interCityRides.setSeatsCapacity(rs.getString("seats_capacity"));
		    interCityRides.setDate(rs.getString("date"));
		    interCityRides.setTime(rs.getString("time"));
		    
		     return interCityRides;
		    
	}

}
