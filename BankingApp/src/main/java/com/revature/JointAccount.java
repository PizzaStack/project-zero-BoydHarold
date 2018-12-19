package com.revature;


import java.text.DecimalFormat;

import com.revature.dao.CheckingAccountDao;
import com.revature.dao.CustomerDao;
import com.revature.dao.JointAccountDao;
import com.revature.dao.SavingsAccountDao;
import com.revature.daoimp.CheckingAccountDaoImp;
import com.revature.daoimp.CustomerDaoImp;
import com.revature.daoimp.JointAccountDaoImp;
import com.revature.daoimp.SavingsAccountDaoImp;

public class JointAccount{
	DecimalFormat df = new DecimalFormat("#0.00");
	CheckingAccountDao checkingAccountDao = new CheckingAccountDaoImp();
	SavingsAccountDao savingsAccountDao = new SavingsAccountDaoImp();
	JointAccountDao jointAccountDao = new JointAccountDaoImp();

	public boolean withdrawl(int customerId, double amount) {
		double currentBalance = jointAccountDao.getBalance(customerId);
		double newBalance = currentBalance - amount;
		if (newBalance < 0) {
			System.out.println("You do not have enough money to withdrawl that much! You only have: $" + df.format(currentBalance)
					+ " remaining in your account!");
			return false;
		} else {
			jointAccountDao.setBalance(customerId, newBalance);
			return true;
		}
	}

	public void deposit(int customerId, double amount) {
		double currentBalance = jointAccountDao.getBalance(customerId);
		double newBalance = currentBalance + amount;
		jointAccountDao.setBalance(customerId, newBalance);
	}

	public void transfer(int customerId, double amount, String source, String destination) {
		CheckingAccount ca = new CheckingAccount();
		SavingsAccount sa = new SavingsAccount();

		if (destination.equals("Checking")) {
			withdrawl(customerId, amount);
			ca.deposit(customerId, amount);
			System.out.println("New Joint Account Balance: $" + df.format(jointAccountDao.getBalance(customerId)));
			System.out.println("New Checking Account Balance: $" + df.format(checkingAccountDao.getBalance(customerId)));
		} else if (destination.equals("Savings")) {
			withdrawl(customerId, amount);
			sa.deposit(customerId, amount);
			System.out.println("New Joint Account Balance: $" + df.format(jointAccountDao.getBalance(customerId)));
			System.out.println("New Savings Account Balance: $" + df.format(savingsAccountDao.getBalance(customerId)));
		}
	}

	public void applyForAccount(int customerId1, int customerId2) {
		CustomerDao customerDao = new CustomerDaoImp();
		boolean customerCheck = false;
		
		if(customerId1 == customerId2) {
			System.out.println("You cannot open a joint account with yourself!");
		} else {
		Customer customer = new Customer();
		customer = customerDao.getCustomerById(customerId2);
		if(customer == null) {
		customerCheck = false;
		}
		
		String approvalStatus = jointAccountDao.getApprovalStatus(customerId1);
		
		if (approvalStatus.equals("p")) {
			System.out.println("Approval already pending!");
		} else {
		
				
		
		if(customerCheck) {
			if(approvalStatus.equals("0")) {
				
			boolean exists1 = jointAccountDao.getAccountExists(customerId1);
			
			boolean exists2 = jointAccountDao.getAccountExists(customerId2);
				
			if(exists1 == true || exists2 == false) {
				System.out.println("Either you or the requested customer already have a joint account!");
			} else {

			jointAccountDao.applyForAccount(customerId1);
			jointAccountDao.applyForAccount(customerId2);
			
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
