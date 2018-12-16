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

public class Employee {
	private String firstName;
	private String lastName;
	private String address;
	private String birthDate;
	private String emailAddress;
	private String phoneNumber;
	private int employeeId;
	private int employeeIsActive;
	private String employeePath = "C:\\Users\\boydt\\Desktop\\Project0\\project-zero-BoydHarold\\BankingApp\\PseudoDB\\PseudoTables\\Employee\\";
	private File employeeIdFile = new File(employeePath + "EmployeeId.txt");
	private File employeeFirstNameFile = new File(employeePath + "EmployeeFirstName.txt");
	private File employeeLastNameFile = new File(employeePath + "EmployeeLastName.txt");
	private File employeeAddressFile = new File(employeePath + "EmployeeAddress.txt");
	private File employeeBirthDateFile = new File(employeePath + "EmployeeBirthDate.txt");
	private File employeeEmailAddressFile = new File(employeePath + "EmployeeEmailAddress.txt");
	private File employeePhoneNumberFile = new File(employeePath + "EmployeePhoneNumber.txt");
	private File employeeIsActiveFile = new File(employeePath + "EmployeeIsActive.txt");
	
	public void makeBaselineEmployee() {
		boolean exists = employeeIdFile.exists();
		if(!exists) {
			try (
					FileOutputStream fosEmployeeId = new FileOutputStream(employeeIdFile, true);
					PrintStream psEmployeeId = new PrintStream(fosEmployeeId);

					FileOutputStream fosEmployeeFirstName = new FileOutputStream(employeeFirstNameFile, true);
					PrintStream psEmployeeFirstName = new PrintStream(fosEmployeeFirstName);
					
					FileOutputStream fosEmployeeLastName = new FileOutputStream(employeeLastNameFile, true);
					PrintStream psEmployeeLastName = new PrintStream(fosEmployeeLastName);
					
					FileOutputStream fosEmployeeAddress = new FileOutputStream(employeeAddressFile, true);
					PrintStream psEmployeeAddress = new PrintStream(fosEmployeeAddress);
					
					FileOutputStream fosEmployeeBirthDate = new FileOutputStream(employeeBirthDateFile, true);
					PrintStream psEmployeeBirthDate = new PrintStream(fosEmployeeBirthDate);
					
					FileOutputStream fosEmployeeEmailAddress = new FileOutputStream(employeeEmailAddressFile, true);
					PrintStream psEmployeeEmailAddress = new PrintStream(fosEmployeeEmailAddress);
					
					FileOutputStream fosEmployeePhoneNumber = new FileOutputStream(employeePhoneNumberFile, true);
					PrintStream psEmployeePhoneNumber = new PrintStream(fosEmployeePhoneNumber);
					
					FileOutputStream fosEmployeeIsActive = new FileOutputStream(employeeIsActiveFile, true);
					PrintStream psEmployeeIsActive = new PrintStream(fosEmployeeIsActive);) {
				
				psEmployeeId.println("0");
				psEmployeeFirstName.println("0");
				psEmployeeLastName.println("0");
				psEmployeeAddress.println("0");
				psEmployeeBirthDate.println("0");
				psEmployeeEmailAddress.println("0");
				psEmployeePhoneNumber.println("0");
				psEmployeeIsActive.println("1");
				
			} catch (FileNotFoundException e) {

			} catch (IOException e) {

			}
			
		}
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

			public void setEmployeeId(int employeeId) {
				this.employeeId = employeeId;
			}
			
			public void setEmployeeIsActive(int employeeIsActive) {
				this.employeeIsActive = employeeIsActive;
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
			
			public int generateEmployeeId() {
				int newId = 0;
				
				try (
						FileInputStream fisEmployeeId = new FileInputStream(employeeIdFile);
						BufferedReader brEmployeeId = new BufferedReader(new InputStreamReader(fisEmployeeId));
				)
				{
					String lastId = "";
					String currentId = "";
					while((currentId = brEmployeeId.readLine()) != null) {
						lastId = currentId;
					}
					
					newId = Integer.parseInt(lastId) + 1;
					
				} catch (FileNotFoundException e) {

				} catch (IOException e) {
					
				}
				
				return newId;
			}

			public int commitEmployee() {
				try (
						FileOutputStream fosEmployeeId = new FileOutputStream(employeeIdFile, true);
						PrintStream psEmployeeId = new PrintStream(fosEmployeeId);

						FileOutputStream fosEmployeeFirstName = new FileOutputStream(employeeFirstNameFile, true);
						PrintStream psEmployeeFirstName = new PrintStream(fosEmployeeFirstName);
						
						FileOutputStream fosEmployeeLastName = new FileOutputStream(employeeLastNameFile, true);
						PrintStream psEmployeeLastName = new PrintStream(fosEmployeeLastName);
						
						FileOutputStream fosEmployeeAddress = new FileOutputStream(employeeAddressFile, true);
						PrintStream psEmployeeAddress = new PrintStream(fosEmployeeAddress);
						
						FileOutputStream fosEmployeeBirthDate = new FileOutputStream(employeeBirthDateFile, true);
						PrintStream psEmployeeBirthDate = new PrintStream(fosEmployeeBirthDate);
						
						FileOutputStream fosEmployeeEmailAddress = new FileOutputStream(employeeEmailAddressFile, true);
						PrintStream psEmployeeEmailAddress = new PrintStream(fosEmployeeEmailAddress);
						
						FileOutputStream fosEmployeePhoneNumber = new FileOutputStream(employeePhoneNumberFile, true);
						PrintStream psEmployeePhoneNumber = new PrintStream(fosEmployeePhoneNumber);
						
						FileOutputStream fosEmployeeIsActive = new FileOutputStream(employeeIsActiveFile, true);
						PrintStream psEmployeeIsActive = new PrintStream(fosEmployeeIsActive);) {
					
					this.employeeId = generateEmployeeId();
					psEmployeeId.println(String.valueOf(employeeId).trim());
					psEmployeeFirstName.println(firstName.trim());
					psEmployeeLastName.println(lastName.trim());
					psEmployeeAddress.println(address.trim());
					psEmployeeBirthDate.println(birthDate.trim());
					psEmployeeEmailAddress.println(emailAddress.trim());
					psEmployeePhoneNumber.println(phoneNumber.trim());
					psEmployeeIsActive.println("1");

				} catch (FileNotFoundException e) {

				} catch (IOException e) {

				}
				
				return this.employeeId;
			}

			public void getCommittedEmployeeInformation(int employeeId) {
				this.employeeId = employeeId;
				
				try (
						
		            	FileInputStream fisEmployeeFirstName = new FileInputStream(employeeFirstNameFile);
						BufferedReader brEmployeeFirstName = new BufferedReader(new InputStreamReader(fisEmployeeFirstName));
						
		            	FileInputStream fisEmployeeLastName = new FileInputStream(employeeLastNameFile);
						BufferedReader brEmployeeLastName = new BufferedReader(new InputStreamReader(fisEmployeeLastName));
						
		            	FileInputStream fisEmployeeAddress = new FileInputStream(employeeAddressFile);
						BufferedReader brEmployeeAddress = new BufferedReader(new InputStreamReader(fisEmployeeAddress));
						
		            	FileInputStream fisEmployeeBirthDate = new FileInputStream(employeeBirthDateFile);
						BufferedReader brEmployeeBirthDate = new BufferedReader(new InputStreamReader(fisEmployeeBirthDate));
						
		            	FileInputStream fisEmployeeEmailAddress = new FileInputStream(employeeEmailAddressFile);
						BufferedReader brEmployeeEmailAddress = new BufferedReader(new InputStreamReader(fisEmployeeEmailAddress));
						
		            	FileInputStream fisEmployeePhoneNumber = new FileInputStream(employeePhoneNumberFile);
						BufferedReader brEmployeePhoneNumber = new BufferedReader(new InputStreamReader(fisEmployeePhoneNumber));
						
		            	FileInputStream fisEmployeeIsActive = new FileInputStream(employeeIsActiveFile);
						BufferedReader brEmployeeIsActive = new BufferedReader(new InputStreamReader(fisEmployeeIsActive));
					){
					
					ArrayList<String> list = new ArrayList<>();
		            String line = brEmployeeFirstName.readLine();
		            while(line != null){
		            	list.add(line);
		                line = brEmployeeFirstName.readLine();
		            }  
		            
		            this.firstName = list.get(employeeId);
		            
		            line = brEmployeeLastName.readLine();
		        	list.clear();
		            while(line != null){
		            	list.add(line);
		                line = brEmployeeLastName.readLine();
		            }  
		            
		            this.lastName = list.get(employeeId);
		            
		            line = brEmployeeAddress.readLine();
		        	list.clear();
		            while(line != null){
		            	list.add(line);
		                line = brEmployeeAddress.readLine();
		            }  
		            
		            this.address = list.get(employeeId);
		            
		            line = brEmployeeBirthDate.readLine();
		        	list.clear();
		            while(line != null){
		            	list.add(line);
		                line = brEmployeeBirthDate.readLine();
		            }  
		            
		            this.birthDate = list.get(employeeId);
		            
		            line = brEmployeeEmailAddress.readLine();
		        	list.clear();
		            while(line != null){
		            	list.add(line);
		                line = brEmployeeEmailAddress.readLine();
		            }  
		            
		            this.emailAddress = list.get(employeeId);
		            
		            line = brEmployeePhoneNumber.readLine();
		        	list.clear();
		            while(line != null){
		            	list.add(line);
		                line = brEmployeePhoneNumber.readLine();
		            }  
		            
		            this.phoneNumber = list.get(employeeId);
		            
		            line = brEmployeeIsActive.readLine();
		        	list.clear();
		            while(line != null){
		            	list.add(line);
		                line = brEmployeeIsActive.readLine();
		            }  
		            
		            this.employeeIsActive = Integer.parseInt(list.get(employeeId));
		            	
		    		} catch (FileNotFoundException e) {

		    		} catch (IOException e) {
		    			
		    		}
			}
			
			public boolean employeeCheck(int employeeId) {
				boolean exists = false;
				try 
				(
				FileInputStream fisEmployeeId = new FileInputStream(employeeIdFile);
				BufferedReader brEmployeeId = new BufferedReader(new InputStreamReader(fisEmployeeId));
						
				) {
					String line = "";
					while((line = brEmployeeId.readLine()) != null) {
						
						if(Integer.parseInt(line) == employeeId) {
							exists = true;
						}
					}
				} catch (FileNotFoundException e) {
					
				} catch (IOException e) {
					
				}
				
				return exists;
			}
		
		
	}

