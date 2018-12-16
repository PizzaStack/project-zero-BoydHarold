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

public class Administrator {
	private String firstName;
	private String lastName;
	private String address;
	private String birthDate;
	private String emailAddress;
	private String phoneNumber;
	private int administratorId;
	private int administratorIsActive;
	private String administratorPath = "C:\\Users\\boydt\\Desktop\\Project0\\project-zero-BoydHarold\\BankingApp\\PseudoDB\\PseudoTables\\Administrator\\";
	private File administratorIdFile = new File(administratorPath + "AdministratorId.txt");
	private File administratorFirstNameFile = new File(administratorPath + "AdministratorFirstName.txt");
	private File administratorLastNameFile = new File(administratorPath + "AdministratorLastName.txt");
	private File administratorAddressFile = new File(administratorPath + "AdministratorAddress.txt");
	private File administratorBirthDateFile = new File(administratorPath + "AdministratorBirthDate.txt");
	private File administratorEmailAddressFile = new File(administratorPath + "AdministratorEmailAddress.txt");
	private File administratorPhoneNumberFile = new File(administratorPath + "AdministratorPhoneNumber.txt");
	private File administratorIsActiveFile = new File(administratorPath + "AdministratorIsActive.txt");
	
	public void makeBaselineAdministrator() {
		boolean exists = administratorIdFile.exists();
		if(!exists) {
			try (
					FileOutputStream fosAdministratorId = new FileOutputStream(administratorIdFile, true);
					PrintStream psAdministratorId = new PrintStream(fosAdministratorId);

					FileOutputStream fosAdministratorFirstName = new FileOutputStream(administratorFirstNameFile, true);
					PrintStream psAdministratorFirstName = new PrintStream(fosAdministratorFirstName);
					
					FileOutputStream fosAdministratorLastName = new FileOutputStream(administratorLastNameFile, true);
					PrintStream psAdministratorLastName = new PrintStream(fosAdministratorLastName);
					
					FileOutputStream fosAdministratorAddress = new FileOutputStream(administratorAddressFile, true);
					PrintStream psAdministratorAddress = new PrintStream(fosAdministratorAddress);
					
					FileOutputStream fosAdministratorBirthDate = new FileOutputStream(administratorBirthDateFile, true);
					PrintStream psAdministratorBirthDate = new PrintStream(fosAdministratorBirthDate);
					
					FileOutputStream fosAdministratorEmailAddress = new FileOutputStream(administratorEmailAddressFile, true);
					PrintStream psAdministratorEmailAddress = new PrintStream(fosAdministratorEmailAddress);
					
					FileOutputStream fosAdministratorPhoneNumber = new FileOutputStream(administratorPhoneNumberFile, true);
					PrintStream psAdministratorPhoneNumber = new PrintStream(fosAdministratorPhoneNumber);
					
					FileOutputStream fosAdministratorIsActive = new FileOutputStream(administratorIsActiveFile, true);
					PrintStream psAdministratorIsActive = new PrintStream(fosAdministratorIsActive);) {
				
				psAdministratorId.println("0");
				psAdministratorFirstName.println("0");
				psAdministratorLastName.println("0");
				psAdministratorAddress.println("0");
				psAdministratorBirthDate.println("0");
				psAdministratorEmailAddress.println("0");
				psAdministratorPhoneNumber.println("0");
				psAdministratorIsActive.println("1");
				
			} catch (FileNotFoundException e) {

			} catch (IOException e) {

			}
			
		}
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

			public void setAdministratorId(int administratorId) {
				this.administratorId = administratorId;
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
			
			public int generateAdministratorId() {
				int newId = 0;
				
				try (
						FileInputStream fisAdministratorId = new FileInputStream(administratorIdFile);
						BufferedReader brAdministratorId = new BufferedReader(new InputStreamReader(fisAdministratorId));
				)
				{
					String lastId = "";
					String currentId = "";
					while((currentId = brAdministratorId.readLine()) != null) {
						lastId = currentId;
					}
					
					newId = Integer.parseInt(lastId) + 1;
					
				} catch (FileNotFoundException e) {

				} catch (IOException e) {
					
				}
				
				return newId;
			}

			public int commitAdministrator() {
				try (
						FileOutputStream fosAdministratorId = new FileOutputStream(administratorIdFile, true);
						PrintStream psAdministratorId = new PrintStream(fosAdministratorId);

						FileOutputStream fosAdministratorFirstName = new FileOutputStream(administratorFirstNameFile, true);
						PrintStream psAdministratorFirstName = new PrintStream(fosAdministratorFirstName);
						
						FileOutputStream fosAdministratorLastName = new FileOutputStream(administratorLastNameFile, true);
						PrintStream psAdministratorLastName = new PrintStream(fosAdministratorLastName);
						
						FileOutputStream fosAdministratorAddress = new FileOutputStream(administratorAddressFile, true);
						PrintStream psAdministratorAddress = new PrintStream(fosAdministratorAddress);
						
						FileOutputStream fosAdministratorBirthDate = new FileOutputStream(administratorBirthDateFile, true);
						PrintStream psAdministratorBirthDate = new PrintStream(fosAdministratorBirthDate);
						
						FileOutputStream fosAdministratorEmailAddress = new FileOutputStream(administratorEmailAddressFile, true);
						PrintStream psAdministratorEmailAddress = new PrintStream(fosAdministratorEmailAddress);
						
						FileOutputStream fosAdministratorPhoneNumber = new FileOutputStream(administratorPhoneNumberFile, true);
						PrintStream psAdministratorPhoneNumber = new PrintStream(fosAdministratorPhoneNumber);
						
						FileOutputStream fosAdministratorIsActive = new FileOutputStream(administratorIsActiveFile, true);
						PrintStream psAdministratorIsActive = new PrintStream(fosAdministratorIsActive);) {
					
					this.administratorId = generateAdministratorId();
					psAdministratorId.println(String.valueOf(administratorId).trim());
					psAdministratorFirstName.println(firstName.trim());
					psAdministratorLastName.println(lastName.trim());
					psAdministratorAddress.println(address.trim());
					psAdministratorBirthDate.println(birthDate.trim());
					psAdministratorEmailAddress.println(emailAddress.trim());
					psAdministratorPhoneNumber.println(phoneNumber.trim());
					psAdministratorIsActive.println("1");

				} catch (FileNotFoundException e) {

				} catch (IOException e) {

				}
				
				return this.administratorId;
			}

			public void getCommittedAdministratorInformation(int administratorId) {
				this.administratorId = administratorId;
				
				try (
						
		            	FileInputStream fisAdministratorFirstName = new FileInputStream(administratorFirstNameFile);
						BufferedReader brAdministratorFirstName = new BufferedReader(new InputStreamReader(fisAdministratorFirstName));
						
		            	FileInputStream fisAdministratorLastName = new FileInputStream(administratorLastNameFile);
						BufferedReader brAdministratorLastName = new BufferedReader(new InputStreamReader(fisAdministratorLastName));
						
		            	FileInputStream fisAdministratorAddress = new FileInputStream(administratorAddressFile);
						BufferedReader brAdministratorAddress = new BufferedReader(new InputStreamReader(fisAdministratorAddress));
						
		            	FileInputStream fisAdministratorBirthDate = new FileInputStream(administratorBirthDateFile);
						BufferedReader brAdministratorBirthDate = new BufferedReader(new InputStreamReader(fisAdministratorBirthDate));
						
		            	FileInputStream fisAdministratorEmailAddress = new FileInputStream(administratorEmailAddressFile);
						BufferedReader brAdministratorEmailAddress = new BufferedReader(new InputStreamReader(fisAdministratorEmailAddress));
						
		            	FileInputStream fisAdministratorPhoneNumber = new FileInputStream(administratorPhoneNumberFile);
						BufferedReader brAdministratorPhoneNumber = new BufferedReader(new InputStreamReader(fisAdministratorPhoneNumber));
						
		            	FileInputStream fisAdministratorIsActive = new FileInputStream(administratorIsActiveFile);
						BufferedReader brAdministratorIsActive = new BufferedReader(new InputStreamReader(fisAdministratorIsActive));
					){
					
					ArrayList<String> list = new ArrayList<>();
		            String line = brAdministratorFirstName.readLine();
		            while(line != null){
		            	list.add(line);
		                line = brAdministratorFirstName.readLine();
		            }  
		            
		            this.firstName = list.get(administratorId);
		            
		            line = brAdministratorLastName.readLine();
		        	list.clear();
		            while(line != null){
		            	list.add(line);
		                line = brAdministratorLastName.readLine();
		            }  
		            
		            this.lastName = list.get(administratorId);
		            
		            line = brAdministratorAddress.readLine();
		        	list.clear();
		            while(line != null){
		            	list.add(line);
		                line = brAdministratorAddress.readLine();
		            }  
		            
		            this.address = list.get(administratorId);
		            
		            line = brAdministratorBirthDate.readLine();
		        	list.clear();
		            while(line != null){
		            	list.add(line);
		                line = brAdministratorBirthDate.readLine();
		            }  
		            
		            this.birthDate = list.get(administratorId);
		            
		            line = brAdministratorEmailAddress.readLine();
		        	list.clear();
		            while(line != null){
		            	list.add(line);
		                line = brAdministratorEmailAddress.readLine();
		            }  
		            
		            this.emailAddress = list.get(administratorId);
		            
		            line = brAdministratorPhoneNumber.readLine();
		        	list.clear();
		            while(line != null){
		            	list.add(line);
		                line = brAdministratorPhoneNumber.readLine();
		            }  
		            
		            this.phoneNumber = list.get(administratorId);
		            
		            line = brAdministratorIsActive.readLine();
		        	list.clear();
		            while(line != null){
		            	list.add(line);
		                line = brAdministratorIsActive.readLine();
		            }  
		            
		            this.administratorIsActive = Integer.parseInt(list.get(administratorId));
		            	
		    		} catch (FileNotFoundException e) {

		    		} catch (IOException e) {
		    			
		    		}
			}
			
			public boolean administratorCheck(int administratorId) {
				boolean exists = false;
				try 
				(
				FileInputStream fisAdministratorId = new FileInputStream(administratorIdFile);
				BufferedReader brAdministratorId = new BufferedReader(new InputStreamReader(fisAdministratorId));
						
				) {
					String line = "";
					while((line = brAdministratorId.readLine()) != null) {
						
						if(Integer.parseInt(line) == administratorId) {
							exists = true;
						}
					}
				} catch (FileNotFoundException e) {
					
				} catch (IOException e) {
					
				}
				
				return exists;
			}
	
}
