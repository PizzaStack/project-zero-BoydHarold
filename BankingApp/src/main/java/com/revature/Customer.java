package com.revature;

public class Customer {
	private int customerId;
	private String firstName;
	private String lastName;
	private String address;
	private String birthDate;
	private String emailAddress;
	private String phoneNumber;
	private int customerIsActive;

	public Customer(String firstName, String lastName, String address, String birthDate, String emailAddress,
			String phoneNumber, int status) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
		this.birthDate = birthDate;
		this.emailAddress = emailAddress;
		this.phoneNumber = phoneNumber;
		this.customerIsActive = status;
	}

	public Customer() {

	}

	public void setCustomerFirstName(String name) {
		this.firstName = name;
	}

	public void setCustomerLastName(String name) {
		this.lastName = name;
	}

	public void setCustomerAddress(String address) {
		this.address = address;
	}

	public void setCustomerBirthDate(int day, int month, int year) {
		String birthDate = String.valueOf(day) + "/" + String.valueOf(month) + "/" + String.valueOf(year);
		this.birthDate = birthDate;
	}

	public void setCustomerEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public void setCustomerPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public void setCustomerIsActive(int customerIsActive) {
		this.customerIsActive = customerIsActive;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public String getCustomerFirstName() {
		return this.firstName;
	}

	public String getCustomerLastName() {
		return this.lastName;
	}

	public String getCustomerAddress() {
		return this.address;
	}

	public String getCustomerBirthDate() {
		return this.birthDate;
	}

	public String getCustomerEmailAddress() {
		return this.emailAddress;
	}

	public String getCustomerPhoneNumber() {
		return this.phoneNumber;
	}

	public int getCustomerIsActive() {
		return this.customerIsActive;
	}

	public int getCustomerId() {
		return this.customerId;
	}

}
