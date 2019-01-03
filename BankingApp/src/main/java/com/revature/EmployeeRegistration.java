package com.revature;

import java.util.List;

import com.revature.dao.EmployeeDao;
import com.revature.dao.EmployeeRegistrationDao;

public class EmployeeRegistration {
	private int userId;
	private int employeeId;
	private String username;
	private String password;
	private int status;

	public EmployeeRegistration(int employeeId, String username, String password, int status) {
		this.employeeId = employeeId;
		this.username = username;
		this.password = password;
		this.status = status;
	}

	public EmployeeRegistration() {

	}

	public boolean registerUser(String employeeId, String username, String password) {
		EmployeeRegistrationDao employeeRegistrationDao = new EmployeeRegistrationDao();
		EmployeeRegistration employeeRegistration = employeeRegistrationDao.getEmployeeUserByUsername(username);
		EmployeeDao employeeDao = new EmployeeDao();
		Employee employee = employeeDao.getEmployeeById(Integer.parseInt(employeeId));
		List<EmployeeRegistration> employeeUsers = employeeRegistrationDao.getAllEmployeeUsers();

		boolean success = false;

		int id = 0;

		for (EmployeeRegistration employeeUser : employeeUsers) {
			if (employeeUser.getEmployeeId() == Integer.parseInt(employeeId)) {
				id = employeeUser.getEmployeeId();
			}
		}

		boolean userExists = false;
		if (employeeRegistration == null) {
			userExists = false;
		} else {
			userExists = true;
		}

		boolean employeeOnboarded = false;
		if (employee == null) {
			employeeOnboarded = false;
		} else {
			employeeOnboarded = true;
		}

		if (id != 0) {
			System.out.println("You already have an account! You are only allowed one account.");
			success = false;
		} else if (employeeOnboarded == false) {
			System.out.println(
					"Employee does not have an account with the bank, please speak to a bank employee so you can be onboarded.");
			success = false;
		} else if (userExists == true) {
			System.out.println("Account already exists with that username!");
			success = false;
		} else {
			employeeRegistrationDao.addUser(employeeId, username, password);
		}
		return success;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

}
