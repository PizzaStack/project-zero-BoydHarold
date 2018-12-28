package com.revature.consolelogic;

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
	boolean loop = true;
	
	public void customerOptions(int customerId, String accessType) {
		while(loop) {
		CheckingAccountDao checkingAccountDao = new CheckingAccountDao();
		SavingsAccountDao savingsAccountDao = new SavingsAccountDao();
		JointAccountDao jointAccountDao = new JointAccountDao();
		
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
		
		if(checkingAccountStatus.equals("1") || savingsAccountStatus.equals("1") || jointAccountStatus.equals("1")) {
			if(accessType.equals("Customer")) {
			System.out.println("\n1. Manage Accounts");
			System.out.println("2. Apply for Account(s)");
			System.out.println("3. Log Out");
			} else if(accessType.equals("Administrator")){
				System.out.println("\n1. Manage Accounts");
				System.out.println("2. Apply for Account(s)");
				System.out.println("3. Back");
			}
			String choice = sc.nextLine();
			
			while(validEntry == false) {
				if(choice.equals("1") || choice.equals("2") || choice.equals("3")) {
					validEntry = true;
				} else {
					validEntry = false;
					System.out.println("Invalid entry! Please enter in 1, 2, or 3.");
					choice = sc.nextLine();
				}
			}
				
				if(choice.equals("1")) {
					System.out.println("\nWhat would you like to do?");
					
					if(numOfAccounts >= 2) {
					System.out.println("\n1. Withdrawl");
					System.out.println("2. Deposit");
					System.out.println("3. Transfer");
					System.out.println("4. Back");
					choice = sc.nextLine();
					
					while(validEntry == false) {
						if(choice.equals("1") || choice.equals("2") || choice.equals("3") || choice.equals("4")) {
							validEntry = true;
						} else {
							validEntry = false;
							System.out.println("Invalid entry! Please enter in either 1, 2, 3, or 4.");
							choice = sc.nextLine();
						}
					}
					
					if(choice.equals("1")) {
						wd.withdrawl(customerId, accessType);
					} else if(choice.equals("2")) {
						dd.deposit(customerId, accessType);
					} else if(choice.equals("3")) {
						td.transfer(customerId, accessType);
					} else if(choice.equals("4")) {
						customerOptions(customerId, accessType);
					}
					
					} else {
						System.out.println("\n1. Withdrawl");
						System.out.println("2. Deposit");
						System.out.println("3. Back");
						choice = sc.nextLine();
						
						while(validEntry == false) {
							if(choice.equals("1") || choice.equals("2") || choice.equals("3")) {
								validEntry = true;
							} else {
								validEntry = false;
								System.out.println("Invalid entry! Please enter in 1, 2, or 3.");
								choice = sc.nextLine();
							}
						}
						
						if(choice.equals("1")) {
							wd.withdrawl(customerId, accessType);
						} else if(choice.equals("2")) {
							dd.deposit(customerId, accessType);
						} else if(choice.equals("3")) {
							customerOptions(customerId, accessType);
						}
					}
				} else if(choice.equals("2")){
					afad.apply(customerId, accessType);
				} else if(choice.equals("3")) {
					if(accessType.equals("Customer")) {
					LoginDialogue loginDialogue = new LoginDialogue();
					loginDialogue.login();
					System.out.println("\nLogged out succesfully!");
					} else if(accessType.endsWith("Administrator")) {
						AdministratorPortalDialogue apd = new AdministratorPortalDialogue();
						apd.administratorOptions();	
					}
				}
			
		} else {
			System.out.println("\n1. Apply for Account(s)");
			System.out.println("2. Log Out");
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
			afad.apply(customerId, accessType);
			} else if(choice.equals("2")) {
				LoginDialogue loginDialogue = new LoginDialogue();
				loginDialogue.login();
				System.out.println("\nLogged out succesfully!");
			}
		}
		
		
	}
	}
}
