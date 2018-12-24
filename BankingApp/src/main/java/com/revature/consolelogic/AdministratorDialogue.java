package com.revature.consolelogic;

import java.sql.Connection;
import java.text.DecimalFormat;
import java.time.Year;
import java.util.Scanner;


import com.revature.Administrator;
import com.revature.dao.AdministratorDao;


public class AdministratorDialogue {
	private String firstName = "";
	private String lastName = "";
	private String address = "";
	private String birthDate = "";
	private String day = "";
	private String month = "";
	private String year = "";
	private String emailAddress = "";
	private String phoneNumber = "";
	private boolean validEntry = false;
	private String commit = "n";
	private Scanner sc = new Scanner(System.in);
	private int administratorId;
	DecimalFormat df = new DecimalFormat("#0.00");
	
	public int addNewAdministrator(Connection connection) {
		while(commit.equals("n")) {
			
		while (firstName.equals("")) {
			System.out.println("Enter in the Administrator's First Name:");
			firstName = sc.nextLine();
		}

		while (lastName.equals("")) {
			System.out.println("\nEnter in the Administrator's Last Name:");
			lastName = sc.nextLine();
		}

		while (address.equals("")) {
			System.out.println("\nEnter in the Administrator's Address:");
			address = sc.nextLine();
		}

		System.out.println("\nEnter in the Administrator's Birth Date:");
		
		while (month.equals("")) {
			System.out.println("Month:");
			month = sc.nextLine();
			boolean isNumeric = isNumeric(month);
			while (isNumeric == false) {
				month = "";
				System.out.println("Invalid entry! Please use the following format: 1-12");
				month = sc.nextLine();
				isNumeric = isNumeric(month);
			}

			while (month.length() > 2) {
				month = "";
				System.out.println("Invalid entry! Please use the following format: 1-12");
				month = sc.nextLine();
			}

			while (Integer.parseInt(month) > 31 || Integer.parseInt(month) < 1) {
				month = "";
				System.out.println("Invalid entry! Please use the following format: 1-12");
				month = sc.nextLine();
			}

		}
		
		while (day.equals("")) {
			System.out.println("Day:");
			day = sc.nextLine();
			boolean isNumeric = isNumeric(day);
			while (isNumeric == false) {
				day = "";
				System.out.println("Invalid entry! Please use the following format: 1-31");
				day = sc.nextLine();
				isNumeric = isNumeric(day);
			}

			while (day.length() > 2) {
				day = "";
				System.out.println("Invalid entry! Please use the following format: 1-31");
				day = sc.nextLine();
			}

			while (Integer.parseInt(day) > 31 || Integer.parseInt(day) < 1) {
				day = "";
				System.out.println("Invalid entry! Please use the following format: 1-31");
				day = sc.nextLine();
			}

		}
		
		int currentYear = Year.now().getValue();
		
		while (year.equals("")) {
			System.out.println("Year:");
			year = sc.nextLine();
			boolean isNumeric = isNumeric(year);
			while (isNumeric == false) {
				year = "";
				System.out.println("Invalid entry! Please use the following format: 1900-" + currentYear);
				year = sc.nextLine();
				isNumeric = isNumeric(month);
			}

			while (year.length() > 4) {
				year = "";
				System.out.println("Invalid entry! Please use the following format: 1900-" + currentYear);
				year = sc.nextLine();
			}

			while (Integer.parseInt(year) < 1900 || Integer.parseInt(year) >= currentYear) {
				year = "";
				System.out.println("Invalid entry! Please use the following format: 1900-" + currentYear);
				year = sc.nextLine();
			}
			

		}
		
		System.out.println("\nEnter in the Administrator's Email Address:");
		
		
		while(emailAddress.equals("")) {
			emailAddress = sc.nextLine();
			while(validEntry == false) {
				if(emailAddress.contains("@")) {
				validEntry = true;
				} else {
					System.out.println("Invalid entry! Make sure the Email Address contains the '@' sign and Domain!");
					emailAddress = sc.nextLine();
					validEntry = false;
				}
			}
			
		}
		
		System.out.println("\nEnter in the Administrator's Phone Number:");
		validEntry = false;
		while(phoneNumber.equals("")) {
			phoneNumber = sc.nextLine();
			if(phoneNumber.contains("-")) {
				validEntry = true;
			} else {
				System.out.println("Invalid entry! Make sure the Phone Number contains a '-' character!");
				phoneNumber = sc.nextLine();
				validEntry = false;
			}
		}
		
		birthDate = String.valueOf(month) + "/" + String.valueOf(day) + "/" + String.valueOf(year);
		
		Administrator administrator = new Administrator(firstName, lastName, address, birthDate, emailAddress, phoneNumber, 0, "p");
		
		System.out.println("\nPlease review the following information:");
		System.out.println("First Name: " + administrator.getAdministratorFirstName());
		System.out.println("Last Name: " + administrator.getAdministratorLastName());
		System.out.println("Address: " + administrator.getAdministratorAddress());
		System.out.println("Birth Date: " + administrator.getAdministratorBirthDate());
		System.out.println("Email Address: " + administrator.getAdministratorEmailAddress());
		System.out.println("Phone Number: " + administrator.getAdministratorPhoneNumber());
		
		System.out.println("\nCommit? (Y/N)");
		commit = sc.nextLine().toLowerCase();
		validEntry = false;
		
		while(validEntry = false) {
			while(commit.length() > 1 || commit.length() < 1) {
				System.out.println("Invalid entry! Please submit either Y or N!");
				commit = sc.nextLine().toLowerCase();
				validEntry = false;
			}
			
			if(commit.equals("y") || commit.equals("n")) {
				validEntry = true;
			}
		}
		
		if(commit.equals("n")) {
			System.out.println("\nEnter the Administrator information again.\n");
			firstName = "";
			lastName = "";
			emailAddress = "";
			address = "";
			day = "";
			month = "";
			year = "";
		} else {
			
			AdministratorDao administratorDao = new AdministratorDao(connection);
			administratorDao.addAdministrator(administrator);
			administratorId = 0;
			
			for(Administrator getAllAdministrators : administratorDao.getAllAdministrators()) {
				if(firstName.equals(getAllAdministrators.getAdministratorFirstName())
						&& lastName.equals(getAllAdministrators.getAdministratorLastName())
						&& emailAddress.equals(getAllAdministrators.getAdministratorEmailAddress())
						&& address.equals(getAllAdministrators.getAdministratorAddress())
						&& birthDate.equals(getAllAdministrators.getAdministratorBirthDate())
						&& phoneNumber.equals(getAllAdministrators.getAdministratorPhoneNumber()))
						{
					administratorId = getAllAdministrators.getAdministratorId();
				}
			}
			
			System.out.println("\nCommited!");
			System.out.println("\nAdministrator ID generated! Make sure to write this down!\nAdministrator ID: " + administratorId);
		}
		
		}
		
		return administratorId;

	}

