package com.techpp.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.techpp.dao.RideHistoryDao;
import com.techpp.dao.rowmapper.RideHistoryRowMapper;
import com.techpp.modal.RideHistory;
import com.techpp.utils.AppUtils;

@Repository("rideHistory")
public class RideHistoryDaoImpl implements RideHistoryDao {

	@Autowired
	JdbcTemplate jdbcTemplate;
	
	@Override
	public List<RideHistory> rideHistory(String mobileNumber) {
		
		List<RideHistory> rideHistories = null;
		rideHistories = jdbcTemplate.query(RIDE_HISTORY_BY_MOBILE, new Object[] {mobileNumber} ,new RideHistoryRowMapper());
		
		if(AppUtils.isNullOEmptyList(rideHistories)) {
			return null;
		}
		else {
			return rideHistories;
		}
	}
}
