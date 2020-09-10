package com.techpp.dao.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.techpp.modal.Driver;

public class DriversRowMapper implements RowMapper<Driver> {
	
	@Override
	public Driver mapRow(ResultSet rs,int rowNum) throws SQLException {
		
		Driver driver = null;
		driver = new Driver();
	
		
		
		
		driver.setMobileNumber(rs.getString("mobile_no"));
		driver.setLicenceNO(rs.getString("licence_no"));
		driver.setStatus(rs.getString("status"));
		driver.setCurentLocationLat(rs.getString("latitude"));
		driver.setCurentLocationLong(rs.getString("longitude"));
		
	
		return driver;
	}

}
