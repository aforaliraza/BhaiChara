package com.techpp.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.techpp.dao.UserDao;
import com.techpp.dao.rowmapper.UserRowMapper;

import com.techpp.modal.User;
import com.techpp.utils.AppUtils;

@Repository("userDao")
public class UserDaoImpl implements UserDao{

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public User findUserByContact(String contactNumber) {
		List<User> users = null;

		users = jdbcTemplate.query(FIND_USER_BY_MOBILE_NUM, new Object[] {contactNumber}, new UserRowMapper());
		if(AppUtils.isNullOEmptyList(users)) {
			return null;
		} else 
			return users.get(0);
		}

	@Override
	public int saveUser(User user) {
		int rows = 0;
		rows = jdbcTemplate.update(SAVE_NEW_USER, user.getMobileNumber(), user.getFirstName(), user.getLastName(), user.getEmailId(), user.getGroupCode(),user.getRoles());
		return rows ;
	}
	
	@Override
	public int editUser(User user) {

		int rows =0;
		rows = jdbcTemplate.update(UPDATE_USER  ,user.getFirstName(), user.getLastName(), user.getEmailId(), user.getGroupCode(), user.getRoles()  , user.getMobileNumber() );
		return rows ;
	}
	
	public int changeRoles(User user) {
		
		int rows = 0;
		rows = jdbcTemplate.update(CHANGE_ROLES  ,user.getRoles() ,user.getMobileNumber());
		return rows;
	}
	

}
