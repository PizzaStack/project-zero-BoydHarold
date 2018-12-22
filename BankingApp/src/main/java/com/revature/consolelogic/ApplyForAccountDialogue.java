package com.revature.consolelogic;

import java.sql.Connection;
import java.util.Scanner;

import com.revature.CheckingAccount;
import com.revature.Customer;
import com.revature.JointAccount;
import com.revature.SavingsAccount;

public class ApplyForAccountDialogue {
	Scanner sc = new Scanner(System.in);
	CheckingAccount ca = new CheckingAccount();
	SavingsAccount sa = new SavingsAccount();
	JointAccount ja = new JointAccount();
	
	public void apply(int customerId, Connection connection) {
		System.out.println("\nWhich type of account would you like to apply for?");
		System.out.println("\n1. Checking");
		System.out.println("2. Savings");
		System.out.println("3. Joint");
		System.out.println("4. Back");
		
		String choice = sc.nextLine();
		
		boolean validEntry = false;
		
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
			ca.applyForAccount(customerId);
		} else if (choice.equals("2")) {
			sa.applyForAccount(customerId);
		} else if (choice.equals("3")) {
			System.out.println("Enter in the customer id of whom you would like to open this joint account with:");
			String customerId2 = sc.nextLine();
			
			validEntry = false;
			
			while(validEntry == false) {
				boolean isNumeric = isNumeric(customerId2);
				if(isNumeric) {
					validEntry = true;
				} else {
					System.out.println("Invalid entry! Enter in a valid customer id!");
				}
			}

			ja.applyForAccount(customerId, Integer.parseInt(customerId2), connection);
		} else if(choice.equals("4")) {
			CustomerPortalDialogue cpd = new CustomerPortalDialogue();
			cpd.customerOptions(customerId, connection);
		}
	}
	
	public boolean isNumeric(String datePart) {
		boolean isNumeric = false;
		try {
			Integer.parseInt(datePart);
			isNumeric = true;
		} catch (Exception e) {
			isNumeric = false;
		}

		return isNumeric;
	}
}
