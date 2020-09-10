package com.techpp.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.techpp.dao.VehicleDetailsDao;
import com.techpp.modal.ResponseObject;
import com.techpp.modal.VehicleDetails;
import com.techpp.service.VehicleDetailsService;


@Service
public class VehicleDetailsServiceImpl implements VehicleDetailsService {
	
	@Autowired
	VehicleDetailsDao vehicleDetailsDao;
	
	@Override
	public ResponseObject saveVehicleDetails( VehicleDetails vehicleDetails) {
		
		ResponseObject response = new ResponseObject();
         
         int rows;
         rows=vehicleDetailsDao.saveVehicleDetails(vehicleDetails);
          if(rows>0){
        	  response.setResponseCode("00");
        	  response.setResponseDesc("Success");
          }else {
        	  response.setResponseCode("04");
        	  response.setResponseDesc("Request Failed");
          }
		return response;
		}

}
