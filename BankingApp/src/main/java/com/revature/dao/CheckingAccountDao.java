package com.revature.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.revature.CheckingAccount;
import com.revature.dao.CheckingAccountDao;
import com.revature.jdbcinfo.EstablishConnection;

public class CheckingAccountDao{
	private double balance;
	String approvalStatus;
	private Connection connection;

	
	public CheckingAccountDao(Connection connection) {
		this.connection = connection;
	}

	public List<CheckingAccount> getAllCheckingAccounts(){
		List<CheckingAccount> checkingAccounts = new ArrayList<>();
		try {
			PreparedStatement preGetAllCheckingAccounts = null;
			preGetAllCheckingAccounts = connection.prepareStatement("SELECT * FROM CheckingAccount;");
			ResultSet rs = preGetAllCheckingAccounts.executeQuery();
			CheckingAccount checkingAccount = null;
			while(rs.next()) {
				checkingAccount = new CheckingAccount(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getString(4), rs.getDouble(5));
				checkingAccounts.add(checkingAccount);
			}
		} catch (SQLException e) {

		}
		
		return checkingAccounts;
	}
	
	public CheckingAccount getCheckingAccountById(int customerId) {
		CheckingAccount checkingAccount = new CheckingAccount();
		try {
			PreparedStatement preGetCheckingAccount = null;
			preGetCheckingAccount = connection.prepareStatement("SELECT * FROM CheckingAccount WHERE CustomerId = ?");
			preGetCheckingAccount.setInt(1, customerId);
			ResultSet rs = preGetCheckingAccount.executeQuery();
			checkingAccount = null;
			while(rs.next()) {
				checkingAccount = new CheckingAccount(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getString(4), rs.getDouble(5));
			}
		} catch (SQLException e) {
			
		}
		
		
		return checkingAccount;
	}

	public void updateCheckingAccount(CheckingAccount checkingAccount) {
		try {
			PreparedStatement preUpdateCheckingAccount = connection.prepareStatement("UPDATE CheckingAccount SET Status = ?, ApprovalStatus = ?, Balance = ? WHERE CustomerId = ?;");
			preUpdateCheckingAccount.setInt(1, checkingAccount.getStatus());
			preUpdateCheckingAccount.setString(2, checkingAccount.getApprovalStatus());
			preUpdateCheckingAccount.setDouble(3, checkingAccount.getBalance());
			preUpdateCheckingAccount.setInt(4, checkingAccount.getCustomerId());
			preUpdateCheckingAccount.executeUpdate();

		} catch (SQLException e) {
			
		}
	}
	
	public void addCheckingAccount(CheckingAccount checkingAccount) {
		try {
			PreparedStatement preAddCheckingAccount = connection.prepareStatement("INSERT INTO CheckingAccount (CustomerId, Status, ApprovalStatus, Balance) VALUES (?,?,?,?);");
			preAddCheckingAccount.setInt(1, checkingAccount.getCustomerId());
			preAddCheckingAccount.setInt(2, checkingAccount.getStatus());
			preAddCheckingAccount.setString(3, checkingAccount.getApprovalStatus());
			preAddCheckingAccount.setDouble(4, checkingAccount.getBalance());
			preAddCheckingAccount.executeUpdate();

		} catch (SQLException e) {
			
		}
	}


}
