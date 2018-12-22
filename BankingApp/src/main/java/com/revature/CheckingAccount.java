package com.revature;

import java.text.DecimalFormat;

import com.revature.dao.CheckingAccountDao;
import com.revature.dao.CheckingAccountDao;
import com.revature.dao.JointAccountDao;
import com.revature.dao.JointAccountDao;
import com.revature.dao.SavingsAccountDao;
import com.revature.jdbcinfo.EstablishConnection;
import com.revature.dao.SavingsAccountDao;

public class CheckingAccount {
	DecimalFormat df = new DecimalFormat("#0.00");
	EstablishConnection establishConnection = new EstablishConnection();
	CheckingAccountDao checkingAccountDao = new CheckingAccountDao(establishConnection.establishConnection());
	SavingsAccountDao savingsAccountDao = new SavingsAccountDao(establishConnection.establishConnection());
	JointAccountDao jointAccountDao = new JointAccountDao(establishConnection.establishConnection());
	private int accountNumber;
	private int customerId;
	private int status;
	private String approvalStatus;
	private double balance;

	public CheckingAccount(int accountNumber, int customerId, int status, String approvalStatus, double balance){
		this.accountNumber = accountNumber;
		this.customerId = customerId;
		this.status = status;
		this.approvalStatus = approvalStatus;
		this.balance = balance;
	}
	
	public CheckingAccount() {
		
	}
	
	public int getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(int accountNumber) {
		this.accountNumber = accountNumber;
	}

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getApprovalStatus() {
		return approvalStatus;
	}

	public void setApprovalStatus(String approvalStatus) {
		this.approvalStatus = approvalStatus;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}
	
	public void initialize(int customerId) {
		CheckingAccount checkingAccount = checkingAccountDao.getCheckingAccountById(customerId);
		
		if(checkingAccount == null) {
			checkingAccount = new CheckingAccount();
			checkingAccount.setCustomerId(customerId);
			checkingAccount.setStatus(0);
			checkingAccount.setApprovalStatus("u");
			checkingAccount.setBalance(0.00);
			checkingAccountDao.addCheckingAccount(checkingAccount);
		}
		
	}

	public double withdrawl(int customerId, double amount) {
		CheckingAccount checkingAccount = checkingAccountDao.getCheckingAccountById(customerId);
		double currentBalance = checkingAccount.getBalance();
		double newBalance = currentBalance - amount;
		if (newBalance < 0) {
			System.out.println("You do not have enough money to withdrawl that much! You only have: $" + df.format(currentBalance)
					+ " remaining in your account!");
			return currentBalance;
		} else {
			checkingAccount.setBalance(newBalance);
			checkingAccountDao.updateCheckingAccount(checkingAccount);
			return newBalance;
		}
	}

	public double deposit(int customerId, double amount) {
		CheckingAccount checkingAccount = checkingAccountDao.getCheckingAccountById(customerId);
		double currentBalance = checkingAccount.getBalance();
		double newBalance = currentBalance + amount;
		checkingAccount.setBalance(newBalance);
		checkingAccountDao.updateCheckingAccount(checkingAccount);
		return newBalance;
	}

	public void transfer(int customerId, double amount, String source, String destination) {
		SavingsAccount savingsAccount = savingsAccountDao.getSavingsAccountById(customerId);
		CheckingAccount checkingAccount = checkingAccountDao.getCheckingAccountById(customerId);
		JointAccount jointAccount = jointAccountDao.getJointAccountById(customerId);
		
		if (destination.equals("Savings")) {
			withdrawl(customerId, amount);
			savingsAccount.deposit(customerId, amount);
			savingsAccount = savingsAccountDao.getSavingsAccountById(customerId);
			checkingAccount = checkingAccountDao.getCheckingAccountById(customerId);
			jointAccount = jointAccountDao.getJointAccountById(customerId);
			System.out.println("New Checking Account Balance: $" + df.format(checkingAccount.getBalance()));
			System.out.println("New Savings Account Balance: $" + df.format(savingsAccount.getBalance()));
		} else if (destination.equals("Joint")) {
			withdrawl(customerId, amount);
			jointAccount.deposit(customerId, amount);
			savingsAccount = savingsAccountDao.getSavingsAccountById(customerId);
			checkingAccount = checkingAccountDao.getCheckingAccountById(customerId);
			jointAccount = jointAccountDao.getJointAccountById(customerId);
			System.out.println("New Checking Account Balance: $" + df.format(checkingAccount.getBalance()));
			System.out.println("New Joint Account Balance: $" + df.format(jointAccount.getBalance()));
		}
	}

	public void applyForAccount(int customerId) {
		CheckingAccount checkingAccount = checkingAccountDao.getCheckingAccountById(customerId);
		String approvalStatus = checkingAccount.getApprovalStatus();
		if (approvalStatus.equals("p")) {
			System.out.println("Approval already pending!");
		} else {
			
			if(approvalStatus.equals("d") || approvalStatus.equals("u")) {
			checkingAccount.setApprovalStatus("p");
			checkingAccountDao.updateCheckingAccount(checkingAccount);
				
			System.out.println("Applied for Checking Account!");
			} else {
				System.out.println("You already have an account!");
			}
		}
	}


}
