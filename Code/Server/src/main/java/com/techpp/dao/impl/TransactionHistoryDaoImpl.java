package com.techpp.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.techpp.dao.TransactionHistoryDao;
import com.techpp.dao.rowmapper.TransactionHistoryRowMapper;
import com.techpp.modal.TransactionHistory;
import com.techpp.utils.AppUtils;

@Repository("transactionHistory")
public class TransactionHistoryDaoImpl implements TransactionHistoryDao{

	@Autowired
	JdbcTemplate jdbcTemplate;
	
	@Override
	public List<TransactionHistory> getTransactionHistory(String mobileNumber) {
		
		List<TransactionHistory> transactionHistory = null;
		transactionHistory = jdbcTemplate.query(GET_TRANSACTION_HISTORY,new Object[]{mobileNumber} ,new TransactionHistoryRowMapper());
		
		if(AppUtils.isNullOEmptyList(transactionHistory)) {
			return null;
		}
		else
		return transactionHistory;
	}

	@Override
	public int insertTransactionHistory(TransactionHistory transactionHistory) {
		
		int row = 0;
		row = jdbcTemplate.update(INSERT_TRANSACTION_HISTORY, transactionHistory.getMobileNumber(), transactionHistory.getAmount());
		
		if(row > 0) {
			return row;
		}
		else {
			return 0;
		}
		
	}

}
