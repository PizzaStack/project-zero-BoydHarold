package com.revature;

import com.revature.dao.EmployeeRegistrationDao;

public class EmployeeRegistration{

	
	public boolean registerUser(String employeeId, String username, String password) {
		EmployeeRegistrationDao employeeRegistrationDao = new EmployeeRegistrationDao();
		boolean userExists = employeeRegistrationDao.getUserExists(username);
		boolean employeeOnboarded = employeeRegistrationDao.getEmployeeById(Integer.parseInt(employeeId));
		boolean success = false;
		if(userExists == true) {
			System.out.println("Account already exists with that username!");
			success = false;
		} else if(employeeOnboarded == false) {
			System.out.println("Employee does not have an account with the bank, please speak to a bank employee so you can be onboarded.");
			success = false;
		} else {
			employeeRegistrationDao.addUser(employeeId, username, password);
		}
		return success;
	}
	


}
