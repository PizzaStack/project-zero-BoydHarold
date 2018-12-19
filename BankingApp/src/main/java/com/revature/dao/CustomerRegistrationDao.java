package com.revature.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.revature.Customer;
import com.revature.dao.CustomerRegistrationDao;
import com.revature.jdbcinfo.EstablishConnection;

public class CustomerRegistrationDao{
	private Connection connection;
	private Customer customer;
	
	public CustomerRegistrationDao(Connection connection) {
		this.connection = connection;
	}

	
	public boolean getCustomerById(int id) {
		boolean exists = false;
		try {
			PreparedStatement preGetCustomer = null;
			String getCustomer = "SELECT * FROM Customer WHERE CustomerId = ?";
			preGetCustomer = connection.prepareStatement(getCustomer);
			preGetCustomer.setInt(1, id);
			ResultSet rs = preGetCustomer.executeQuery();
			while(rs.next()) {
				exists = true;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return exists;
	}

	
	public boolean getUserExists(String username) {
		boolean exists = false;
		try {
			PreparedStatement preGetCustomer = null;
			String getCustomer = "SELECT * FROM CustomerUsers WHERE Username = ?";
			preGetCustomer = connection.prepareStatement(getCustomer);
			preGetCustomer.setString(1, username);
			ResultSet rs = preGetCustomer.executeQuery();
			while(rs.next()) {
				exists = true;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return exists;
	}

	
	public void addUser(String customerId, String username, String password) {
		try {
			PreparedStatement preAddCustomer = null;
			String addCustomer = "INSERT INTO CustomerUsers(CustomerId, Username, Password, Status) VALUES (?,?,?,?)";
			preAddCustomer = connection.prepareStatement(addCustomer);
			preAddCustomer.setInt(1,Integer.parseInt(customerId));
			preAddCustomer.setString(2,username);
			preAddCustomer.setString(3,password);
			preAddCustomer.setInt(4,1);
			preAddCustomer.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	
	public boolean getUserAlreadyHasAccount(int id) {
		boolean exists = false;
		try {
			PreparedStatement preGetCustomer = null;
			String getCustomer = "SELECT * FROM CustomerUsers WHERE CustomerId = ?";
			preGetCustomer = connection.prepareStatement(getCustomer);
			preGetCustomer.setInt(1, id);
			ResultSet rs = preGetCustomer.executeQuery();
			while(rs.next()) {
				exists = true;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return exists;
	}

}
