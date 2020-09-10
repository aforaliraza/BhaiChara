package com.techpp.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.techpp.dao.DriversDao;
import com.techpp.modal.Driver;
import com.techpp.modal.ResponseObject;
import com.techpp.service.DriversService;
import com.techpp.utils.AppUtils;
import com.techpp.utils.RequestTypes;
import com.techpp.utils.RespCodes;

@Service
public class DriversServiceImpl implements DriversService {

	@Autowired
	DriversDao driversDao;

	@Override
	public ResponseObject insertDriver(Driver driver) {


		ResponseObject response = new ResponseObject();

		int rows;

		rows = driversDao.insertDriver(driver);

		if(rows > 0){
			response.setResponseCode(RespCodes.SUCCESS);
			response.setResponseDesc("Success");
		}else {
			response.setResponseCode(RespCodes.REQUEST_FAILED);
			response.setResponseDesc("Request Failed");
		}
		return response;
	}

	@Override
	public ResponseObject findDriverByContact(String contactNumber) {

		Driver driver = null;		
		ResponseObject response = new ResponseObject();
		response.setRequestType(RequestTypes.FIND_DRIVERS);
		driver = driversDao.findDriverByContact(contactNumber);

		if(AppUtils.isNull(driver)) {
			response.setResponseCode(RespCodes.NO_DATA_FOUND);
			response.setResponseDesc("No Record Found");
		} else {
			response.setResponseCode(RespCodes.SUCCESS);
			response.setResponseDesc("Driver Data found");
			response.setPayload(driver);
		}
		return response;
	}

	@Override
	public ResponseObject findDriversByStatus(String groupCode, String status) {

		List<Driver> driver = null;		
		ResponseObject response = new ResponseObject();
		
		driver = driversDao.findDriversByStatus(groupCode, status);

		if(AppUtils.isNull(driver)) {
			response.setResponseCode(RespCodes.NO_DATA_FOUND);
			response.setResponseDesc("No Record Found");	
		} else {
			response.setResponseCode(RespCodes.SUCCESS);
			response.setResponseDesc("Driver Data found");
			response.setPayload(driver);
		}		
		return response;
	}

	@Override
	public ResponseObject changeStatus(Driver driver) {

		ResponseObject response = new ResponseObject();
		int rows;
		
		rows = driversDao.changeStatus(driver);
		if(rows > 0){
			response.setResponseCode(RespCodes.SUCCESS);
			response.setResponseDesc("Success");
		}else {
			response.setResponseCode(RespCodes.NO_DATA_FOUND);
			response.setResponseDesc("No Record Found");
		}
		return response;
	}




}
