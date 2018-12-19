package com.revature.dao;

public interface EmployeeRegistrationDao {
	boolean getEmployeeById(int id);
	boolean getUserExists(String username);
	boolean getUserAlreadyHasAccount(int id);
	void addUser(String employeeId, String username, String password);
}
