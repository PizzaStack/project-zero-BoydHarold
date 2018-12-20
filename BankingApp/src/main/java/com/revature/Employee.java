package com.revature;

public class Employee {
	private String firstName;
	private String lastName;
	private String address;
	private String birthDate;
	private String emailAddress;
	private String phoneNumber;
	private int employeeIsActive;
	private int employeeId;
	
	public Employee(String firstName, String lastName, String address, String birthDate, String emailAddress, String phoneNumber, int status) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
		this.birthDate = birthDate;
		this.emailAddress = emailAddress;
		this.phoneNumber = phoneNumber;
		this.employeeIsActive = status;
	}
	
	public Employee() {
		
	}
			
			public void setEmployeeFirstName(String name) {
				this.firstName = name;
			}

			public void setEmployeeLastName(String name) {
				this.lastName = name;
			}

			public void setEmployeeAddress(String address) {
				this.address = address;
			}

			public void setEmployeeBirthDate(int day, int month, int year) {
				String birthDate = String.valueOf(day) + "/" + String.valueOf(month) + "/" + String.valueOf(year);
				this.birthDate = birthDate;
			}

			public void setEmployeeEmailAddress(String emailAddress) {
				this.emailAddress = emailAddress;
			}

			public void setEmployeePhoneNumber(String phoneNumber) {
				this.phoneNumber = phoneNumber;
			}

			
			public void setEmployeeIsActive(int employeeIsActive) {
				this.employeeIsActive = employeeIsActive;
			}
			
			public void setEmployeeId(int employeeId) {
				this.employeeId = employeeId;
			}

			public String getEmployeeFirstName() {
				return this.firstName;
			}

			public String getEmployeeLastName() {
				return this.lastName;
			}

			public String getEmployeeAddress() {
				return this.address;
			}

			public String getEmployeeBirthDate() {
				return this.birthDate;
			}

			public String getEmployeeEmailAddress() {
				return this.emailAddress;
			}

			public String getEmployeePhoneNumber() {
				return this.phoneNumber;
			}
			
			public int getEmployeeIsActive() {
				return this.employeeIsActive;
			}
			
			public int getEmployeeId() {
				return this.employeeId;
			}
		
	}

