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


	public boolean withdrawl(int customerId, double amount) {
		double currentBalance = checkingAccountDao.getBalance(customerId);
		double newBalance = currentBalance - amount;
		if (newBalance < 0) {
			System.out.println("You do not have enough money to withdrawl that much! You only have: $" + df.format(currentBalance)
					+ " remaining in your account!");
			return false;
		} else {
			checkingAccountDao.setBalance(customerId, newBalance);
			return true;
		}
	}

	public void deposit(int customerId, double amount) {
		double currentBalance = checkingAccountDao.getBalance(customerId);
		double newBalance = currentBalance + amount;
		checkingAccountDao.setBalance(customerId, newBalance);
	}

	public void transfer(int customerId, double amount, String source, String destination) {
		SavingsAccount sa = new SavingsAccount();
		JointAccount ja = new JointAccount();

		if (destination.equals("Savings")) {
			withdrawl(customerId, amount);
			sa.deposit(customerId, amount);
			System.out.println("New Checking Account Balance: $" + df.format(checkingAccountDao.getBalance(customerId)));
			System.out.println("New Savings Account Balance: $" + df.format(savingsAccountDao.getBalance(customerId)));
		} else if (destination.equals("Joint")) {
			withdrawl(customerId, amount);
			ja.deposit(customerId, amount);
			System.out.println("New Checking Account Balance: $" + df.format(checkingAccountDao.getBalance(customerId)));
			System.out.println("New Joint Account Balance: $" + df.format(jointAccountDao.getBalance(customerId)));
		}
	}

	public void applyForAccount(int customerId) {
		String approvalStatus = checkingAccountDao.getApprovalStatus(customerId);
		if (approvalStatus.equals("p")) {
			System.out.println("Approval already pending!");
		} else {
			
			if(approvalStatus.equals("d")) {
			checkingAccountDao.applyForAccount(customerId);
				
			System.out.println("Applied for Checking Account!");
			} else {
				System.out.println("You already have an account!");
			}
		}
	}


}
