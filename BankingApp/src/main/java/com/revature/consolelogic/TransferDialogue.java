package com.revature.consolelogic;

import java.text.DecimalFormat;
import java.util.Scanner;

import com.revature.CheckingAccount;
import com.revature.JointAccount;
import com.revature.SavingsAccount;

public class TransferDialogue {
	String source;
	String destination;
	boolean validEntry = false;
	double amount;
	Scanner sc = new Scanner(System.in);
	CheckingAccount ca = new CheckingAccount();
	SavingsAccount sa = new SavingsAccount();
	JointAccount ja = new JointAccount();
	DecimalFormat df = new DecimalFormat("#0.00");
	
	
	public void transfer(int customerId) {
		double checkingBalance = ca.getBalance(customerId);
		double savingsBalance = sa.getBalance(customerId);
		double jointBalance = ja.getBalance(ja.getPosition(customerId));
		
		ca.setupDefaultValues();
    	sa.setupDefaultValues();
    	ja.setupDefaultValues();
    	
    	String checkingAccountStatus = ca.getAccountStatus(customerId);
    	String savingsAccountStatus = sa.getAccountStatus(customerId);
    	String jointAccountStatus = ja.getAccountStatus(ja.getPosition(customerId));
    	
    	System.out.println("Which account would you like to transfer from?\n");
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
        	
        	validEntry = false;
        	
        	System.out.println("\nWhich account would you like to transfer to?");
        	if(source.equals("1")) {
            	System.out.println("1. Savings");
            	System.out.println("2. Joint");
            	source = "Checking";
        	} else if(source.equals("2")) {
            	System.out.println("1. Checking");
            	System.out.println("2. Joint");
            	source = "Savings";
        	} else if(source.equals("3")) {
            	System.out.println("1. Checking");
            	System.out.println("2. Savings");
            	source = "Joint";
        	}
        	destination = sc.nextLine();
        	
        	while(validEntry == false) {
        		if(destination.equals("1") || destination.equals("2")) {
        			validEntry = true;
        		} else {
        	    	validEntry = false;
            		System.out.println("Invalid entry. Please enter in one of the following values: 1 or 2.");
        	    	destination = sc.nextLine();
        		}
        	}

        	if(source.equals("Checking") && destination.equals("1")) {
        		destination = "Savings";
        	} else if(source.equals("Checking") && destination.equals("2")) {
        		destination = "Joint";
        	} else if(source.equals("Savings") && destination.equals("1")) {
        		destination = "Checking";
        	} else if(source.equals("Savings") && destination.equals("2")) {
        		destination = "Joint";
        	} else if(source.equals("Joint") && destination.equals("1")) {
        		destination = "Checking";
        	} else if(source.equals("Joint") && destination.equals("2")) {
        		destination = "Savings";
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
        	
        	
        	validEntry = false;
        	
        	System.out.println("\nWhich account would you like to transfer to?");
        	if(source.equals("1")) {
            	System.out.println("1. Savings");
            	source = "Checking";
        	} else if(source.equals("2")) {
            	System.out.println("1. Checking");
            	source = "Savings";
        	} 
        	destination = sc.nextLine();
        	
        	while(validEntry == false) {
        		if(destination.equals("1")) {
        			validEntry = true;
        		} else {
        	    	validEntry = false;
            		System.out.println("Invalid entry. Please enter in the following value: 1.");
        	    	destination = sc.nextLine();
        		}
        	}

        	if(source.equals("Checking") && destination.equals("1")) {
        		destination = "Savings";
        	} else if(source.equals("Savings") && destination.equals("1")) {
        		destination = "Checking";
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
        	
        	validEntry = false;
        	
        	System.out.println("\nWhich account would you like to transfer to?");
        	if(source.equals("1")) {
            	System.out.println("1. Joint");
            	source = "Checking";
        	} else if(source.equals("2")) {
            	System.out.println("1. Checking");
            	source = "Joint";
        	}
        	destination = sc.nextLine();
        	
        	while(validEntry == false) {
        		if(destination.equals("1")) {
        			validEntry = true;
        		} else {
        	    	validEntry = false;
            		System.out.println("Invalid entry. Please enter in the following value: 1.");
        	    	destination = sc.nextLine();
        		}
        	}

        	if(source.equals("Checking") && destination.equals("1")) {
        		destination = "Joint";
        	} else if(source.equals("Joint") && destination.equals("1")) {
        		destination = "Checking";
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
        	
        	validEntry = false;
        	
        	System.out.println("\nWhich account would you like to transfer to?");
        	if(source.equals("1")) {
            	System.out.println("1. Joint");
            	source = "Savings";
        	} else if(source.equals("2")) {
            	System.out.println("1. Savings");
            	source = "Joint";
        	}
        	destination = sc.nextLine();
        	
        	while(validEntry == false) {
        		if(destination.equals("1")) {
        			validEntry = true;
        		} else {
        	    	validEntry = false;
            		System.out.println("Invalid entry. Please enter in the following value: 1.");
        	    	destination = sc.nextLine();
        		}
        	}

        	if(source.equals("Savings") && destination.equals("1")) {
        		destination = "Joint";
        	} else if(source.equals("Joint") && destination.equals("1")) {
        		destination = "Savings";
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
    		
    		if(amountString.contains("-")) {
    			System.out.println("Amount contains invalid character '-', please enter in a new amount!");
    		} else {
    			conditionCheck--;
    		}
    		
    		if(numberOfTrailingDigits.length() > 2 && containsDecimal > 0){
    			System.out.println("Amount invalid, please enter in a valid value. Ex: 19.94");
    			amountString = sc.nextLine();
    			numberOfTrailingDigits = amountString.substring(amountString.indexOf(".") + 1, amountString.length());
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
    		ca.transfer(customerId, amount, source, destination);
    	} else if(source.equals("Savings")) {
    		sa.transfer(customerId, amount, source, destination);
    	} else if(source.equals("Joint")) {
    		ja.transfer(customerId, amount, source, destination);
    	}
	}
}
