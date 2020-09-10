package com.techpp.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.techpp.dao.GroupDao;
import com.techpp.dao.RideDao;
import com.techpp.modal.Driver;
import com.techpp.modal.Group;
import com.techpp.modal.ResponseObject;
import com.techpp.modal.Ride;
import com.techpp.modal.User;
import com.techpp.modal.VehicleDetails;
import com.techpp.service.RidesCacheManagerService;
import com.techpp.utils.AppUtils;
import com.techpp.utils.RequestTypes;
import com.techpp.utils.RespCodes;

@Service
public class RidesCacheManagerServiceImpl implements RidesCacheManagerService {

	private static Map<String, Map<String, Driver>> driversCache = new HashMap<String, Map<String,Driver>>();

	@Autowired
	GroupDao groupDao;
	
	@Autowired
	RideDao rideDao;

	public void initCache() {		

		List<Driver> activeDrivers = null;
		List<Group> allGroupsList = groupDao.findAllGroups();
		for(Group group : allGroupsList){				
				driversCache.put(group.getGroupCode() , new HashMap<String, Driver>());
		}
		
		activeDrivers = rideDao.findAllActiveRides();
		if(AppUtils.isNullOEmptyList(activeDrivers)) {
			return;
		}
		for (Driver driver : activeDrivers) {
			driversCache.get(driver.getGroupCode()).put(driver.getMobileNumber(), driver);
		}
	}

	public void addDriver(Group group, Driver driver) {
		
		Map<String, Driver> driversByCompany = driversCache.get(group.getGroupCode());

		driversByCompany.put(driver.getMobileNumber(), driver);
	}

	public void removeDriver(Group group, Driver driver) {
		
		Map<String, Driver> driversByCompany = driversCache.get(group.getGroupCode());

		if(AppUtils.isNullOEmptyMap(driversByCompany)) {

		} else {
			driversByCompany.remove(driver.getMobileNumber());
		}
	}

	public void updateDriverLocation(Group group, Driver driver) {
		
		Map<String, Driver> driversByCompany = driversCache.get(group.getGroupCode());
		Driver cachedDriver = null;
		if(AppUtils.isNullOEmptyMap(driversByCompany)) {

		} else {
			cachedDriver = driversByCompany.get(driver.getMobileNumber());
			cachedDriver.setCurentLocationLat(driver.getCurentLocationLat());
			cachedDriver.setCurentLocationLong(driver.getCurentLocationLong());
		}
	}

	public void updateVehicalStatus(Group group, Driver driver) {
		
		Map<String, Driver> driversByCompany = driversCache.get(group.getGroupCode());
		Driver cachedDriver = null;
		if(AppUtils.isNullOEmptyMap(driversByCompany)) {

		} else {
			cachedDriver = driversByCompany.get(driver.getMobileNumber());
			cachedDriver.setStatus(driver.getStatus());

		}
	}
	
	//public List<Driver> getDriversByGroupCode(String groupCode){
	public ResponseObject getDriversByGroupCode(String groupCode){
		ResponseObject response = new ResponseObject();
		
		Map<String, Driver> driversByGroupCode = driversCache.get(groupCode);
		
		if(!AppUtils.isNullOEmptyMap(driversByGroupCode)) {
			
			response.setResponseCode(RespCodes.SUCCESS);
			response.setResponseDesc("Driver Data found");
			response.setPayload(new ArrayList<Driver>(driversByGroupCode.values()));
		
			
			//return new ArrayList<Driver>(driversByGroupCode.values());
		} else {
			response.setResponseCode(RespCodes.NO_DATA_FOUND);
			response.setResponseDesc("No Record Found");
			
		}
		
		return response;
	}
	
	public 	Map<String, Driver> getDriversMapByGroupCode(String groupCode){
        
		Map<String, Driver> driversByGroupCode = driversCache.get(groupCode);
		
		if(!AppUtils.isNullOEmptyMap(driversByGroupCode)) {
			
			return driversByGroupCode;
			
		} else {
			return null;
		}
	}

	@Override
	public ResponseObject getDriver(String groupCode, String mobileNumber) {
	
		ResponseObject responseObject = new ResponseObject();
		Map<String, Driver> driversByGroupCode = driversCache.get(groupCode);
		
		if(!AppUtils.isNullOEmptyMap(driversByGroupCode)) {
			
			if(!AppUtils.isNull(driversByGroupCode.get(mobileNumber))) {
			
	        responseObject.setPayload(driversByGroupCode.get(mobileNumber));
	        responseObject.setResponseCode(RespCodes.SUCCESS);
	        responseObject.setResponseDesc("Driver data found");
	        responseObject.setRequestType(RequestTypes.FIND_DRIVERS);
	        return responseObject;
			}
			else {
				  responseObject.setPayload(null);
			        responseObject.setResponseCode(RespCodes.NO_DATA_FOUND);
			        responseObject.setResponseDesc("No Driver found for the given mobile number");
			        responseObject.setRequestType(RequestTypes.FIND_DRIVERS);
			        return responseObject;
			}
			
		} else {
			 responseObject.setPayload(null);
		        responseObject.setResponseCode(RespCodes.NO_DATA_FOUND);
		        responseObject.setResponseDesc("No Driver found for the given groupcode");
		        responseObject.setRequestType(RequestTypes.FIND_DRIVERS);
		        return responseObject;
		}
	
	}
	
}

