package com.revature;

import java.util.List;

import com.revature.dao.AdminRegistrationDao;
import com.revature.dao.CustomerRegistrationDao;
import com.revature.dao.EmployeeRegistrationDao;

public class Login {
	private int id;
	boolean validatedCredentials = false;

	public int validateCredentials(String accountType, String username, String password) {
		boolean validatedUsername = false;
		boolean validatedPassword = false;

		id = 0;

		if (accountType.equals("1")) {
			CustomerRegistrationDao customerRegistrationDao = new CustomerRegistrationDao();
			List<CustomerRegistration> customerRegistrations = customerRegistrationDao.getAllCustomerUsers();

			for (CustomerRegistration customerRegistration : customerRegistrations) {
				if (customerRegistration.getUsername().equals(username)) {
					validatedUsername = true;
				}
			}

			CustomerRegistration customerRegistration = customerRegistrationDao.getCustomerUserByUsername(username);

			if (customerRegistration == null) {
				System.out.println("Account does not exist!\n");
			} else if (customerRegistration.getStatus() == 0) {
				System.out.println(
						"Account is disabled and must be enabled by a bank admin. Please reach out to a bank employee for further assistance.\n");
				id = -1;
			} else if (customerRegistration.getPassword().equals(password)) {
				validatedPassword = true;
			} else {
				System.out.println("Invalid password!\n");
			}

			if (validatedUsername == true && validatedPassword == true) {
				validatedCredentials = true;
				id = customerRegistration.getCustomerId();
			}

		} else if (accountType.equals("2")) {
			EmployeeRegistrationDao employeeRegistrationDao = new EmployeeRegistrationDao();
			List<EmployeeRegistration> employeeRegistrations = employeeRegistrationDao.getAllEmployeeUsers();

			for (EmployeeRegistration employeeRegistration : employeeRegistrations) {
				if (employeeRegistration.getUsername().equals(username)) {
					validatedUsername = true;
				}
			}

			EmployeeRegistration employeeRegistration = employeeRegistrationDao.getEmployeeUserByUsername(username);

			if (employeeRegistration == null) {
				System.out.println("Account does not exist!\n");
			} else if (employeeRegistration.getStatus() == 0) {
				System.out.println(
						"Account is disabled and must be enabled by a bank admin. Please reach out to a bank employee for further assistance.\n");
				id = -1;
			} else if (employeeRegistration.getPassword().equals(password)) {
				validatedPassword = true;
			} else {
				System.out.println("Invalid password!\n");
			}

			if (validatedUsername == true && validatedPassword == true) {
				validatedCredentials = true;
				id = employeeRegistration.getEmployeeId();
			}
		} else if (accountType.equals("3")) {
			AdminRegistrationDao administratorRegistrationDao = new AdminRegistrationDao();
			List<AdminRegistration> administratorRegistrations = administratorRegistrationDao
					.getAllAdministratorUsers();

			for (AdminRegistration administratorRegistration : administratorRegistrations) {
				if (administratorRegistration.getUsername().equals(username)) {
					validatedUsername = true;
				}
			}

			AdminRegistration administratorRegistration = administratorRegistrationDao
					.getAdministratorUserByUsername(username);

			if (administratorRegistration == null) {
				System.out.println("Account does not exist!\n");
			} else if (administratorRegistration.getStatus() == 0) {
				System.out.println(
						"Account is disabled and must be enabled by a bank admin. Please reach out to a bank employee for further assistance.\n");
				id = -1;
			} else if (administratorRegistration.getPassword().equals(password)) {
				validatedPassword = true;
			} else {
				System.out.println("Invalid password!\n");
			}

			if (validatedUsername == true && validatedPassword == true) {
				validatedCredentials = true;
				id = administratorRegistration.getAdministratorId();
			}
		}

		return id;
	}

}
