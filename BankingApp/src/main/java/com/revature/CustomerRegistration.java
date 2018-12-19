package com.revature;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;

import com.revature.daoimp.AdminRegistrationDaoImp;
import com.revature.daoimp.CustomerRegistrationDaoImp;

public class CustomerRegistration{

	
	public boolean registerUser(String customerId, String username, String password) {
		
		CustomerRegistrationDaoImp customerRegistrationDao = new CustomerRegistrationDaoImp();
		boolean userExists = customerRegistrationDao.getUserExists(username);
		boolean customerOnboarded = customerRegistrationDao.getCustomerById(Integer.parseInt(customerId));
		boolean success = false;
		if(userExists == true) {
			System.out.println("Account already exists with that username!");
			success = false;
		} else if(customerOnboarded == false) {
			System.out.println("Customer does not have an account with the bank, please speak to a bank employee so you can be onboarded.");
			success = false;
		} else {
			customerRegistrationDao.addUser(customerId, username, password);
		}
		return success;
	}
	

}
