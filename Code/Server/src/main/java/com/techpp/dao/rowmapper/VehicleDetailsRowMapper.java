package com.techpp.dao.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.techpp.modal.VehicleDetails;

public class VehicleDetailsRowMapper implements RowMapper<VehicleDetails> {
	
	@Override
	public VehicleDetails mapRow(ResultSet rs, int rowNum) throws SQLException {
		VehicleDetails vehicleDetails = null;

		vehicleDetails = new VehicleDetails();
		vehicleDetails.setMobileNumber(rs.getString("mobile_no"));
		vehicleDetails.setCarType(rs.getString("car_type"));
		vehicleDetails.setSeatAvailable(rs.getString("seat_available"));
		vehicleDetails.setCarNumber(rs.getString("car_number"));
		vehicleDetails.setSource(rs.getString("source"));
		vehicleDetails.setDestination(rs.getString("destination"));
		vehicleDetails.setDate(rs.getString("date"));
		vehicleDetails.setTime(rs.getString("time"));		
		
		
		return vehicleDetails;
	}

}
