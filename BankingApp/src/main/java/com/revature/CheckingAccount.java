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

public class CheckingAccount implements Account {
	private String checkingFilePath = "C:\\Users\\boydt\\Desktop\\Project0\\project-zero-BoydHarold\\BankingApp\\PseudoDB\\PseudoTables\\Account\\Checking\\";
	File customerIdFile = new File(checkingFilePath + "CustomerId.txt");
	File customerCheckingAccountBalanceFile = new File(checkingFilePath + "CustomerCheckingAccountBalance.txt");
	File customerAccountStatusFile = new File(checkingFilePath + "CustomerAccountStatus.txt");
	File customerAccountApprovalStatusFile = new File(checkingFilePath + "CustomerAccountApprovalStatus.txt");
	DecimalFormat df = new DecimalFormat("#0.00");

	@Override
	public void setupDefaultValues() {

		boolean exists = customerIdFile.exists();
		if (!exists) {
			try (FileOutputStream fosCustomerId = new FileOutputStream(customerIdFile, true);
					PrintStream psCustomerId = new PrintStream(fosCustomerId);

					FileOutputStream fosCustomerCheckingAccountBalance = new FileOutputStream(
							customerCheckingAccountBalanceFile, true);
					PrintStream psCustomerCheckingAccountBalance = new PrintStream(fosCustomerCheckingAccountBalance);

					FileOutputStream fosCustomerAccountStatus = new FileOutputStream(customerAccountStatusFile, true);
					PrintStream psCustomerAccountStatus = new PrintStream(fosCustomerAccountStatus);

					FileOutputStream fosCustomerApprovalStatus = new FileOutputStream(customerAccountApprovalStatusFile,
							true);
					PrintStream psCustomerApprovalStatus = new PrintStream(fosCustomerApprovalStatus);

			) {
				psCustomerId.println("0");
				psCustomerCheckingAccountBalance.println("0.00");
				psCustomerAccountStatus.println("0");
				psCustomerApprovalStatus.println("0");

			} catch (FileNotFoundException e) {

			} catch (IOException e) {

			}
		}
	}

