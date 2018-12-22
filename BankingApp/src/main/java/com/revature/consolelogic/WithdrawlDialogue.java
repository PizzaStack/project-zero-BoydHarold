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

public class WithdrawlDialogue {
	boolean validEntry = false;
	double amount;
	Scanner sc = new Scanner(System.in);

	DecimalFormat df = new DecimalFormat("#0.00");
	String source;

	
	
	public void withdrawl(int customerId, Connection connection, String accessType) {
		
		CheckingAccountDao checkingAccountDao = new CheckingAccountDao(connection);
		SavingsAccountDao savingsAccountDao = new SavingsAccountDao(connection);
		JointAccountDao jointAccountDao = new JointAccountDao(connection);
		
		CheckingAccount ca = checkingAccountDao.getCheckingAccountById(customerId);
		SavingsAccount sa = savingsAccountDao.getSavingsAccountById(customerId);
		JointAccount ja = jointAccountDao.getJointAccountById(customerId);
		
		if(ca == null) {
			ca = new CheckingAccount();
			ca.setBalance(0.00);
			ca.setStatus(0);
			
		}
		
		if(sa == null) {
			sa = new SavingsAccount();
			sa.setBalance(0.00);
			sa.setStatus(0);
		}
		
		if(ja == null) {
			ja = new JointAccount();
			ja.setBalance(0.00);
			ja.setStatus(0);
		}
		
		double checkingBalance = ca.getBalance();
		double savingsBalance = sa.getBalance();
		double jointBalance = ja.getBalance();
		
    	
    	String checkingAccountStatus = String.valueOf(ca.getStatus());
    	String savingsAccountStatus = String.valueOf(sa.getStatus());
    	String jointAccountStatus = String.valueOf(ja.getStatus());
    	
    	System.out.println("Which account would you like to withdrawl from?\n");
    	if(checkingAccountStatus.equals("1") && savingsAccountStatus.equals("1") && jointAccountStatus.equals("1")) {
    		System.out.println("Current Balances:");
    		System.out.println("Checking Balance: $" + df.format(checkingBalance));
    		System.out.println("Savings Balance: $" + df.format(savingsBalance));
    		System.out.println("Joint Balance: $" + df.format(jointBalance));
        	System.out.println("\n1. Checking");
        	System.out.println("2. Savings");
        	System.out.println("3. Joint");
        	System.out.println("4. Back");
        	source = sc.nextLine();
        	
        	while(validEntry == false) {
            	if(source.equals("1") || source.equals("2") || source.equals("3") || source.equals("4")) {
            		validEntry = true;
            	} else {
            		validEntry = false;
            		System.out.println("Invalid entry. Please enter in one of the following values: 1, 2, 3, or 4.");
            		source = sc.nextLine();
            	}
            	}
        	
        	if(source.equals("1")) {
        		source = "Checking";
        	} else if(source.equals("2")) {
        		source = "Savings";
        	} else if(source.equals("3")) {
        		source = "Joint";
        	} else if(source.equals("4")) {
        		CustomerPortalDialogue cpd = new CustomerPortalDialogue();
        		cpd.customerOptions(customerId, connection, accessType);
        	}
        	
    	} else if(checkingAccountStatus.equals("1") && savingsAccountStatus.equals("1") && jointAccountStatus.equals("0")) {
    		System.out.println("Current Balances:");
    		System.out.println("Checking Balance: $" + df.format(checkingBalance));
    		System.out.println("Savings Balance: $" + df.format(savingsBalance));
        	System.out.println("\n1. Checking");
        	System.out.println("2. Savings");
        	System.out.println("3. Back");
        	source = sc.nextLine();
        	
        	while(validEntry == false) {
            	if(source.equals("1") || source.equals("2") || source.equals("3")) {
            		validEntry = true;
            	} else {
            		validEntry = false;
            		System.out.println("Invalid entry. Please enter in one of the following values: 1, 2, or 3.");
            		source = sc.nextLine();
            	}
            	}
        	
        	if(source.equals("1")) {
        		source = "Checking";
        	} else if(source.equals("2")) {
        		source = "Savings";
        	} else if(source.equals("3")) {
        		CustomerPortalDialogue cpd = new CustomerPortalDialogue();
        		cpd.customerOptions(customerId, connection, accessType);
        	}
        	
    	} else if(checkingAccountStatus.equals("1") && savingsAccountStatus.equals("0") && jointAccountStatus.equals("1")) {
    		System.out.println("Current Balances:");
    		System.out.println("Checking Balance: $" + df.format(checkingBalance));
    		System.out.println("Joint Balance: $" + df.format(jointBalance));
        	System.out.println("\n1. Checking");
        	System.out.println("2. Joint");
        	System.out.println("3. Back");
        	source = sc.nextLine();
        	
        	while(validEntry == false) {
            	if(source.equals("1") || source.equals("2") || source.equals("3")) {
            		validEntry = true;
            	} else {
            		validEntry = false;
            		System.out.println("Invalid entry. Please enter in one of the following values: 1, 2, or 3.");
            		source = sc.nextLine();
            	}
            	}
        	
        	if(source.equals("1")) {
        		source = "Checking";
        	} else if(source.equals("2")) {
        		source = "Joint";
        	} else if(source.equals("3")) {
        		CustomerPortalDialogue cpd = new CustomerPortalDialogue();
        		cpd.customerOptions(customerId, connection, accessType);
        	}
        	
    	} else if(checkingAccountStatus.equals("0") && savingsAccountStatus.equals("1") && jointAccountStatus.equals("1")) {
    		System.out.println("Current Balances:");
    		System.out.println("Savings Balance: $" + df.format(savingsBalance));
    		System.out.println("Joint Balance: $" + df.format(jointBalance));
        	System.out.println("\n1. Savings");
        	System.out.println("2. Joint");
        	System.out.println("3. Back");
        	source = sc.nextLine();
        	
        	while(validEntry == false) {
            	if(source.equals("1") || source.equals("2") || source.equals("3")) {
            		validEntry = true;
            	} else {
            		validEntry = false;
            		System.out.println("Invalid entry. Please enter in one of the following values: 1, 2, or 3.");
            		source = sc.nextLine();
            	}
    
     	} 
        	
        	if(source.equals("1")) {
        		source = "Savings";
        	} else if(source.equals("2")) {
        		source = "Joint";
        	} else if(source.equals("3")) {
        		CustomerPortalDialogue cpd = new CustomerPortalDialogue();
        		cpd.customerOptions(customerId, connection, accessType);
        	}
    		
     	} else if(checkingAccountStatus.equals("0") && savingsAccountStatus.equals("0") && jointAccountStatus.equals("1")) {
     		System.out.println("Current Balances:");
    		System.out.println("Joint Balance: $" + df.format(jointBalance));
        	System.out.println("\n1. Joint");
        	System.out.println("2. Back");
        	source = sc.nextLine();
        	
        	while(validEntry == false) {
            	if(source.equals("1") || source.equals("2")) {
            		validEntry = true;
            	} else {
            		validEntry = false;
            		System.out.println("Invalid entry. Please enter in one of the following values: 1 or 2.");
            		source = sc.nextLine();
            	}
    
     	} 
        	
        	if(source.equals("1")) {
        		source = "Joint";
        	} else if(source.equals("2")) {
        		CustomerPortalDialogue cpd = new CustomerPortalDialogue();
        		cpd.customerOptions(customerId, connection, accessType);
        	}
     	} else if(checkingAccountStatus.equals("0") && savingsAccountStatus.equals("1") && jointAccountStatus.equals("0")) {
     		System.out.println("Current Balances:");
    		System.out.println("Savings Balance: $" + df.format(jointBalance));
        	System.out.println("\n1. Savings");
        	System.out.println("2. Back");
        	source = sc.nextLine();
        	
        	while(validEntry == false) {
            	if(source.equals("1") || source.equals("2")) {
            		validEntry = true;
            	} else {
            		validEntry = false;
            		System.out.println("Invalid entry. Please enter one of the following values: 1 or 2.");
            		source = sc.nextLine();
            	}
        	}
        	
        	if(source.equals("1")) {
        		source = "Savings";
        	} else if(source.equals("2")) {
        		CustomerPortalDialogue cpd = new CustomerPortalDialogue();
        		cpd.customerOptions(customerId, connection,accessType);
        	}
     	} else if(checkingAccountStatus.equals("1") && savingsAccountStatus.equals("0") && jointAccountStatus.equals("0")) {
     		System.out.println("Current Balances:");
    		System.out.println("Checking Balance: $" + df.format(jointBalance));
        	System.out.println("\n1. Checking");
        	System.out.println("2. Back");
        	source = sc.nextLine();
        	
        	while(validEntry == false) {
            	if(source.equals("1") || source.equals("2")) {
            		validEntry = true;
            	} else {
            		validEntry = false;
            		System.out.println("Invalid entry. Please enter one of the following values: 1 or 2.");
            		source = sc.nextLine();
            	}
            	
        	}
        	
        	if(source.equals("1")) {
        		source = "Checking";
        	} else if(source.equals("2")) {
        		CustomerPortalDialogue cpd = new CustomerPortalDialogue();
        		cpd.customerOptions(customerId, connection, accessType);
        	}
     	}
    	
    	validEntry = false;
    	System.out.println("\nPlease enter the amount:");
    	String amountString = sc.nextLine();
    	String numberOfTrailingDigits = amountString.substring(amountString.indexOf(".") + 1, amountString.length());
    	int containsDecimal = amountString.indexOf(".");
    	
    	while(validEntry == false) {
    		int conditionCheck = 3;
    		containsDecimal = amountString.indexOf(".");
			numberOfTrailingDigits = amountString.substring(amountString.indexOf(".") + 1, amountString.length());
			
    		if(amountString.contains("-")) {
    			System.out.println("Amount contains invalid character '-', please enter in a new amount!");
    		} else {
    			conditionCheck--;
    		}
    		
    		if(numberOfTrailingDigits.length() > 2 && containsDecimal > 0){
    			System.out.println("Amount invalid, please enter in a valid value. Ex: 19.94");

    		} else {
    			conditionCheck--;
    		}
    		
    		amount = Double.parseDouble(amountString);

    		
    		
    		if(source.equals("Checking")) {
        		
        		if((checkingBalance - amount) < 0) {
        			System.out.println("You do not have enough money in your checking account! Your current checking account balance is: $" + df.format(checkingBalance));
        		} else {
        			conditionCheck--;
        		}
        	} else if(source.equals("Savings")) {
        		
        		if((savingsBalance - amount) < 0) {
        			System.out.println("You do not have enough money in your savings account! Your current savings account balance is: $" + df.format(savingsBalance));
        		} else {
        			conditionCheck--;
        		}
        	} else if(source.equals("Joint")) {
        		if((jointBalance - amount) < 0) {
        			System.out.println("You do not have enough money in your joint account! Your current joint account balance is: $" + df.format(jointBalance));
        		} else {
        			conditionCheck--;
        		}
        	}
    		
    		if(conditionCheck == 0) {
    			validEntry = true;
    		} else {
    		amountString = sc.nextLine();
    		}
    	}

    	
    	if(source.equals("Checking")) {
    		double newBalance = ca.withdrawl(customerId, amount);
    		System.out.println("\n$" + df.format(amount) + " withdrawn from checking account.");
    		System.out.println("\nNew balance: $" + df.format(newBalance));
    	} else if(source.equals("Savings")) {
    		double newBalance = sa.withdrawl(customerId, amount);
    		System.out.println("\n$" + df.format(amount) + " withdrawn from savings account.");
    		System.out.println("\nNew balance: $" + df.format(newBalance));
    	} else if(source.equals("Joint")) {
    		double newBalance = ja.withdrawl(customerId, amount);
    		System.out.println("\n$" + df.format(amount) + " withdrawn from joint account.");
    		System.out.println("\nNew balance: $" + df.format(newBalance));
    	}
	}
	}

