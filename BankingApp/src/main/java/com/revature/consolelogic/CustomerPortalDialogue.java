package com.revature.consolelogic;

import java.sql.Connection;
import java.text.DecimalFormat;
import java.util.Scanner;

import com.revature.CheckingAccount;
import com.revature.JointAccount;
import com.revature.SavingsAccount;
import com.revature.dao.CheckingAccountDao;
import com.revature.dao.JointAccountDao;
import com.revature.dao.SavingsAccountDao;

public class CustomerPortalDialogue {
	CheckingAccount ca = new CheckingAccount();
	SavingsAccount sa = new SavingsAccount();
	JointAccount ja = new JointAccount();
	ApplyForAccountDialogue afad = new ApplyForAccountDialogue();
	WithdrawlDialogue wd = new WithdrawlDialogue();
	DepositDialogue dd = new DepositDialogue();
	TransferDialogue td = new TransferDialogue();
	DecimalFormat df = new DecimalFormat("#0.00");
	Scanner sc = new Scanner(System.in);
	
	public void customerOptions(int customerId, Connection connection) {
		CheckingAccountDao checkingAccountDao = new CheckingAccountDao(connection);
		SavingsAccountDao savingsAccountDao = new SavingsAccountDao(connection);
		JointAccountDao jointAccountDao = new JointAccountDao(connection);
		
		ca = checkingAccountDao.getCheckingAccountById(customerId);
		sa = savingsAccountDao.getSavingsAccountById(customerId);
		ja = jointAccountDao.getJointAccountById(customerId);
		
		if(ca == null) {
			ca = new CheckingAccount();
			ca.setStatus(0);
		}
		
		if(sa == null) {
			sa = new SavingsAccount();
			sa.setStatus(0);
		}
		
		if(ja == null) {
			ja = new JointAccount();
			ja.setStatus(0);
		}
		
    	String checkingAccountStatus = String.valueOf(ca.getStatus());
    	String savingsAccountStatus = String.valueOf(sa.getStatus());
    	String jointAccountStatus = String.valueOf(ja.getStatus());
    	int numOfAccounts = 0;
    	
    	if(checkingAccountStatus.equals("1")) {
    		numOfAccounts++;
    	}
    	
    	if(savingsAccountStatus.equals("1")){
    		numOfAccounts++;
    	}
    	
    	if(jointAccountStatus.equals("1")) {
    		numOfAccounts++;
    	}
    	
    	boolean validEntry = false;
    	
		System.out.println("\nWhat would you like to do?");
		
		if(checkingAccountStatus.equals("1") || savingsAccountStatus.equals("2") || jointAccountStatus.equals("3")) {
			System.out.println("\n1. Manage Accounts");
			System.out.println("2. Apply for Account(s)");
			String choice = sc.nextLine();
			
			while(validEntry == false) {
				if(choice.equals("1") || choice.equals("2")) {
					validEntry = true;
				} else {
					validEntry = false;
					System.out.println("Invalid entry! Please enter in either 1 or 2.");
					choice = sc.nextLine();
				}
			}
				
				if(choice.equals("1")) {
					System.out.println("\nWhat would you like to do?");
					
					if(numOfAccounts >= 2) {
					System.out.println("\n1. Withdrawl");
					System.out.println("2. Deposit");
					System.out.println("3. Transfer");
					choice = sc.nextLine();
					
					while(validEntry == false) {
						if(choice.equals("1") || choice.equals("2") || choice.equals("3")) {
							validEntry = true;
						} else {
							validEntry = false;
							System.out.println("Invalid entry! Please enter in either 1, 2, or 3.");
							choice = sc.nextLine();
						}
					}
					
					if(choice.equals("1")) {
						wd.withdrawl(customerId, connection);
					} else if(choice.equals("2")) {
						dd.deposit(customerId, connection);
					} else if(choice.equals("3")) {
						td.transfer(customerId, connection);
					}
					
					} else {
						System.out.println("\n1. Withdrawl");
						System.out.println("2. Deposit");
						choice = sc.nextLine();
						
						while(validEntry == false) {
							if(choice.equals("1") || choice.equals("2")) {
								validEntry = true;
							} else {
								validEntry = false;
								System.out.println("Invalid entry! Please enter in either 1 or 2.");
								choice = sc.nextLine();
							}
						}
						
						if(choice.equals("1")) {
							wd.withdrawl(customerId, connection);
						} else if(choice.equals("2")) {
							dd.deposit(customerId, connection);
						}
					}
				} else {
					afad.apply(customerId, connection);
				}
			
		} else {
			System.out.println("\n1. Apply for Account(s)");
			String choice = sc.nextLine();
			
			while(validEntry == false) {
				if(choice.equals("1")) {
					validEntry = true;
				} else {
					validEntry = false;
					System.out.println("Invalid entry! Please enter in 1.");
					choice = sc.nextLine();
				}
			}
			
			afad.apply(customerId, connection);
		}
		
		
	}
}
