package com.revature;


import java.sql.Connection;
import java.text.DecimalFormat;

import com.revature.dao.CheckingAccountDao;
import com.revature.dao.CustomerDao;
import com.revature.dao.JointAccountDao;
import com.revature.dao.SavingsAccountDao;
import com.revature.jdbcinfo.EstablishConnection;

public class JointAccount{
	DecimalFormat df = new DecimalFormat("#0.00");
	EstablishConnection establishConnection = new EstablishConnection();
	CheckingAccountDao checkingAccountDao = new CheckingAccountDao(establishConnection.establishConnection());
	SavingsAccountDao savingsAccountDao = new SavingsAccountDao(establishConnection.establishConnection());
	JointAccountDao jointAccountDao = new JointAccountDao(establishConnection.establishConnection());
	private int accountNumber;
	private int customerId1;
	private int customerId2;
	private int status;
	private String approvalStatus;
	private double balance;

	public JointAccount(int accountNumber, int customerId1, int customerId2, int status, String approvalStatus, double balance){
		this.accountNumber = accountNumber;
		this.customerId1 = customerId1;
		this.customerId2 = customerId2;
		this.status = status;
		this.approvalStatus = approvalStatus;
		this.balance = balance;
	}
	
	public int getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(int accountNumber) {
		this.accountNumber = accountNumber;
	}

	public int getCustomerId1() {
		return customerId1;
	}

	public void setCustomerId1(int customerId1) {
		this.customerId1 = customerId1;
	}

	public int getCustomerId2() {
		return customerId2;
	}

	public void setCustomerId2(int customerId2) {
		this.customerId2 = customerId2;
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

	public JointAccount() {
		
	}
	
	public boolean withdrawl(int customerId, double amount) {
		JointAccount jointAccount = jointAccountDao.getJointAccountById(customerId);
		double currentBalance = jointAccount.getBalance();
		double newBalance = currentBalance - amount;
		if (newBalance < 0) {
			System.out.println("You do not have enough money to withdrawl that much! You only have: $" + df.format(currentBalance)
					+ " remaining in your account!");
			return false;
		} else {
			jointAccount.setBalance(newBalance);
			jointAccountDao.updateJointAccount(jointAccount);
			return true;
		}
	}

	public void deposit(int customerId, double amount) {
		JointAccount jointAccount = jointAccountDao.getJointAccountById(customerId);
		double currentBalance = jointAccount.getBalance();
		double newBalance = currentBalance + amount;
		jointAccount.setBalance(newBalance);
		jointAccountDao.updateJointAccount(jointAccount);
	}

	public void transfer(int customerId, double amount, String source, String destination) {
		CheckingAccount ca = new CheckingAccount();
		JointAccount sa = new JointAccount();
		SavingsAccount savingsAccount = savingsAccountDao.getSavingsAccountById(customerId);
		CheckingAccount checkingAccount = checkingAccountDao.getCheckingAccountById(customerId);
		JointAccount jointAccount = jointAccountDao.getJointAccountById(customerId);

		if (destination.equals("Checking")) {
			withdrawl(customerId, amount);
			ca.deposit(customerId, amount);
			System.out.println("New Joint Account Balance: $" + df.format(jointAccount.getBalance()));
			System.out.println("New Checking Account Balance: $" + df.format(checkingAccount.getBalance()));
		} else if (destination.equals("Joint")) {
			withdrawl(customerId, amount);
			sa.deposit(customerId, amount);
			System.out.println("New Joint Account Balance: $" + df.format(jointAccount.getBalance()));
			System.out.println("New Savings Account Balance: $" + df.format(savingsAccount.getBalance()));
		}
	}

	public void applyForAccount(int customerId) {
		SavingsAccount savingsAccount = savingsAccountDao.getSavingsAccountById(customerId);
		String approvalStatus = savingsAccount.getApprovalStatus();
		if (approvalStatus.equals("p")) {
			System.out.println("Approval already pending!");
		} else {
			
			if(approvalStatus.equals("d")) {
			savingsAccount.setApprovalStatus("p");
			savingsAccountDao.updateSavingsAccount(savingsAccount);
				
			System.out.println("Applied for Savings Account!");
			} else {
				System.out.println("You already have an account!");
			}
		}
	}
	
	public void applyForAccount(int customerId1, int customerId2, Connection connection) {
		boolean customerCheck = false;
		JointAccount jointAccount = jointAccountDao.getJointAccountById(customerId1);
		if(customerId1 == customerId2) {
			System.out.println("You cannot open a joint account with yourself!");
		} else {
		Customer customer = new Customer();
		CustomerDao customerDao = new CustomerDao(connection);
		customer = customerDao.getCustomerById(customerId2);
		if(customer == null) {
		customerCheck = false;
		}
		
		String approvalStatus = jointAccount.getApprovalStatus();
		
		if (approvalStatus.equals("p")) {
			System.out.println("Approval already pending!");
		} else {
		
				
		
		if(customerCheck) {
			if(approvalStatus.equals("0")) {
				
			JointAccount jointAccount1 = jointAccountDao.getJointAccountById(customerId1);
			JointAccount jointAccount2 = jointAccountDao.getJointAccountById(customerId2);
	
				
			if(jointAccount1 != null || jointAccount2 != null) {
				System.out.println("Either you or the requested customer already have a joint account!");
			} else {

			jointAccount1.setApprovalStatus("p");
			jointAccount2.setApprovalStatus("p");
				
			jointAccountDao.updateJointAccount(jointAccount1);
			jointAccountDao.updateJointAccount(jointAccount2);
			
			System.out.println("Applied for Joint Account!");
			}
			} else {
				System.out.println("You already have an account!");
		} 
		} else {
			System.out.println("The customer you wish to open a Joint Account with does not exist!");
		}

		}
		}
	}

}
