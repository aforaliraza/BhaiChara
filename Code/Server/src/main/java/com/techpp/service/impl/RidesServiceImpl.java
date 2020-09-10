package com.techpp.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.techpp.dao.DriversDao;
import com.techpp.dao.GroupDao;
import com.techpp.dao.RideDao;
import com.techpp.dao.UserDao;
import com.techpp.dao.VehicleDetailsDao;
import com.techpp.modal.Driver;
import com.techpp.modal.Group;
import com.techpp.modal.InterCityRides;
import com.techpp.modal.ResponseObject;
import com.techpp.modal.Ride;
import com.techpp.modal.Rider;
import com.techpp.modal.User;
import com.techpp.modal.VehicleDetails;
import com.techpp.service.RidesCacheManagerService;
import com.techpp.service.RidesService;
import com.techpp.utils.AppUtils;
import com.techpp.utils.RequestTypes;
import com.techpp.utils.RespCodes;
import com.techpp.utils.RideStatus;
import com.techpp.utils.RideTypes;

@Service
public class RidesServiceImpl implements RidesService {

	@Autowired
	RideDao rideDao;
	@Autowired
	UserDao userDao;
	@Autowired
	GroupDao groupDao;
	@Autowired
	DriversDao driverDao;
	@Autowired
	VehicleDetailsDao vehicleDetailsDao;
	@Autowired
	RidesCacheManagerService rideCMSI;
	

	@Override
	public ResponseObject rideStart(Ride ride) {
		
		VehicleDetails vehicleDetails = null;
		Driver driver = null;
		Group group = null;
		User user = null;
		
		int rows;
		ResponseObject response = new ResponseObject();
		
		    user = null;
			driver = null;
			group = null;
			
		
			driver = driverDao.findDriverByContact(ride.getMobileNumber());
			user = userDao.findUserByContact(ride.getMobileNumber());			
			if(AppUtils.isNull(user)) {
				response.setResponseCode(RespCodes.NO_DATA_FOUND);
				response.setResponseDesc("Invalid User");
				return response;
			}
			
			driver.setUser(user);
			
			group = groupDao.findByGoupCode(user.getGroupCode());
			//driver = driverDao.findDriverByContact(ride.getMobileNumber());
			if(AppUtils.isNull(driver)) {
		
				response.setResponseCode(RespCodes.NO_DATA_FOUND);
				response.setResponseDesc("Invalid Driver");
				return response;
			}
			driver.setGroupCode(user.getGroupCode());
			
   			vehicleDetails = vehicleDetailsDao.findVehicalByMobileNumber(driver.getMobileNumber());
			if(AppUtils.isNull(vehicleDetails)) {
				response.setResponseCode(RespCodes.NO_DATA_FOUND);
				response.setResponseDesc("No vehical found for this driver");
				return response;
			}
			driver.setVehicle(vehicleDetails);
			
			if(AppUtils.isNull(ride)) {
				response.setResponseCode(RespCodes.NO_DATA_FOUND);
				response.setResponseDesc("Invalid User");
				return response;
			}
			driver.setRides(ride);
			driver.setCurentLocationLat(ride.getStartLat());
			driver.setCurentLocationLong(ride.getStartLong());
			driver.setStatus("Active");
			
		
			
			rows = rideDao.rideStart(ride);
			rideCMSI.addDriver(group, driver);
			
			response.setResponseCode(RespCodes.SUCCESS);
			response.setResponseDesc("Success");
					
		
		return response;
		
	}

