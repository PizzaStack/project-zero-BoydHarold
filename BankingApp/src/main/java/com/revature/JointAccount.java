package com.revature;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class JointAccount implements Account{
	private String jointFilePath = "C:\\Users\\boydt\\eclipse-workspace\\BankingApp\\PseudoDB\\PseudoTables\\Account\\Joint\\";
	File customerIdFile = new File(jointFilePath + "CustomerId.txt");
	File customerJointAccountBalanceFile = new File(jointFilePath + "CustomerJointAccountBalance.txt");
	File customerAccountStatusFile = new File(jointFilePath + "CustomerAccountStatus.txt");
	
	@Override
	public void setupDefaultValues() {
		boolean exists = customerIdFile.exists();
		if(!exists) {
			try(
					FileOutputStream fosCustomerId = new FileOutputStream(customerIdFile, true);
					PrintStream psCustomerId = new PrintStream(fosCustomerId);
					
					FileOutputStream fosCustomerJointAccountBalance = new FileOutputStream(customerJointAccountBalanceFile, true);
					PrintStream psCustomerJointAccountBalance = new PrintStream(fosCustomerJointAccountBalance);
					
					FileOutputStream fosCustomerAccountStatus = new FileOutputStream(customerAccountStatusFile, true);
					PrintStream psCustomerAccountStatus = new PrintStream(fosCustomerAccountStatus);
					
			){
				psCustomerId.println("0");
				psCustomerJointAccountBalance.println("0");
				psCustomerAccountStatus.println("0");
				
			} catch (FileNotFoundException e) {
				
			} catch (IOException e) {
				
			}
		}
	}
	
	@Override
	public boolean withdrawl(int customerId, double amount) {
		double currentBalance = getBalance(customerId);
		double newBalance = currentBalance - amount;
		if(newBalance < 0) {
			System.out.println("You do not have enough money to withdrawl that much! You only have: $" + currentBalance + " remaining in your account!");
			return false;
		} else {
		setBalance(customerId, newBalance);
		return true;
		}
	}

	@Override
	public void deposit(int customerId, double amount) {
		double currentBalance = getBalance(customerId);
		double newBalance = currentBalance + amount;
		setBalance(customerId, newBalance);
	}

	@Override
	public void transfer(int customerId, double amount, String source, String destination) {
		CheckingAccount ca = new CheckingAccount();
		SavingsAccount sa = new SavingsAccount();
		
		if(destination.equals("Checking")) {
			withdrawl(customerId, amount);
			ca.deposit(customerId, amount);
			System.out.println("New Joint Account Balance: $" + getBalance(customerId));
			System.out.println("New Checking Account Balance: $" + ca.getBalance(customerId));
		} else if(destination.equals("Savings")) {
			withdrawl(customerId, amount);
			sa.deposit(customerId, amount);
			System.out.println("New Joint Account Balance: $" + getBalance(customerId));
			System.out.println("New Savings Account Balance: $" + sa.getBalance(customerId));
		}
	}

	@Override
	public void applyForAccount(int customerId) {
		
	}

	@Override
	public double getBalance(int customerId) {
		String balance = "0";
		try (
		FileInputStream fisGetBalance = new FileInputStream(customerJointAccountBalanceFile);
		BufferedReader brGetBalance = new BufferedReader(new InputStreamReader(fisGetBalance));
		) {
			String line = "";
			int lineNumber = 0;
			while(lineNumber <= customerId) {
				line = brGetBalance.readLine();
				balance = line;
				lineNumber++;
			}
		} catch (FileNotFoundException e) {
			
		} catch (IOException e) {
			
		}
		return Double.parseDouble(balance);
	}

	@Override
	public void setBalance(int customerId, double balance) {
		ArrayList<String> alOne = new ArrayList<String>();
		ArrayList<String> alTwo = new ArrayList<String>();
		ArrayList<String> alThree = new ArrayList<String>();
		
		try (	
				FileInputStream fisGetBalance = new FileInputStream(customerJointAccountBalanceFile);
				BufferedReader brGetBalance = new BufferedReader(new InputStreamReader(fisGetBalance));
		) {
			String line = "";
			while((line = brGetBalance.readLine()) != null) {
				alOne.add(line);
			}
			
			for(int i = 0; i < alOne.size(); i++) {
				if(i < customerId) {
					alTwo.add(alOne.get(i));
				} else if (i > customerId){
					alThree.add(alOne.get(i));
				}
			}
			
			DecimalFormat df = new DecimalFormat("##.00");
			String formattedBalance = df.format(balance);
			
			alOne.clear();
			alOne.addAll(alTwo);
			alOne.add(String.valueOf(formattedBalance));
			alOne.addAll(alThree);
			
		} catch (FileNotFoundException e) {
			
		} catch (IOException e) {
			
		}
		
		customerJointAccountBalanceFile.delete();
		
		try(				
				FileOutputStream fosSetBalance = new FileOutputStream(customerJointAccountBalanceFile, true);
				PrintStream psSetBalance = new PrintStream(fosSetBalance);
			) {
			for(int i = 0; i < alOne.size(); i++) {
				psSetBalance.println(alOne.get(i));
			}
		} catch (FileNotFoundException e) {
			
		} catch (IOException e) {
			
		}
	}

}
