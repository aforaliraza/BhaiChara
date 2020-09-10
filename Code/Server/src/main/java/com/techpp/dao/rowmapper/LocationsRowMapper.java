package com.techpp.dao.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.techpp.modal.Location;

public class LocationsRowMapper implements RowMapper<Location> {
	
	@Override
	public Location mapRow(ResultSet rs, int rowNum) throws SQLException {
		Location location = null;

		location = new Location();
		location.setAddrees(rs.getString("address"));
		location.setCategory(rs.getString("category"));
		location.setTitle(rs.getString("title"));
		location.setCity(rs.getString("city"));
		location.setLongitude(rs.getFloat("latitude"));
		location.setLongitude(rs.getFloat("longitude"));
		return location;
	}


}
