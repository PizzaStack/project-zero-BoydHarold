package com.revature;

import java.util.List;

import com.revature.dao.AdminRegistrationDao;
import com.revature.dao.AdministratorDao;

public class AdminRegistration {
	private int userId;
	private int administratorId;
	private String username;
	private String password;
	private int status;

	public AdminRegistration(int administratorId, String username, String password, int status) {
		this.administratorId = administratorId;
		this.username = username;
		this.password = password;
		this.status = status;
	}

	public AdminRegistration() {

	}

	public boolean registerUser(String administratorId, String username, String password) {

		AdminRegistrationDao administratorRegistrationDao = new AdminRegistrationDao();
		AdminRegistration administratorRegistration = administratorRegistrationDao
				.getAdministratorUserByUsername(username);
		AdministratorDao administratorDao = new AdministratorDao();
		Administrator administrator = administratorDao.getAdministratorById(Integer.parseInt(administratorId));
		List<AdminRegistration> administratorUsers = administratorRegistrationDao.getAllAdministratorUsers();

		int id = 0;

		for (AdminRegistration administratorUser : administratorUsers) {
			if (administratorUser.getAdministratorId() == Integer.parseInt(administratorId)) {
				id = administratorUser.getAdministratorId();
			}
		}

		boolean userExists = false;
		if (administratorRegistration == null) {
			userExists = false;
		} else {
			userExists = true;
		}

		boolean administratorOnboarded = false;
		if (administrator == null) {
			administratorOnboarded = false;
		} else {
			administratorOnboarded = true;
		}

		boolean success = false;
		if (id != 0) {
			System.out.println("You already have an account! You are only allowed one account.");
			success = false;
		} else if (administratorOnboarded == false) {
			System.out.println(
					"Administrator does not have an account with the bank, please speak to a bank employee so you can be onboarded.");
			success = false;
		} else if (userExists == true) {
			System.out.println("Account already exists with that username!");
			success = false;
		} else {
			administratorRegistration = new AdminRegistration(Integer.parseInt(administratorId), username, password, 1);
			administratorRegistrationDao.addAdministratorUser(administratorRegistration);
		}
		return success;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getAdministratorId() {
		return administratorId;
	}

	public void setAdministratorId(int administratorId) {
		this.administratorId = administratorId;
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
