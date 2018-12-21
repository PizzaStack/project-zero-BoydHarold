package com.revature;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import com.revature.dao.ApprovalDao;
import com.revature.dao.CheckingAccountDao;
import com.revature.dao.JointAccountDao;
import com.revature.dao.SavingsAccountDao;

public class Approval {
	private int customerId;
	private int customerId2;
	private int status;
	private String approvalStatus;
	private String firstName;
	private String lastName;
	private String firstName2;
	private String lastName2;
	CheckingAccount ca = new CheckingAccount();
	SavingsAccount sa = new SavingsAccount();
	JointAccount ja = new JointAccount();
	
	public Approval(int customerId, int status, String approvalStatus, String firstName, String lastName) {
		this.customerId = customerId;
		this.status = status;
		this.approvalStatus = approvalStatus;
		this.firstName = firstName;
		this.lastName = lastName;
	}
	
	public Approval(int customerId1, int customerId2, int status, String approvalStatus, String firstName, String lastName, String firstName2, String lastName2) {
		this.customerId = customerId1;
		this.customerId2 = customerId2;
		this.status = status;
		this.approvalStatus = approvalStatus;
		this.firstName = firstName;
		this.lastName = lastName;
		this.firstName2 = firstName2;
		this.lastName2 = lastName2;
	}
	
	public Approval() {
		
	}
	
	public void listPendingChecking(Connection connection) {
		ApprovalDao approvalDao = new ApprovalDao(connection);
		List<Approval> checkingAccounts = approvalDao.getAllCheckingAccounts();
		
		for(Approval checkingAccount : checkingAccounts) {
			if(checkingAccount.getApprovalStatus().equals("p")) {
			System.out.println("\nCustomer Id: " + checkingAccount.getCustomerId());
			System.out.println("Full Name: " + checkingAccount.getFirstName() + " " + checkingAccount.getLastName());
			System.out.println("Approval Status: Pending");
			}
		}

	}
	
	public void listPendingSavings(Connection connection) {
		ApprovalDao approvalDao = new ApprovalDao(connection);
		List<Approval> savingsAccounts = approvalDao.getAllSavingsAccounts();
		
		for(Approval savingsAccount : savingsAccounts) {
			if(savingsAccount.getApprovalStatus().equals("p")) {
			System.out.println("\nCustomer Id: " + savingsAccount.getCustomerId());
			System.out.println("Full Name: " + savingsAccount.getFirstName() + " " + savingsAccount.getLastName());
			System.out.println("Approval Status: Pending");
			}
		}
	}
	
	public void listPendingJoint(Connection connection) {
		ApprovalDao approvalDao = new ApprovalDao(connection);
		List<Approval> jointAccounts = approvalDao.getAllJointAccounts();
		
		for(Approval jointAccount : jointAccounts) {
			if(jointAccount.getApprovalStatus().equals("p")) {
			System.out.println("\nCustomer Ids: '" + jointAccount.getCustomerId() + "' and '" + jointAccount.getCustomerId2() + "'");
			System.out.println("Full Names: '" + jointAccount.getFirstName() + " " + jointAccount.getLastName() + "' and '" + jointAccount.getFirstName2() + " " + jointAccount.getLastName2() + "'");
			System.out.println("Approval Status: Pending");
			}
		}
	}
	
	public void approve(int customerId, String accountType, Connection connection) {
		String approvalStatus = "u";

		ApprovalDao approvalDao = new ApprovalDao(connection);



		
		Approval checkingAccountChosen = new Approval();
		Approval savingsAccountChosen = new Approval();
		Approval jointAccountChosen = new Approval();
		
		
		if(accountType.equals("1")) {
			List<Approval> checkingAccounts = approvalDao.getAllCheckingAccounts();
			for(Approval checkingAccount : checkingAccounts) {
				if(checkingAccount.getCustomerId() == customerId) {
					checkingAccountChosen.setCustomerId(checkingAccount.getCustomerId());
					checkingAccountChosen.setFirstName(checkingAccount.getFirstName());
					checkingAccountChosen.setLastName(checkingAccount.getLastName());
					checkingAccountChosen.setStatus(checkingAccount.getStatus());
					checkingAccountChosen.setApprovalStatus(checkingAccount.getApprovalStatus());
				}
			}
			approvalStatus = checkingAccountChosen.getApprovalStatus();
		} else if(accountType.equals("2")) {
			List<Approval> savingsAccounts = approvalDao.getAllSavingsAccounts();
			for(Approval savingsAccount : savingsAccounts) {
				if(savingsAccount.getCustomerId() == customerId) {
					savingsAccountChosen.setCustomerId(savingsAccount.getCustomerId());
					savingsAccountChosen.setFirstName(savingsAccount.getFirstName());
					savingsAccountChosen.setLastName(savingsAccount.getLastName());
					savingsAccountChosen.setStatus(savingsAccount.getStatus());
					savingsAccountChosen.setApprovalStatus(savingsAccount.getApprovalStatus());
				}
			}
			approvalStatus = savingsAccountChosen.getApprovalStatus();
		} else if(accountType.equals("3")) {
			List<Approval> jointAccounts = approvalDao.getAllJointAccounts();
			for(Approval jointAccount : jointAccounts) {
				if(jointAccount.getCustomerId() == customerId) {
					jointAccountChosen.setCustomerId(jointAccount.getCustomerId());
					jointAccountChosen.setFirstName(jointAccount.getFirstName());
					jointAccountChosen.setLastName(jointAccount.getLastName());
					jointAccountChosen.setFirstName2(jointAccount.getFirstName2());
					jointAccountChosen.setLastName2(jointAccount.getLastName2());
					jointAccountChosen.setStatus(jointAccount.getStatus());
					jointAccountChosen.setApprovalStatus(jointAccount.getApprovalStatus());
				}
			}
			approvalStatus = jointAccountChosen.getApprovalStatus();
		}
		
		
		if(approvalStatus.equals("p")) {
		
			if(accountType.equals("1")) {
				checkingAccountChosen.setStatus(1);
				checkingAccountChosen.setApprovalStatus("a");
				approvalDao.updateCheckingAccount(checkingAccountChosen);
			} else if(accountType.equals("2")) {
				savingsAccountChosen.setStatus(1);
				savingsAccountChosen.setApprovalStatus("a");
				approvalDao.updateSavingsAccount(savingsAccountChosen);
			} else if(accountType.equals("3")) {
				jointAccountChosen.setStatus(1);
				jointAccountChosen.setApprovalStatus("a");
				approvalDao.updateJointAccount(jointAccountChosen);
			}
			
			System.out.println("Account approved!");
			
		} else {
			System.out.println("Approval not pending for customer!");
		}
	}
	
