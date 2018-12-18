package com.revature;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.ArrayList;

public class Customer {
	private String firstName;
	private String lastName;
	private String address;
	private String birthDate;
	private String emailAddress;
	private String phoneNumber;
	private int customerId;
	private int customerIsActive;
	private String customerPath = "C:\\Users\\boydt\\Desktop\\Project0\\project-zero-BoydHarold\\BankingApp\\PseudoDB\\PseudoTables\\Customer\\";
	private String accountPath = "C:\\Users\\boydt\\Desktop\\Project0\\project-zero-BoydHarold\\BankingApp\\PseudoDB\\PseudoTables\\Account\\";
	private File customerIdFile = new File(customerPath + "CustomerId.txt");
	private File customerFirstNameFile = new File(customerPath + "CustomerFirstName.txt");
	private File customerLastNameFile = new File(customerPath + "CustomerLastName.txt");
	private File customerAddressFile = new File(customerPath + "CustomerAddress.txt");
	private File customerBirthDateFile = new File(customerPath + "CustomerBirthDate.txt");
	private File customerEmailAddressFile = new File(customerPath + "CustomerEmailAddress.txt");
	private File customerPhoneNumberFile = new File(customerPath + "CustomerPhoneNumber.txt");
	private File customerIsActiveFile = new File(customerPath + "CustomerIsActive.txt");
	private File customerAccountApprovalStatusCheckingFile = new File(accountPath + "Checking\\CustomerAccountApprovalStatus.txt");
	private File customerAccountApprovalStatusSavingsFile = new File(accountPath + "Savings\\CustomerAccountApprovalStatus.txt");
	private File customerIdCheckingFile = new File(accountPath + "Checking\\CustomerId.txt");
	private File customerCheckingAccountBalanceFile = new File(accountPath + "Checking\\CustomerCheckingAccountBalance.txt");
	private File customerAccountStatusCheckingFile = new File(accountPath + "Checking\\CustomerAccountStatus.txt");
	private File customerIdSavingsFile = new File(accountPath + "Savings\\CustomerId.txt");
	private File customerSavingsAccountBalanceFile = new File(accountPath + "Savings\\CustomerSavingsAccountBalance.txt");
	private File customerAccountStatusSavingsFile = new File(accountPath + "Savings\\CustomerAccountStatus.txt");
	
