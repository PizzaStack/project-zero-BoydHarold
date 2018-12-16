package com.revature.consolelogic;

import java.time.Year;
import java.util.Scanner;

import com.revature.Customer;

public class CustomerDialog {
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
	private Customer customer = new Customer();

	public void addNewCustomer() {
		while(commit.equals("n")) {
			
		while (firstName.equals("")) {
			System.out.println("Enter in the Customer's First Name:");
			firstName = sc.nextLine();
		}

		while (lastName.equals("")) {
			System.out.println("\nEnter in the Customer's Last Name:");
			lastName = sc.nextLine();
		}

		while (address.equals("")) {
			System.out.println("\nEnter in the Customer's Address:");
			address = sc.nextLine();
		}

		System.out.println("\nEnter in the Customer's Birth Date:");
		
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
		
		System.out.println("\nEnter in the Customer's Email Address:");
		
		
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
		
		System.out.println("\nEnter in the Customer's Phone Number:");
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
		

		
		customer.setCustomerFirstName(firstName);
		customer.setCustomerLastName(lastName);
		customer.setCustomerAddress(address);
		customer.setCustomerBirthDate(Integer.parseInt(month), Integer.parseInt(day), Integer.parseInt(year));
		customer.setCustomerEmailAddress(emailAddress);
		customer.setCustomerPhoneNumber(phoneNumber);
		
		System.out.println("\nPlease review the following information:");
		System.out.println(customer.getCustomerFirstName());
		System.out.println(customer.getCustomerLastName());
		System.out.println(customer.getCustomerAddress());
		System.out.println(customer.getCustomerBirthDate());
		System.out.println(customer.getCustomerEmailAddress());
		System.out.println(customer.getCustomerPhoneNumber());
		
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
			System.out.println("\nEnter the Customer information again.\n");
			firstName = "";
			lastName = "";
			emailAddress = "";
			address = "";
			day = "";
			month = "";
			year = "";
		} else {
			System.out.println("\nCommited!");
			int customerId = customer.commitCustomer();
			System.out.println("\nCustomer ID generated! Make sure to write this down!\nCustomer ID: " + customerId);
		}
		
		}

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
	
	public void displayCustomer() {
		System.out.println("Enter in the Customer ID:");
		int customerId = Integer.parseInt(sc.nextLine());
		boolean exists = customer.customerCheck(customerId);
		if(exists) {
		customer.getCommittedCustomerInformation(customerId);
		System.out.println("Displaying Customer Information for Customer ID: " + customerId);
		System.out.println(customer.getCustomerFirstName());
		System.out.println(customer.getCustomerLastName());
		System.out.println(customer.getCustomerAddress());
		System.out.println(customer.getCustomerBirthDate());
		System.out.println(customer.getCustomerEmailAddress());
		System.out.println(customer.getCustomerPhoneNumber());
		} else {
			System.out.println("Customer does not exist!");
		}
	}
}
