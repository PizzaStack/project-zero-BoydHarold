package com.revature.consolelogic;

import java.text.DecimalFormat;
import java.util.Scanner;

import com.revature.CheckingAccount;
import com.revature.JointAccount;
import com.revature.SavingsAccount;

public class DepositDialogue {
	boolean validEntry = false;
	double amount;
	Scanner sc = new Scanner(System.in);
	CheckingAccount ca = new CheckingAccount();
	SavingsAccount sa = new SavingsAccount();
	JointAccount ja = new JointAccount();
	DecimalFormat df = new DecimalFormat("#0.00");
	String source;
	
	public void deposit(int customerId) {
		double checkingBalance = ca.getBalance(customerId);
		double savingsBalance = sa.getBalance(customerId);
		double jointBalance = ja.getBalance(ja.getPosition(customerId));
		
    	
    	String checkingAccountStatus = ca.getAccountStatus(customerId);
    	String savingsAccountStatus = sa.getAccountStatus(customerId);
    	String jointAccountStatus = ja.getAccountStatus(ja.getPosition(customerId));
    	
    	System.out.println("Which account would you like to deposit to?\n");
    	if(checkingAccountStatus.equals("1") && savingsAccountStatus.equals("1") && jointAccountStatus.equals("1")) {
    		System.out.println("Current Balances:");
    		System.out.println("Checking Balance: $" + df.format(checkingBalance));
    		System.out.println("Savings Balance: $" + df.format(savingsBalance));
    		System.out.println("Joint Balance: $" + df.format(jointBalance));
        	System.out.println("\n1. Checking");
        	System.out.println("2. Savings");
        	System.out.println("3. Joint");
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
        		source = "Joint";
        	}
        	
    	} else if(checkingAccountStatus.equals("1") && savingsAccountStatus.equals("1") && jointAccountStatus.equals("0")) {
    		System.out.println("Current Balances:");
    		System.out.println("Checking Balance: $" + df.format(checkingBalance));
    		System.out.println("Savings Balance: $" + df.format(savingsBalance));
        	System.out.println("\n1. Checking");
        	System.out.println("2. Savings");
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
        		source = "Checking";
        	} else if(source.equals("2")) {
        		source = "Savings";
        	}
        	
    	} else if(checkingAccountStatus.equals("1") && savingsAccountStatus.equals("0") && jointAccountStatus.equals("1")) {
    		System.out.println("Current Balances:");
    		System.out.println("Checking Balance: $" + df.format(checkingBalance));
    		System.out.println("Joint Balance: $" + df.format(jointBalance));
        	System.out.println("\n1. Checking");
        	System.out.println("2. Joint");
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
        		source = "Checking";
        	} else if(source.equals("2")) {
        		source = "Joint";
        	}
        	
    	} else if(checkingAccountStatus.equals("0") && savingsAccountStatus.equals("1") && jointAccountStatus.equals("1")) {
    		System.out.println("Current Balances:");
    		System.out.println("Savings Balance: $" + df.format(savingsBalance));
    		System.out.println("Joint Balance: $" + df.format(jointBalance));
        	System.out.println("\n1. Savings");
        	System.out.println("2. Joint");
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
        		source = "Savings";
        	} else if(source.equals("2")) {
        		source = "Joint";
        	}
    		
     	} else if(checkingAccountStatus.equals("0") && savingsAccountStatus.equals("0") && jointAccountStatus.equals("1")) {
     		System.out.println("Current Balances:");
    		System.out.println("Joint Balance: $" + df.format(jointBalance));
        	System.out.println("\n1. Joint");
        	source = sc.nextLine();
        	
        	while(validEntry == false) {
            	if(source.equals("1")) {
            		validEntry = true;
            	} else {
            		validEntry = false;
            		System.out.println("Invalid entry. Please enter the following value: 1.");
            		source = sc.nextLine();
            	}
    
     	} 
        	
        	if(source.equals("1")) {
        		source = "Joint";
        	}
     	} else if(checkingAccountStatus.equals("0") && savingsAccountStatus.equals("1") && jointAccountStatus.equals("0")) {
     		System.out.println("Current Balances:");
    		System.out.println("Savings Balance: $" + df.format(jointBalance));
        	System.out.println("\n1. Savings");
        	source = sc.nextLine();
        	
        	while(validEntry == false) {
            	if(source.equals("1")) {
            		validEntry = true;
            	} else {
            		validEntry = false;
            		System.out.println("Invalid entry. Please enter the following value: 1.");
            		source = sc.nextLine();
            	}
        	}
        	
        	if(source.equals("1")) {
        		source = "Savings";
        	}
     	} else if(checkingAccountStatus.equals("1") && savingsAccountStatus.equals("0") && jointAccountStatus.equals("0")) {
     		System.out.println("Current Balances:");
    		System.out.println("Checking Balance: $" + df.format(jointBalance));
        	System.out.println("\n1. Checking");
        	source = sc.nextLine();
        	
        	while(validEntry == false) {
            	if(source.equals("1")) {
            		validEntry = true;
            	} else {
            		validEntry = false;
            		System.out.println("Invalid entry. Please enter the following value: 1.");
            		source = sc.nextLine();
            	}
            	
        	}
        	
        	if(source.equals("1")) {
        		source = "Checking";
        	}
     	}
    	
    	validEntry = false;
    	System.out.println("\nPlease enter the amount:");
    	String amountString = sc.nextLine();
    	String numberOfTrailingDigits = amountString.substring(amountString.indexOf(".") + 1, amountString.length());
    	int containsDecimal = amountString.indexOf(".");
    	
    	while(validEntry == false) {
    		int conditionCheck = 2;
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

    		
    		if(conditionCheck == 0) {
    			validEntry = true;
    		} else {
    		amountString = sc.nextLine();

    		}
    	}

    	
    	if(source.equals("Checking")) {
    		ca.deposit(customerId, amount);
    		double newBalance = ca.getBalance(customerId);
    		System.out.println("\n$" + df.format(amount) + " added to checking account.");
    		System.out.println("\nNew balance: $" + df.format(newBalance));
    	} else if(source.equals("Savings")) {
    		sa.deposit(customerId, amount);
    		double newBalance = sa.getBalance(customerId);
    		System.out.println("\n$" + df.format(amount) + " added to savings account.");
    		System.out.println("\nNew balance: $" + df.format(newBalance));
    	} else if(source.equals("Joint")) {
    		ja.deposit(customerId, amount);
    		double newBalance = ja.getBalance(customerId);
    		System.out.println("\n$" + df.format(amount) + " added to joint account.");
    		System.out.println("\nNew balance: $" + df.format(newBalance));
    	}
	}
	}

