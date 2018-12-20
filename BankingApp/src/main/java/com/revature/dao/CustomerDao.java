package com.revature.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.revature.Customer;
import com.revature.dao.CustomerDao;
import com.revature.jdbcinfo.EstablishConnection;

public class CustomerDao{
	private Customer customer;
	private Connection connection;

	public CustomerDao(Connection connection) {
		this.connection = connection;
	}
	
	public List<Customer> getAllCustomers(){
		List<Customer> customers = new ArrayList<>();
		try {
			PreparedStatement preGetAllCustomers = null;
			String getAllCustomers = "SELECT * FROM Customer;";
			preGetAllCustomers = connection.prepareStatement(getAllCustomers);
			ResultSet rs = preGetAllCustomers.executeQuery();
			while(rs.next()) {
				customer = new Customer(rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getInt(8));
				customer.setCustomerId(rs.getInt(1));
				customers.add(customer);
			}
		} catch (SQLException e) {
			
		}
		
		return customers;
	}
	
	public Customer getCustomerById(int id) {
		try {
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
						rs.getString(7),
						rs.getInt(8));
			}

			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return customer;
	
	}

	
	public void addCustomer(Customer customer) {
		try {
			PreparedStatement preAddCustomer = null;
			String addCustomer = "INSERT INTO Customer(FirstName, LastName, Address, BirthDate, EmailAddress, PhoneNumber, Status) VALUES (?,?,?,?,?,?,?)";
			preAddCustomer = connection.prepareStatement(addCustomer);
			preAddCustomer.setString(1,customer.getCustomerFirstName());
			preAddCustomer.setString(2,customer.getCustomerLastName());
			preAddCustomer.setString(3,customer.getCustomerAddress());
			preAddCustomer.setString(4,customer.getCustomerBirthDate());
			preAddCustomer.setString(5,customer.getCustomerEmailAddress());
			preAddCustomer.setString(6,customer.getCustomerPhoneNumber());
			preAddCustomer.setInt(7, customer.getCustomerIsActive());
			preAddCustomer.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		

		
	}


}
