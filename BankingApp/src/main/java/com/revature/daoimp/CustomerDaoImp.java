package com.revature.daoimp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.revature.Customer;
import com.revature.dao.CustomerDao;
import com.revature.jdbcinfo.JDBCConnectionInfo;

public class CustomerDaoImp implements CustomerDao{
	private JDBCConnectionInfo jdbc;
	private ArrayList<String> connectionInfo;
	private String username;
	private String password;
	private String url;
	private Customer customer;

	public CustomerDaoImp() {
		jdbc = new JDBCConnectionInfo();
		connectionInfo = jdbc.getJDBCCredentials();
		username = connectionInfo.get(0);
		password = connectionInfo.get(1);
		url = connectionInfo.get(2);
	}
	
	@Override
	public Customer getCustomerById(int id) {
		try {
			Connection connection = DriverManager.getConnection(url, username, password);
			PreparedStatement preGetCustomer = null;
			String getCustomer = "SELECT * FROM Customer WHERE CustomerId = ?";
			preGetCustomer = connection.prepareStatement(getCustomer);
			preGetCustomer.setInt(1, id);
			ResultSet rs = preGetCustomer.executeQuery();
			customer = null;
			while(rs.next()) {
				customer = new Customer(rs.getString(2),
						rs.getString(3),
						rs.getString(4),
						rs.getString(5),
						rs.getString(6),
						rs.getString(7));
			}

			
			connection.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return customer;
	
	}

	@Override
	public void addCustomer(Customer customer) {
		try {
			Connection connection = DriverManager.getConnection(url, username, password);
			PreparedStatement preAddCustomer = null;
			String addCustomer = "INSERT INTO Customer(FirstName, LastName, Address, BirthDate, EmailAddress, PhoneNumber, Status) VALUES (?,?,?,?,?,?,?)";
			preAddCustomer = connection.prepareStatement(addCustomer);
			preAddCustomer.setString(1,customer.getCustomerFirstName());
			preAddCustomer.setString(2,customer.getCustomerLastName());
			preAddCustomer.setString(3,customer.getCustomerAddress());
			preAddCustomer.setString(4,customer.getCustomerBirthDate());
			preAddCustomer.setString(5,customer.getCustomerEmailAddress());
			preAddCustomer.setString(6,customer.getCustomerPhoneNumber());
			preAddCustomer.setInt(7, 1);
			preAddCustomer.executeUpdate();
			connection.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		

		
	}

	@Override
	public int getCustomerId(Customer customer) {
		int id = 0;
		try {
			Connection connection = DriverManager.getConnection(url, username, password);
			PreparedStatement preGetCustomerId = null;
			String getCustomerId = "SELECT CustomerId FROM Customer WHERE FirstName = ? AND LastName = ? AND Address = ? AND BirthDate = ? AND EmailAddress = ? AND PhoneNumber = ?;";
			preGetCustomerId = connection.prepareStatement(getCustomerId);
			preGetCustomerId.setString(1,customer.getCustomerFirstName());
			preGetCustomerId.setString(2,customer.getCustomerLastName());
			preGetCustomerId.setString(3,customer.getCustomerAddress());
			preGetCustomerId.setString(4,customer.getCustomerBirthDate());
			preGetCustomerId.setString(5,customer.getCustomerEmailAddress());
			preGetCustomerId.setString(6,customer.getCustomerPhoneNumber());
			ResultSet rs = preGetCustomerId.executeQuery();
			
			while(rs.next()) {
				id = rs.getInt(1);
			}
			
			
			
			connection.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return id;

	}

	@Override
	public int getStatus(int id) {
		int status = 0;
		try {
			Connection connection = DriverManager.getConnection(url, username, password);
			PreparedStatement preGetCustomerStatus = null;
			String getCustomerStatus = "SELECT Status FROM Customer WHERE CustomerId = ?";
			preGetCustomerStatus = connection.prepareStatement(getCustomerStatus);
			preGetCustomerStatus.setInt(1, id);
			ResultSet rs = preGetCustomerStatus.executeQuery();
			
			while(rs.next()) {
				status = rs.getInt(1);
			}

			
			connection.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return status;
	}


}