	public boolean isNumeric(String datePart) {
		boolean isNumeric = false;
		try {
			Integer.parseInt(datePart);
			isNumeric = true;
		} catch (Exception e) {
			isNumeric = false;
		}

		return isNumeric;
	}
	
	public void displayAdministrator(Connection connection) {
		System.out.println("Enter in the Administrator ID:");
		int administratorId = Integer.parseInt(sc.nextLine());
		boolean exists = false;
		
		AdministratorDao administratorDao = new AdministratorDao(connection);
		Administrator administrator = administratorDao.getAdministratorById(administratorId);
		if(administrator != null){
	        	exists = true;
	        	} else {
	        		exists = false;
	        	}
		if(exists) {
		System.out.println("Displaying General Administrator Information for Administrator ID: " + administratorId);
		System.out.println("First Name: " + administrator.getAdministratorFirstName());
		System.out.println("Last Name: " + administrator.getAdministratorLastName());
		System.out.println("Address: " + administrator.getAdministratorAddress());
		System.out.println("Birth Date: " + administrator.getAdministratorBirthDate());
		System.out.println("Email Address: " + administrator.getAdministratorEmailAddress());
		System.out.println("Phone Number: " + administrator.getAdministratorPhoneNumber());
		
		int status = administrator.getAdministratorIsActive();
		String statusString = "";
		if(status == 0) {
			statusString = "Disabled";
		} else if(status == 1) {
			statusString = "Enabled";
		}
		System.out.println("Status: " + statusString);
		
		} else {
			System.out.println("Administrator does not exist!");
		}
	}
}
