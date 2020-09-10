package com.techpp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.techpp.modal.ResponseObject;
import com.techpp.service.RideHistoryService;

@RestController
public class RideHistoryController {

	@Autowired
	RideHistoryService rideHistoryService;
	
	@PostMapping(value = "/rideHistory")
	public ResponseObject RideHistory(@RequestParam("mobileNumber") String mobileNumber) {
		
		ResponseObject response = new ResponseObject();
		response = rideHistoryService.rideHistory(mobileNumber);
		return response;
	}
}
