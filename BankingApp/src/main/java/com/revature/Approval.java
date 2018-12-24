package com.revature;

import java.sql.Connection;
import java.util.List;

import com.revature.dao.ApprovalDao;
import com.revature.dao.CustomerDao;

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
		int pendingCount = 0;
		
		for(Approval checkingAccount : checkingAccounts) {
			if(checkingAccount.getApprovalStatus().equals("p")) {
				pendingCount++;
			}
		}
		
		if(pendingCount>0) {
		System.out.println("\nDisplaying pending Checking Accounts:");
		for(Approval checkingAccount : checkingAccounts) {
			if(checkingAccount.getApprovalStatus().equals("p")) {
			System.out.println("\nCustomer Id: " + checkingAccount.getCustomerId());
			System.out.println("Full Name: " + checkingAccount.getFirstName() + " " + checkingAccount.getLastName());
			System.out.println("Approval Status: Pending");
			}
		}
		
		} else {
			System.out.println("\nThere are not pending Checking Accounts!");
		}

	}
	
	public void listPendingSavings(Connection connection) {
		ApprovalDao approvalDao = new ApprovalDao(connection);
		List<Approval> savingsAccounts = approvalDao.getAllSavingsAccounts();
		int pendingCount = 0;
		
		for(Approval savingsAccount : savingsAccounts) {
			if(savingsAccount.getApprovalStatus().equals("p")) {
				pendingCount++;
			}
		}
		
		if(pendingCount>0) {
		System.out.println("\nDisplaying pending Savings Accounts:");
		
		for(Approval savingsAccount : savingsAccounts) {
			if(savingsAccount.getApprovalStatus().equals("p")) {
			System.out.println("\nCustomer Id: " + savingsAccount.getCustomerId());
			System.out.println("Full Name: " + savingsAccount.getFirstName() + " " + savingsAccount.getLastName());
			System.out.println("Approval Status: Pending");
			}
		}
		
		} else {
			System.out.println("\nThere are not pending Savings Accounts!");
		}
	}
	
	public void listPendingJoint(Connection connection) {
		ApprovalDao approvalDao = new ApprovalDao(connection);
		List<Approval> jointAccounts = approvalDao.getAllJointAccounts();
		int pendingCount = 0;
		
		for(Approval jointAccount : jointAccounts) {
			if(jointAccount.getApprovalStatus().equals("p")) {
				pendingCount++;
			}
		}
		
		if(pendingCount>0) {
		System.out.println("\nDisplaying pending Joint Accounts:");
		
		for(Approval jointAccount : jointAccounts) {
			if(jointAccount.getApprovalStatus().equals("p")) {
			System.out.println("\nCustomer Ids: '" + jointAccount.getCustomerId() + "' and '" + jointAccount.getCustomerId2() + "'");
			System.out.println("Full Names: '" + jointAccount.getFirstName() + " " + jointAccount.getLastName() + "' and '" + jointAccount.getFirstName2() + " " + jointAccount.getLastName2() + "'");
			System.out.println("Approval Status: Pending");
			}
		}
		} else {
			System.out.println("\nThere are not pending Joint Accounts!");
		}
	}
	
	public void listPendingEmployees(Connection connection) {
		ApprovalDao approvalDao = new ApprovalDao(connection);
		int pendingCount = 0;
		List<Employee> employees = approvalDao.getAllEmployees();
		
		for(Employee employee : employees) {
			if(employee.getApprovalStatus().equals("p")) {
				pendingCount++;
			}
		}
		
		if(pendingCount>0) {
			System.out.println("\nDisplaying pending Employee accounts:");
		for(Employee employee : employees) {
			if(employee.getApprovalStatus().equals("p")) {
				System.out.println("\nEmployee id: " + employee.getEmployeeId());
				System.out.println("Employee name: " + employee.getEmployeeFirstName() + " " + employee.getEmployeeLastName());
				System.out.println("Approval Status: Pending");
			}
		}
		} else {
			System.out.println("There are no Employee accounts currently pending!");
		}
	}
	
	public void listPendingAdministrators(Connection connection) {
		ApprovalDao approvalDao = new ApprovalDao(connection);
		int pendingCount = 0;
		List<Administrator> administrators = approvalDao.getAllAdministrators();
		
		for(Administrator administrator : administrators) {
			if(administrator.getApprovalStatus().equals("p")) {
				pendingCount++;
			}
		}
		
		if(pendingCount>0) {
			System.out.println("\nDisplaying pending Administrator accounts:");
		for(Administrator administrator : administrators) {
			if(administrator.getApprovalStatus().equals("p")) {
				System.out.println("\nAdministrator id: " + administrator.getAdministratorId());
				System.out.println("Administrator name: " + administrator.getAdministratorFirstName() + " " + administrator.getAdministratorLastName());
				System.out.println("Approval Status: Pending");
			}
		}
		} else {
			System.out.println("There are no Administrator accounts currently pending!");
		}
	}
	
	public void approve(int customerId, String accountType, Connection connection) {
		String approvalStatus = "u";
		boolean customerExists = false;

		CustomerDao customerDao = new CustomerDao(connection);
		ApprovalDao approvalDao = new ApprovalDao(connection);
		
		
		
		Approval checkingAccountChosen = new Approval();
		Approval savingsAccountChosen = new Approval();
		Approval jointAccountChosen = new Approval();
		
		int pendingCount = 0;
		List<Approval> checkingAccounts = approvalDao.getAllCheckingAccounts();
		List<Approval> savingsAccounts = approvalDao.getAllSavingsAccounts();
		List<Approval> jointAccounts = approvalDao.getAllJointAccounts();

		
		if(accountType.equals("1")) {	
			for(Approval checkingAccount : checkingAccounts) {
				if(checkingAccount.getApprovalStatus().equals("p")) {
					pendingCount++;
				}
				
				if(checkingAccount.getCustomerId() == customerId) {
					customerExists = true;
				}
			}

			if(customerExists){
			
			if(pendingCount>0) {
				
			
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
			}
		} else {
			System.out.println("Customer does not exist with that id!");
		}
		} else if(accountType.equals("2")) {
			for(Approval savingsAccount : savingsAccounts) {
				if(savingsAccount.getApprovalStatus().equals("p")) {
					pendingCount++;
				}
				
				if(savingsAccount.getCustomerId() == customerId) {
					customerExists = true;
				}
			}
			
			if(customerExists) {
			if(pendingCount>0) {
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
			}
			} else {
				System.out.println("Customer does not exist with that id!");
			}
		} else if(accountType.equals("3")) {

			for(Approval jointAccount : jointAccounts) {
				if(jointAccount.getApprovalStatus().equals("p")) {
					pendingCount++;
				}
				
			}
			
			Customer customer = customerDao.getCustomerById(customerId);
			
			if(customer == null) {
				customerExists = false;
			} else {
				customerExists = true;
			}
			
			if(customerExists) {
			if(pendingCount>0) {
			for(Approval jointAccount : jointAccounts) {
				if(jointAccount.getCustomerId() == customerId || jointAccount.getCustomerId2() == customerId) {
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
			} else {
				System.out.println("Customer does not exist with that id!");
			}
		}
		
		if(customerExists) {
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
		
		
	}
	
	public void deny(int customerId, String accountType, Connection connection) {
		String approvalStatus = "u";

		ApprovalDao approvalDao = new ApprovalDao(connection);
		CustomerDao customerDao = new CustomerDao(connection);
		boolean customerExists = false;

		
		Approval checkingAccountChosen = new Approval();
		Approval savingsAccountChosen = new Approval();
		Approval jointAccountChosen = new Approval();
		
		int pendingCount = 0;
		List<Approval> checkingAccounts = approvalDao.getAllCheckingAccounts();
		List<Approval> savingsAccounts = approvalDao.getAllSavingsAccounts();
		List<Approval> jointAccounts = approvalDao.getAllJointAccounts();
		
		if(accountType.equals("1")) {
			for(Approval checkingAccount : checkingAccounts) {
				if(checkingAccount.getApprovalStatus().equals("p")) {
					pendingCount++;
				}
				
				if(checkingAccount.getCustomerId() == customerId) {
					customerExists = true;
				}
			}

			if(customerExists) {
			if(pendingCount>0) {
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
			}
		} else {
			System.out.println("Customer does not exist with that id!");
		}
		}else if(accountType.equals("2")) {
			for(Approval savingsAccount : savingsAccounts) {
				if(savingsAccount.getApprovalStatus().equals("p")) {
					pendingCount++;
				}
				
				if(savingsAccount.getCustomerId() == customerId) {
					customerExists = true;
				}
			}
			
			if(customerExists) {
			if(pendingCount>0) {
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
			}
		} else {
			System.out.println("Customer does not exist with that id!");
		}
		}else if(accountType.equals("3")) {
			for(Approval jointAccount : jointAccounts) {
				if(jointAccount.getApprovalStatus().equals("p")) {
					pendingCount++;
				}
			}
			
			Customer customer = customerDao.getCustomerById(customerId);
			
			if(customer == null) {
				customerExists = false;
			} else {
				customerExists = true;
			}
			
			if(customerExists) {
			if(pendingCount>0) {
			for(Approval jointAccount : jointAccounts) {
				if(jointAccount.getCustomerId() == customerId || jointAccount.getCustomerId2() == customerId) {
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
		} else {
			System.out.println("Customer does not exist with that id!");
		}
		} 
		

		if(customerExists) {
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
	}
	
	public void approveEmployee(int id, Connection connection) {
		ApprovalDao approvalDao = new ApprovalDao(connection);
		int pendingCount = 0;
		String approvalStatus = "u";
		
		List<Employee> employees = approvalDao.getAllEmployees();
		Employee employeeChosen = new Employee();
		
		for(Employee employee : employees) {
			if(employee.getApprovalStatus().equals("p")) {
				pendingCount++;
			}
		}

			
		if(pendingCount>0) {
			for(Employee employee : employees) {
				if(employee.getEmployeeId() == id) {
				employeeChosen = new Employee(
						employee.getEmployeeFirstName(),
						employee.getEmployeeLastName(),
						employee.getEmployeeAddress(),
						employee.getEmployeeBirthDate(),
						employee.getEmployeeEmailAddress(),
						employee.getEmployeePhoneNumber(),
						employee.getEmployeeIsActive(),
						employee.getApprovalStatus());
				employeeChosen.setEmployeeId(employee.getEmployeeId());
				}
			}
			
			approvalStatus = employeeChosen.getApprovalStatus();
			employeeChosen.setApprovalStatus("a");
			employeeChosen.setEmployeeIsActive(1);
		}
			
		if(approvalStatus.equals("p")) {
			approvalDao.updateEmployee(employeeChosen);
			
			System.out.println("Employee account approved!");
		} else {
			System.out.println("Approval not pending for employee!");
		}
	}
	
	public void denyEmployee(int id, Connection connection) {
		ApprovalDao approvalDao = new ApprovalDao(connection);
		int pendingCount = 0;
		String approvalStatus = "u";
		List<Employee> employees = approvalDao.getAllEmployees();
		Employee employeeChosen = new Employee();
		
		for(Employee employee : employees) {
			if(employee.getApprovalStatus().equals("p")) {
				pendingCount++;
			}
		}

			
		if(pendingCount>0) {
			for(Employee employee : employees) {
				if(employee.getEmployeeId() == id) {
				employeeChosen = new Employee(
						employee.getEmployeeFirstName(),
						employee.getEmployeeLastName(),
						employee.getEmployeeAddress(),
						employee.getEmployeeBirthDate(),
						employee.getEmployeeEmailAddress(),
						employee.getEmployeePhoneNumber(),
						employee.getEmployeeIsActive(),
						employee.getApprovalStatus());
				employeeChosen.setEmployeeId(employee.getEmployeeId());
				}
				
			}
			
			approvalStatus = employeeChosen.getApprovalStatus();
			employeeChosen.setApprovalStatus("d");
			employeeChosen.setEmployeeIsActive(0);
		}
			
		if(approvalStatus.equals("p")) {
			
			approvalDao.deleteEmployee(employeeChosen);
			
			System.out.println("Employee account denied!");
		} else {
			System.out.println("Approval not pending for employee!");
		}
	}
	
	public void approveAdministrator(int id, Connection connection) {
		ApprovalDao approvalDao = new ApprovalDao(connection);
		int pendingCount = 0;
		String approvalStatus = "u";
		List<Administrator> administrators = approvalDao.getAllAdministrators();
		Administrator administratorChosen = new Administrator();
		
		for(Administrator administrator : administrators) {
			if(administrator.getApprovalStatus().equals("p")) {
				pendingCount++;
			}
		}

			
		if(pendingCount>0) {
			for(Administrator administrator : administrators) {
				if(administrator.getAdministratorId() == id) {
				administratorChosen = new Administrator(
						administrator.getAdministratorFirstName(),
						administrator.getAdministratorLastName(),
						administrator.getAdministratorAddress(),
						administrator.getAdministratorBirthDate(),
						administrator.getAdministratorEmailAddress(),
						administrator.getAdministratorPhoneNumber(),
						administrator.getAdministratorIsActive(),
						administrator.getApprovalStatus());
				administratorChosen.setAdministratorId(administrator.getAdministratorId());
				}
				
			}
			approvalStatus = administratorChosen.getApprovalStatus();
			administratorChosen.setApprovalStatus("a");
			administratorChosen.setAdministratorIsActive(1);
		}
			
		if(approvalStatus.equals("p")) {

			approvalDao.updateAdministrator(administratorChosen);
			
			System.out.println("Administrator account approved!");
		} else {
			System.out.println("Approval not pending for administrator!");
		}
	}
	
	public void denyAdministrator(int id, Connection connection) {
		ApprovalDao approvalDao = new ApprovalDao(connection);
		int pendingCount = 0;
		String approvalStatus = "u";
		List<Administrator> administrators = approvalDao.getAllAdministrators();
		Administrator administratorChosen = new Administrator();
		
		for(Administrator administrator : administrators) {
			if(administrator.getApprovalStatus().equals("p")) {
				pendingCount++;
			}
		}

			
		if(pendingCount>0) {
			for(Administrator administrator : administrators) {
				if(administrator.getAdministratorId() == id) {
				administratorChosen = new Administrator(
						administrator.getAdministratorFirstName(),
						administrator.getAdministratorLastName(),
						administrator.getAdministratorAddress(),
						administrator.getAdministratorBirthDate(),
						administrator.getAdministratorEmailAddress(),
						administrator.getAdministratorPhoneNumber(),
						administrator.getAdministratorIsActive(),
						administrator.getApprovalStatus());
				administratorChosen.setAdministratorId(administrator.getAdministratorId());
				}
				
			}
			approvalStatus = administratorChosen.getApprovalStatus();
			administratorChosen.setApprovalStatus("d");
			administratorChosen.setAdministratorIsActive(0);
		}
			
		if(approvalStatus.equals("p")) {

			approvalDao.deleteAdministrator(administratorChosen);
			
			System.out.println("Administrator account denied!");
		} else {
			System.out.println("Approval not pending for administrator!");
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
