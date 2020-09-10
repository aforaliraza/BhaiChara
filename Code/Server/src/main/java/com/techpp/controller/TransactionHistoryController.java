package com.techpp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.techpp.modal.ResponseObject;
import com.techpp.modal.TransactionHistory;
import com.techpp.service.TransactionHistoryService;

@RestController
public class TransactionHistoryController {
	
	@Autowired
	TransactionHistoryService transactionHistoryService;
	
	@PostMapping(value = "/getTransactionHistory")
	public ResponseObject getTransactionHistory(@RequestParam("mobileNumber") String mobileNumber) {
		
		ResponseObject response = null;
		response = transactionHistoryService.getTransactionHistory(mobileNumber);
		return response;
	}
	
	@PostMapping(value = "/insertTransactionHistory")
	public ResponseObject insertTransactionHistory(@ModelAttribute("transactionHistory") TransactionHistory transactionHistory){
		
		ResponseObject response = new ResponseObject();
		response = transactionHistoryService.insertTransactionHistory(transactionHistory);
		return response;
	}

}
