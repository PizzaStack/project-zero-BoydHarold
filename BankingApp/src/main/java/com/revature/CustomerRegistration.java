package com.revature;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;

public class CustomerRegistration{
	private String authenticationPath = "C:\\Users\\boydt\\Desktop\\Project0\\project-zero-BoydHarold\\BankingApp\\PseudoDB\\PseudoTables\\Authentication\\Customer\\";
	File customerIdFile = new File(authenticationPath + "CustomerId.txt");
	File usernamesFile = new File(authenticationPath + "Usernames.txt");
	File passwordFile = new File(authenticationPath + "Passwords.txt");
	File accountTypeFile = new File(authenticationPath + "AccountTypeFile.txt");
	File customerIdFile2 = new File("C:\\Users\\boydt\\Desktop\\Project0\\project-zero-BoydHarold\\BankingApp\\PseudoDB\\PseudoTables\\Customer\\CustomerId.txt");

	public void makeBaselineUser() {
		boolean exists = usernamesFile.exists();
		if(!exists) {
			try (
					FileOutputStream fosCustomerId = new FileOutputStream(customerIdFile, true);
					PrintStream psCustomerId = new PrintStream(fosCustomerId);

					FileOutputStream fosUsernames = new FileOutputStream(usernamesFile, true);
					PrintStream psUsernames = new PrintStream(fosUsernames);

					FileOutputStream fosPasswords = new FileOutputStream(passwordFile, true);
					PrintStream psPasswords= new PrintStream(fosPasswords);

					FileOutputStream fosAccountType= new FileOutputStream(accountTypeFile, true);
					PrintStream psAccountType = new PrintStream(fosAccountType);
			
			){
				
				psCustomerId.println("0");
				psUsernames.println("0");
				psPasswords.println("0");
				psAccountType.println("0");
				
			} catch (FileNotFoundException e) {

			} catch (IOException e) {
				
			}
		}
	}
	
	public boolean registerUser(String customerId, String username, String password) {
		int userExists = checkUserExists(username);
		int customerOnboarded = checkCustomerOnboarded(customerId);
		boolean success = false;
		if(userExists == 1) {
			System.out.println("Account already exists with that username!");
			success = false;
		} else if(customerOnboarded == 0) {
			System.out.println("Customer does not have an account with the bank, please speak to a bank employee so you can be onboarded.");
			success = false;
		} else {
			try (
					FileOutputStream fosCustomerId = new FileOutputStream(customerIdFile, true);
					PrintStream psCustomerId = new PrintStream(fosCustomerId);

					FileOutputStream fosUsernames = new FileOutputStream(usernamesFile, true);
					PrintStream psUsernames = new PrintStream(fosUsernames);

					FileOutputStream fosPasswords = new FileOutputStream(passwordFile, true);
					PrintStream psPasswords= new PrintStream(fosPasswords);

					FileOutputStream fosAccountType= new FileOutputStream(accountTypeFile, true);
					PrintStream psAccountType = new PrintStream(fosAccountType);
			
			){
				
				psCustomerId.println(customerId);
				psUsernames.println(username);
				psPasswords.println(password);
				psAccountType.println("c");
				success = true;
			} catch (FileNotFoundException e) {

			} catch (IOException e) {
				
			}
		}
		return success;
	}
	
	public int checkUserExists(String username) {
		int userExists = 0;
		
		try (			
			FileInputStream fis = new FileInputStream(usernamesFile);
			BufferedReader br = new BufferedReader(new InputStreamReader(fis));
		) {
			String currentUser;
			while((currentUser = br.readLine()) != null){
				if(currentUser.equals(username)) {
					userExists = 1;
				}
			}
	
			
		} catch (FileNotFoundException e) {
			
			return userExists;
		} catch (IOException e) {

		}
		return userExists;
	}
	
	public int checkCustomerOnboarded(String customerId) {
		int customerExists = 0;
		
		try (			
			FileInputStream fis = new FileInputStream(customerIdFile2);
			BufferedReader br = new BufferedReader(new InputStreamReader(fis));
		) {
			String currentId;
			while((currentId = br.readLine()) != null){
				if(currentId.equals(customerId)) {
					customerExists = 1;
				}
			}
	
			
		} catch (FileNotFoundException e) {
			
			return customerExists;
		} catch (IOException e) {

		}
		return customerExists;
	}
	
	public boolean checkUserAlreadyHasAccount(int customerId) {
		boolean userHasAccountAlready = false;
		try (			
				FileInputStream fis = new FileInputStream(customerIdFile);
				BufferedReader br = new BufferedReader(new InputStreamReader(fis));
			) {
				String currentId;
				while((currentId = br.readLine()) != null){
					if(currentId.equals(customerId)) {
						userHasAccountAlready = true;
					}
				}
		
				
			} catch (FileNotFoundException e) {
				
				return userHasAccountAlready;
			} catch (IOException e) {

			}
			return userHasAccountAlready;
	}

}
