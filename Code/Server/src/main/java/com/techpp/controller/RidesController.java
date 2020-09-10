package com.techpp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.techpp.modal.InterCityRides;
import com.techpp.modal.ResponseObject;
import com.techpp.modal.Ride;
import com.techpp.service.RidesService;


@RestController
public class RidesController {
	
	@Autowired
	RidesService ridesService;
	
	@PostMapping(value = "/ridestart")
	public ResponseObject rideStart(@ModelAttribute("ride") Ride ride) {
		
		ResponseObject response = null;
		response = ridesService.rideStart(ride);
		return response;
	}
	
	@PostMapping(value = "/rideupdate")
	public ResponseObject rideUpdate(@ModelAttribute("ride") Ride ride) {
		
		ResponseObject response = null;
		response = ridesService.rideUpdate(ride);
		return response;
	}
	
	@PostMapping(value = "/rideEnd")
	public ResponseObject rideEnd(@ModelAttribute("ride") Ride ride) {
		
		ResponseObject response = null;
		response = ridesService.rideEnd(ride);
		return response;
	}
	
	@PostMapping(value = "/bookaride")
	public ResponseObject getUserInfoByMobile(@RequestParam("mobilenumuser") String mobileNumUser, @RequestParam("mobilenumdriver")
	String mobileNumDriver, @RequestParam("pickuplat") String pickUpLat, @RequestParam("pickuplong") String pickUpLong,
	@RequestParam("destinationlat") String destinationLat, @RequestParam("destinationlong") String destinationLong,
	@RequestParam("groupcodeuser") String groupCode, @RequestParam("rideType") String rideType) {
		
		ResponseObject response = null;
		response = ridesService.confirmDriver(mobileNumUser, mobileNumDriver, pickUpLat, pickUpLong, destinationLat, destinationLong,
				groupCode, rideType);
		return response;
	}
	
	@PostMapping(value = "/confirmRide")
	public ResponseObject confirmRide(@RequestParam("groupCode") String groupCode, @RequestParam ("mobileNumber") String mobileNumber) {
	    ResponseObject response = null;    
	   response = ridesService.confirmRide(groupCode, mobileNumber);
	    return response;
	}
	
	@PostMapping(value = "/confirmOrCancelRide")
	public ResponseObject confirmOrCancelRide(@RequestParam("groupCode") String groupCode, @RequestParam ("mobileNumber") String mobileNumber,
    @RequestParam ("riderMobileNumber") String riderMobileNumber, @RequestParam("rideStatus") String rideStatus, @RequestParam("requestType") String requestType) {
	    ResponseObject response = null;    
	   response = ridesService.confirmCancelRide(groupCode, mobileNumber, riderMobileNumber, rideStatus, requestType);
	    return response;
	}
	
	@PostMapping(value = "/intercityRidesInsert")
	public ResponseObject interCityRides(@ModelAttribute("interCityRides") InterCityRides interCityRides) {
	    ResponseObject response = null;
	    response = ridesService.interCityRidesInsert(interCityRides);
	    return response;
	}
	
	@PostMapping(value = "/intercityRiders")
	public ResponseObject interCityRidersList(@ModelAttribute("interCityRides") InterCityRides interCityRides) {
		 ResponseObject response = null;
		 response = ridesService.interCitySelectRiders(interCityRides);
		 return response;
	}
	
	@PostMapping(value = "/intercityDrivers")
	public ResponseObject interCityDriversList(@ModelAttribute("interCityRides") InterCityRides interCityRides) {
		 ResponseObject response = null;
		 response = ridesService.interCitySelectDrivers(interCityRides);
		 return response;
	}
	
	


}


