package com.revature;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

public class Login {
	private int positionUsername = 0;
	private int finalUsernamePosition;
	private int positionPassword = 0;
	private int finalPasswordPosition;
	private String customerAuthenticationPath = "C:\\Users\\boydt\\Desktop\\Project0\\project-zero-BoydHarold\\BankingApp\\PseudoDB\\PseudoTables\\Authentication\\Customer\\";
	File customerUsernamesFile = new File(customerAuthenticationPath + "Usernames.txt");
	File customerPasswordFile = new File(customerAuthenticationPath + "Passwords.txt");
	private String employeeAuthenticationPath = "C:\\Users\\boydt\\Desktop\\Project0\\project-zero-BoydHarold\\BankingApp\\PseudoDB\\PseudoTables\\Authentication\\Employee\\";
	File employeeUsernamesFile = new File(employeeAuthenticationPath + "Usernames.txt");
	File employeePasswordFile = new File(employeeAuthenticationPath + "Passwords.txt");
	private String adminAuthenticationPath = "C:\\Users\\boydt\\Desktop\\Project0\\project-zero-BoydHarold\\BankingApp\\PseudoDB\\PseudoTables\\Authentication\\Administrator\\";
	File adminUsernamesFile = new File(adminAuthenticationPath + "Usernames.txt");
	File adminPasswordFile = new File(adminAuthenticationPath + "Passwords.txt");
	
	public boolean validateCredentials(String accountType, String username, String password) {
		boolean validatedUsername = false;
		boolean validatedPassword = false;
		boolean validatedCredentials = false;
		
		
		if(accountType.equals("1")) {
		try 
		(
		FileInputStream fisUsername = new FileInputStream(customerUsernamesFile);
		BufferedReader brUsername = new BufferedReader(new InputStreamReader(fisUsername));
				
		FileInputStream fisPassword = new FileInputStream(customerPasswordFile);
		BufferedReader brPassword = new BufferedReader(new InputStreamReader(fisPassword));
				
		) {
			String line = "";
			while((line = brUsername.readLine()) != null) {
				positionUsername++;
				if(line.equals(username)) {
					validatedUsername = true;
					finalUsernamePosition = positionUsername;
				}
			}
			
			String line2 = "";
			while((line2 = brPassword.readLine()) != null) {
				positionPassword++;
				if(line2.equals(password)) {
					validatedPassword = true;
					finalPasswordPosition = positionPassword;
				}
			}
		} catch (FileNotFoundException e) {
			
		} catch (IOException e) {
			
		}
		} else if (accountType.equals("2")) {
			try 
			(
			FileInputStream fisUsername = new FileInputStream(employeeUsernamesFile);
			BufferedReader brUsername = new BufferedReader(new InputStreamReader(fisUsername));
					
			FileInputStream fisPassword = new FileInputStream(employeePasswordFile);
			BufferedReader brPassword = new BufferedReader(new InputStreamReader(fisPassword));
					
			) {
				String line = "";
				while((line = brUsername.readLine()) != null) {
					positionUsername++;
					if(line.equals(username)) {
						validatedUsername = true;
						finalUsernamePosition = positionUsername;
					}
				}
				
				String line2 = "";
				while((line2 = brPassword.readLine()) != null) {
					positionPassword++;
					if(line2.equals(password)) {
						validatedPassword = true;
						finalPasswordPosition = positionPassword;
					}
				}
			} catch (FileNotFoundException e) {
				
			} catch (IOException e) {
				
			}
		} else if (accountType.equals("3")) {
			try 
			(
			FileInputStream fisUsername = new FileInputStream(adminUsernamesFile);
			BufferedReader brUsername = new BufferedReader(new InputStreamReader(fisUsername));
					
			FileInputStream fisPassword = new FileInputStream(adminPasswordFile);
			BufferedReader brPassword = new BufferedReader(new InputStreamReader(fisPassword));
					
			) {
				String line = "";
				while((line = brUsername.readLine()) != null) {
					positionUsername++;
					if(line.equals(username)) {
						validatedUsername = true;
						finalUsernamePosition = positionUsername;
					}
				}
				
				String line2 = "";
				while((line2 = brPassword.readLine()) != null) {
					positionPassword++;
					if(line2.equals(password)) {
						validatedPassword = true;
						finalPasswordPosition = positionPassword;
					}
				}
			} catch (FileNotFoundException e) {
				
			} catch (IOException e) {
				
			}
		}
		
		if((validatedUsername == true && validatedPassword == true) && (finalUsernamePosition == finalPasswordPosition)) {
			validatedCredentials = true;
		} else {
			validatedCredentials = false;
		}
		
		return validatedCredentials;
	}
	
}