	@Override
	public ResponseObject rideUpdate(Ride ride) {
		VehicleDetails vehicleDetails = null;
		Driver driver = null;
		Group group = null;
		User user = new User();
		
		int rows;
		ResponseObject response = new ResponseObject();
		
		    user = null;
			driver = null;
			group = null;
			
		
			driver = driverDao.findDriverByContact(ride.getMobileNumber());
			user = userDao.findUserByContact(ride.getMobileNumber());			
			if(AppUtils.isNull(user)) {
				response.setResponseCode(RespCodes.NO_DATA_FOUND);
				response.setResponseDesc("Invalid User");
				return response;
			}
			driver.setUser(user);
			
			group = groupDao.findByGoupCode(user.getGroupCode());
			//driver = driverDao.findDriverByContact(ride.getMobileNumber());
			if(AppUtils.isNull(driver)) {
		
				response.setResponseCode(RespCodes.NO_DATA_FOUND);
				response.setResponseDesc("Invalid Driver");
				return response;
			}
			driver.setGroupCode(user.getGroupCode());
			
   			vehicleDetails = vehicleDetailsDao.findVehicalByMobileNumber(driver.getMobileNumber());
			if(AppUtils.isNull(vehicleDetails)) {
				response.setResponseCode(RespCodes.NO_DATA_FOUND);
				response.setResponseDesc("No vehical found for this driver");
				return response;
			}
			driver.setVehicle(vehicleDetails);
			
			if(AppUtils.isNull(ride)) {
				response.setResponseCode(RespCodes.NO_DATA_FOUND);
				response.setResponseDesc("Invalid User");
				return response;
			}
			driver.setRides(ride);
			driver.setCurentLocationLat(ride.getStartLat());
			driver.setCurentLocationLong(ride.getStartLong());
			driver.setStatus("Active");
			
		
			
			rows = rideDao.rideUpdate(ride);
			rideCMSI.updateDriverLocation(group, driver);
			rideCMSI.updateVehicalStatus(group, driver);
			
			response.setResponseCode(RespCodes.SUCCESS);
			response.setResponseDesc("Success");
					
		
		return response;
	
		
			}

	@Override
	public ResponseObject rideEnd(Ride ride) {
		
		Driver driver = null;
		Group group = null;
		User user = null;
		int rows;
		ResponseObject response = new ResponseObject();
		

		rows = rideDao.rideEnd(ride);

		if(rows > 0){
			response.setResponseCode(RespCodes.SUCCESS);
			response.setResponseDesc("Success");
			
			user = new User();
			driver = new Driver();
			group = new Group();
			
			user = userDao.findUserByContact(ride.getMobileNumber());
			group = groupDao.findByGoupCode(user.getGroupCode());
			driver = driverDao.findDriverByContact(user.getGroupCode());
			
			rideCMSI.removeDriver(group, driver);
		}else {
			response.setResponseCode(RespCodes.REQUEST_FAILED);
			response.setResponseDesc("Request Failed");
		}
		return response;
	}
	
	@Override
	public ResponseObject confirmDriver(String contactNumberUser,String contactNumberDriver, String  pickUpLat, 
			String pickUpLong, String destinationLat, String destinationLong, String groupCode, String rideType) {
		
		User user = null;
		Rider rider = new Rider();
		Driver driver = null;
		
		ResponseObject response = new ResponseObject();
		user = userDao.findUserByContact(contactNumberUser);
		if(AppUtils.isNull(user)) {
			response.setResponseCode("05");
			response.setResponseDesc("No Record Found");
			return response;
		} else {
			if(AppUtils.isNullOEmptyMap(rideCMSI.getDriversMapByGroupCode(groupCode))) {
				response.setResponseCode(RespCodes.NO_DATA_FOUND);
				response.setResponseDesc("No Driver Found for the given group code");
				return response;
			}
			Date date = new Date();
			SimpleDateFormat timeform = new SimpleDateFormat("hh:mm:ss"); 
 
			rider.setMobileNumber(user.getMobileNumber());
			rider.setGroupCode(user.getGroupCode());
			rider.setSrcLat(pickUpLat);
			rider.setSrcLong(pickUpLong);
			rider.setDestLat(destinationLat);
			rider.setDestLong(destinationLong);
			rider.setTypeOfRide(rideType);
			rider.setRideStartTime(timeform.format(date));
			rider.setRideStatus(RideStatus.UN_CONFIRMED);
			rider.setRiderId(" ");
			rideDao.bookARide(rider);
			if(!AppUtils.isNull(rideCMSI.getDriversMapByGroupCode(groupCode).get(contactNumberDriver))) {
			rideCMSI.getDriversMapByGroupCode(groupCode).get(contactNumberDriver).addToRidersList(rider);
			driver = rideCMSI.getDriversMapByGroupCode(groupCode).get(contactNumberDriver);
			response.setResponseCode(RespCodes.SUCCESS);
			response.setResponseDesc("Driver Assigned");
			response.setPayload(driver);
			response.setRequestType(RequestTypes.REQUEST_RIDE);
			return response;
			}
			else {
				response.setResponseCode(RespCodes.NO_DATA_FOUND);
				response.setResponseDesc("No data Found ");
				response.setPayload(null);
				response.setRequestType(RequestTypes.REQUEST_RIDE);
				return response;
				
				
			}
		}
				
	}

