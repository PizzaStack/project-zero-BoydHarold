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

public class Approval {
	private String checkingFilePath = "C:\\Users\\boydt\\Desktop\\Project0\\project-zero-BoydHarold\\BankingApp\\PseudoDB\\PseudoTables\\Account\\Checking\\";
	File checkingCustomerIdFile = new File(checkingFilePath + "CustomerId.txt");
	File checkingCustomerAccountStatusFile = new File(checkingFilePath + "CustomerAccountStatus.txt");
	File checkingCustomerAccountApprovalStatusFile = new File(checkingFilePath + "CustomerAccountApprovalStatus.txt");
	private String savingsFilePath = "C:\\Users\\boydt\\Desktop\\Project0\\project-zero-BoydHarold\\BankingApp\\PseudoDB\\PseudoTables\\Account\\Savings\\";
	File savingsCustomerIdFile = new File(savingsFilePath + "CustomerId.txt");
	File savingsCustomerAccountStatusFile = new File(savingsFilePath + "CustomerAccountStatus.txt");
	File savingsCustomerAccountApprovalStatusFile = new File(savingsFilePath + "CustomerAccountApprovalStatus.txt");
	private String jointFilePath = "C:\\Users\\boydt\\Desktop\\Project0\\project-zero-BoydHarold\\BankingApp\\PseudoDB\\PseudoTables\\Account\\Joint\\";
	File jointCustomerIdFile1 = new File(jointFilePath + "CustomerId1.txt");
	File jointCustomerIdFile2 = new File(jointFilePath + "CustomerId2.txt");
	File jointCustomerAccountStatusFile = new File(jointFilePath + "CustomerAccountStatus.txt");
	File jointCustomerAccountApprovalStatusFile = new File(jointFilePath + "CustomerAccountApprovalStatus.txt");
	File customerJointAccountBalanceFile = new File(jointFilePath + "CustomerJointAccountBalance.txt");
	File approvalStatusFileLocation;
	File accountStatusLocation;
	CheckingAccount ca = new CheckingAccount();
	SavingsAccount sa = new SavingsAccount();
	JointAccount ja = new JointAccount();
	
	
	public void listPendingChecking() {
		try
		(
				FileInputStream fisChecking = new FileInputStream(checkingCustomerAccountApprovalStatusFile);
				BufferedReader brChecking = new BufferedReader(new InputStreamReader(fisChecking));
		) {
			String list = "";
			
			int id = 0;
			
			while((list = brChecking.readLine()) != null) {
				
				if(list.equals("p")) {
					System.out.println("Customer Id: " + id + " \nStatus: Pending\n");
				}
				id++;
			}
			
		} catch (FileNotFoundException e) {
			
		} catch (IOException e) {
			
		}
	}
	
	public void listPendingSavings() {
		try
		(
				FileInputStream fisSavings = new FileInputStream(savingsCustomerAccountApprovalStatusFile);
				BufferedReader brSavings = new BufferedReader(new InputStreamReader(fisSavings));
		) {
			String list = "";
			
			int id = 0;
			
			while((list = brSavings.readLine()) != null) {
				
				if(list.equals("p")) {
					System.out.println("Customer Id: " + id + " \nStatus: Pending\n");
				}
				id++;
			}
			
		} catch (FileNotFoundException e) {
			
		} catch (IOException e) {
			
		}
	}
	
	public void listPendingJoint() {
		try
		(
				FileInputStream fisJoint = new FileInputStream(jointCustomerAccountApprovalStatusFile);
				BufferedReader brJoint = new BufferedReader(new InputStreamReader(fisJoint));
				
				
		) {
			String list = "";
			
			int id = 0;
			
			while((list = brJoint.readLine()) != null) {
				
				if(list.equals("p")) {
					System.out.println("Customer Id1: " + getCustomerId1(id) + "\nCustomer Id2: " + getCustomerId2(id) + "\nStatus: Pending\n");
				}
				id++;
			}
			
		} catch (FileNotFoundException e) {
			
		} catch (IOException e) {
			
		}
	}
	
