package com.techpp.dao.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.techpp.modal.Group;

public class GroupsRowMapper implements RowMapper<Group>{

	@Override
	public Group mapRow(ResultSet rs, int rowNum) throws SQLException {
		Group group = null;
		group = new Group();
		
		group.setName(rs.getString("name"));
		group.setAddress(rs.getString("address"));
		group.setGroupCode(rs.getString("group_code"));
		group.setLongitude(rs.getString("longitude"));
		group.setLatitude(rs.getString("latitude"));
		group.setMl(rs.getString("ml"));
		

		
		
		return group;
	}

}
