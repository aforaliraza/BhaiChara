package com.techpp.dao.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.techpp.modal.RideHistory;

public class RideHistoryRowMapper implements RowMapper<RideHistory> {

	@Override
	public RideHistory mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		RideHistory rideHistory = new RideHistory();
		
		rideHistory.setMobileNumber(rs.getString("driver_mobile_no"));
		rideHistory.setStartLat(rs.getString("ride_start_lat"));
		rideHistory.setStartLong(rs.getString("ride_start_long"));
		rideHistory.setEndLat(rs.getString("ride_end_lat"));
		rideHistory.setEndLong(rs.getString("ride_end_long"));
		rideHistory.setStartDate(rs.getString("start_date"));
		rideHistory.setStartTime(rs.getString("start_time"));
		rideHistory.setEndDate(rs.getString("end_date"));
		rideHistory.setEndTime(rs.getString("end_time"));
		
		return rideHistory;
	}

}
