package com.techpp.dao.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.techpp.modal.User;

public class UserRowMapper implements RowMapper<User>{

	@Override
	public User mapRow(ResultSet rs, int rowNum) throws SQLException {
		User user = null;

		user = new User();
		user.setMobileNumber(rs.getString("mobile_no"));
		user.setFirstName(rs.getString("first_name"));
		user.setLastName(rs.getString("last_name"));
		user.setEmailId(rs.getString("email_id"));
		user.setGroupCode(rs.getString("group_code"));
		user.setRoles(rs.getString("roles"));
		return user;
	}

}
