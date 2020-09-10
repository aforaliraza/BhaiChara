package com.techpp.service;

import com.techpp.modal.ResponseObject;
import com.techpp.modal.TransactionHistory;

public interface TransactionHistoryService {

	public abstract ResponseObject getTransactionHistory(String mobileNumber);
	public abstract ResponseObject insertTransactionHistory(TransactionHistory transactionHistory);
}
