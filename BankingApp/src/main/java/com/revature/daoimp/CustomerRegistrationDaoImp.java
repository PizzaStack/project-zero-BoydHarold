package com.revature.daoimp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.revature.Customer;
import com.revature.dao.CustomerRegistrationDao;
import com.revature.jdbcinfo.JDBCConnectionInfo;

public class CustomerRegistrationDaoImp implements CustomerRegistrationDao{
	private JDBCConnectionInfo jdbc;
	private ArrayList<String> connectionInfo;
	private String username;
	private String password;
	private String url;
	private Customer customer;
	
	public CustomerRegistrationDaoImp() {
		jdbc = new JDBCConnectionInfo();
		connectionInfo = jdbc.getJDBCCredentials();
		username = connectionInfo.get(0);
		password = connectionInfo.get(1);
		url = connectionInfo.get(2);
	}

	@Override
	public boolean getCustomerById(int id) {
		boolean exists = false;
		try {
			Connection connection = DriverManager.getConnection(url, this.username, this.password);
			PreparedStatement preGetCustomer = null;
			String getCustomer = "SELECT * FROM Customer WHERE CustomerId = ?";
			preGetCustomer = connection.prepareStatement(getCustomer);
			preGetCustomer.setInt(1, id);
			ResultSet rs = preGetCustomer.executeQuery();
			while(rs.next()) {
				exists = true;
			}

			
			connection.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return exists;
	}

	@Override
	public boolean getUserExists(String username) {
		boolean exists = false;
		try {
			Connection connection = DriverManager.getConnection(url, this.username, this.password);
			PreparedStatement preGetCustomer = null;
			String getCustomer = "SELECT * FROM CustomerUsers WHERE Username = ?";
			preGetCustomer = connection.prepareStatement(getCustomer);
			preGetCustomer.setString(1, username);
			ResultSet rs = preGetCustomer.executeQuery();
			while(rs.next()) {
				exists = true;
			}

			
			connection.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return exists;
	}

	@Override
	public void addUser(String customerId, String username, String password) {
		try {
			Connection connection = DriverManager.getConnection(url, this.username, this.password);
			PreparedStatement preAddCustomer = null;
			String addCustomer = "INSERT INTO CustomerUsers(CustomerId, Username, Password, Status) VALUES (?,?,?,?)";
			preAddCustomer = connection.prepareStatement(addCustomer);
			preAddCustomer.setInt(1,Integer.parseInt(customerId));
			preAddCustomer.setString(2,username);
			preAddCustomer.setString(3,password);
			preAddCustomer.setInt(4,1);
			preAddCustomer.executeUpdate();
			connection.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public boolean getUserAlreadyHasAccount(int id) {
		boolean exists = false;
		try {
			Connection connection = DriverManager.getConnection(url, this.username, this.password);
			PreparedStatement preGetCustomer = null;
			String getCustomer = "SELECT * FROM CustomerUsers WHERE CustomerId = ?";
			preGetCustomer = connection.prepareStatement(getCustomer);
			preGetCustomer.setInt(1, id);
			ResultSet rs = preGetCustomer.executeQuery();
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
