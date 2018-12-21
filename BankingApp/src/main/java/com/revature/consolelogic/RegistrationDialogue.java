package com.revature.consolelogic;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.revature.AdminRegistration;
import com.revature.Administrator;
import com.revature.Customer;
import com.revature.CustomerRegistration;
import com.revature.Employee;
import com.revature.EmployeeRegistration;
import com.revature.dao.AdminRegistrationDao;
import com.revature.dao.AdministratorDao;
import com.revature.dao.CustomerDao;
import com.revature.dao.CustomerRegistrationDao;
import com.revature.dao.EmployeeDao;
import com.revature.dao.EmployeeRegistrationDao;

public class RegistrationDialogue {
	CustomerDialogue cd = new CustomerDialogue();
	Scanner sc = new Scanner(System.in);
	AdminRegistration ar = new AdminRegistration();
	CustomerRegistration cr = new CustomerRegistration();
	EmployeeRegistration er = new EmployeeRegistration();
	String username;
	String password;
	
	public void register(Connection connection) {
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
		
		
		CustomerRegistrationDao customerRegistrationDao = new CustomerRegistrationDao(connection);
		EmployeeRegistrationDao employeeRegistrationDao = new EmployeeRegistrationDao(connection);
		AdminRegistrationDao adminRegistrationDao = new AdminRegistrationDao(connection);
		
		boolean hasCustomerAccount = false;;
		boolean hasEmployeeAccount = false;
		boolean hasAdministratorAccount = false;
		
		boolean customerOnboarded = false;
		boolean employeeOnboarded = false;
		boolean administratorOnboarded = false;
		
		validEntry = false;
		boolean validEntry2 = false;
		
		
		if(accountType.equals("1")) {
			
			List<CustomerRegistration> customerUsers = customerRegistrationDao.getAllCustomerUsers();
			
			for(CustomerRegistration customerUser : customerUsers) {
				if(customerUser.getCustomerId() == Integer.parseInt(id)) {
					hasCustomerAccount = true;
				}
			}
			
			CustomerDao customerDao = new CustomerDao(connection);
			Customer customer = customerDao.getCustomerById(Integer.parseInt(id));
			
			if(customer != null) {
				customerOnboarded = true;
			}
			
			if(hasCustomerAccount == false) {
				validEntry = true;
			}
			
			if(customerOnboarded == true) {
				validEntry2 = true;
			}
		} else if(accountType.equals("2")) {

			List<EmployeeRegistration> employeeUsers = employeeRegistrationDao.getAllEmployeeUsers();
			
			for(EmployeeRegistration employeeUser : employeeUsers) {
				if(employeeUser.getEmployeeId() == Integer.parseInt(id)) {
					hasEmployeeAccount = true;
				}
			}
			
			EmployeeDao employeeDao = new EmployeeDao(connection);
			Employee employee = employeeDao.getEmployeeById(Integer.parseInt(id));
			
			if(employee != null) {
				employeeOnboarded = true;
			}
			
			
			if(hasEmployeeAccount == false) {
				validEntry = true;
			}
			
			if(employeeOnboarded == true) {
				validEntry2 = true;
			}
		} else if(accountType.equals("3")) {

			List<AdminRegistration> administratorUsers = adminRegistrationDao.getAllAdministratorUsers();
			
			for(AdminRegistration administratorUser : administratorUsers) {
				if(administratorUser.getAdministratorId() == Integer.parseInt(id)) {
					hasAdministratorAccount = true;
				}
			}
			
			
			AdministratorDao administratorDao = new AdministratorDao(connection);
			Administrator administrator = administratorDao.getAdministratorById(Integer.parseInt(id));
			
			if(administrator != null) {
				administratorOnboarded = true;
			}
			
			
			if(hasAdministratorAccount == false) {
				validEntry = true;
			}
			
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
			
			CustomerRegistration customerUser = customerRegistrationDao.getCustomerUserByUsername(username);
			
			if(customerUser != null) {
				customerUsernameExists = true;
			}
			
			if(customerUsernameExists == false) {
				validEntry = true;
			}
			
		} else if(accountType.equals("2")) {

			EmployeeRegistration employeeUser = employeeRegistrationDao.getEmployeeUserByUsername(username);
			
			if(employeeUser != null) {
				employeeUsernameExists = true;
			}
			
			if(employeeUsernameExists == false) {
				validEntry = true;
			}

		} else if(accountType.equals("3")) {

			AdminRegistration administratorUser = adminRegistrationDao.getAdministratorUserByUsername(username);
			
			if(administratorUser != null) {
				adminUsernameExists = true;
			}
			
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
				CustomerRegistration customerUser = customerRegistrationDao.getCustomerUserByUsername(username);
				
				if(customerUser != null) {
					customerUsernameExists = true;
				}
				
				if(customerUsernameExists == false) {
					validEntry = true;
				}
				
			} else if(accountType.equals("2")) {
				EmployeeRegistration employeeUser = employeeRegistrationDao.getEmployeeUserByUsername(username);
				
				if(employeeUser != null) {
					employeeUsernameExists = true;
				}
				
				if(employeeUsernameExists == false) {
					validEntry = true;
				}

			} else if(accountType.equals("3")) {
				AdminRegistration administratorUser = adminRegistrationDao.getAdministratorUserByUsername(username);
				
				if(administratorUser != null) {
					adminUsernameExists = true;
				}
				
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
			CustomerRegistration customerUser = new CustomerRegistration();
			customerUser.setCustomerId(Integer.parseInt(id));
			customerUser.setUsername(username);
			customerUser.setPassword(password);
			customerUser.setStatus(1);
			
			customerRegistrationDao.addCustomerUser(customerUser);
		} else if(accountType.equals("2")) {
			EmployeeRegistration employeeUser = new EmployeeRegistration();
			employeeUser.setEmployeeId(Integer.parseInt(id));
			employeeUser.setUsername(username);
			employeeUser.setPassword(password);
			employeeUser.setStatus(1);
			
			employeeRegistrationDao.addEmployeeUser(employeeUser);
		} else if(accountType.equals("3")) {
			AdminRegistration administratorUser = new AdminRegistration();
			administratorUser.setAdministratorId(Integer.parseInt(id));
			administratorUser.setUsername(username);
			administratorUser.setPassword(password);
			administratorUser.setStatus(1);
			
			adminRegistrationDao.addAdministratorUser(administratorUser);
		}
		
		System.out.println("Account created successfully!");
		
		}
		}
	}
}
