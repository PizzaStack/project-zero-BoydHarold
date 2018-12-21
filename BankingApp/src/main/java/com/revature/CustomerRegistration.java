package com.revature;

import java.sql.Connection;
import java.util.List;

import com.revature.dao.CustomerDao;
import com.revature.dao.CustomerRegistrationDao;

public class CustomerRegistration{
	private int userId;
	private int customerId;
	private String username;
	private String password;
	private int status;
	
	public CustomerRegistration(int customerId, String username, String password, int status) {
		this.customerId = customerId;
		this.username = username;
		this.password = password;
		this.status = status;
	}
	
	public CustomerRegistration() {
		
	}
	
	public boolean registerUser(String customerId, String username, String password, Connection connection) {
		
		CustomerRegistrationDao customerRegistrationDao = new CustomerRegistrationDao(connection);
		CustomerRegistration customerRegistration = customerRegistrationDao.getCustomerUserByUsername(username);
		CustomerDao customerDao = new CustomerDao(connection);
		Customer customer = customerDao.getCustomerById(Integer.parseInt(customerId));
		List<CustomerRegistration> customerUsers = customerRegistrationDao.getAllCustomerUsers();
		
		int id = 0;
		
		for(CustomerRegistration customerUser : customerUsers) {
			if(customerUser.getCustomerId() == Integer.parseInt(customerId)) {
				id = customerUser.getCustomerId();
			}
		}
		
		
		boolean userExists = false;
		if(customerRegistration == null) {
			userExists = false;
		} else {
			userExists = true;
		}
		
		boolean customerOnboarded = false;
		if(customer == null) {
			customerOnboarded = false;
		} else {
			customerOnboarded = true;
		}
		
		
		boolean success = false;
		if(id != 0) {
			System.out.println("You already have an account! You are only allowed one account.");
			success = false;
		} else if(customerOnboarded == false) {
			System.out.println("Customer does not have an account with the bank, please speak to a bank employee so you can be onboarded.");
			success = false;
		} else if(userExists == true) {
			System.out.println("Account already exists with that username!");
			success = false;
		} else {
			customerRegistration = new CustomerRegistration(Integer.parseInt(customerId), username, password, 1);
			customerRegistrationDao.addCustomerUser(customerRegistration);
		}
		return success;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
	

}
