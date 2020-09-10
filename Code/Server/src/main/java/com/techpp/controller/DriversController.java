package com.techpp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.techpp.modal.Driver;
import com.techpp.modal.ResponseObject;
import com.techpp.service.DriversService;
import com.techpp.service.RidesCacheManagerService;
import com.techpp.service.RidesService;
import com.techpp.utils.DriverStatus;
import com.techpp.utils.RequestTypes;

@RestController
public class DriversController {

	@Autowired
	DriversService driversService; 
	@Autowired
    RidesCacheManagerService rideCache;
	@Autowired
	RidesService ridesService;
	
	@PostMapping(value = "/createdriversaccount")
	public ResponseObject createdriversaccount(@ModelAttribute("drivers") Driver driver) {
		ResponseObject response = null;
		response = driversService.insertDriver(driver);	
		return response;	
	}


	@PostMapping(value = "/checkdriversaccount")
	public ResponseObject checkVehicalInfoExist(@RequestParam("mobilenum") String mobileNum) {
		ResponseObject response = null;
		response = driversService.findDriverByContact(mobileNum);	
		return response;	
	}

	@PostMapping(value = "/driversonline")
	public ResponseObject finddriversaccount(@RequestParam("groupCode")  String groupCode) {
		ResponseObject response = null;
		response = driversService.findDriversByStatus(groupCode, DriverStatus.ONLINE);	
		return response;	
	}

	@PostMapping(value = "/changedriverstatus")
	public ResponseObject changestatus(@ModelAttribute Driver driver) {
		ResponseObject response = null;
		response = driversService.changeStatus(driver);	
		return response;	
	}
	
	@PostMapping(value = "/driversByGroupCode")
	public ResponseObject findDriversByGroupCode(@RequestParam("groupCode") String groupCode) {
	    ResponseObject response = null;
	    response = rideCache.getDriversByGroupCode(groupCode);
	    response.setRequestType(RequestTypes.FIND_DRIVERS);
	    return response;
	}

}
