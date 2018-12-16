package com.revature;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;

public class EmployeeRegistration{
	private String authenticationPath = "C:\\Users\\boydt\\Desktop\\Project0\\project-zero-BoydHarold\\BankingApp\\PseudoDB\\PseudoTables\\Authentication\\Employee\\";
	File employeeIdFile = new File(authenticationPath + "EmployeeId.txt");
	File usernamesFile = new File(authenticationPath + "Usernames.txt");
	File passwordFile = new File(authenticationPath + "Passwords.txt");
	File accountTypeFile = new File(authenticationPath + "AccountTypeFile.txt");
	File employeeIdFile2 = new File("C:\\Users\\boydt\\Desktop\\Project0\\project-zero-BoydHarold\\BankingApp\\PseudoDB\\PseudoTables\\Employee\\EmployeeId.txt");

	public void makeBaselineUser() {
		boolean exists = usernamesFile.exists();
		if(!exists) {
			try (
					FileOutputStream fosEmployeeId = new FileOutputStream(employeeIdFile, true);
					PrintStream psEmployeeId = new PrintStream(fosEmployeeId);

					FileOutputStream fosUsernames = new FileOutputStream(usernamesFile, true);
					PrintStream psUsernames = new PrintStream(fosUsernames);

					FileOutputStream fosPasswords = new FileOutputStream(passwordFile, true);
					PrintStream psPasswords= new PrintStream(fosPasswords);

					FileOutputStream fosAccountType= new FileOutputStream(accountTypeFile, true);
					PrintStream psAccountType = new PrintStream(fosAccountType);
			
			){
				
				psEmployeeId.println("0");
				psUsernames.println("employee");
				psPasswords.println("password");
				psAccountType.println("e");
				
			} catch (FileNotFoundException e) {

			} catch (IOException e) {
				
			}
		}
	}
	
	public boolean registerUser(String employeeId, String username, String password) {
		int userExists = checkUserExists(username);
		int customerOnboarded = checkEmployeeOnboarded(employeeId);
		boolean success = false;
		if(userExists == 1) {
			System.out.println("Account already exists with that username!");
			success = false;
		} else if(customerOnboarded == 0) {
			System.out.println("Employee does not have an account with the bank, please speak to a bank employee so you can be onboarded.");
			success = false;
		} else {
			try (
					FileOutputStream fosEmployeeId = new FileOutputStream(employeeIdFile, true);
					PrintStream psEmployeeId = new PrintStream(fosEmployeeId);

					FileOutputStream fosUsernames = new FileOutputStream(usernamesFile, true);
					PrintStream psUsernames = new PrintStream(fosUsernames);

					FileOutputStream fosPasswords = new FileOutputStream(passwordFile, true);
					PrintStream psPasswords= new PrintStream(fosPasswords);

					FileOutputStream fosAccountType= new FileOutputStream(accountTypeFile, true);
					PrintStream psAccountType = new PrintStream(fosAccountType);
			
			){
				
				psEmployeeId.println(employeeId);
				psUsernames.println(username);
				psPasswords.println(password);
				psAccountType.println("e");
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
	
	public int checkEmployeeOnboarded(String employeeId) {
		int employeeExists = 0;
		
		try (			
			FileInputStream fis = new FileInputStream(employeeIdFile2);
			BufferedReader br = new BufferedReader(new InputStreamReader(fis));
		) {
			String currentId;
			while((currentId = br.readLine()) != null){
				if(currentId.equals(employeeId)) {
					employeeExists = 1;
				}
			}
	
			
		} catch (FileNotFoundException e) {
			
			return employeeExists;
		} catch (IOException e) {

		}
		return employeeExists;
	}
	
	public boolean checkUserAlreadyHasAccount(int employeeId) {
		boolean userHasAccountAlready = false;
		try (			
				FileInputStream fis = new FileInputStream(employeeIdFile);
				BufferedReader br = new BufferedReader(new InputStreamReader(fis));
			) {
				String currentId;
				while((currentId = br.readLine()) != null){
					if(currentId.equals(employeeId)) {
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
