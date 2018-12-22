package com.revature.consolelogic;

import java.sql.Connection;
import java.util.Scanner;

import com.revature.Login;

public class LoginDialogue {
	Scanner sc = new Scanner(System.in);
	RegistrationDialogue rd = new RegistrationDialogue();
	String username;
	String password;
	Login login = new Login();
	boolean loop = true;
	
	public void login(Connection connection) {
		while(loop) {
		System.out.println("Welcome to the Bank of Revature!");
		System.out.println("\nPlease choose what you would like to do today:");
		System.out.println("\n1. Login");
		System.out.println("2. Register");
		System.out.println("3. Close Application");
		
		String choice = sc.nextLine();
		
		boolean validEntry = false;
		
		while(validEntry == false) {
			if(choice.equals("1") || choice.equals("2") || choice.equals("3")) {
				validEntry = true;
			} else {
				System.out.println("Invalid entry! Please enter in either 1, 2, or 3.");
				choice = sc.nextLine();
				validEntry = false;
			}
		}
		
		if(choice.equals("3")) {
			System.exit(0);
		} else if(choice.equals("2")) {
			rd.register(connection);
		} else {
			System.out.println("\nChoose a login type:");
			System.out.println("1. Customer");
			System.out.println("2. Employee");		
			System.out.println("3. Administrator");
			System.out.println("4. Back");
			String loginType = sc.nextLine();
			
			validEntry = false;
			
			while(validEntry == false) {
				if(loginType.equals("1") || loginType.equals("2") || loginType.equals("3") || loginType.equals("4")) {
					validEntry = true;
				} else {
					validEntry = false;
					System.out.println("Invalid entry! Please enter either 1, 2, 3, or 4.");
					loginType = sc.nextLine();
				}
			}
			
			if(loginType.equals("4")) {
				login(connection);
			}
			
			boolean loginSuccessful = false;
			
			while(loginSuccessful == false) {
			
			System.out.println("\nPlease enter in the following information.");
			System.out.println("\nUsername:");
			username = sc.nextLine();
			
			validEntry = false;
			
			while(validEntry == false) {
				if(username.equals("")) {
					validEntry = false;
					System.out.println("Invalid entry! Please enter in a valid username!");
					username = sc.nextLine();
				} else {
					validEntry = true;
				}
			}
			
			System.out.println("\nPassword:");
			password = sc.nextLine();
			
			validEntry = false;
			
			while(validEntry == false) {
				if(password.equals("")) {
					validEntry = false;
					System.out.println("Invalid entry! Please enter in a valid password!");
					password = sc.nextLine();
				} else {
					validEntry = true;
				}
			}
			
			int loginValidated = login.validateCredentials(loginType, username, password, connection);
			if(loginValidated > 0) {
				System.out.println("Login successful! Please wait...");
				loginSuccessful = true;
				if(loginType.equals("1")) {
					String accessType = "Customer";
					CustomerPortalDialogue cpd = new CustomerPortalDialogue();
					cpd.customerOptions(loginValidated, connection, accessType);
				} else if(loginType.equals("2")) {
					EmployeePortalDialogue epd = new EmployeePortalDialogue();
					epd.employeeOptions(connection);
				} else if(loginType.equals("3")) {
					AdministratorPortalDialogue apd = new AdministratorPortalDialogue();
					apd.administratorOptions(connection);
				}
			} else if(loginValidated == -1){
				loginSuccessful = true;
			} else {
				System.out.println("Login failed. Try again!");
				loginSuccessful = false;
			}
			
			}
			
		}
	}
	}
}
