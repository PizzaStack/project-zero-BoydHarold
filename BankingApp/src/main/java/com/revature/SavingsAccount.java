package com.revature;

import java.text.DecimalFormat;

import com.revature.dao.CheckingAccountDao;
import com.revature.dao.JointAccountDao;
import com.revature.dao.SavingsAccountDao;
import com.revature.daoimp.CheckingAccountDaoImp;
import com.revature.daoimp.JointAccountDaoImp;
import com.revature.daoimp.SavingsAccountDaoImp;

public class SavingsAccount {
	DecimalFormat df = new DecimalFormat("#0.00");
	CheckingAccountDao checkingAccountDao = new CheckingAccountDaoImp();
	SavingsAccountDao savingsAccountDao = new SavingsAccountDaoImp();
	JointAccountDao jointAccountDao = new JointAccountDaoImp();


	
	public boolean withdrawl(int customerId, double amount) {
		double currentBalance = savingsAccountDao.getBalance(customerId);
		double newBalance = currentBalance - amount;
		if(newBalance < 0) {
			System.out.println("You do not have enough money to withdrawl that much! You only have: $" + df.format(currentBalance) + " remaining in your account!");
			return false;
		} else {
			savingsAccountDao.setBalance(customerId, newBalance);
		return true;
		}
	}

	public void deposit(int customerId, double amount) {
		double currentBalance = savingsAccountDao.getBalance(customerId);
		double newBalance = currentBalance + amount;
		savingsAccountDao.setBalance(customerId, newBalance);
	}

	public void transfer(int customerId, double amount, String source, String destination) {
		CheckingAccount ca = new CheckingAccount();
		JointAccount ja = new JointAccount();
		
		if(destination.equals("Checking")) {
			withdrawl(customerId, amount);
			ca.deposit(customerId, amount);
			System.out.println("New Savings Account Balance: $" + df.format(savingsAccountDao.getBalance(customerId)));
			System.out.println("New Checking Account Balance: $" + df.format(checkingAccountDao.getBalance(customerId)));
		} else if(destination.equals("Joint")) {
			withdrawl(customerId, amount);
			ja.deposit(customerId, amount);
			System.out.println("New Savings Account Balance: $" + df.format(savingsAccountDao.getBalance(customerId)));
			System.out.println("New Joint Account Balance: $" + df.format(jointAccountDao.getBalance(customerId)));
		}
	}

	public void applyForAccount(int customerId) {
		String approvalStatus = savingsAccountDao.getApprovalStatus(customerId);
		if (approvalStatus.equals("p")) {
			System.out.println("Approval already pending!");
		} else {
			
			if(approvalStatus.equals("d")) {
			savingsAccountDao.applyForAccount(customerId);
			System.out.println("Applied for Savings Account!");
			
			} else {
				System.out.println("You already have an account!");
			}
		}
	}

	

}
