package com.techpp.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.techpp.dao.RideHistoryDao;
import com.techpp.modal.ResponseObject;
import com.techpp.modal.RideHistory;
import com.techpp.service.RideHistoryService;
import com.techpp.utils.AppUtils;
import com.techpp.utils.RespCodes;

@Service
public class RideHistoryServiceImpl implements RideHistoryService{

	@Autowired
	RideHistoryDao rideHistoryDao;

	@Override
	public ResponseObject rideHistory(String mobileNumber) {
		
		List<RideHistory> rideHistories = null;
		ResponseObject response = new ResponseObject();		
		rideHistories = rideHistoryDao.rideHistory(mobileNumber);
		
		if(AppUtils.isNullOEmptyList(rideHistories)) {
			response.setResponseCode(RespCodes.NO_DATA_FOUND);
			response.setResponseDesc("No Record Found");
		}
		else {
			response.setResponseCode(RespCodes.SUCCESS);
			response.setResponseDesc("Ride History Found");
			response.setPayload(rideHistories);
		}
		return response;
	}
	
}
