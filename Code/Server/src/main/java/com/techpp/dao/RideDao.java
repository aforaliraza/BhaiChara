package com.techpp.dao;

import java.util.List;

import com.techpp.modal.Driver;
import com.techpp.modal.InterCityRides;
import com.techpp.modal.Ride;
import com.techpp.modal.Rider;
import com.techpp.modal.VehicleDetails;

public interface RideDao {
	
	public final static String BOOK_A_RIDE = "insert into rides (driver_mobile_no, ride_start_lat, ride_start_long, ride_end_lat, ride_end_long, ride_type, ride_status, start_date, start_time) VALUES (?,?,?,?,?,?,?,?,?)";
	public final static String START_RIDE = "UPDATE rides SET ride_end_lat = ?, ride_end_long = ?,ride_status = ?, end_date = ?, end_time = ? WHERE driver_mobile_no = ?";
	public final static String UPDATE_RIDE = "UPDATE rides SET ride_end_lat = ?, ride_end_long = ?,ride_status = ?, end_date = ?, end_time = ? WHERE driver_mobile_no = ?";
	public final static String END_RIDE = "UPDATE rides SET ride_end_lat = ?, ride_end_long = ?,ride_status = ?, end_date = ?, end_time = ?, comment = ? WHERE driver_mobile_no = ?";
	public final static String ALL_ACTIVE_RIDES ="SELECT u.mobile_no, u.first_name, u.last_name,u.group_code, r.ride_start_lat, r.ride_start_long, r.ride_type, r.ride_status, v.car_type, v.seat_available, v.car_number, v.source, v.destination, v.date, v.time  FROM users u,vehicle_details v,rides r  WHERE r.driver_mobile_no= v.mobile_no AND r.driver_mobile_no = u.mobile_no and r.ride_status = ?;";
	public final static String INTER_CITY_RIDES_INSERT = "INSERT INTO inter_city_rides(mobile_no, source, destination, car_type, car_number, seats_capacity, roles, date, time, status) VALUES (?,?,?,?,?,?,?,?,?,?)"; 
	public final static String INTER_CITY_RIDES_SELECT_RIDERS = "SELECT mobile_no, source, destination, seats_capacity, date, time FROM inter_city_rides WHERE roles = 'Rider' AND source=? AND destination=?";
	public final static String INTER_CITY_RIDES_SELECT_DRIVERS = "SELECT mobile_no, source, destination, car_type, car_number, seats_capacity, date, time FROM inter_city_rides  WHERE roles = 'Driver' AND source = ? AND destination = ?";
			
	public abstract int rideStart(Ride ride);
	public abstract int interCityRidesInsert(InterCityRides interCityRide);
	public abstract int rideUpdate(Ride ride);
	public abstract int rideEnd(Ride ride);
	public abstract int bookARide(Rider rider);
	public abstract List<Driver> findAllActiveRides();
	public abstract List<InterCityRides> interCityRider(InterCityRides interCityRide);
	public abstract List<InterCityRides> interCityDriver(InterCityRides interCityRide);
	
}
