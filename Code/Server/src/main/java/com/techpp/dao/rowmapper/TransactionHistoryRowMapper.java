package com.techpp.dao.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.techpp.modal.TransactionHistory;

public class TransactionHistoryRowMapper implements RowMapper<TransactionHistory>{

	@Override
	public TransactionHistory mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		TransactionHistory transactionHistory = new TransactionHistory();
		
		transactionHistory.setMobileNumber(rs.getString("mobile_number"));
		transactionHistory.setAmount(rs.getString("amount"));
		return transactionHistory;
	}

}
