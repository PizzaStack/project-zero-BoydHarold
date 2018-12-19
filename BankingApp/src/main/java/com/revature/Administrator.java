package com.revature;

public class Administrator {
	private String firstName;
	private String lastName;
	private String address;
	private String birthDate;
	private String emailAddress;
	private String phoneNumber;
	private int administratorIsActive;
			
	public Administrator(String firstName, String lastName, String address, String birthDate, String emailAddress, String phoneNumber) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
		this.birthDate = birthDate;
		this.emailAddress = emailAddress;
		this.phoneNumber = phoneNumber;
	}
	
	public Administrator() {
		
	}

			public void setAdministratorFirstName(String name) {
				this.firstName = name;
			}

			public void setAdministratorLastName(String name) {
				this.lastName = name;
			}

			public void setAdministratorAddress(String address) {
				this.address = address;
			}

			public void setAdministratorBirthDate(int day, int month, int year) {
				String birthDate = String.valueOf(day) + "/" + String.valueOf(month) + "/" + String.valueOf(year);
				this.birthDate = birthDate;
			}

			public void setAdministratorEmailAddress(String emailAddress) {
				this.emailAddress = emailAddress;
			}

			public void setAdministratorPhoneNumber(String phoneNumber) {
				this.phoneNumber = phoneNumber;
			}
			
			public void setAdministratorIsActive(int administratorIsActive) {
				this.administratorIsActive = administratorIsActive;
			}

			public String getAdministratorFirstName() {
				return this.firstName;
			}

			public String getAdministratorLastName() {
				return this.lastName;
			}

			public String getAdministratorAddress() {
				return this.address;
			}

			public String getAdministratorBirthDate() {
				return this.birthDate;
			}

			public String getAdministratorEmailAddress() {
				return this.emailAddress;
			}

			public String getAdministratorPhoneNumber() {
				return this.phoneNumber;
			}
			
			public int getAdministratorIsActive() {
				return this.administratorIsActive;
			}
			
	
}
