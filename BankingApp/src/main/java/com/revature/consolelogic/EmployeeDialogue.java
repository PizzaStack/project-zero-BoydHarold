package com.revature.consolelogic;

import java.text.DecimalFormat;
import java.time.Year;
import java.util.Scanner;

import com.revature.Employee;
import com.revature.dao.EmployeeDao;

public class EmployeeDialogue {
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
	private int employeeId;
	DecimalFormat df = new DecimalFormat("#0.00");

	public int addNewEmployee() {
		EmployeeDao employeeDao = new EmployeeDao();
		while (commit.equals("n")) {

			while (firstName.equals("")) {
				System.out.println("Enter in the Employee's First Name:");
				firstName = sc.nextLine();
			}

			while (lastName.equals("")) {
				System.out.println("\nEnter in the Employee's Last Name:");
				lastName = sc.nextLine();
			}

			while (address.equals("")) {
				System.out.println("\nEnter in the Employee's Address:");
				address = sc.nextLine();
			}

			System.out.println("\nEnter in the Employee's Birth Date:");

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

			System.out.println("\nEnter in the Employee's Email Address:");

			while (emailAddress.equals("")) {
				emailAddress = sc.nextLine();
				while (validEntry == false) {
					if (emailAddress.contains("@")) {
						validEntry = true;
					} else {
						System.out.println(
								"Invalid entry! Make sure the Email Address contains the '@' sign and Domain!");
						emailAddress = sc.nextLine();
						validEntry = false;
					}
				}

			}

			System.out.println("\nEnter in the Employee's Phone Number:");
			validEntry = false;
			while (phoneNumber.equals("")) {
				phoneNumber = sc.nextLine();
				if (phoneNumber.contains("-")) {
					validEntry = true;
				} else {
					System.out.println("Invalid entry! Make sure the Phone Number contains a '-' character!");
					phoneNumber = sc.nextLine();
					validEntry = false;
				}
			}

			birthDate = String.valueOf(month) + "/" + String.valueOf(day) + "/" + String.valueOf(year);

			Employee employee = new Employee(firstName, lastName, address, birthDate, emailAddress, phoneNumber, 0,
					"p");

			System.out.println("\nPlease review the following information:");
			System.out.println("First Name: " + employee.getEmployeeFirstName());
			System.out.println("Last Name: " + employee.getEmployeeLastName());
			System.out.println("Address: " + employee.getEmployeeAddress());
			System.out.println("Birth Date: " + employee.getEmployeeBirthDate());
			System.out.println("Email Address: " + employee.getEmployeeEmailAddress());
			System.out.println("Phone Number: " + employee.getEmployeePhoneNumber());

			System.out.println("\nCommit? (Y/N)");
			commit = sc.nextLine().toLowerCase();
			validEntry = false;

			while (validEntry = false) {
				while (commit.length() > 1 || commit.length() < 1) {
					System.out.println("Invalid entry! Please submit either Y or N!");
					commit = sc.nextLine().toLowerCase();
					validEntry = false;
				}

				if (commit.equals("y") || commit.equals("n")) {
					validEntry = true;
				}
			}

			if (commit.equals("n")) {
				System.out.println("\nEnter the Employee information again.\n");
				firstName = "";
				lastName = "";
				emailAddress = "";
				address = "";
				day = "";
				month = "";
				year = "";
			} else {
				employeeDao.addEmployee(employee);

				employeeId = 0;

				for (Employee getAllEmployees : employeeDao.getAllEmployees()) {
					if (firstName.equals(getAllEmployees.getEmployeeFirstName())
							&& lastName.equals(getAllEmployees.getEmployeeLastName())
							&& emailAddress.equals(getAllEmployees.getEmployeeEmailAddress())
							&& address.equals(getAllEmployees.getEmployeeAddress())
							&& birthDate.equals(getAllEmployees.getEmployeeBirthDate())
							&& phoneNumber.equals(getAllEmployees.getEmployeePhoneNumber())) {
						employeeId = getAllEmployees.getEmployeeId();
					}
				}

				System.out.println("\nCommited!");
				System.out
						.println("\nEmployee ID generated! Make sure to write this down!\nEmployee ID: " + employeeId);
			}

		}

		return employeeId;
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

	public void displayEmployee() {
		EmployeeDao employeeDao = new EmployeeDao();
		System.out.println("Enter in the Employee ID:");
		int employeeId = Integer.parseInt(sc.nextLine());
		boolean exists = false;

		Employee employee = employeeDao.getEmployeeById(employeeId);
		if (employee != null) {
			exists = true;
		} else {
			exists = false;
		}

		if (exists) {

			System.out.println("Displaying General Employee Information for Employee ID: " + employeeId);
			System.out.println("First Name: " + employee.getEmployeeFirstName());
			System.out.println("Last Name: " + employee.getEmployeeLastName());
			System.out.println("Address: " + employee.getEmployeeAddress());
			System.out.println("Birth Date: " + employee.getEmployeeBirthDate());
			System.out.println("Email Address: " + employee.getEmployeeEmailAddress());
			System.out.println("Phone Number: " + employee.getEmployeePhoneNumber());

			int status = employee.getEmployeeIsActive();
			String statusString = "";
			if (status == 0) {
				statusString = "Disabled";
			} else if (status == 1) {
				statusString = "Enabled";
			}
			System.out.println("Status: " + statusString);

		} else {
			System.out.println("Employee does not exist!");
		}

	}
}
