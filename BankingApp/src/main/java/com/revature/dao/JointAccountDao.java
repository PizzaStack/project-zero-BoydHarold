package com.revature.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.revature.JointAccount;
import com.revature.SavingsAccount;
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

	public List<JointAccount> getAllJointAccounts(){
		List<JointAccount> jointAccounts = new ArrayList<>();
		try {
			PreparedStatement preGetAllJointAccounts = null;
			preGetAllJointAccounts = connection.prepareStatement("SELECT * FROM JointAccount;");
			ResultSet rs = preGetAllJointAccounts.executeQuery();
			JointAccount jointAccount = null;
			while(rs.next()) {
				jointAccount = new JointAccount(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getInt(4), rs.getString(5), rs.getDouble(6));
				jointAccounts.add(jointAccount);
			}
		} catch (SQLException e) {

		}
		
		return jointAccounts;
	}
	
	public JointAccount getJointAccountById(int customerId) {
		JointAccount jointAccount = new JointAccount();
		try {
			PreparedStatement preGetJointAccount = null;
			preGetJointAccount = connection.prepareStatement("SELECT * FROM JointAccount WHERE CustomerId1 = ? OR CustomerId2 = ?");
			preGetJointAccount.setInt(1, customerId);
			preGetJointAccount.setInt(2, customerId);
			ResultSet rs = preGetJointAccount.executeQuery();
			jointAccount = null;
			while(rs.next()) {
				jointAccount = new JointAccount(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getInt(4), rs.getString(5), rs.getDouble(6));
			}
		} catch (SQLException e) {
			
		}
		
		
		return jointAccount;
	}
	
	public void updateJointAccount(JointAccount jointAccount) {
		try {
			PreparedStatement preUpdateJointAccount = connection.prepareStatement("UPDATE JointAccount SET Status = ?, ApprovalStatus = ?, Balance = ? WHERE CustomerId1 = ? OR CustomerId2 = ?;");
			preUpdateJointAccount.setInt(1, jointAccount.getStatus());
			preUpdateJointAccount.setString(2, jointAccount.getApprovalStatus());
			preUpdateJointAccount.setDouble(3, jointAccount.getBalance());
			preUpdateJointAccount.setInt(4, jointAccount.getCustomerId1());
			preUpdateJointAccount.setInt(5, jointAccount.getCustomerId1());
			preUpdateJointAccount.executeUpdate();

		} catch (SQLException e) {
			
		}
	}
	
	public void addJointAccount(JointAccount jointAccount) {
		try {
			PreparedStatement preAddJointAccount = connection.prepareStatement("INSERT INTO JointAccount (CustomerId1, CustomerId2, Status, ApprovalStatus, Balance) VALUES (?,?,?,?,?);");
			preAddJointAccount.setInt(1, jointAccount.getCustomerId1());
			preAddJointAccount.setInt(2, jointAccount.getCustomerId2());
			preAddJointAccount.setInt(3, jointAccount.getStatus());
			preAddJointAccount.setString(4, jointAccount.getApprovalStatus());
			preAddJointAccount.setDouble(5, jointAccount.getBalance());
			preAddJointAccount.executeUpdate();

		} catch (SQLException e) {
			
		}
	}
}
