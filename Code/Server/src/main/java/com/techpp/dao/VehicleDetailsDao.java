package com.techpp.dao;


import com.techpp.modal.VehicleDetails;

public interface  VehicleDetailsDao {
	
	public final static String SAVE_VEHICLE_DETAILS = "insert into vehicle_details (mobile_no, car_type, seat_available, car_number, source, destination ,date ,time) VALUES (?,?,?,?,?,?,?,?)";
	public final static String FIND_VEHICLE_BY_MOBILE_NUM = "select * from vehicle_details where mobile_no = ?";
	
	public abstract int saveVehicleDetails(VehicleDetails vehicleDetails);
	public abstract VehicleDetails findVehicalByMobileNumber(String mobileNumber);
	
}
