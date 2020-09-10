package com.techpp.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.techpp.dao.TransactionHistoryDao;
import com.techpp.modal.ResponseObject;
import com.techpp.modal.TransactionHistory;
import com.techpp.service.TransactionHistoryService;
import com.techpp.utils.AppUtils;
import com.techpp.utils.RespCodes;

@Service
public class TransactionHistoryServiceImpl implements TransactionHistoryService{

	@Autowired
	TransactionHistoryDao transactionHistoryDao;
	
	@Override
	public ResponseObject getTransactionHistory(String mobileNumber) {
		
		List<TransactionHistory> transactionHistories = null;
		ResponseObject response = new ResponseObject();
		transactionHistories = transactionHistoryDao.getTransactionHistory(mobileNumber);
		
		if(AppUtils.isNullOEmptyList(transactionHistories)) {
			response.setResponseCode(RespCodes.NO_DATA_FOUND);
			response.setResponseDesc("No Record Found");
		}
		else {
			response.setResponseCode(RespCodes.SUCCESS);
			response.setResponseDesc("Transaction History Found");
			response.setPayload(transactionHistories);
		}
		
		return response;
	}

	@Override
	public ResponseObject insertTransactionHistory(TransactionHistory transactionHistory) {
		
		int row = 0;
		ResponseObject response = new ResponseObject();
		row = transactionHistoryDao.insertTransactionHistory(transactionHistory);
		
		if(row > 0) {
			response.setResponseCode(RespCodes.SUCCESS);
			response.setResponseDesc("Success");
		}
		else{
			response.setResponseCode(RespCodes.REQUEST_FAILED);
			response.setResponseDesc("Request Failed");
		}
		return response;
	}

}
