package com.revature;

public interface Account {
	public void setupDefaultValues();
	public boolean withdrawl(int customerId, double amount);
	public void deposit(int customerId, double amount);
	public void transfer(int customerId, double amount, String source, String destination);
	public void applyForAccount(int customerId);
	public double getBalance(int customerId);
	public void setBalance(int customerId, double balance);
	public String getAccountStatus(int customerId);
}
