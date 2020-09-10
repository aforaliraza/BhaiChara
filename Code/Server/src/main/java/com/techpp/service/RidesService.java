package com.techpp.service;

import com.techpp.modal.InterCityRides;
import com.techpp.modal.ResponseObject;
import com.techpp.modal.Ride;

public interface RidesService {
	
	
	public abstract ResponseObject rideStart(Ride ride);
	public abstract ResponseObject rideUpdate(Ride ride);
	public abstract ResponseObject rideEnd(Ride ride);
	public abstract ResponseObject confirmRide(String groupCode, String mobileNumber);
	public abstract ResponseObject interCityRidesInsert(InterCityRides interCityRide);
	public abstract ResponseObject interCitySelectRiders(InterCityRides interCityRide);
	public abstract ResponseObject interCitySelectDrivers(InterCityRides interCityRide);
	public abstract ResponseObject confirmCancelRide(String groupCode, String mobileNumber, String riderMobileNumber, String rideStatus, String requestType);
	public abstract ResponseObject confirmDriver(String contactNumberUser,String contactNumberDriver,  
			String  pickUpLat, String pickUpLong, String destinationLat, String destinationLong, String groupCode
			, String rideType);

}
