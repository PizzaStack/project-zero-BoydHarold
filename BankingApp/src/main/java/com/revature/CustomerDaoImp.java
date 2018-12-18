package com.revature;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CustomerDaoImp implements CustomerDao{
	private String username;
	private String password;
	private String url;

	private List<Customer> customers;
	
	@Override
	public List<Customer> getAllCustomers() {

		return customers;
	}

	@Override
	public Customer getCustomerById(int id) {
		// TODO Auto-generated method stub
		return customers.get(id);
	}

	@Override
	public void addCustomer(Customer customer) {
		getJDBCCredentials();
		try {
			Connection connection = DriverManager.getConnection(url, username, password);
			PreparedStatement preAddCustomer = null;
			String addCustomer = "INSERT INTO Customer(FirstName, LastName, Address, BirthDate, EmailAddress, PhoneNumber) VALUES (?,?,?,?,?,?)";
			preAddCustomer = connection.prepareStatement(addCustomer);
			preAddCustomer.setString(1,customer.getCustomerFirstName());
			preAddCustomer.setString(2,customer.getCustomerLastName());
			preAddCustomer.setString(3,customer.getCustomerAddress());
			preAddCustomer.setString(4,customer.getCustomerBirthDate());
			preAddCustomer.setString(5,customer.getCustomerEmailAddress());
			preAddCustomer.setString(6,customer.getCustomerPhoneNumber());
			preAddCustomer.executeUpdate();
			connection.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		

		
	}

	@Override
	public void removeCustomer(Customer customer) {
		// TODO Auto-generated method stub
		customers.remove(customer);
	}

	@Override
	public void updateCustomer(Customer customer) {
		// TODO Auto-generated method stub
	
	}
	
	public void getJDBCCredentials() {
		try
		(
		FileInputStream fis = new FileInputStream("C:\\Users\\boydt\\Desktop\\Project0\\project-zero-BoydHarold\\BankingApp\\JDBCConnectionInfo.txt");
		BufferedReader br = new BufferedReader(new InputStreamReader(fis));
		) {
			String line = "";
			int count = 1;
			while((line = br.readLine()) != null) {
				if(count == 1) {
				this.username = line;
				} else if (count == 2) {
					this.password = line;
				} else if (count == 3) {
					this.url = line;
				}
				count++;

			}
		}
			catch (FileNotFoundException e) {
			
		} catch (IOException e) {
			
		}
	}

	@Override
	public int getCustomerId(Customer customer) {
		getJDBCCredentials();
		int id = 0;
		try {
			Connection connection = DriverManager.getConnection(url, username, password);
			PreparedStatement preAddCustomer = null;
			String addCustomer = "SELECT CustomerId FROM Customer WHERE FirstName = ? AND LastName = ? AND Address = ? AND BirthDate = ? AND EmailAddress = ? AND PhoneNumber = ?;";
			preAddCustomer = connection.prepareStatement(addCustomer);
			preAddCustomer.setString(1,customer.getCustomerFirstName());
			preAddCustomer.setString(2,customer.getCustomerLastName());
			preAddCustomer.setString(3,customer.getCustomerAddress());
			preAddCustomer.setString(4,customer.getCustomerBirthDate());
			preAddCustomer.setString(5,customer.getCustomerEmailAddress());
			preAddCustomer.setString(6,customer.getCustomerPhoneNumber());
			ResultSet rs = preAddCustomer.executeQuery();
			
			while(rs.next()) {
				id = rs.getInt(1);
			}
			
			
			
			connection.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return id;

	}

}
