package com.revature.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.revature.SavingsAccount;
import com.revature.dao.SavingsAccountDao;
import com.revature.jdbcinfo.EstablishConnection;
import com.revature.dao.SavingsAccountDao;

public class SavingsAccountDao{
	String approvalStatus;
	
	public List<SavingsAccount> getAllSavingsAccounts(){
		List<SavingsAccount> savingsAccounts = new ArrayList<>();
		try {
			PreparedStatement preGetAllSavingsAccounts = null;
			preGetAllSavingsAccounts = EstablishConnection.connection.prepareStatement("SELECT * FROM SavingsAccount;");
			ResultSet rs = preGetAllSavingsAccounts.executeQuery();
			SavingsAccount savingsAccount = null;
			while(rs.next()) {
				savingsAccount = new SavingsAccount(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getString(4), rs.getDouble(5));
				savingsAccounts.add(savingsAccount);
			}
		} catch (SQLException e) {

		}
		
		return savingsAccounts;
	}
	
	public SavingsAccount getSavingsAccountById(int customerId) {
		SavingsAccount savingsAccount = new SavingsAccount();
		try {
			PreparedStatement preGetSavingsAccount = null;
			preGetSavingsAccount = EstablishConnection.connection.prepareStatement("SELECT * FROM SavingsAccount WHERE CustomerId = ?");
			preGetSavingsAccount.setInt(1, customerId);
			ResultSet rs = preGetSavingsAccount.executeQuery();
			savingsAccount = null;
			while(rs.next()) {
				savingsAccount = new SavingsAccount(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getString(4), rs.getDouble(5));
			}
		} catch (SQLException e) {
			
		}
		
		
		return savingsAccount;
	}

	public void updateSavingsAccount(SavingsAccount savingsAccount) {
		try {
			PreparedStatement preUpdateSavingsAccount = EstablishConnection.connection.prepareStatement("UPDATE SavingsAccount SET Status = ?, ApprovalStatus = ?, Balance = ? WHERE CustomerId = ?;");
			preUpdateSavingsAccount.setInt(1, savingsAccount.getStatus());
			preUpdateSavingsAccount.setString(2, savingsAccount.getApprovalStatus());
			preUpdateSavingsAccount.setDouble(3, savingsAccount.getBalance());
			preUpdateSavingsAccount.setInt(4, savingsAccount.getCustomerId());
			preUpdateSavingsAccount.executeUpdate();

		} catch (SQLException e) {
			
		}
	}
	
	public void addSavingsAccount(SavingsAccount savingsAccount) {
		try {
			PreparedStatement preAddSavingsAccount = EstablishConnection.connection.prepareStatement("INSERT INTO SavingsAccount (CustomerId, Status, ApprovalStatus, Balance) VALUES (?,?,?,?);");
			preAddSavingsAccount.setInt(1, savingsAccount.getCustomerId());
			preAddSavingsAccount.setInt(2, savingsAccount.getStatus());
			preAddSavingsAccount.setString(3, savingsAccount.getApprovalStatus());
			preAddSavingsAccount.setDouble(4, savingsAccount.getBalance());
			preAddSavingsAccount.executeUpdate();

		} catch (SQLException e) {
			
		}
	}

}
