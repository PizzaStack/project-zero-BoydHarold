package com.revature.dao;

public interface CustomerRegistrationDao {
	boolean getCustomerById(int id);
	boolean getUserExists(String username);
	boolean getUserAlreadyHasAccount(int id);
	void addUser(String customerId, String username, String password);
}
