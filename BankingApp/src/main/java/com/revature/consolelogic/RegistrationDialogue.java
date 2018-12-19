package com.revature.consolelogic;

import java.util.Scanner;

import com.revature.AdminRegistration;
import com.revature.CustomerRegistration;
import com.revature.EmployeeRegistration;
import com.revature.dao.AdminRegistrationDao;
import com.revature.dao.CustomerRegistrationDao;
import com.revature.dao.EmployeeRegistrationDao;

public class RegistrationDialogue {
	CustomerDialogue cd = new CustomerDialogue();
	Scanner sc = new Scanner(System.in);
	AdminRegistration ar = new AdminRegistration();
	CustomerRegistration cr = new CustomerRegistration();
	EmployeeRegistration er = new EmployeeRegistration();
	String username;
	String password;
	
	public void register() {
		System.out.println("\nWhich type of account would you like to Register?\n");
		System.out.println("1. Customer");
		System.out.println("2. Employee");
		System.out.println("3. Administrator");
		String accountType = sc.nextLine();
		
		boolean validEntry = false;
		
		while(validEntry == false) {
			if(accountType.equals("1") || accountType.equals("2") || accountType.equals("3")) {
				validEntry = true;
			} else {
				validEntry = false;
				System.out.println("Invalid entry! Please enter in either 1, 2, or 3!");
				accountType = sc.nextLine();
			}
		}
		
		if(accountType.equals("1")) {
			System.out.println("\nEnter in your Customer ID:");
		} else if(accountType.equals("2")) {
			System.out.println("\nEnter in your Employye ID:");
		} else if(accountType.equals("3")) {
			System.out.println("\nEnter in your Administrator ID:");
		}
		
		String id = sc.nextLine();
		
		validEntry = false;
		
		while(validEntry == false) {
			boolean isNumeric = cd.isNumeric(id);
			if(isNumeric) {
				validEntry = true;
			} else {
				System.out.println("Invalid entry! Please enter a valid Id!");
				id = sc.nextLine();
			}
		}
		
		
		CustomerRegistrationDao customerRegistrationDao = new CustomerRegistrationDao();
		EmployeeRegistrationDao employeeRegistrationDao = new EmployeeRegistrationDao();
		AdminRegistrationDao adminRegistrationDao = new AdminRegistrationDao();
		
		boolean hasCustomerAccount = false;;
		boolean hasEmployeeAccount = false;
		boolean hasAdministratorAccount = false;
		
		boolean customerOnboarded = false;
		boolean employeeOnboarded = false;
		boolean administratorOnboarded = false;
		
		validEntry = false;
		boolean validEntry2 = false;
		
		
		if(accountType.equals("1")) {
			hasCustomerAccount = customerRegistrationDao.getUserAlreadyHasAccount(Integer.parseInt(id));
			if(hasCustomerAccount == false) {
				validEntry = true;
			}
			
			customerOnboarded = customerRegistrationDao.getCustomerById(Integer.parseInt(id));
			if(customerOnboarded == true) {
				validEntry2 = true;
			}
		} else if(accountType.equals("2")) {
			hasEmployeeAccount = employeeRegistrationDao.getUserAlreadyHasAccount(Integer.parseInt(id));
			if(hasEmployeeAccount == false) {
				validEntry = true;
			}
			
			employeeOnboarded = employeeRegistrationDao.getEmployeeById(Integer.parseInt(id));
			if(employeeOnboarded == true) {
				validEntry2 = true;
			}
		} else if(accountType.equals("3")) {
			hasAdministratorAccount = adminRegistrationDao.getUserAlreadyHasAccount(Integer.parseInt(id));
			if(hasAdministratorAccount == false) {
				validEntry = true;
			}
			
			administratorOnboarded = adminRegistrationDao.getAdminById(Integer.parseInt(id));
			if(administratorOnboarded == true) {
				validEntry2 = true;
			}
		}
		
		
		boolean commit = false;
		
		while(commit == false) {
		
		if(validEntry == false) {
			System.out.println("\nYou already have an account!");
			commit = true;
		} else if (validEntry2 == false){
			System.out.println("\nYou are not onboarded in our system, please speak with a bank employee for further assistance!");
			commit = true;
		} else {
			
			
			
			
			System.out.println("\nEnter in your desired username:");
		
		
		username = sc.nextLine();
		
		validEntry = false;
		
		while(validEntry == false) {
			
			if(username.equals("")) {
				System.out.println("Enter in a valid username:");
				username = sc.nextLine();
			} else {
				validEntry = true;
			}
		}
		
		boolean customerUsernameExists = false;
		boolean employeeUsernameExists = false;
		boolean adminUsernameExists = false;
		
		validEntry = false;
		
		if(accountType.equals("1")) {
			customerUsernameExists = customerRegistrationDao.getUserExists(username);
			if(customerUsernameExists == false) {
				validEntry = true;
			}
			
		} else if(accountType.equals("2")) {
			employeeUsernameExists = employeeRegistrationDao.getUserExists(username);
			if(employeeUsernameExists == false) {
				validEntry = true;
			}

		} else if(accountType.equals("3")) {
			adminUsernameExists = adminRegistrationDao.getUserExists(username);
			if(adminUsernameExists == false) {
				validEntry = true;
			}

		}
		
		while(validEntry == false) {
			System.out.println("\nUsername already exists, try another one:");
			
			
			username = sc.nextLine();
			
			boolean validEntry3 = false;
			
			while(validEntry3 == false) {
				
				if(username.equals("")) {
					System.out.println("Enter in a valid username:");
					username = sc.nextLine();
				} else {
					validEntry3 = true;
				}
			}
			
			if(accountType.equals("1")) {
				customerUsernameExists = customerRegistrationDao.getUserExists(username);
				if(customerUsernameExists == false) {
					validEntry = true;
				}
				
			} else if(accountType.equals("2")) {
				employeeUsernameExists = employeeRegistrationDao.getUserExists(username);
				if(employeeUsernameExists == false) {
					validEntry = true;
				}

			} else if(accountType.equals("3")) {
				adminUsernameExists = adminRegistrationDao.getUserExists(username);
				if(adminUsernameExists == false) {
					validEntry = true;
				}

			}
			
		}
		
		System.out.println("\nEnter in your desired password:");
		
		password = sc.nextLine();
		
				validEntry = false;
		
		while(validEntry == false) {
			if(password.equals("")) {
				System.out.println("Enter in a valid password:");
				password = sc.nextLine();
			} else if(password.length() < 8) {
				System.out.println("Password did not meet length requirement (8 characters minimum). Try again:");
				password = sc.nextLine();
			} else {
				validEntry = true;
			}
		}
		
		System.out.println("\nPlease review and confirm:");
		System.out.println("Username: " + username);
		System.out.println("Password: " + password);
		System.out.println("\nConfirm? (Y/N)");
		
		String confirm = sc.nextLine().toLowerCase();
		
		validEntry = false;
		
		while(validEntry == false) {
			if(confirm.equals("y") || confirm.equals("n")) {
				validEntry = true;
				if(confirm.equals("y")) {
					commit = true;
				} else {
					commit = false;
				}
			} else {
				System.out.println("Invalid entry! Please enter in either Y or N");
				confirm = sc.nextLine();
			}
		}
		

		
		
		if(accountType.equals("1")) {
			customerRegistrationDao.addUser(id, username, password);
		} else if(accountType.equals("2")) {
			employeeRegistrationDao.addUser(id, username, password);
		} else if(accountType.equals("3")) {
			adminRegistrationDao.addUser(id, username, password);
		}
		
		System.out.println("Account created successfully!");
		
		}
		}
	}
}
