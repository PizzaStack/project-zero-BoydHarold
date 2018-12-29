package com.revature;

import java.text.DecimalFormat;

import org.apache.log4j.Logger;

import com.revature.dao.SavingsAccountDao;
import com.revature.dao.CheckingAccountDao;
import com.revature.dao.JointAccountDao;
import com.revature.jdbcinfo.EstablishConnection;

public class SavingsAccount {
	DecimalFormat df = new DecimalFormat("#0.00");
	EstablishConnection establishConnection = new EstablishConnection();
	CheckingAccountDao checkingAccountDao = new CheckingAccountDao();
	SavingsAccountDao savingsAccountDao = new SavingsAccountDao();
	JointAccountDao jointAccountDao = new JointAccountDao();
	static final Logger log = Logger.getLogger(SavingsAccount.class);
	private int accountNumber;
	private int customerId;
	private int status;
	private String approvalStatus;
	private double balance;

	public SavingsAccount(int accountNumber, int customerId, int status, String approvalStatus, double balance){
		this.accountNumber = accountNumber;
		this.customerId = customerId;
		this.status = status;
		this.approvalStatus = approvalStatus;
		this.balance = balance;
	}
	
	public SavingsAccount() {
		
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
		SavingsAccount savingsAccount = savingsAccountDao.getSavingsAccountById(customerId);
		
		if(savingsAccount == null) {
			savingsAccount = new SavingsAccount();
			savingsAccount.setCustomerId(customerId);
			savingsAccount.setStatus(0);
			savingsAccount.setApprovalStatus("u");
			savingsAccount.setBalance(0.00);
			savingsAccountDao.addSavingsAccount(savingsAccount);
		}
		
	}

	public double withdrawl(int customerId, double amount) {
		SavingsAccount savingsAccount = savingsAccountDao.getSavingsAccountById(customerId);
		double currentBalance = savingsAccount.getBalance();
		double newBalance = currentBalance - amount;
		if (newBalance < 0) {
			System.out.println("You do not have enough money to withdrawl that much! You only have: $" + df.format(currentBalance)
					+ " remaining in your account!");
			return currentBalance;
		} else {
			savingsAccount.setBalance(newBalance);
			savingsAccountDao.updateSavingsAccount(savingsAccount);
			return newBalance;
		}
	}

	public double deposit(int customerId, double amount) {
		SavingsAccount savingsAccount = savingsAccountDao.getSavingsAccountById(customerId);
		double currentBalance = savingsAccount.getBalance();
		double newBalance = currentBalance + amount;
		savingsAccount.setBalance(newBalance);
		savingsAccountDao.updateSavingsAccount(savingsAccount);
		return newBalance;
	}

	public void transfer(int customerId, double amount, String source, String destination) {
		SavingsAccount savingsAccount = savingsAccountDao.getSavingsAccountById(customerId);
		CheckingAccount checkingAccount = checkingAccountDao.getCheckingAccountById(customerId);
		JointAccount jointAccount = jointAccountDao.getJointAccountById(customerId);
		
		if(destination.equals("Checking")) {
			withdrawl(customerId, amount);
			checkingAccount.deposit(customerId, amount);
			savingsAccount = savingsAccountDao.getSavingsAccountById(customerId);
			checkingAccount = checkingAccountDao.getCheckingAccountById(customerId);
			jointAccount = jointAccountDao.getJointAccountById(customerId);
			System.out.println("New Savings Account Balance: $" + df.format(savingsAccount.getBalance()));
			System.out.println("New Checking Account Balance: $" + df.format(checkingAccount.getBalance()));
			log.info("$" + df.format(amount) + " was transferred from savings to checking for Customer id: " + customerId);
		} else if(destination.equals("Joint")) {
			withdrawl(customerId, amount);
			jointAccount.deposit(customerId, amount);
			savingsAccount = savingsAccountDao.getSavingsAccountById(customerId);
			checkingAccount = checkingAccountDao.getCheckingAccountById(customerId);
			jointAccount = jointAccountDao.getJointAccountById(customerId);
			System.out.println("New Savings Account Balance: $" + df.format(savingsAccount.getBalance()));
			System.out.println("New Joint Account Balance: $" + df.format(jointAccount.getBalance()));
			log.info("$" + df.format(amount) + " was transferred from savings to joint for Customer id: " + customerId);
		}
	}

	public void applyForAccount(int customerId) {
		SavingsAccount savingsAccount = savingsAccountDao.getSavingsAccountById(customerId);
		String approvalStatus = savingsAccount.getApprovalStatus();
		if (approvalStatus.equals("p")) {
			System.out.println("Approval already pending!");
		} else {
			
			if(approvalStatus.equals("d") || approvalStatus.equals("u")) {
			savingsAccount.setApprovalStatus("p");
			savingsAccountDao.updateSavingsAccount(savingsAccount);
				
			System.out.println("Applied for Savings Account!");
			} else {
				System.out.println("You already have an account!");
			}
		}
	}

	

}
