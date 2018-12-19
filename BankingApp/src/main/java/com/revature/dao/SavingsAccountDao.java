package com.revature.dao;

public interface SavingsAccountDao {
	double getBalance(int id);
	void setBalance(int id, double balance);
	int getStatus(int id);
	String getApprovalStatus(int id);
	void applyForAccount(int id);
	boolean getAccountExists(int id);
}
