package com.revature.consolelogic;

import java.sql.Connection;
import java.util.Scanner;

import com.revature.Approval;
import com.revature.dao.AdministrativeFunctionsDao;

public class AdministratorPortalDialogue {
	private boolean loop = true;
	private Scanner sc = new Scanner(System.in);
	
	public void administratorOptions(Connection connection) {
	while(loop) {
		System.out.println("\nWhat would you like to do?");
		System.out.println("\n1. View Customer Information");
		System.out.println("2. View Pending Accountts");
		System.out.println("3. Approve/Deny Pending Accounts");
		System.out.println("4. Manage Checking/Savings/Joint Accounts");
		System.out.println("5. Cancel Accounts");
		String choice = sc.nextLine();
		
		boolean validEntry = false;
		
		while(validEntry == false) {
			if(choice.equals("1") || choice.equals("2") || choice.equals("3") || choice.equals("4") || choice.equals("5")) {
				validEntry = true;
			} else {
				validEntry = false;
				System.out.println("Entry invalid! Please enter in either 1, 2, 3, 4, or 5.");
				choice = sc.nextLine();
			}
		}
		
		if(choice.equals("1")) {
			CustomerDialogue cd = new CustomerDialogue();
			cd.displayCustomer(connection);
		} else if(choice.equals("2")) {
			System.out.println("\nChoose an account type:");
			System.out.println("\n1. Checking");
			System.out.println("2. Savings");
			System.out.println("3. Joint");
			
			String choice2 = sc.nextLine();
			
			validEntry = false;
			
			while(validEntry == false) {
				if(choice2.equals("1") || choice2.equals("2") || choice2.equals("3")) {
					validEntry = true;
				} else {
					validEntry = false;
					System.out.println("Entry invalid! Please enter in either 1, 2, or 3.");
					choice2 = sc.nextLine();
				}
			}
			
			
			Approval approval = new Approval();
			
			if(choice2.equals("1")) {
				approval.listPendingChecking(connection);
			} else if(choice2.equals("2")) {
				approval.listPendingSavings(connection);
			} else if(choice2.equals("3")) {
				approval.listPendingJoint(connection);
			}

		} else if(choice.equals("3")) {
			System.out.println("\nChoose an account type:");
			System.out.println("\n1. Checking");
			System.out.println("2. Savings");
			System.out.println("3. Joint");
			
			String choice2 = sc.nextLine();
			
			validEntry = false;
			
			while(validEntry == false) {
				if(choice2.equals("1") || choice2.equals("2") || choice2.equals("3")) {
					validEntry = true;
				} else {
					validEntry = false;
					System.out.println("Entry invalid! Please enter in either 1, 2, or 3.");
					choice2 = sc.nextLine();
				}
			}
			
			Approval approval = new Approval();
			
			System.out.println("\nWhat would you like to do?");
			System.out.println("\n1. Approve");
			System.out.println("2. Deny");
			
			String choice3 = sc.nextLine();
			
			validEntry = false;
			while(validEntry == false) {
				if(choice3.equals("1") || choice3.equals("2")) {
					validEntry = true;
				} else {
					validEntry = false;
					System.out.println("Invalid entry! Enter in either 1 or 2.");
					choice3 = sc.nextLine();
				}
			}
			
			System.out.println("\nEnter in the customerId:");
			
			String id = sc.nextLine();
			ApplyForAccountDialogue afad = new ApplyForAccountDialogue();
			validEntry = false;
			while(validEntry == false) {
					boolean isNumeric = afad.isNumeric(id);
					if(isNumeric) {
						validEntry = true;
					} else {
						validEntry = false;
						System.out.println("Invalid entry. Please enter in a valid id!");
						id = sc.nextLine();
					}
			}
			
			
			if(choice2.equals("1")) {
				if(choice3.equals("1")) {
					approval.approve(Integer.parseInt(id), "1", connection);
				} else {
					approval.deny(Integer.parseInt(id), "1", connection);
				}
			} else if(choice2.equals("2")) {
				if(choice3.equals("1")) {
					approval.approve(Integer.parseInt(id), "2", connection);
				} else {
					approval.deny(Integer.parseInt(id), "2", connection);
				}
			} else if(choice2.equals("3")) {
				if(choice3.equals("1")) {
					approval.approve(Integer.parseInt(id), "3", connection);
				} else {
					approval.deny(Integer.parseInt(id), "3", connection);
				}
			}
		} else if(choice.equals("4")) {
			System.out.println("\nEnter in the Customer id belonging to the account(s) you wish to interact with:");
			String id = sc.nextLine();
			
			ApplyForAccountDialogue afad = new ApplyForAccountDialogue();
			validEntry = false;
			while(validEntry == false) {
					boolean isNumeric = afad.isNumeric(id);
					if(isNumeric) {
						validEntry = true;
					} else {
						validEntry = false;
						System.out.println("Invalid entry. Please enter in a valid id!");
						id = sc.nextLine();
					}
			}
			
			CustomerPortalDialogue cpd = new CustomerPortalDialogue();
			cpd.customerOptions(Integer.parseInt(id), connection);
			
		} else if(choice.equals("5")) {
			System.out.println("\nWhich type of account would you like to cancel?");
			System.out.println("\n1. Customer");
			System.out.println("2. Employee");
			System.out.println("3. Administrator");
			
			String choice4 = sc.nextLine();
			
			validEntry = false;
			while(validEntry == false) {
				if(choice4.equals("1") || choice4.equals("2") || choice4.equals("3")) {
					validEntry = true;
				} else {
					validEntry = false;
					System.out.println("Invalid entry! Enter in either 1, 2, or 3.");
					choice4 = sc.nextLine();
				}
			}
			
			System.out.println("\nEnter in the id you wish to cancel account(s) for:");
			String id = sc.nextLine();
			
			ApplyForAccountDialogue afad = new ApplyForAccountDialogue();
			validEntry = false;
			while(validEntry == false) {
					boolean isNumeric = afad.isNumeric(id);
					if(isNumeric) {
						validEntry = true;
					} else {
						validEntry = false;
						System.out.println("Invalid entry. Please enter in a valid id!");
						id = sc.nextLine();
					}
			}
			
			AdministrativeFunctionsDao afd = new AdministrativeFunctionsDao(connection);
			
			afd.cancelAccount(Integer.parseInt(id), choice4);
			
			
		}
	}
}
}
