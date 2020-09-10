package com.techpp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.techpp.modal.ResponseObject;
import com.techpp.modal.VehicleDetails;
import com.techpp.service.VehicleDetailsService;

@RestController
public class VehicleDetailsController {
	
	@Autowired
	VehicleDetailsService vehicleDetailsService; 
	
	@PostMapping(value = "/addvehicledetails")
	public ResponseObject addVehicleDetails(@ModelAttribute("vehicleDetails") VehicleDetails vehicleDetails) {
		ResponseObject response = null;
		response = vehicleDetailsService.saveVehicleDetails(vehicleDetails);	
		return response;	
	}

}
