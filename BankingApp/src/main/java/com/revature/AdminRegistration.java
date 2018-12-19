package com.revature;

import com.revature.daoimp.AdminRegistrationDaoImp;

public class AdminRegistration {

	
	public boolean registerUser(String administratorId, String username, String password) {
		
		AdminRegistrationDaoImp adminRegistrationDao = new AdminRegistrationDaoImp();
		boolean userExists = adminRegistrationDao.getUserExists(username);
		boolean administratorOnboarded = adminRegistrationDao.getAdminById(Integer.parseInt(administratorId));
		boolean success = false;
		if(userExists == true) {
			System.out.println("Account already exists with that username!");
			success = false;
		} else if(administratorOnboarded == false) {
			System.out.println("Administrator does not have an account with the bank, please speak to a bank employee so you can be onboarded.");
			success = false;
		} else {
			adminRegistrationDao.addUser(administratorId, username, password);
		}
		return success;
	}

}
