package com.revature.dao;

import com.revature.Customer;

public interface CustomerDao {
	Customer getCustomerById(int id);
	void addCustomer(Customer customer);
	int getCustomerId(Customer customer);
	int getStatus(int id);
}
