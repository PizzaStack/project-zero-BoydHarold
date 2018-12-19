package com.revature.dao;

public interface AdminRegistrationDao {
	boolean getAdminById(int id);
	boolean getUserExists(String username);
	boolean getUserAlreadyHasAccount(int id);
	void addUser(String administratorId, String username, String password);
}
