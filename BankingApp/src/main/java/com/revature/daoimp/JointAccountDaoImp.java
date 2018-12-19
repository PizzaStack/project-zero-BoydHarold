package com.revature.daoimp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.revature.dao.CheckingAccountDao;
import com.revature.dao.JointAccountDao;
import com.revature.jdbcinfo.JDBCConnectionInfo;

public class JointAccountDaoImp implements JointAccountDao{
	private JDBCConnectionInfo jdbc;
	private ArrayList<String> connectionInfo;
	private String username;
	private String password;
	private String url;
	private double balance;
	String approvalStatus;
	
	public JointAccountDaoImp() {
		jdbc = new JDBCConnectionInfo();
		connectionInfo = jdbc.getJDBCCredentials();
		username = connectionInfo.get(0);
		password = connectionInfo.get(1);
		url = connectionInfo.get(2);
	}

	@Override
	public double getBalance(int id) {
		try {
			Connection connection = DriverManager.getConnection(url, username, password);
			PreparedStatement preGetBalance = connection.prepareStatement("SELECT Balance FROM JointAccount WHERE CustomerId1 = ? OR CustomerId2 = ?;");
			preGetBalance.setInt(1, id);
			preGetBalance.setInt(2, id);
			ResultSet rs = preGetBalance.executeQuery();
			while(rs.next()) {
				balance = rs.getDouble(1);
			}
		
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return balance;
	}

	@Override
	public void setBalance(int id, double balance) {
		try {
			Connection connection = DriverManager.getConnection(url, username, password);
			PreparedStatement preSetBalance = connection.prepareStatement("UPDATE JointAccount SET Balance = ? WHERE CustomerId1 = ? OR CustomerId2 = ?;");
			preSetBalance.setDouble(1, balance);
			preSetBalance.setInt(2, id);
			preSetBalance.setInt(3, id);
			preSetBalance.executeUpdate();
			
			connection.close();
		} catch (SQLException e) {
			
		}
		
	}

	@Override
	public int getStatus(int id) {
		int status = 0;
		try {
			Connection connection = DriverManager.getConnection(url, username, password);
			PreparedStatement preGetStatus = connection.prepareStatement("SELECT Status FROM JointAccount WHERE CustomerId1 = ? OR CustomerId2 = ?;");
			preGetStatus.setInt(1, id);
			preGetStatus.setInt(2, id);
			ResultSet rs = preGetStatus.executeQuery();
			while(rs.next()) {
				status = rs.getInt(1);
			}
		
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return status;
	}

	@Override
	public String getApprovalStatus(int id) {
		try {
			Connection connection = DriverManager.getConnection(url, username, password);
			PreparedStatement preGetApprovalStatus = connection.prepareStatement("SELECT ApprovalStatus FROM JointAccount WHERE CustomerId1 = ? OR CustomerId2 = ?;");
			preGetApprovalStatus.setInt(1, id);
			preGetApprovalStatus.setInt(2, id);
			ResultSet rs = preGetApprovalStatus.executeQuery();
			while(rs.next()) {
				approvalStatus = rs.getString(1);
			}
		
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return approvalStatus;
	}

	@Override
	public void applyForAccount(int id) {
		try {
			Connection connection = DriverManager.getConnection(url, username, password);
			PreparedStatement preApplyForAccount = connection.prepareStatement("UPDATE JointAccount SET ApprovalStatus = 'p' WHERE CustomerId1 = ? OR CustomerId2 = ?;");
			preApplyForAccount.setInt(1, id);
			preApplyForAccount.setInt(2, id);
			preApplyForAccount.executeUpdate();
			
			connection.close();
		} catch (SQLException e) {
			
		}
		
	}

	@Override
	public boolean getAccountExists(int id) {
		boolean exists = false;
		try {
			Connection connection = DriverManager.getConnection(url, username, password);
			PreparedStatement preGetExists = connection.prepareStatement("SELECT * FROM JointAccount WHERE CustomerId1 = ? OR CustomerId2 = ?;");
			preGetExists.setInt(1, id);
			preGetExists.setInt(2, id);
			ResultSet rs = preGetExists.executeQuery();
			while(rs.next()) {
				exists = true;
			}
		
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return exists;
	}

}
