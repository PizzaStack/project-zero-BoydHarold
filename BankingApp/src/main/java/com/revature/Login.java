package com.revature;

import java.sql.Connection;
import java.util.List;

import com.revature.dao.AdminRegistrationDao;
import com.revature.dao.CustomerRegistrationDao;
import com.revature.dao.EmployeeRegistrationDao;

public class Login {

	
	public boolean validateCredentials(String accountType, String username, String password, Connection connection) {
		boolean validatedUsername = false;
		boolean validatedPassword = false;
		boolean validatedCredentials = false;
		
		
		if(accountType.equals("1")) {
		CustomerRegistrationDao customerRegistrationDao = new CustomerRegistrationDao(connection);
		List<CustomerRegistration> customerRegistrations = customerRegistrationDao.getAllCustomerUsers();
			
		for(CustomerRegistration customerRegistration : customerRegistrations) {
			if(customerRegistration.getUsername().equals(username)) {
				validatedUsername = true;
			}
		}
		
		CustomerRegistration customerRegistration = customerRegistrationDao.getCustomerUserByUsername(username);
		
		if(customerRegistration == null) {
			System.out.println("Account does not exist!\n");
		} else if(customerRegistration.getPassword().equals(password)) {
			validatedPassword = true;
		} else {
			System.out.println("Invalid password!\n");
		}
		
		if(validatedUsername == true && validatedPassword == true) {
			validatedCredentials = true;
		}
			
		} else if (accountType.equals("2")) {
			EmployeeRegistrationDao employeeRegistrationDao = new EmployeeRegistrationDao(connection);
			List<EmployeeRegistration> employeeRegistrations = employeeRegistrationDao.getAllEmployeeUsers();
				
			for(EmployeeRegistration employeeRegistration : employeeRegistrations) {
				if(employeeRegistration.getUsername().equals(username)) {
					validatedUsername = true;
				}
			}
			
			EmployeeRegistration employeeRegistration = employeeRegistrationDao.getEmployeeUserByUsername(username);
			
			if(employeeRegistration == null) {
				System.out.println("Account does not exist!\n");
			} else if(employeeRegistration.getPassword().equals(password)) {
				validatedPassword = true;
			} else {
				System.out.println("Invalid password!\n");
			}
			
			if(validatedUsername == true && validatedPassword == true) {
				validatedCredentials = true;
			}
		} else if (accountType.equals("3")) {
			AdminRegistrationDao administratorRegistrationDao = new AdminRegistrationDao(connection);
			List<AdminRegistration> administratorRegistrations = administratorRegistrationDao.getAllAdministratorUsers();
				
			for(AdminRegistration administratorRegistration : administratorRegistrations) {
				if(administratorRegistration.getUsername().equals(username)) {
					validatedUsername = true;
				}
			}
			
			AdminRegistration administratorRegistration = administratorRegistrationDao.getAdministratorUserByUsername(username);
			
			if(administratorRegistration == null) {
				System.out.println("Account does not exist!\n");
			} else if(administratorRegistration.getPassword().equals(password)) {
				validatedPassword = true;
			} else {
				System.out.println("Invalid password!\n");
			}
			
			if(validatedUsername == true && validatedPassword == true) {
				validatedCredentials = true;
			}
		}
		
	
		
		return validatedCredentials;
	}
	
}
