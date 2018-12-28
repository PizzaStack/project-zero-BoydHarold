package com.revature.consolelogic;

import java.util.Scanner;

import com.revature.Administrator;
import com.revature.Approval;
import com.revature.Customer;
import com.revature.Employee;
import com.revature.dao.AdministrativeFunctionsDao;
import com.revature.dao.AdministratorDao;
import com.revature.dao.CustomerDao;
import com.revature.dao.EmployeeDao;

public class AdministratorPortalDialogue {
	private boolean loop = true;
	private Scanner sc = new Scanner(System.in);
	
	public void administratorOptions() {
	while(loop) {
		System.out.println("\nWhat would you like to do?");
		System.out.println("\n1. View Customer Information");
		System.out.println("2. View Pending Customer Accountts");
		System.out.println("3. Approve/Deny Pending Customer Accounts");
		System.out.println("4. Manage Checking/Savings/Joint Accounts");
		System.out.println("5. View Pending Employee/Administrator Accounts");
		System.out.println("6. Approve/Deny Pending Employee/Administrator Accounts");
		System.out.println("7. Cancel Accounts");
		System.out.println("8. Enable Accounts");
		System.out.println("9. Log Out");
		String choice = sc.nextLine();
		
		boolean validEntry = false;
		
		while(validEntry == false) {
			if(choice.equals("1") || choice.equals("2") || choice.equals("3") || choice.equals("4") || choice.equals("5") || choice.equals("6") || choice.equals("7") || choice.equals("8") || choice.equals("9")) {
				validEntry = true;
			} else {
				validEntry = false;
				System.out.println("Entry invalid! Please enter in either 1, 2, 3, 4, 5, 6, 7, 8, or 9.");
				choice = sc.nextLine();
			}
		}
		

		
		if(choice.equals("1")) {
			CustomerDialogue cd = new CustomerDialogue();
			cd.displayCustomer();
		} else if(choice.equals("2")) {
			System.out.println("\nChoose an account type:");
			System.out.println("\n1. Checking");
			System.out.println("2. Savings");
			System.out.println("3. Joint");
			System.out.println("4. Back");
			
			String choice2 = sc.nextLine();
			
			validEntry = false;
			
			while(validEntry == false) {
				if(choice2.equals("1") || choice2.equals("2") || choice2.equals("3") || choice2.equals("4")) {
					validEntry = true;
				} else {
					validEntry = false;
					System.out.println("Entry invalid! Please enter in either 1, 2, 3, or 4.");
					choice2 = sc.nextLine();
				}
			}
			
			
			Approval approval = new Approval();
			
			if(choice2.equals("1")) {
				approval.listPendingChecking();
			} else if(choice2.equals("2")) {
				approval.listPendingSavings();
			} else if(choice2.equals("3")) {
				approval.listPendingJoint();
			} else if(choice2.equals("4")) {
				administratorOptions();
			}

		} else if(choice.equals("3")) {
			System.out.println("\nChoose an account type:");
			System.out.println("\n1. Checking");
			System.out.println("2. Savings");
			System.out.println("3. Joint");
			System.out.println("4. Back");
			
			String choice2 = sc.nextLine();
			
			validEntry = false;
			
			while(validEntry == false) {
				if(choice2.equals("1") || choice2.equals("2") || choice2.equals("3") || choice2.equals("4")) {
					validEntry = true;
				} else {
					validEntry = false;
					System.out.println("Entry invalid! Please enter in either 1, 2, 3, or 4.");
					choice2 = sc.nextLine();
				}
			}
			
			if(choice2.equals("4")) {
				administratorOptions();
			}
			
			Approval approval = new Approval();
			
			System.out.println("\nWhat would you like to do?");
			System.out.println("\n1. Approve");
			System.out.println("2. Deny");
			System.out.println("3. Back");
			
			String choice3 = sc.nextLine();
			
			validEntry = false;
			while(validEntry == false) {
				if(choice3.equals("1") || choice3.equals("2") || choice3.equals("3")) {
					validEntry = true;
				} else {
					validEntry = false;
					System.out.println("Invalid entry! Enter in either 1, 2, or 3.");
					choice3 = sc.nextLine();
				}
			}
			
			if(choice3.equals("3")) {
				administratorOptions();
			}
			
			System.out.println("\nEnter in the customerId:");
			
			String id = sc.nextLine();
			ApplyForAccountDialogue afad = new ApplyForAccountDialogue();
			validEntry = false;
			while(validEntry == false) {
					boolean isNumeric = afad.isNumeric(id);
					if(isNumeric) {
						validEntry = true;
					} else {
						validEntry = false;
						System.out.println("Invalid entry. Please enter in a valid id!");
						id = sc.nextLine();
					}
			}
			
			
			if(choice2.equals("1")) {
				if(choice3.equals("1")) {
					approval.approve(Integer.parseInt(id), "1");
				} else {
					approval.deny(Integer.parseInt(id), "1");
				}
			} else if(choice2.equals("2")) {
				if(choice3.equals("1")) {
					approval.approve(Integer.parseInt(id), "2");
				} else {
					approval.deny(Integer.parseInt(id), "2");
				}
			} else if(choice2.equals("3")) {
				if(choice3.equals("1")) {
					approval.approve(Integer.parseInt(id), "3");
				} else {
					approval.deny(Integer.parseInt(id), "3");
				}
			}
		} else if(choice.equals("4")) {
			System.out.println("\nEnter in the Customer id belonging to the account(s) you wish to interact with:");
			String id = sc.nextLine();
			
			ApplyForAccountDialogue afad = new ApplyForAccountDialogue();
			validEntry = false;
			while(validEntry == false) {
					boolean isNumeric = afad.isNumeric(id);
					if(isNumeric) {
						validEntry = true;
						CustomerDao customerDao = new CustomerDao();
						Customer customer = customerDao.getCustomerById(Integer.parseInt(id));
						if(customer != null) {
							validEntry = true;
						} else {
							validEntry = false;
							System.out.println("Customer does not exist. Please enter in a valid id!");
							id = sc.nextLine();
						}
					} else {
						validEntry = false;
						System.out.println("Invalid entry. Please enter in a valid id!");
						id = sc.nextLine();
					}
					
			}
			
			CustomerPortalDialogue cpd = new CustomerPortalDialogue();
			String accessType = "Administrator";
			cpd.customerOptions(Integer.parseInt(id), accessType);
			
		} else if(choice.equals("5")){
			System.out.println("\nChoose which type:");
			System.out.println("\n1. Employee");
			System.out.println("2. Administrator");
			System.out.println("3. Back");
			
			String choice7 = sc.nextLine();
			
			validEntry = false;
			while(validEntry == false) {
				if(choice7.equals("1") || choice7.equals("2") || choice7.equals("3")) {
					validEntry = true;
				} else {
					validEntry = false;
					System.out.println("Invalid entry! Please enter in either 1, 2, or 3.");
					choice7 = sc.nextLine();
				}
			}
			
			Approval approval = new Approval();
			if(choice7.equals("1")) {
				approval.listPendingEmployees();
			} else if(choice7.equals("2")) {
				approval.listPendingAdministrators();
			} else if(choice7.equals("3")) {
				administratorOptions();
			}
			
		} else if(choice.equals("6")) {
			System.out.println("\nChoose which type of account:");
			System.out.println("\n1. Employee");
			System.out.println("2. Administrator");
			System.out.println("3. Back");
			
			String choice5 = sc.nextLine();
			
			validEntry = false;
			while(validEntry == false) {
				if(choice5.equals("1") || choice5.equals("2") || choice5.equals("3")) {
					validEntry = true;
				} else {
					validEntry = false;
					System.out.println("Invalid entry! Please enter in either 1, 2, or 3.");
					choice5 = sc.nextLine();
				}
			}
			
			if(choice5.equals("1")){
				System.out.println("\nWhat would you like to do?");
				System.out.println("\n1. Approve");
				System.out.println("2. Deny");
				System.out.println("3. Back");
				
				String choice6 = sc.nextLine();
				
				validEntry = false;
				while(validEntry == false) {
					if(choice6.equals("1") || choice6.equals("2") || choice6.equals("3")) {
						validEntry = true;
					} else {
						validEntry = false;
						System.out.println("Invalid entry! Please enter in either 1, 2, or 3.");
						choice6 = sc.nextLine();
					}
				}
				
				if(choice6.equals("1")) {
					System.out.println("\nEnter in the employee id of the user you wish to approve:");
					
					String id = sc.nextLine();
					
					ApplyForAccountDialogue afad = new ApplyForAccountDialogue();
					validEntry = false;
					while(validEntry == false) {
							boolean isNumeric = afad.isNumeric(id);
							if(isNumeric) {
								validEntry = true;
								EmployeeDao employeeDao = new EmployeeDao();
								Employee employee = employeeDao.getEmployeeById(Integer.parseInt(id));
								if(employee != null) {
									validEntry = true;
								} else {
									validEntry = false;
									System.out.println("Employee does not exist. Please enter in a valid id!");
									id = sc.nextLine();
								}
							} else {
								validEntry = false;
								System.out.println("Invalid entry. Please enter in a valid id!");
								id = sc.nextLine();
							}
					}
	
					Approval approval = new Approval();
					approval.approveEmployee(Integer.parseInt(id));
					
				} else if(choice6.equals("2")) {
					System.out.println("\nEnter in the employee id of the user you wish to deny:");
					
					String id = sc.nextLine();
					
					ApplyForAccountDialogue afad = new ApplyForAccountDialogue();
					validEntry = false;
					while(validEntry == false) {
							boolean isNumeric = afad.isNumeric(id);
							if(isNumeric) {
								validEntry = true;
								EmployeeDao employeeDao = new EmployeeDao();
								Employee employee = employeeDao.getEmployeeById(Integer.parseInt(id));
								if(employee != null) {
									validEntry = true;
								} else {
									validEntry = false;
									System.out.println("Employee does not exist. Please enter in a valid id!");
									id = sc.nextLine();
								}
							} else {
								validEntry = false;
								System.out.println("Invalid entry. Please enter in a valid id!");
								id = sc.nextLine();
							}
					}
					
					Approval approval = new Approval();
					approval.denyEmployee(Integer.parseInt(id));
				} else if(choice6.equals("3")) {
					administratorOptions();
				}
			} else if(choice5.equals("2")) {
				System.out.println("\nWhat would you like to do?");
				System.out.println("\n1. Approve");
				System.out.println("2. Deny");
				System.out.println("3. Back");
				
				String choice6 = sc.nextLine();
				
				validEntry = false;
				while(validEntry = false) {
					if(choice6.equals("1") || choice6.equals("2") || choice6.equals("3")) {
						validEntry = true;
					} else {
						validEntry = false;
						System.out.println("Invalid entry! Please enter in either 1, 2, or 3.");
						choice6 = sc.nextLine();
					}
				}
				
				if(choice6.equals("1")) {
					System.out.println("\nEnter in the administrator id of the user you wish to approve:");
					
					String id = sc.nextLine();
					
					ApplyForAccountDialogue afad = new ApplyForAccountDialogue();
					validEntry = false;
					while(validEntry == false) {
							boolean isNumeric = afad.isNumeric(id);
							if(isNumeric) {
								validEntry = true;
								AdministratorDao administratorDao = new AdministratorDao();
								Administrator administrator = administratorDao.getAdministratorById(Integer.parseInt(id));
								if(administrator != null) {
									validEntry = true;
								} else {
									validEntry = false;
									System.out.println("Administrator does not exist. Please enter in a valid id!");
									id = sc.nextLine();
								}
							} else {
								validEntry = false;
								System.out.println("Invalid entry. Please enter in a valid id!");
								id = sc.nextLine();
							}
					}
					
					Approval approval = new Approval();
					approval.approveAdministrator(Integer.parseInt(id));
					
				} else if(choice6.equals("2")) {
					System.out.println("\nEnter in the administrator id of the user you wish to deny:");
					
					String id = sc.nextLine();
					
					ApplyForAccountDialogue afad = new ApplyForAccountDialogue();
					validEntry = false;
					while(validEntry == false) {
							boolean isNumeric = afad.isNumeric(id);
							if(isNumeric) {
								validEntry = true;
								AdministratorDao administratorDao = new AdministratorDao();
								Administrator administrator = administratorDao.getAdministratorById(Integer.parseInt(id));
								if(administrator != null) {
									validEntry = true;
								} else {
									validEntry = false;
									System.out.println("Administrator does not exist. Please enter in a valid id!");
									id = sc.nextLine();
								}
							} else {
								validEntry = false;
								System.out.println("Invalid entry. Please enter in a valid id!");
								id = sc.nextLine();
							}
					}
					
					Approval approval = new Approval();
					approval.denyAdministrator(Integer.parseInt(id));
				} else if(choice6.equals("3")) {
					administratorOptions();
				}
			
			} else if(choice5.equals("3")) {
				administratorOptions();
			}
			
		} else if(choice.equals("7")) {
		
			System.out.println("\nWhich type of account would you like to cancel?");
			System.out.println("\n1. Customer");
			System.out.println("2. Employee");
			System.out.println("3. Administrator");
			System.out.println("4. Back");
			
			String choice4 = sc.nextLine();
			
			validEntry = false;
			while(validEntry == false) {
				if(choice4.equals("1") || choice4.equals("2") || choice4.equals("3") || choice4.equals("4")) {
					validEntry = true;
				} else {
					validEntry = false;
					System.out.println("Invalid entry! Enter in either 1, 2, 3, or 4.");
					choice4 = sc.nextLine();
				}
			}
			
			if(choice4.equals("4")) {
				administratorOptions();
			}
			
			System.out.println("\nEnter in the id you wish to cancel account(s) for:");
			String id = sc.nextLine();
			
			ApplyForAccountDialogue afad = new ApplyForAccountDialogue();
			validEntry = false;
			while(validEntry == false) {
					boolean isNumeric = afad.isNumeric(id);
					if(isNumeric) {
						validEntry = true;
						
						
						if(choice4.equals("1")) {
							CustomerDao customerDao = new CustomerDao();
							Customer customer = customerDao.getCustomerById(Integer.parseInt(id));
							if(customer != null) {
								validEntry = true;
							} else {
								validEntry = false;
								System.out.println("Customer does not exist. Please enter in a valid id!");
								id = sc.nextLine();
							}
						} else if(choice4.equals("2")) {
							EmployeeDao employeeDao = new EmployeeDao();
							Employee employee = employeeDao.getEmployeeById(Integer.parseInt(id));
							if(employee != null) {
								validEntry = true;
							} else {
								validEntry = false;
								System.out.println("Employee does not exist. Please enter in a valid id!");
								id = sc.nextLine();
							}
						} else if(choice4.equals("3")) {
							AdministratorDao administratorDao = new AdministratorDao();
							Administrator administrator = administratorDao.getAdministratorById(Integer.parseInt(id));
							if(administrator != null) {
								validEntry = true;
							} else {
								validEntry = false;
								System.out.println("Administrator does not exist. Please enter in a valid id!");
								id = sc.nextLine();
							}
						}			
						
					} else {
						validEntry = false;
						System.out.println("Invalid entry. Please enter in a valid id!");
						id = sc.nextLine();
					}
			}
			
			AdministrativeFunctionsDao afd = new AdministrativeFunctionsDao();
			
			afd.cancelAccount(Integer.parseInt(id), choice4);
			
			
		} else if(choice.equals("8")) {
			System.out.println("\nWhich type of account would you like to enable?");
			System.out.println("\n1. Customer");
			System.out.println("2. Employee");
			System.out.println("3. Administrator");
			System.out.println("4. Back");
			
			String choice4 = sc.nextLine();
			
			validEntry = false;
			while(validEntry == false) {
				if(choice4.equals("1") || choice4.equals("2") || choice4.equals("3") || choice4.equals("4")) {
					validEntry = true;
				} else {
					validEntry = false;
					System.out.println("Invalid entry! Enter in either 1, 2, 3, or 4.");
					choice4 = sc.nextLine();
				}
			}
			
			if(choice4.equals("4")) {
				administratorOptions();
			}
			
			System.out.println("\nEnter in the id you wish to enable account(s) for:");
			String id = sc.nextLine();
			
			ApplyForAccountDialogue afad = new ApplyForAccountDialogue();
			validEntry = false;
			while(validEntry == false) {
					boolean isNumeric = afad.isNumeric(id);
					if(isNumeric) {
						validEntry = true;
						
						
						if(choice4.equals("1")) {
							CustomerDao customerDao = new CustomerDao();
							Customer customer = customerDao.getCustomerById(Integer.parseInt(id));
							if(customer != null) {
								validEntry = true;
							} else {
								validEntry = false;
								System.out.println("Customer does not exist. Please enter in a valid id!");
								id = sc.nextLine();
							}
						} else if(choice4.equals("2")) {
							EmployeeDao employeeDao = new EmployeeDao();
							Employee employee = employeeDao.getEmployeeById(Integer.parseInt(id));
							if(employee != null) {
								validEntry = true;
							} else {
								validEntry = false;
								System.out.println("Employee does not exist. Please enter in a valid id!");
								id = sc.nextLine();
							}
						} else if(choice4.equals("3")) {
							AdministratorDao administratorDao = new AdministratorDao();
							Administrator administrator = administratorDao.getAdministratorById(Integer.parseInt(id));
							if(administrator != null) {
								validEntry = true;
							} else {
								validEntry = false;
								System.out.println("Administrator does not exist. Please enter in a valid id!");
								id = sc.nextLine();
							}
						}			
						
					} else {
						validEntry = false;
						System.out.println("Invalid entry. Please enter in a valid id!");
						id = sc.nextLine();
					}
			}
			
			AdministrativeFunctionsDao afd = new AdministrativeFunctionsDao();
			
			afd.activateAccount(Integer.parseInt(id), choice4);
		} else if(choice.equals("9")) {
			LoginDialogue loginDialogue = new LoginDialogue();
			loginDialogue.login();
		}
	}
}
}
