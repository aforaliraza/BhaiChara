package com.techpp.dao;

import java.util.List;

import com.techpp.modal.RideHistory;

public interface RideHistoryDao {

	public final static String RIDE_HISTORY_BY_MOBILE = "SELECT "
			+ "driver_mobile_no, ride_start_lat, ride_start_long, ride_end_lat, ride_end_long, start_date, start_time, end_date, end_time "
			+ "FROM  rides "
			+ "WHERE driver_mobile_no = ?";
	
	public abstract List<RideHistory> rideHistory(String mobileNumber);
}
