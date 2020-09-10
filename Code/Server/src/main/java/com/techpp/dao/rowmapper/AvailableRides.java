package com.techpp.dao.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import com.techpp.modal.Driver;
import com.techpp.modal.Group;
import com.techpp.modal.Ride;
import com.techpp.modal.User;
import com.techpp.modal.VehicleDetails;

public class AvailableRides implements RowMapper<Driver> {

	@Override
	public Driver mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		
		Driver driver = new Driver() ;
		User user = new User();
		Ride ride = new Ride();
		VehicleDetails vehicleDetails = new VehicleDetails();
	
	    driver.setUser(user);
	    driver.setRides(ride);
	    driver.setVehicle(vehicleDetails);
	    driver.setMobileNumber(rs.getString("mobile_no"));
	    driver.setGroupCode(rs.getString("group_code"));
	    driver.setCurentLocationLat(String.valueOf(rs.getFloat("ride_start_lat")));
	    driver.setCurentLocationLong(String.valueOf(rs.getString("ride_start_long")));
	    
		user.setFirstName(rs.getString("first_name"));
		user.setLastName(rs.getString("last_name"));
		user.setMobileNumber(rs.getString("mobile_no"));
		user.setGroupCode(rs.getString("group_code"));
		
		vehicleDetails.setMobileNumber(rs.getString("mobile_no"));
		vehicleDetails.setCarType(rs.getString("car_type"));
		vehicleDetails.setSeatAvailable(rs.getString("seat_available"));
		vehicleDetails.setCarNumber(rs.getString("car_number"));
		vehicleDetails.setDate(rs.getString("date"));
		vehicleDetails.setTime(rs.getString("time"));
		vehicleDetails.setSource(rs.getString("source"));
		vehicleDetails.setDestination(rs.getString("destination"));
		
	     ride.setMobileNumber(rs.getString("mobile_no"));
	     ride.setStartLat(rs.getString("ride_start_lat"));
		 ride.setStartLong(rs.getString("ride_start_long"));
		 ride.setRideStatus(rs.getString("ride_status"));
		 ride.setRideType(rs.getString("ride_type"));
		 
	    driver.getUser().setMobileNumber(rs.getString("mobile_no"));
		driver.getUser().setFirstName(rs.getString("first_name"));
		driver.getUser().setLastName(rs.getString("last_name"));
		driver.getUser().setGroupCode(rs.getString("group_code"));
		driver.getRides().setStartLat(rs.getString("ride_start_lat"));
		driver.getRides().setStartLong(rs.getString("ride_start_long"));
		driver.getRides().setRideStatus(rs.getString("ride_status"));
		driver.getRides().setRideType(rs.getString("ride_type"));
		driver.getVehicle().setSeatAvailable(rs.getString("seat_available"));
		driver.getVehicle().setCarType(rs.getString("car_type"));
		driver.getVehicle().setCarNumber(rs.getString("car_number"));
		driver.getVehicle().setDate(rs.getString("date"));
		driver.getVehicle().setTime(rs.getString("time"));
		driver.getVehicle().setSource(rs.getString("source"));
		driver.getVehicle().setDestination(rs.getString("destination"));
		
		return driver;
		
			}

}