	public Customer(String firstName, String lastName, String address, String birthDate, String emailAddress, String phoneNumber) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
		this.birthDate = birthDate;
		this.emailAddress = emailAddress;
		this.phoneNumber = phoneNumber;
	}
	
	public Customer() {
		
	}
	
	public void makeBaselineCustomer() {
		boolean exists = customerIdFile.exists();
		if(!exists) {
			try (
					FileOutputStream fosCustomerId = new FileOutputStream(customerIdFile, true);
					PrintStream psCustomerId = new PrintStream(fosCustomerId);

					FileOutputStream fosCustomerFirstName = new FileOutputStream(customerFirstNameFile, true);
					PrintStream psCustomerFirstName = new PrintStream(fosCustomerFirstName);
					
					FileOutputStream fosCustomerLastName = new FileOutputStream(customerLastNameFile, true);
					PrintStream psCustomerLastName = new PrintStream(fosCustomerLastName);
					
					FileOutputStream fosCustomerAddress = new FileOutputStream(customerAddressFile, true);
					PrintStream psCustomerAddress = new PrintStream(fosCustomerAddress);
					
					FileOutputStream fosCustomerBirthDate = new FileOutputStream(customerBirthDateFile, true);
					PrintStream psCustomerBirthDate = new PrintStream(fosCustomerBirthDate);
					
					FileOutputStream fosCustomerEmailAddress = new FileOutputStream(customerEmailAddressFile, true);
					PrintStream psCustomerEmailAddress = new PrintStream(fosCustomerEmailAddress);
					
					FileOutputStream fosCustomerPhoneNumber = new FileOutputStream(customerPhoneNumberFile, true);
					PrintStream psCustomerPhoneNumber = new PrintStream(fosCustomerPhoneNumber);
					
					FileOutputStream fosCustomerIsActive = new FileOutputStream(customerIsActiveFile, true);
					PrintStream psCustomerIsActive = new PrintStream(fosCustomerIsActive);
					
					) {
				
				psCustomerId.println("0");
				psCustomerFirstName.println("0");
				psCustomerLastName.println("0");
				psCustomerAddress.println("0");
				psCustomerBirthDate.println("0");
				psCustomerEmailAddress.println("0");
				psCustomerPhoneNumber.println("0");
				psCustomerIsActive.println("0");
				
			} catch (FileNotFoundException e) {

			} catch (IOException e) {

			}
		}
		
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

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
	
	public void setCustomerIsActive(int customerIsActive) {
		this.customerIsActive = customerIsActive;
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
	
	public int generateCustomerId() {
		int newId = 0;
		
		try (
				FileInputStream fisCustomerId = new FileInputStream(customerIdFile);
				BufferedReader brCustomerId = new BufferedReader(new InputStreamReader(fisCustomerId));
		)
		{
			String lastId = "";
			String currentId = "";
			while((currentId = brCustomerId.readLine()) != null) {
				lastId = currentId;
			}
			
			newId = Integer.parseInt(lastId) + 1;
			
		} catch (FileNotFoundException e) {

		} catch (IOException e) {
			
		}
		
		return newId;
	}

	public int commitCustomer() {
		try (
				FileOutputStream fosCustomerId = new FileOutputStream(customerIdFile, true);
				PrintStream psCustomerId = new PrintStream(fosCustomerId);

				FileOutputStream fosCustomerFirstName = new FileOutputStream(customerFirstNameFile, true);
				PrintStream psCustomerFirstName = new PrintStream(fosCustomerFirstName);
				
				FileOutputStream fosCustomerLastName = new FileOutputStream(customerLastNameFile, true);
				PrintStream psCustomerLastName = new PrintStream(fosCustomerLastName);
				
				FileOutputStream fosCustomerAddress = new FileOutputStream(customerAddressFile, true);
				PrintStream psCustomerAddress = new PrintStream(fosCustomerAddress);
				
				FileOutputStream fosCustomerBirthDate = new FileOutputStream(customerBirthDateFile, true);
				PrintStream psCustomerBirthDate = new PrintStream(fosCustomerBirthDate);
				
				FileOutputStream fosCustomerEmailAddress = new FileOutputStream(customerEmailAddressFile, true);
				PrintStream psCustomerEmailAddress = new PrintStream(fosCustomerEmailAddress);
				
				FileOutputStream fosCustomerPhoneNumber = new FileOutputStream(customerPhoneNumberFile, true);
				PrintStream psCustomerPhoneNumber = new PrintStream(fosCustomerPhoneNumber);
				
				FileOutputStream fosCustomerIsActive = new FileOutputStream(customerIsActiveFile, true);
				PrintStream psCustomerIsActive = new PrintStream(fosCustomerIsActive);
				
				FileOutputStream fosCustomerCheckingId = new FileOutputStream(customerIdCheckingFile, true);
				PrintStream psCustomerCheckingId = new PrintStream(fosCustomerCheckingId);
				
				FileOutputStream fosCustomerCheckingAccountBalance = new FileOutputStream(customerCheckingAccountBalanceFile, true);
				PrintStream psCustomerCheckingAccountBalance = new PrintStream(fosCustomerCheckingAccountBalance);
				
				FileOutputStream fosCustomerAccountCheckingStatus = new FileOutputStream(customerAccountStatusCheckingFile, true);
				PrintStream psCustomerAccountCheckingStatus = new PrintStream(fosCustomerAccountCheckingStatus);
				
				FileOutputStream fosCustomerSavingsId = new FileOutputStream(customerIdSavingsFile, true);
				PrintStream psCustomerSavingsId = new PrintStream(fosCustomerSavingsId);
				
				FileOutputStream fosCustomerSavingsAccountBalance = new FileOutputStream(customerSavingsAccountBalanceFile, true);
				PrintStream psCustomerSavingsAccountBalance = new PrintStream(fosCustomerSavingsAccountBalance);
				
				FileOutputStream fosCustomerAccountSavingsStatus = new FileOutputStream(customerAccountStatusSavingsFile, true);
				PrintStream psCustomerAccountSavingsStatus = new PrintStream(fosCustomerAccountSavingsStatus);
				
				FileOutputStream fosCustomerAccountApprovalStatusChecking = new FileOutputStream(customerAccountApprovalStatusCheckingFile, true);
				PrintStream psCustomerAccountApprovalStatusChecking = new PrintStream(fosCustomerAccountApprovalStatusChecking);
				
				FileOutputStream fosCustomerAccountApprovalStatusSavings = new FileOutputStream(customerAccountApprovalStatusSavingsFile, true);
				PrintStream psCustomerAccountApprovalStatusSavings = new PrintStream(fosCustomerAccountApprovalStatusSavings);
						
				) {
			
			this.customerId = generateCustomerId();
			psCustomerId.println(customerId);
			psCustomerFirstName.println(firstName.trim());
			psCustomerLastName.println(lastName.trim());
			psCustomerAddress.println(address.trim());
			psCustomerBirthDate.println(birthDate.trim());
			psCustomerEmailAddress.println(emailAddress.trim());
			psCustomerPhoneNumber.println(phoneNumber.trim());
			psCustomerIsActive.println("1");
			psCustomerCheckingId.println(customerId);
			psCustomerCheckingAccountBalance.println("0");
			psCustomerAccountCheckingStatus.println("0");
			psCustomerSavingsId.println(customerId);
			psCustomerSavingsAccountBalance.println("0");
			psCustomerAccountSavingsStatus.println("0");
			psCustomerAccountApprovalStatusChecking.println("u");
			psCustomerAccountApprovalStatusSavings.println("u");
		} catch (FileNotFoundException e) {

		} catch (IOException e) {

		}
		return this.customerId;
	}

	public void getCommittedCustomerInformation(int customerId) {
		this.customerId = customerId;
		
		try (
				
            	FileInputStream fisCustomerFirstName = new FileInputStream(customerFirstNameFile);
				BufferedReader brCustomerFirstName = new BufferedReader(new InputStreamReader(fisCustomerFirstName));
				
            	FileInputStream fisCustomerLastName = new FileInputStream(customerLastNameFile);
				BufferedReader brCustomerLastName = new BufferedReader(new InputStreamReader(fisCustomerLastName));
				
            	FileInputStream fisCustomerAddress = new FileInputStream(customerAddressFile);
				BufferedReader brCustomerAddress = new BufferedReader(new InputStreamReader(fisCustomerAddress));
				
            	FileInputStream fisCustomerBirthDate = new FileInputStream(customerBirthDateFile);
				BufferedReader brCustomerBirthDate = new BufferedReader(new InputStreamReader(fisCustomerBirthDate));
				
            	FileInputStream fisCustomerEmailAddress = new FileInputStream(customerEmailAddressFile);
				BufferedReader brCustomerEmailAddress = new BufferedReader(new InputStreamReader(fisCustomerEmailAddress));
				
            	FileInputStream fisCustomerPhoneNumber = new FileInputStream(customerPhoneNumberFile);
				BufferedReader brCustomerPhoneNumber = new BufferedReader(new InputStreamReader(fisCustomerPhoneNumber));
				
            	FileInputStream fisCustomerIsActive = new FileInputStream(customerIsActiveFile);
				BufferedReader brCustomerIsActive = new BufferedReader(new InputStreamReader(fisCustomerIsActive));
			){
			
			ArrayList<String> list = new ArrayList<>();
            String line = brCustomerFirstName.readLine();
            while(line != null){
            	list.add(line);
                line = brCustomerFirstName.readLine();
            }  
            
            this.firstName = list.get(customerId);
            
            line = brCustomerLastName.readLine();
        	list.clear();
            while(line != null){
            	list.add(line);
                line = brCustomerLastName.readLine();
            }  
            
            this.lastName = list.get(customerId);
            
            line = brCustomerAddress.readLine();
        	list.clear();
            while(line != null){
            	list.add(line);
                line = brCustomerAddress.readLine();
            }  
            
            this.address = list.get(customerId);
            
            line = brCustomerBirthDate.readLine();
        	list.clear();
            while(line != null){
            	list.add(line);
                line = brCustomerBirthDate.readLine();
            }  
            
            this.birthDate = list.get(customerId);
            
            line = brCustomerEmailAddress.readLine();
        	list.clear();
            while(line != null){
            	list.add(line);
                line = brCustomerEmailAddress.readLine();
            }  
            
            this.emailAddress = list.get(customerId);
            
            line = brCustomerPhoneNumber.readLine();
        	list.clear();
            while(line != null){
            	list.add(line);
                line = brCustomerPhoneNumber.readLine();
            }  
            
            this.phoneNumber = list.get(customerId);
            
            line = brCustomerIsActive.readLine();
        	list.clear();
            while(line != null){
            	list.add(line);
                line = brCustomerIsActive.readLine();
            }  
            
            this.customerIsActive = Integer.parseInt(list.get(customerId));
            	
    		} catch (FileNotFoundException e) {

    		} catch (IOException e) {
    			
    		}
	}
	
	public boolean customerCheck(int customerId) {
		boolean exists = false;
		try 
		(
		FileInputStream fisCustomerId = new FileInputStream(customerIdFile);
		BufferedReader brCustomerId = new BufferedReader(new InputStreamReader(fisCustomerId));
				
		) {
			String line = "";
			while((line = brCustomerId.readLine()) != null) {
				
				if(Integer.parseInt(line) == customerId) {
					exists = true;
				}
			}
		} catch (FileNotFoundException e) {
			
		} catch (IOException e) {
			
		}
		
		return exists;
	}


	
	

}
