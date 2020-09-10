package com.techpp.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.techpp.dao.LocationsDao;
import com.techpp.dao.rowmapper.LocationsRowMapper;
import com.techpp.modal.Location;


@Repository("PickUpLocationsDao")
public class LocationsImpl implements LocationsDao  {

	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	
	@Override
	public List<Location> findLocationBySuggestion(String address) {

		List<Location> location = null;
		location = jdbcTemplate.query(FIND_LOCATION_BY_SUGGESTION, new Object[] {"%"+address+"%"}, new LocationsRowMapper());
		
		
		return location;
	}

}
