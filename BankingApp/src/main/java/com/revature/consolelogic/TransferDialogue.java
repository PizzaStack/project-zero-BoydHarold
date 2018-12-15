package com.revature.consolelogic;

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
	
	
	public void transfer(int customerId) {
		ca.setupDefaultValues();
    	sa.setupDefaultValues();
    	ja.setupDefaultValues();
    	
    	System.out.println("Which account would you like to transfer from?");
    	System.out.println("1. Checking");
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
    	
    	validEntry = false;
    	System.out.println("\nPlease enter the amount:");
    	String amountString = sc.nextLine();
    	
    	String numberOfTrailingDigits = amountString.substring(amountString.indexOf(".") + 1, amountString.length());
    	
    	while(validEntry == false) {
    		if(amountString.contains("-")) {
    			validEntry = false;
    			System.out.println("Amount contains invalid character '-', please enter in a new amount!");
    		} else if(numberOfTrailingDigits.length() > 2){
    			validEntry = false;
    			System.out.println("Amount invalid, please enter in a valid value. Ex: 19.94");
    			amountString = sc.nextLine();
    			numberOfTrailingDigits = amountString.substring(amountString.indexOf(".") + 1, amountString.length());
    		} else {
    			validEntry = true;
    		}
    		amount = Double.parseDouble(amountString);
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
