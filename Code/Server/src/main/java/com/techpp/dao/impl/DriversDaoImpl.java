package com.techpp.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import com.techpp.dao.DriversDao;
import com.techpp.dao.rowmapper.DriversRowMapper;
import com.techpp.modal.Driver;
import com.techpp.utils.AppUtils;


@Repository("driverDao")
public class DriversDaoImpl implements DriversDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public int insertDriver(Driver driver) {
		int rows = 0;
		rows = jdbcTemplate.update(SAVE_NEW_DRIVER, driver.getMobileNumber(), driver.getLicenceNO());
		return rows ;
	}
	
	@Override
	public Driver findDriverByContact(String contactNumber) {
		List<Driver> driver = null;

		driver = jdbcTemplate.query(FIND_DRIVER_BY_MOBILE_NUM, new Object[] {contactNumber}, new DriversRowMapper());
		
		if(AppUtils.isNullOEmptyList(driver)) {
		
			return null;
		}
		else 
	
			return driver.get(0);
	}
	
	
	@Override
	public List<Driver> findDriversByStatus(String groupCode, String status) {
		
		return jdbcTemplate.query(FIND_DRIVERS_BY_STATUS,new Object[] {groupCode, status}, new DriversRowMapper());
	}

	@Override
	public int changeStatus(Driver driver) {
		
		int rows = 0;
		rows = jdbcTemplate.update(CHANGE_STATUS, driver.getStatus(), driver.getMobileNumber());
		return rows ;
		
	}
}
