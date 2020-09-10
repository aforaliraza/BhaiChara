package com.techpp.dao.impl;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.techpp.dao.RideDao;
import com.techpp.dao.rowmapper.AvailableRides;
import com.techpp.dao.rowmapper.UserRowMapper;
import com.techpp.dao.rowmapper.VehicleDetailsRowMapper;
import com.techpp.modal.Driver;
import com.techpp.modal.InterCityRides;
import com.techpp.modal.Ride;
import com.techpp.modal.Rider;
import com.techpp.modal.VehicleDetails;
import com.techpp.utils.AppUtils;
import com.techpp.utils.RideStatus;


@Repository("rideDao")
public class RideDaoImpl implements RideDao{

	@Autowired
	private JdbcTemplate jdbcTemplate;
	SimpleDateFormat timeform = new SimpleDateFormat("hh:mm:ss"); 
	SimpleDateFormat dateform = new SimpleDateFormat("yyyy-MM-dd"); 

	@Override
	public int rideStart(Ride ride) {
		
		Date date = new Date();
		return jdbcTemplate.update(UPDATE_RIDE, ride.getEndLat(), ride.getEndLong(),  RideStatus.IN_PROGRESS, dateform.format(date), timeform.format(date), ride.getMobileNumber()); 
          
		}


	@Override
	public int rideUpdate(Ride ride) {

		Date date = new Date();
		return jdbcTemplate.update(UPDATE_RIDE, ride.getEndLat(), ride.getEndLong(),  RideStatus.IN_PROGRESS, dateform.format(date), timeform.format(date), ride.getMobileNumber()); 

	}

	@Override
	public int rideEnd(Ride ride) {

		Date date = new Date();
		return jdbcTemplate.update(END_RIDE, ride.getEndLat(), ride.getEndLong(), RideStatus.ENDED, dateform.format(date), timeform.format(date), ride.getComment(), ride.getMobileNumber()); 

	}


	@Override
	public  List<Driver> findAllActiveRides() {

		List<Driver> availableDrivers = null;

		availableDrivers = jdbcTemplate.query(ALL_ACTIVE_RIDES, new Object[]{RideStatus.IN_PROGRESS}, new AvailableRides());

		if(AppUtils.isNullOEmptyList(availableDrivers)) {
			return null;
		}else {
			return availableDrivers;
		}
	}


	@Override
	public int bookARide(Rider rider) {
		
		Date date = new Date();
		KeyHolder keyholder = new GeneratedKeyHolder();
		return jdbcTemplate.update(BOOK_A_RIDE, rider.getMobileNumber(), rider.getSrcLat(), rider.getSrcLong(), rider.getDestLat(), 
		rider.getDestLong(), rider.getTypeOfRide(), RideStatus.UN_CONFIRMED, dateform.format(date), timeform.format(date)); 
		
	}


	@Override
	public int interCityRidesInsert(InterCityRides interCityRide) {
	   
		return jdbcTemplate.update(INTER_CITY_RIDES_INSERT, interCityRide.getMobileNumber(), interCityRide.getSource(),
				interCityRide.getDestination(), interCityRide.getCarType(), interCityRide.getCarNumber(), 
				interCityRide.getSeatsCapacity(), interCityRide.getRoles(), interCityRide.getDate(),
				interCityRide.getTime(), interCityRide.getStatus()); 
				
		
	}


	@Override
	public List<InterCityRides> interCityRider(InterCityRides interCityRide) {
		
		List<InterCityRides> listRiders = null;
		listRiders = jdbcTemplate.query(INTER_CITY_RIDES_SELECT_RIDERS, new Object[] {interCityRide.getSource(), interCityRide.getDestination()}, new InterCityRiderRowMapper());
		if(AppUtils.isNullOEmptyList(listRiders)) {
			return null;
		} else { 
			return listRiders;
		}
	}


	@Override
	public List<InterCityRides> interCityDriver(InterCityRides interCityRide) {
		List<InterCityRides> listDrivers = null;
		listDrivers = jdbcTemplate.query(INTER_CITY_RIDES_SELECT_DRIVERS, new Object[] {interCityRide.getSource(), interCityRide.getDestination()}, new InterCityRiderRowMapper());
		if(AppUtils.isNullOEmptyList(listDrivers)) {
			return null;
		} else { 
			return listDrivers;
		}
		
	}
	
	




}
