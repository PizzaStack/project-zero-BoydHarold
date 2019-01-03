package com.revature.consolelogic;

import java.util.Scanner;

import com.revature.Approval;

public class EmployeePortalDialogue {
	private Scanner sc = new Scanner(System.in);
	private boolean loop = true;

	public void employeeOptions() {
		while (loop) {
			System.out.println("\nWhat would you like to do?");
			System.out.println("\n1. View Customer Information");
			System.out.println("2. View Pending Accountts");
			System.out.println("3. Approve/Deny Pending Accounts");
			System.out.println("4. Log Out");
			String choice = sc.nextLine();

			boolean validEntry = false;

			while (validEntry == false) {
				if (choice.equals("1") || choice.equals("2") || choice.equals("3") || choice.equals("4")) {
					validEntry = true;
				} else {
					validEntry = false;
					System.out.println("Entry invalid! Please enter in either 1, 2, 3, or 4.");
					choice = sc.nextLine();
				}
			}

			if (choice.equals("1")) {
				CustomerDialogue cd = new CustomerDialogue();
				cd.displayCustomer();
			} else if (choice.equals("2")) {
				System.out.println("\nChoose an account type:");
				System.out.println("\n1. Checking");
				System.out.println("2. Savings");
				System.out.println("3. Joint");
				System.out.println("4. Back");

				String choice2 = sc.nextLine();

				validEntry = false;

				while (validEntry == false) {
					if (choice2.equals("1") || choice2.equals("2") || choice2.equals("3") || choice2.equals("4")) {
						validEntry = true;
					} else {
						validEntry = false;
						System.out.println("Entry invalid! Please enter in either 1, 2, 3, or 4.");
						choice2 = sc.nextLine();
					}
				}

				Approval approval = new Approval();

				if (choice2.equals("1")) {
					approval.listPendingChecking();
				} else if (choice2.equals("2")) {
					approval.listPendingSavings();
				} else if (choice2.equals("3")) {
					approval.listPendingJoint();
				} else if (choice2.equals("4")) {
					employeeOptions();
				}

			} else if (choice.equals("3")) {
				System.out.println("\nChoose an account type:");
				System.out.println("\n1. Checking");
				System.out.println("2. Savings");
				System.out.println("3. Joint");
				System.out.println("4. Back");

				String choice2 = sc.nextLine();

				validEntry = false;

				while (validEntry == false) {
					if (choice2.equals("1") || choice2.equals("2") || choice2.equals("3") || choice2.equals("4")) {
						validEntry = true;
					} else {
						validEntry = false;
						System.out.println("Entry invalid! Please enter in either 1, 2, 3, or 4.");
						choice2 = sc.nextLine();
					}
				}

				if (choice2.equals("4")) {
					employeeOptions();
				}

				Approval approval = new Approval();

				System.out.println("\nWhat would you like to do?");
				System.out.println("\n1. Approve");
				System.out.println("2. Deny");
				System.out.println("3. Back");

				String choice3 = sc.nextLine();

				validEntry = false;
				while (validEntry == false) {
					if (choice3.equals("1") || choice3.equals("2") || choice3.equals("3")) {
						validEntry = true;
					} else {
						validEntry = false;
						System.out.println("Invalid entry! Enter in 1, 2, or 3.");
						choice3 = sc.nextLine();
					}
				}

				if (choice3.equals("3")) {
					employeeOptions();
				}

				System.out.println("\nEnter in the customerId:");

				String id = sc.nextLine();
				ApplyForAccountDialogue afad = new ApplyForAccountDialogue();
				validEntry = false;
				while (validEntry == false) {
					boolean isNumeric = afad.isNumeric(id);
					if (isNumeric) {
						validEntry = true;
					} else {
						validEntry = false;
						System.out.println("Invalid entry. Please enter in a valid id!");
						id = sc.nextLine();
					}
				}

				if (choice2.equals("1")) {
					if (choice3.equals("1")) {
						approval.approve(Integer.parseInt(id), "1");
					} else {
						approval.deny(Integer.parseInt(id), "1");
					}
				} else if (choice2.equals("2")) {
					if (choice3.equals("1")) {
						approval.approve(Integer.parseInt(id), "2");
					} else {
						approval.deny(Integer.parseInt(id), "2");
					}
				} else if (choice2.equals("3")) {
					if (choice3.equals("1")) {
						approval.approve(Integer.parseInt(id), "3");
					} else {
						approval.deny(Integer.parseInt(id), "3");
					}
				}
			} else if (choice.equals("4")) {
				LoginDialogue loginDialogue = new LoginDialogue();
				loginDialogue.login();
			}
		}

	}
}
