package com.techpp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.techpp.modal.ResponseObject;
import com.techpp.service.LocationsService;

@RestController
public class LocationsController {

@Autowired
LocationsService locationsService;

	@PostMapping("/getlocations")
	public ResponseObject getLocationsBySuggestions(String suggestion) {
		return locationsService.findLocationBySuggestion(suggestion);
	}
	
}
