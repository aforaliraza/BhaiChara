package com.techpp.dao;

import java.util.List;

import com.techpp.modal.TransactionHistory;

public interface TransactionHistoryDao {
	
	public final static String GET_TRANSACTION_HISTORY = "SELECT mobile_number, amount From transaction_history WHERE mobile_number = ?";
	public final static String INSERT_TRANSACTION_HISTORY ="INSERT INTO  transaction_history (mobile_number, amount) VALUE (?, ?)";
	
	public abstract List<TransactionHistory> getTransactionHistory(String mobileNumber);
	public abstract int insertTransactionHistory(TransactionHistory transactionHistory);

}
