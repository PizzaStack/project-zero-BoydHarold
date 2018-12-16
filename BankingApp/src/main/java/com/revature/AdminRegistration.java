package com.revature;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;

public class AdminRegistration {
	private String authenticationPath = "C:\\Users\\boydt\\Desktop\\Project0\\project-zero-BoydHarold\\BankingApp\\PseudoDB\\PseudoTables\\Authentication\\Administrator\\";
	File administratorIdFile = new File(authenticationPath + "AdministratorId.txt");
	File usernamesFile = new File(authenticationPath + "Usernames.txt");
	File passwordFile = new File(authenticationPath + "Passwords.txt");
	File accountTypeFile = new File(authenticationPath + "AccountTypeFile.txt");
	File administratorIdFile2 = new File("C:\\Users\\boydt\\Desktop\\Project0\\project-zero-BoydHarold\\BankingApp\\PseudoDB\\PseudoTables\\Administrator\\AdministratorId.txt");

	public void makeBaselineUser() {
		boolean exists = usernamesFile.exists();
		if(!exists) {
			try (
					FileOutputStream fosAdministratorId = new FileOutputStream(administratorIdFile, true);
					PrintStream psAdministratorId = new PrintStream(fosAdministratorId);

					FileOutputStream fosUsernames = new FileOutputStream(usernamesFile, true);
					PrintStream psUsernames = new PrintStream(fosUsernames);

					FileOutputStream fosPasswords = new FileOutputStream(passwordFile, true);
					PrintStream psPasswords= new PrintStream(fosPasswords);

					FileOutputStream fosAccountType= new FileOutputStream(accountTypeFile, true);
					PrintStream psAccountType = new PrintStream(fosAccountType);
			
			){
				
				psAdministratorId.println("0");
				psUsernames.println("admin");
				psPasswords.println("password");
				psAccountType.println("a");
				
			} catch (FileNotFoundException e) {

			} catch (IOException e) {
				
			}
		}
	}
	
	public boolean registerUser(String administratorId, String username, String password) {
		int userExists = checkUserExists(username);
		int administratorOnboarded = checkAdministratorOnboarded(administratorId);
		boolean success = false;
		if(userExists == 1) {
			System.out.println("Account already exists with that username!");
			success = false;
		} else if(administratorOnboarded == 0) {
			System.out.println("Administrator does not have an account with the bank, please speak to a bank employee so you can be onboarded.");
			success = false;
		} else {
			try (
					FileOutputStream fosAdministratorId = new FileOutputStream(administratorIdFile, true);
					PrintStream psAdministratorId = new PrintStream(fosAdministratorId);

					FileOutputStream fosUsernames = new FileOutputStream(usernamesFile, true);
					PrintStream psUsernames = new PrintStream(fosUsernames);

					FileOutputStream fosPasswords = new FileOutputStream(passwordFile, true);
					PrintStream psPasswords= new PrintStream(fosPasswords);

					FileOutputStream fosAccountType= new FileOutputStream(accountTypeFile, true);
					PrintStream psAccountType = new PrintStream(fosAccountType);
			
			){
				
				psAdministratorId.println(administratorId);
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
	
	public int checkAdministratorOnboarded(String administratorId) {
		int administratorExists = 0;
		
		try (			
			FileInputStream fis = new FileInputStream(administratorIdFile2);
			BufferedReader br = new BufferedReader(new InputStreamReader(fis));
		) {
			String currentId;
			while((currentId = br.readLine()) != null){
				if(currentId.equals(administratorId)) {
					administratorExists = 1;
				}
			}
	
			
		} catch (FileNotFoundException e) {
			
			return administratorExists;
		} catch (IOException e) {

		}
		return administratorExists;
	}

	public boolean checkUserAlreadyHasAccount(int administratorId) {
		boolean userHasAccountAlready = false;
		try (			
				FileInputStream fis = new FileInputStream(administratorIdFile);
				BufferedReader br = new BufferedReader(new InputStreamReader(fis));
			) {
				String currentId;
				while((currentId = br.readLine()) != null){
					if(currentId.equals(administratorId)) {
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