	public void deny(int customerId, String accountType, Connection connection) {
		String approvalStatus = "u";

		ApprovalDao approvalDao = new ApprovalDao(connection);



		
		Approval checkingAccountChosen = new Approval();
		Approval savingsAccountChosen = new Approval();
		Approval jointAccountChosen = new Approval();
		

		
		if(accountType.equals("1")) {
			List<Approval> checkingAccounts = approvalDao.getAllCheckingAccounts();
			for(Approval checkingAccount : checkingAccounts) {
				if(checkingAccount.getCustomerId() == customerId) {
					checkingAccountChosen.setCustomerId(checkingAccount.getCustomerId());
					checkingAccountChosen.setFirstName(checkingAccount.getFirstName());
					checkingAccountChosen.setLastName(checkingAccount.getLastName());
					checkingAccountChosen.setStatus(checkingAccount.getStatus());
					checkingAccountChosen.setApprovalStatus(checkingAccount.getApprovalStatus());
				}
			}
			approvalStatus = checkingAccountChosen.getApprovalStatus();
		} else if(accountType.equals("2")) {
			List<Approval> savingsAccounts = approvalDao.getAllSavingsAccounts();
			for(Approval savingsAccount : savingsAccounts) {
				if(savingsAccount.getCustomerId() == customerId) {
					savingsAccountChosen.setCustomerId(savingsAccount.getCustomerId());
					savingsAccountChosen.setFirstName(savingsAccount.getFirstName());
					savingsAccountChosen.setLastName(savingsAccount.getLastName());
					savingsAccountChosen.setStatus(savingsAccount.getStatus());
					savingsAccountChosen.setApprovalStatus(savingsAccount.getApprovalStatus());
				}
			}
			approvalStatus = savingsAccountChosen.getApprovalStatus();
		} else if(accountType.equals("3")) {
			List<Approval> jointAccounts = approvalDao.getAllJointAccounts();
			for(Approval jointAccount : jointAccounts) {
				if(jointAccount.getCustomerId() == customerId) {
					jointAccountChosen.setCustomerId(jointAccount.getCustomerId());
					jointAccountChosen.setFirstName(jointAccount.getFirstName());
					jointAccountChosen.setLastName(jointAccount.getLastName());
					jointAccountChosen.setFirstName2(jointAccount.getFirstName2());
					jointAccountChosen.setLastName2(jointAccount.getLastName2());
					jointAccountChosen.setStatus(jointAccount.getStatus());
					jointAccountChosen.setApprovalStatus(jointAccount.getApprovalStatus());
				}
			}
			approvalStatus = jointAccountChosen.getApprovalStatus();
		}
		

		if(approvalStatus.equals("p")) {
			if(accountType.equals("1")) {
				checkingAccountChosen.setStatus(0);
				checkingAccountChosen.setApprovalStatus("d");
				approvalDao.updateCheckingAccount(checkingAccountChosen);
			} else if(accountType.equals("2")) {
				savingsAccountChosen.setStatus(0);
				savingsAccountChosen.setApprovalStatus("d");
				approvalDao.updateSavingsAccount(savingsAccountChosen);
			} else if(accountType.equals("3")) {
				approvalDao.deleteJointAccount(jointAccountChosen);
			}
			
			System.out.println("Approval denied!");
		} else {
			System.out.println("Approval not pending for customer!");
		}
	}

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
	
	public int getCustomerId2() {
		return customerId2;
	}

	public void setCustomerId2(int customerId2) {
		this.customerId2 = customerId2;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getApprovalStatus() {
		return approvalStatus;
	}

	public void setApprovalStatus(String approvalStatus) {
		this.approvalStatus = approvalStatus;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getFirstName2() {
		return firstName2;
	}

	public void setFirstName2(String firstName2) {
		this.firstName2 = firstName2;
	}

	public String getLastName2() {
		return lastName2;
	}

	public void setLastName2(String lastName2) {
		this.lastName2 = lastName2;
	}

	
	
}
