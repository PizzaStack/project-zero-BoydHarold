package com.revature.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.revature.Approval;

public class ApprovalDao {
	private Connection connection;
	
	public ApprovalDao(Connection connection) {
		this.connection = connection;
	}
	
	public List<Approval> getAllCheckingAccounts(){
		List<Approval> checkingAccounts = new ArrayList<>();
		
		try {
			PreparedStatement preGetAllCheckingAccounts = null;
			preGetAllCheckingAccounts = connection.prepareStatement("SELECT ca.CustomerId, ca.Status, ca.ApprovalStatus, cus.FirstName, cus.LastName FROM CheckingAccount ca JOIN Customer cus ON ca.CustomerId = cus.CustomerId;");
			ResultSet rs = preGetAllCheckingAccounts.executeQuery();
			Approval approval;
			
			while(rs.next()) {
				approval = new Approval(rs.getInt(1),rs.getInt(2),rs.getString(3),rs.getString(4),rs.getString(5));
				checkingAccounts.add(approval);
			}
		} catch (SQLException e) {
			
		}
		
		return checkingAccounts;
	}
	
	public List<Approval> getAllSavingsAccounts(){
		List<Approval> savingsAccounts = new ArrayList<>();
		
		try {
			PreparedStatement preGetAllSavingsAccounts = null;
			preGetAllSavingsAccounts = connection.prepareStatement("SELECT sa.CustomerId, sa.Status, sa.ApprovalStatus, cus.FirstName, cus.LastName FROM SavingsAccount sa JOIN Customer cus ON sa.CustomerId = cus.CustomerId;");
			ResultSet rs = preGetAllSavingsAccounts.executeQuery();
			Approval approval;
			
			while(rs.next()) {
				approval = new Approval(rs.getInt(1),rs.getInt(2),rs.getString(3),rs.getString(4),rs.getString(5));
				savingsAccounts.add(approval);
			}
		} catch (SQLException e) {
			
		}
		
		return savingsAccounts;
	}
	
	public List<Approval> getAllJointAccounts(){
		List<Approval> jointAccounts = new ArrayList<>();
		
		try {
			PreparedStatement preGetAllJointAccounts = null;
			preGetAllJointAccounts = connection.prepareStatement("SELECT ja.CustomerId1, ja.CustomerId2, ja.Status, ja.ApprovalStatus, cus.FirstName, cus.LastName FROM Customer cus JOIN JointAccount ja ON cus.CustomerId = ja.CustomerId1 OR cus.CustomerId = ja.CustomerId2;");
			ResultSet rs = preGetAllJointAccounts.executeQuery();
			Approval approval;
			
			while(rs.next()) {
				int customerId1 = rs.getInt(1);
				int customerId2 = rs.getInt(2);
				int status = rs.getInt(3);
				String approvalStatus = rs.getString(4);
				String firstName1 = rs.getString(5);
				String lastName1 = rs.getString(6);
				rs.next();
				String firstName2 = rs.getString(5);
				String lastName2 = rs.getString(6);
				approval = new Approval(customerId1, customerId2, status, approvalStatus, firstName1, lastName1, firstName2, lastName2);
				jointAccounts.add(approval);
			}
		} catch (SQLException e) {
			
		}
		
		return jointAccounts;
	}
	
	public void updateCheckingAccount(Approval approval) {
		try {
			PreparedStatement preUpdateCheckingAccount = null;
			preUpdateCheckingAccount = connection.prepareStatement("UPDATE CheckingAccount SET Status = ?, ApprovalStatus = ? WHERE CustomerId = ?;");
			preUpdateCheckingAccount.setInt(1, approval.getStatus());
			preUpdateCheckingAccount.setString(2, approval.getApprovalStatus());
			preUpdateCheckingAccount.setInt(3, approval.getCustomerId());
			preUpdateCheckingAccount.executeUpdate();
		} catch (SQLException e) {
		}
	}
	
	public void updateSavingsAccount(Approval approval) {
		try {
			PreparedStatement preUpdateSavingsAccount = null;
			preUpdateSavingsAccount = connection.prepareStatement("UPDATE SavingsAccount SET Status = ?, ApprovalStatus = ? WHERE CustomerId = ?;");
			preUpdateSavingsAccount.setInt(1, approval.getStatus());
			preUpdateSavingsAccount.setString(2, approval.getApprovalStatus());
			preUpdateSavingsAccount.setInt(3, approval.getCustomerId());
			preUpdateSavingsAccount.executeUpdate();
		} catch (SQLException e) {
			
		}
	}
	
	public void updateJointAccount(Approval approval) {
		try {
			PreparedStatement preUpdateJointAccount = null;
			preUpdateJointAccount = connection.prepareStatement("UPDATE JointAccount SET Status = ?, ApprovalStatus = ? WHERE CustomerId1 = ? OR CustomerId2 = ?;");
			preUpdateJointAccount.setInt(1, approval.getStatus());
			preUpdateJointAccount.setString(2, approval.getApprovalStatus());
			preUpdateJointAccount.setInt(3, approval.getCustomerId());
			preUpdateJointAccount.setInt(4, approval.getCustomerId());
			preUpdateJointAccount.executeUpdate();
		} catch (SQLException e) {
			
		}
	}
	
	public void deleteJointAccount(Approval approval) {
		try {
			PreparedStatement preDeleteJointAccount = null;
			preDeleteJointAccount = connection.prepareStatement("DELETE FROM JointAccount WHERE CustomerId1 = ? OR CustomerId2 = ?;");
			preDeleteJointAccount.setInt(1, approval.getCustomerId());
			preDeleteJointAccount.setInt(2, approval.getCustomerId());
			preDeleteJointAccount.executeUpdate();
		} catch (SQLException e) {
			
		}
	}
}
