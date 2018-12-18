package com.revature;

import java.util.List;

public interface CustomerDao {
	List<Customer> getAllCustomers();
	Customer getCustomerById(int id);
	void addCustomer(Customer customer);
	void removeCustomer(Customer customer);
	void updateCustomer(Customer customer);
	int getCustomerId(Customer customer);
}
