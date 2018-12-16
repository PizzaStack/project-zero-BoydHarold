package com.revature.consolelogic;

import java.util.Scanner;

import com.revature.Login;

public class LoginDialogue {
	Scanner sc = new Scanner(System.in);
	RegistrationDialogue rd = new RegistrationDialogue();
	String username;
	String password;
	Login login = new Login();
	
	public void login() {
		System.out.println("Welcome to the Bank of Revature!");
		System.out.println("\nPlease choose what you would like to do today:");
		System.out.println("\n1. Login");
		System.out.println("2. Register");
		
		String choice = sc.nextLine();
		
		boolean validEntry = false;
		
		while(validEntry == false) {
			if(choice.equals("1") || choice.equals("2")) {
				validEntry = true;
			} else {
				System.out.println("Invalid entry! Please enter in either 1 or 2.");
				choice = sc.nextLine();
				validEntry = false;
			}
		}
		
		if(choice.equals("2")) {
			rd.register();
		} else {
			System.out.println("\nChoose a login type:");
			System.out.println("1. Customer");
			System.out.println("2. Employee");		
			System.out.println("3. Administrator");
			String loginType = sc.nextLine();
			
			validEntry = false;
			
			while(validEntry == false) {
				if(loginType.equals("1") || loginType.equals("2") || loginType.equals("3")) {
					validEntry = true;
				} else {
					validEntry = false;
					System.out.println("Invalid entry! Please enter either 1, 2, or 3.");
					loginType = sc.nextLine();
				}
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
			
			boolean loginValidated = login.validateCredentials(loginType, username, password);
			if(loginValidated) {
				System.out.println("Login successful!");
				loginSuccessful = true;
			} else {
				System.out.println("Login failed. Invalid credentials!");
				loginSuccessful = false;
			}
			
			}
			
		}
	}
}
