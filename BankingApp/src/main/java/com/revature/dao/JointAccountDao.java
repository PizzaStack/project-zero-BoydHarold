package com.revature.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.revature.dao.CheckingAccountDao;
import com.revature.dao.JointAccountDao;
import com.revature.jdbcinfo.EstablishConnection;

public class JointAccountDao{
	private Connection connection;
	private double balance;
	String approvalStatus;
	
	public JointAccountDao(Connection connection) {
		this.connection = connection;
	}

	
	public double getBalance(int id) {
		try {
			PreparedStatement preGetBalance = connection.prepareStatement("SELECT Balance FROM JointAccount WHERE CustomerId1 = ? OR CustomerId2 = ?;");
			preGetBalance.setInt(1, id);
			preGetBalance.setInt(2, id);
			ResultSet rs = preGetBalance.executeQuery();
			while(rs.next()) {
				balance = rs.getDouble(1);
			}
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return balance;
	}

	
	public void setBalance(int id, double balance) {
		try {
			PreparedStatement preSetBalance = connection.prepareStatement("UPDATE JointAccount SET Balance = ? WHERE CustomerId1 = ? OR CustomerId2 = ?;");
			preSetBalance.setDouble(1, balance);
			preSetBalance.setInt(2, id);
			preSetBalance.setInt(3, id);
			preSetBalance.executeUpdate();

		} catch (SQLException e) {
			
		}
		
	}

	
	public int getStatus(int id) {
		int status = 0;
		try {
			PreparedStatement preGetStatus = connection.prepareStatement("SELECT Status FROM JointAccount WHERE CustomerId1 = ? OR CustomerId2 = ?;");
			preGetStatus.setInt(1, id);
			preGetStatus.setInt(2, id);
			ResultSet rs = preGetStatus.executeQuery();
			while(rs.next()) {
				status = rs.getInt(1);
			}
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return status;
	}

	
	public String getApprovalStatus(int id) {
		try {
			PreparedStatement preGetApprovalStatus = connection.prepareStatement("SELECT ApprovalStatus FROM JointAccount WHERE CustomerId1 = ? OR CustomerId2 = ?;");
			preGetApprovalStatus.setInt(1, id);
			preGetApprovalStatus.setInt(2, id);
			ResultSet rs = preGetApprovalStatus.executeQuery();
			while(rs.next()) {
				approvalStatus = rs.getString(1);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return approvalStatus;
	}

	
	public void applyForAccount(int id) {
		try {
			PreparedStatement preApplyForAccount = connection.prepareStatement("UPDATE JointAccount SET ApprovalStatus = 'p' WHERE CustomerId1 = ? OR CustomerId2 = ?;");
			preApplyForAccount.setInt(1, id);
			preApplyForAccount.setInt(2, id);
			preApplyForAccount.executeUpdate();

		} catch (SQLException e) {
			
		}
		
	}

	
	public boolean getAccountExists(int id) {
		boolean exists = false;
		try {
			PreparedStatement preGetExists = connection.prepareStatement("SELECT * FROM JointAccount WHERE CustomerId1 = ? OR CustomerId2 = ?;");
			preGetExists.setInt(1, id);
			preGetExists.setInt(2, id);
			ResultSet rs = preGetExists.executeQuery();
			while(rs.next()) {
				exists = true;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return exists;
	}

}