	public void approve(int customerId, String accountType) {
		String approvalStatus = "u";

		
		if(accountType.equals("1")) {
			approvalStatusFileLocation = checkingCustomerAccountApprovalStatusFile;
			accountStatusLocation = checkingCustomerAccountStatusFile;
			approvalStatus = ca.getApprovalStatus(customerId);
		} else if(accountType.equals("2")) {
			approvalStatusFileLocation = savingsCustomerAccountApprovalStatusFile;
			accountStatusLocation = savingsCustomerAccountStatusFile;
			approvalStatus = sa.getApprovalStatus(customerId);
		} else if(accountType.equals("3")) {
			approvalStatusFileLocation = jointCustomerAccountApprovalStatusFile;
			accountStatusLocation = jointCustomerAccountStatusFile;
			customerId = ja.getPosition(customerId);
			approvalStatus = ja.getApprovalStatus(customerId);
		}
		
		
		if(approvalStatus.equals("p")) {
		
			ArrayList<String> alOne = new ArrayList<String>();
			ArrayList<String> alTwo = new ArrayList<String>();
			ArrayList<String> alThree = new ArrayList<String>();

			try (FileInputStream fisGetAccountApprovalStatus = new FileInputStream(approvalStatusFileLocation);
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
				alOne.add("a");
				alOne.addAll(alThree);

			} catch (FileNotFoundException e) {

			} catch (IOException e) {

			}

			approvalStatusFileLocation.delete();

			try (FileOutputStream fosSetAccountApprovalStatus = new FileOutputStream(approvalStatusFileLocation,
					true); PrintStream psSetAccountApprovalStatus = new PrintStream(fosSetAccountApprovalStatus);) {
				for (int i = 0; i < alOne.size(); i++) {
					psSetAccountApprovalStatus.println(alOne.get(i));
				}
			} catch (FileNotFoundException e) {

			} catch (IOException e) {

			}

			System.out.println("Account approved!");
			
			alOne.clear();
			alTwo.clear();
			alThree.clear();

			try (FileInputStream fisGetAccountApprovalStatus = new FileInputStream(accountStatusLocation);
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
				alOne.add("1");
				alOne.addAll(alThree);

			} catch (FileNotFoundException e) {

			} catch (IOException e) {

			}

			accountStatusLocation.delete();

			try (FileOutputStream fosSetAccountApprovalStatus = new FileOutputStream(accountStatusLocation,
					true); PrintStream psSetAccountApprovalStatus = new PrintStream(fosSetAccountApprovalStatus);) {
				for (int i = 0; i < alOne.size(); i++) {
					psSetAccountApprovalStatus.println(alOne.get(i));
				}
			} catch (FileNotFoundException e) {

			} catch (IOException e) {

			}

			
		} else {
			System.out.println("Approval not pending for customer!");
		}
	}
	