	@Override
	public boolean withdrawl(int customerId, double amount) {
		double currentBalance = getBalance(customerId);
		double newBalance = currentBalance - amount;
		if (newBalance < 0) {
			System.out.println("You do not have enough money to withdrawl that much! You only have: $" + df.format(currentBalance)
					+ " remaining in your account!");
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
		SavingsAccount sa = new SavingsAccount();
		JointAccount ja = new JointAccount();

		if (destination.equals("Savings")) {
			withdrawl(customerId, amount);
			sa.deposit(customerId, amount);
			System.out.println("New Checking Account Balance: $" + df.format(getBalance(customerId)));
			System.out.println("New Savings Account Balance: $" + df.format(sa.getBalance(customerId)));
		} else if (destination.equals("Joint")) {
			withdrawl(customerId, amount);
			ja.deposit(customerId, amount);
			System.out.println("New Checking Account Balance: $" + df.format(getBalance(customerId)));
			System.out.println("New Joint Account Balance: $" + df.format(ja.getBalance(customerId)));
		}
	}

	@Override
	public void applyForAccount(int customerId) {
		String approvalStatus = getApprovalStatus(customerId);
		if (approvalStatus.equals("p")) {
			System.out.println("Approval already pending!");
		} else {
			
			if(approvalStatus.equals("d") || approvalStatus.equals("u")) {
			ArrayList<String> alOne = new ArrayList<String>();
			ArrayList<String> alTwo = new ArrayList<String>();
			ArrayList<String> alThree = new ArrayList<String>();

			try (FileInputStream fisGetAccountApprovalStatus = new FileInputStream(customerAccountApprovalStatusFile);
					BufferedReader brGetAccountApprovalStatus = new BufferedReader(
							new InputStreamReader(fisGetAccountApprovalStatus));) {
				String line = "";
				while ((line = brGetAccountApprovalStatus.readLine()) != null) {
					alOne.add(line);
				}

				for (int i = 0; i < alOne.size(); i++) {
					if (i < customerId) {
						alTwo.add(alOne.get(i));
					} else if (i > customerId) {
						alThree.add(alOne.get(i));
					}
				}

				alOne.clear();
				alOne.addAll(alTwo);
				alOne.add("p");
				alOne.addAll(alThree);

			} catch (FileNotFoundException e) {

			} catch (IOException e) {

			}

			customerAccountApprovalStatusFile.delete();

			try (FileOutputStream fosSetAccountApprovalStatus = new FileOutputStream(customerAccountApprovalStatusFile,
					true); PrintStream psSetAccountApprovalStatus = new PrintStream(fosSetAccountApprovalStatus);) {
				for (int i = 0; i < alOne.size(); i++) {
					psSetAccountApprovalStatus.println(alOne.get(i));
				}
			} catch (FileNotFoundException e) {

			} catch (IOException e) {

			}

			System.out.println("Applied for Checking Account!");
			} else {
				System.out.println("You already have an account!");
			}
		}
	}

	@Override
	public double getBalance(int customerId) {
		String balance = "0";
		try (FileInputStream fisGetBalance = new FileInputStream(customerCheckingAccountBalanceFile);
				BufferedReader brGetBalance = new BufferedReader(new InputStreamReader(fisGetBalance));) {
			String line = "";
			int lineNumber = 0;
			while (lineNumber <= customerId) {
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

		try (FileInputStream fisGetBalance = new FileInputStream(customerCheckingAccountBalanceFile);
				BufferedReader brGetBalance = new BufferedReader(new InputStreamReader(fisGetBalance));) {
			String line = "";
			while ((line = brGetBalance.readLine()) != null) {
				alOne.add(line);
			}

			for (int i = 0; i < alOne.size(); i++) {
				if (i < customerId) {
					alTwo.add(alOne.get(i));
				} else if (i > customerId) {
					alThree.add(alOne.get(i));
				}
			}

			String formattedBalance = df.format(balance);

			alOne.clear();
			alOne.addAll(alTwo);
			alOne.add(String.valueOf(formattedBalance));
			alOne.addAll(alThree);

		} catch (FileNotFoundException e) {

		} catch (IOException e) {

		}

		customerCheckingAccountBalanceFile.delete();

		try (FileOutputStream fosSetBalance = new FileOutputStream(customerCheckingAccountBalanceFile, true);
				PrintStream psSetBalance = new PrintStream(fosSetBalance);) {
			for (int i = 0; i < alOne.size(); i++) {
				psSetBalance.println(alOne.get(i));
			}
		} catch (FileNotFoundException e) {

		} catch (IOException e) {

		}

	}

	@Override
	public String getAccountStatus(int customerId) {
		String status = "0";
		try (FileInputStream fisGetAccountStatus = new FileInputStream(customerAccountStatusFile);
				BufferedReader brGetAccountStatus = new BufferedReader(new InputStreamReader(fisGetAccountStatus));) {
			String line = "";
			int lineNumber = 0;
			while (lineNumber <= customerId) {
				line = brGetAccountStatus.readLine();
				status = line;
				lineNumber++;
			}
		} catch (FileNotFoundException e) {

		} catch (IOException e) {

		}
		return status;
	}

	@Override
	public String getApprovalStatus(int customerId) {
		String status = "";
		try (FileInputStream fisGetAccountApprovalStatus = new FileInputStream(customerAccountApprovalStatusFile);
				BufferedReader brGetAccountApprovalStatus = new BufferedReader(
						new InputStreamReader(fisGetAccountApprovalStatus));) {
			String line = "";
			int lineNumber = 0;
			while (lineNumber <= customerId) {
				line = brGetAccountApprovalStatus.readLine();
				status = line;
				lineNumber++;
			}
		} catch (FileNotFoundException e) {

		} catch (IOException e) {

		}
		return status;
	}

	@Override
	public int getPosition(int customerId) {
		// TODO Auto-generated method stub
		return 0;
	}

}
