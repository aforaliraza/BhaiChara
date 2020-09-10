package com.techpp.service;

import com.techpp.modal.Driver;
import com.techpp.modal.ResponseObject;

public interface DriversService {

	public abstract ResponseObject insertDriver(Driver driver);
	public abstract ResponseObject findDriverByContact(String contactNumber);
	public abstract ResponseObject findDriversByStatus(String groupCode, String status);
	public abstract ResponseObject changeStatus(Driver driver);
	
}
