package com.techpp.dao;

import com.techpp.modal.User;



public interface UserDao {

	
	public final static String FIND_USER_BY_MOBILE_NUM = "select * from users where mobile_no = ?";
	public final static String SAVE_NEW_USER = "insert into users (mobile_no, first_name, last_name, email_id, group_code, roles) VALUES (?,?,?,?,?,?)";
	public final static String  UPDATE_USER = "UPDATE users  SET first_name = ? , last_name = ? , email_id = ? ,group_code = ? ,roles = ? WHERE mobile_no= ?";
	public final static String CHANGE_ROLES = "UPDATE users  SET roles = ? WHERE mobile_no= ?";
	
	public abstract User findUserByContact(String contactNumber);
	public abstract int saveUser(User user);
	public abstract int editUser(User user);
	public abstract int changeRoles(User user);



}

