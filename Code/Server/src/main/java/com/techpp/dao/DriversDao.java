package com.techpp.dao;

import java.util.List;

import com.techpp.modal.Driver;

public interface DriversDao {
	
	
	public final static String SAVE_NEW_DRIVER = "insert into drivers (mobile_no, licence_no) VALUES (?,?)";
	public final static String FIND_DRIVER_BY_MOBILE_NUM = "select * from drivers d where mobile_no = ?";
	public final static String FIND_DRIVERS_BY_STATUS = "SELECT * FROM drivers d, users u WHERE u.group_code = ? And d.status = ?";
	public final static String CHANGE_STATUS = "UPDATE drivers  SET status = ? WHERE mobile_no= ?";
	
	
	public abstract int insertDriver(Driver driver);
	public abstract Driver findDriverByContact(String contactNumber);
	public abstract List<Driver> findDriversByStatus(String groupCode, String status);
	public abstract int changeStatus(Driver driver);
	
}
