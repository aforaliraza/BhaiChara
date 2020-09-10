package com.techpp.service.impl;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.techpp.dao.LocationsDao;
import com.techpp.modal.Location;
import com.techpp.modal.ResponseObject;
import com.techpp.service.LocationsService;
import com.techpp.utils.AppUtils;
import com.techpp.utils.RespCodes;

@Service
public class LocationsServiceImpl implements LocationsService  {

	Logger LGR = LogManager.getLogger(LocationsServiceImpl.class);
	
	@Autowired
	LocationsDao locationsDao; 
	
	@Override
	public ResponseObject findLocationBySuggestion(String address) {
		ResponseObject responseObject = new ResponseObject();
		List<Location> locations;
		
		try {
			LGR.info("Finding location by sugestion : " + address);
			locations = locationsDao.findLocationBySuggestion(address);
			
			if(AppUtils.isNullOEmptyList(locations)) {
				responseObject.setResponseCode(RespCodes.NO_DATA_FOUND);
				responseObject.setResponseDesc("No result found against address string " + address);
				return responseObject;
			} else {
				responseObject.setResponseCode(RespCodes.SUCCESS);
				responseObject.setResponseDesc("Result found against address string " + address);
				responseObject.setPayload(locations);
				return responseObject;
			}
		} catch (Exception e) {
			responseObject.setResponseCode(RespCodes.MALFUNCION);
			responseObject.setResponseDesc("Some error occured");
			LGR.error("Some Error Occured", e);
		}
		return responseObject;
	}

}