	public void deny(int customerId, String accountType) {
		String approvalStatus = "u";
		boolean jointAccount = false;
		
		if(accountType.equals("1")) {
			approvalStatusFileLocation = checkingCustomerAccountApprovalStatusFile;
			approvalStatus = ca.getApprovalStatus(customerId);
		} else if(accountType.equals("2")) {
			approvalStatusFileLocation = savingsCustomerAccountApprovalStatusFile;
			approvalStatus = sa.getApprovalStatus(customerId);
		} else if(accountType.equals("3")) {
			approvalStatusFileLocation = jointCustomerAccountApprovalStatusFile;
			customerId = ja.getPosition(customerId);
			approvalStatus = ja.getApprovalStatus(customerId);
			jointAccount = true;
		}
		
		
		
		
		if(approvalStatus.equals("p")) {
		
			ArrayList<String> alOne = new ArrayList<String>();
			ArrayList<String> alTwo = new ArrayList<String>();
			ArrayList<String> alThree = new ArrayList<String>();
			
			ArrayList<String> alFour = new ArrayList<String>();
			ArrayList<String> alFive = new ArrayList<String>();
			ArrayList<String> alSix = new ArrayList<String>();
			
			ArrayList<String> alSeven = new ArrayList<String>();
			ArrayList<String> alEight = new ArrayList<String>();
			ArrayList<String> alNine = new ArrayList<String>();
			
			ArrayList<String> alTen = new ArrayList<String>();
			ArrayList<String> alEleven = new ArrayList<String>();
			ArrayList<String> alTwelve = new ArrayList<String>();
			
			ArrayList<String> alThirteen = new ArrayList<String>();
			ArrayList<String> alFourteen = new ArrayList<String>();
			ArrayList<String> alFifteen = new ArrayList<String>();
			

			try (FileInputStream fisGetAccountApprovalStatus = new FileInputStream(approvalStatusFileLocation);
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
				alOne.add("d");
				alOne.addAll(alThree);

			} catch (FileNotFoundException e) {

			} catch (IOException e) {

			}

			approvalStatusFileLocation.delete();

			try (FileOutputStream fosSetAccountApprovalStatus = new FileOutputStream(approvalStatusFileLocation,
					true); PrintStream psSetAccountApprovalStatus = new PrintStream(fosSetAccountApprovalStatus);) {
				for (int i = 0; i < alOne.size(); i++) {
					psSetAccountApprovalStatus.println(alOne.get(i));
				}
			} catch (FileNotFoundException e) {

			} catch (IOException e) {

			}

			System.out.println("Account denied!");
			
			if(jointAccount) {
				
				alOne.clear();
				alTwo.clear();
				alThree.clear();

				try (FileInputStream fisGetCustomerId1 = new FileInputStream(jointCustomerIdFile1);
						BufferedReader brGetCustomerId1 = new BufferedReader(
								new InputStreamReader(fisGetCustomerId1));
						
						FileInputStream fisGetCustomerId2 = new FileInputStream(jointCustomerIdFile2);
						BufferedReader brGetCustomerId2 = new BufferedReader(
								new InputStreamReader(fisGetCustomerId2));
						
						FileInputStream fisGetAccountStatus = new FileInputStream(jointCustomerAccountStatusFile);
						BufferedReader brGetAccountStatus = new BufferedReader(
								new InputStreamReader(fisGetAccountStatus));
						
						FileInputStream fisGetAccountApprovalStatus = new FileInputStream(jointCustomerAccountApprovalStatusFile);
						BufferedReader brGetAccountApprovalStatus = new BufferedReader(
								new InputStreamReader(fisGetAccountApprovalStatus));
						
						FileInputStream fisGetAccountBalance = new FileInputStream(customerJointAccountBalanceFile);
						BufferedReader brGetAccountBalance = new BufferedReader(
								new InputStreamReader(fisGetAccountBalance));
						
						
						
						
						) {
					String line = "";
					while ((line = brGetCustomerId1.readLine()) != null) {
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
					alOne.addAll(alThree);

					line = "";
					while ((line = brGetCustomerId2.readLine()) != null) {
						alFour.add(line);
					}

					for (int i = 0; i < alFour.size(); i++) {
						if (i < customerId) {
							alFive.add(alFour.get(i));
						} else if (i > customerId) {
							alSix.add(alFour.get(i));
						}
					}

					alFour.clear();
					alFour.addAll(alFive);
					alFour.addAll(alSix);
					
					line = "";
					while ((line = brGetAccountStatus.readLine()) != null) {
						alSeven.add(line);
					}

					for (int i = 0; i < alSeven.size(); i++) {
						if (i < customerId) {
							alEight.add(alSeven.get(i));
						} else if (i > customerId) {
							alNine.add(alSeven.get(i));
						}
					}

					alSeven.clear();
					alSeven.addAll(alEight);
					alSeven.addAll(alNine);
				
					
					line = "";
					while ((line = brGetAccountApprovalStatus.readLine()) != null) {
						alTen.add(line);
					}

					for (int i = 0; i < alTen.size(); i++) {
						if (i < customerId) {
							alEleven.add(alTen.get(i));
						} else if (i > customerId) {
							alTwelve.add(alTen.get(i));
						}
					}

					alTen.clear();
					alTen.addAll(alEleven);
					alTen.addAll(alTwelve);
					
					line = "";
					while ((line = brGetAccountBalance.readLine()) != null) {
						alThirteen.add(line);
					}

					for (int i = 0; i < alThirteen.size(); i++) {
						if (i < customerId) {
							alFourteen.add(alThirteen.get(i));
						} else if (i > customerId) {
							alFifteen.add(alThirteen.get(i));
						}
					}

					alThirteen.clear();
					alThirteen.addAll(alFourteen);
					alThirteen.addAll(alFifteen);

				} catch (FileNotFoundException e) {

				} catch (IOException e) {

				}

				approvalStatusFileLocation.delete();

				
				try (FileOutputStream fosSetCustomerId1 = new FileOutputStream(jointCustomerIdFile1,
						true); PrintStream psSetCustomerId1 = new PrintStream(fosSetCustomerId1);
						
						FileOutputStream fosSetCustomerId2 = new FileOutputStream(jointCustomerIdFile2,
								true); PrintStream psSetCustomerId2 = new PrintStream(fosSetCustomerId2);
						
						FileOutputStream fosSetCustomerAccountStatusFile = new FileOutputStream(jointCustomerAccountStatusFile,
								true); PrintStream psSetCustomerAccountStatusFile = new PrintStream(fosSetCustomerAccountStatusFile);
						
						FileOutputStream fosSetAccountApprovalStatus = new FileOutputStream(jointCustomerAccountApprovalStatusFile,
								true); PrintStream psSetAccountApprovalStatus = new PrintStream(fosSetAccountApprovalStatus);
						
						FileOutputStream fosSetJointAccountBalance = new FileOutputStream(customerJointAccountBalanceFile,
								true); PrintStream psSetJointAccountBalance = new PrintStream(fosSetJointAccountBalance);
						
						) {
					for (int i = 0; i < alOne.size(); i++) {
						psSetAccountApprovalStatus.println(alOne.get(i));
					}
					
					for (int i = 0; i < alFour.size(); i++) {
						psSetAccountApprovalStatus.println(alFour.get(i));
					}
					
					for (int i = 0; i < alSeven.size(); i++) {
						psSetAccountApprovalStatus.println(alSeven.get(i));
					}
					
					for (int i = 0; i < alEleven.size(); i++) {
						psSetAccountApprovalStatus.println(alEleven.get(i));
					}
					
					for (int i = 0; i < alFourteen.size(); i++) {
						psSetAccountApprovalStatus.println(alFourteen.get(i));
					}
				} catch (FileNotFoundException e) {

				} catch (IOException e) {

				}
				
			}
			
		} else {
			System.out.println("Approval not pending for customer!");
		}
	}
	
	public int getCustomerId1(int position) {
		int customerId1 = 0;
		
		try
		(
				FileInputStream fisCustomerId1 = new FileInputStream(jointCustomerIdFile1);
				BufferedReader brCustomerId1 = new BufferedReader(new InputStreamReader(fisCustomerId1));
		) {
			String line = "";
			int count = 0;
			while((line = brCustomerId1.readLine()) != null) {
				if(count == position) {
					customerId1 = Integer.parseInt(line);
				}
				count++;
			}
		} catch (FileNotFoundException e) {
			
		} catch (IOException e) {
			
		}
		
		return customerId1;
	}
	
	public int getCustomerId2(int position) {
		int customerId2 = 0;
		try
		(
				FileInputStream fisCustomerId2 = new FileInputStream(jointCustomerIdFile2);
				BufferedReader brCustomerId2 = new BufferedReader(new InputStreamReader(fisCustomerId2));
		) {
			String line = "";
			int count = 0;
			while((line = brCustomerId2.readLine()) != null) {
				if(count == position) {
					customerId2 = Integer.parseInt(line);
				}
				count++;
			}
		} catch (FileNotFoundException e) {
			
		} catch (IOException e) {
			
		}
		
		return customerId2;
	}
	
	
}