	@Override
	public ResponseObject confirmRide(String groupCode, String mobileNumber) {
		Driver driver = null;
		ResponseObject response = new ResponseObject();
		if(AppUtils.isNull(rideCMSI.getDriversMapByGroupCode(groupCode).get(mobileNumber))) {
			response.setResponseCode(RespCodes.NO_DATA_FOUND);
			response.setResponseDesc("No data found for the given criteria");
			response.setRequestType(RequestTypes.REQUEST_RIDE);
			response.setPayload(null);
			return response;
		}
	
		driver = rideCMSI.getDriversMapByGroupCode(groupCode).get(mobileNumber);
		response.setPayload(driver);
		response.setResponseCode(RespCodes.SUCCESS);
		response.setRequestType(RequestTypes.REQUEST_RIDE);
		response.setResponseDesc("Data refreshed");
		
		return response;
	}

	@Override
	public ResponseObject confirmCancelRide(String groupCode, String mobileNumber, String riderMobileNumber, String rideStatus, String requestType) {
		Driver driver = null;
		ResponseObject response = new ResponseObject();
		if(AppUtils.isNull(rideCMSI.getDriversMapByGroupCode(groupCode).get(mobileNumber))) {
			response.setResponseCode(RespCodes.NO_DATA_FOUND);
			response.setResponseDesc("No data found for the given criteria");
			response.setRequestType(requestType);
			return response;
		}
		
	
		driver = rideCMSI.getDriversMapByGroupCode(groupCode).get(mobileNumber);
		for(Rider rider : driver.getRiders()) {
			if(rider.getMobileNumber().equals(riderMobileNumber)) {
				rider.setRideStatus(rideStatus);
			}
		}
		response.setPayload(driver);
		response.setResponseCode(RespCodes.SUCCESS);
		response.setRequestType(requestType);
		response.setResponseDesc("Data refreshed");
		
		return response;
		
			}

	@Override
	public ResponseObject interCityRidesInsert(InterCityRides interCityRides) {
		  
		ResponseObject response = new ResponseObject(); 
          int rows;
         
         
       	  rows = rideDao.interCityRidesInsert(interCityRides); 
          if(rows>0){
        	  response.setResponseCode(RespCodes.SUCCESS);
        	  response.setResponseDesc("Success");
        	 
          }
          else {
        	  response.setResponseCode(RespCodes.REQUEST_FAILED);
        	  response.setResponseDesc("Request Failed");
          }
          
		return response;
		}

	@Override
	public ResponseObject interCitySelectRiders(InterCityRides interCityRide) {
		
		ResponseObject response = new ResponseObject(); 
		List<InterCityRides> ridersList;
		ridersList = rideDao.interCityRider(interCityRide);
		if(AppUtils.isNullOEmptyList(ridersList)) {
			response.setPayload(null);
			response.setResponseCode(RespCodes.REQUEST_FAILED);
			response.setResponseDesc("Request cannot be processed, try later");
			
		}
		response.setPayload(ridersList);
		response.setResponseCode(RespCodes.SUCCESS);
		response.setResponseDesc("Request Succesfull");
		return response;
	}

	@Override
	public ResponseObject interCitySelectDrivers(InterCityRides interCityRide) {
	
		ResponseObject response = new ResponseObject(); 
		List<InterCityRides> driversList;
		driversList = rideDao.interCityDriver(interCityRide);
		if(AppUtils.isNullOEmptyList(driversList)) {
			response.setPayload(null);
			response.setResponseCode(RespCodes.REQUEST_FAILED);
			response.setResponseDesc("Request cannot be processed, try later");
			
		}
		response.setPayload(driversList);
		response.setResponseCode(RespCodes.SUCCESS);
		response.setResponseDesc("Request Succesfull");
		return response;
	
	
	}
	
	

}
