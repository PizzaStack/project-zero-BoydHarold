package com.revature;

import java.text.DecimalFormat;

import org.apache.log4j.Logger;

import com.revature.dao.CheckingAccountDao;
import com.revature.dao.CustomerDao;
import com.revature.dao.JointAccountDao;
import com.revature.dao.SavingsAccountDao;
import com.revature.jdbcinfo.EstablishConnection;

public class JointAccount{
	DecimalFormat df = new DecimalFormat("#0.00");
	EstablishConnection establishConnection = new EstablishConnection();
	CheckingAccountDao checkingAccountDao = new CheckingAccountDao();
	SavingsAccountDao savingsAccountDao = new SavingsAccountDao();
	JointAccountDao jointAccountDao = new JointAccountDao();
	CustomerDao customerDao = new CustomerDao();
	static final Logger log = Logger.getLogger(JointAccount.class);
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
	
	public double withdrawl(int customerId, double amount) {
		JointAccount jointAccount = jointAccountDao.getJointAccountById(customerId);
		double currentBalance = jointAccount.getBalance();
		double newBalance = currentBalance - amount;
		if (newBalance < 0) {
			System.out.println("You do not have enough money to withdrawl that much! You only have: $" + df.format(currentBalance)
					+ " remaining in your account!");
			return currentBalance;
		} else {
			jointAccount.setBalance(newBalance);
			jointAccountDao.updateJointAccount(jointAccount);
			return newBalance;
		}
	}

	public double deposit(int customerId, double amount) {
		JointAccount jointAccount = jointAccountDao.getJointAccountById(customerId);
		double currentBalance = jointAccount.getBalance();
		double newBalance = currentBalance + amount;
		jointAccount.setBalance(newBalance);
		jointAccountDao.updateJointAccount(jointAccount);
		return newBalance;
	}

	public void transfer(int customerId, double amount, String source, String destination) {
		CheckingAccount checkingAccount = checkingAccountDao.getCheckingAccountById(customerId);
		SavingsAccount savingsAccount = savingsAccountDao.getSavingsAccountById(customerId);
		JointAccount jointAccount = jointAccountDao.getJointAccountById(customerId);

		if (destination.equals("Checking")) {
			withdrawl(customerId, amount);
			checkingAccount.deposit(customerId, amount);
			savingsAccount = savingsAccountDao.getSavingsAccountById(customerId);
			checkingAccount = checkingAccountDao.getCheckingAccountById(customerId);
			jointAccount = jointAccountDao.getJointAccountById(customerId);
			System.out.println("New Joint Account Balance: $" + df.format(jointAccount.getBalance()));
			System.out.println("New Checking Account Balance: $" + df.format(checkingAccount.getBalance()));
			log.info("$" + df.format(amount) + " was transferred from joint to checking for Customer id: " + customerId);
		} else if (destination.equals("Savings")) {
			withdrawl(customerId, amount);
			savingsAccount.deposit(customerId, amount);
			savingsAccount = savingsAccountDao.getSavingsAccountById(customerId);
			checkingAccount = checkingAccountDao.getCheckingAccountById(customerId);
			jointAccount = jointAccountDao.getJointAccountById(customerId);
			System.out.println("New Joint Account Balance: $" + df.format(jointAccount.getBalance()));
			System.out.println("New Savings Account Balance: $" + df.format(savingsAccount.getBalance()));
			log.info("$" + df.format(amount) + " was transferred from joint to savings for Customer id: " + customerId);
		}
	}

	
	public void applyForAccount(int customerId1, int customerId2) {
		boolean customerCheck = false;
		JointAccount jointAccount = jointAccountDao.getJointAccountById(customerId1);
		String approvalStatus = "0";
		
		
		if(customerId1 == customerId2) {
			System.out.println("You cannot open a joint account with yourself!");
		} else {
		Customer customer = customerDao.getCustomerById(customerId2);
		if(customer == null) {
		customerCheck = false;
		} else {
			customerCheck = true;
		}
		
		
		if(jointAccount != null) {
			approvalStatus = jointAccount.getApprovalStatus();
		}
		
		if (approvalStatus.equals("p")) {
			System.out.println("You already have an approval pending for a Joint Account!");
		} else {
		
				
		
		if(customerCheck) {
			if(approvalStatus.equals("0")) {
				
			JointAccount jointAccount1 = jointAccountDao.getJointAccountById(customerId1);
			JointAccount jointAccount2 = jointAccountDao.getJointAccountById(customerId2);
	
			if(jointAccount1 != null || jointAccount2 != null) {
				System.out.println("Either you or the requested customer already have a joint account!");
			} else {

			jointAccount1 = new JointAccount();
	
			jointAccount1.setApprovalStatus("p");
			jointAccount1.setBalance(0.00);
			jointAccount1.setStatus(0);
			jointAccount1.setCustomerId1(customerId1);
			jointAccount1.setCustomerId2(customerId2);
			
			jointAccountDao.addJointAccount(jointAccount1);
			
			System.out.println("Applied for joint account!");
			}
			} else {
				System.out.println("You already have an account! You are only allowed to have one joint account.");
		} 
		} else {
			System.out.println("The customer you wish to open a joint account with does not exist!");
		}

		}
		}
	}

}
