package com.revature;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

import com.revature.consolelogic.AdministratorDialogue;
import com.revature.consolelogic.ApplyForAccountDialogue;
import com.revature.consolelogic.CustomerDialogue;
import com.revature.consolelogic.DepositDialogue;
import com.revature.consolelogic.EmployeeDialogue;
import com.revature.consolelogic.Initialize;
import com.revature.consolelogic.LoginDialogue;
import com.revature.consolelogic.RegistrationDialogue;
import com.revature.consolelogic.TransferDialogue;
import com.revature.consolelogic.WithdrawlDialogue;


/**
 * Hello world!
 *
 */
public class App 
{

    public static void main( String[] args )
    {
    	
    	Initialize init = new Initialize();
    	init.init();
    	AdministratorDialogue ad = new AdministratorDialogue();
    //	ad.addNewAdministrator();
    	//ad.displayAdministrator();
   	CustomerDialogue cd = new CustomerDialogue();
   	

   	
   	EmployeeDialogue ed = new EmployeeDialogue();
   	
   	RegistrationDialogue rd = new RegistrationDialogue();
   	
   	LoginDialogue ld = new LoginDialogue();
   	
   	WithdrawlDialogue wd = new WithdrawlDialogue();
   	
   	DepositDialogue dd = new DepositDialogue();
   	
   	ApplyForAccountDialogue afad = new ApplyForAccountDialogue();
   	
   	afad.apply(1);
//   	cd.displayCustomer();
//   	cd.addNewCustomer();
   	
//   	afad.apply(1);
//   dd.deposit(1);
//   	wd.withdrawl(1);
   	
  // 	ld.login();
   	
//   	rd.register();
   	
//   	ed.addNewEmployee();
   //	ed.displayEmployee();
//    	int customerId = 1;
//    	
//    	JointAccount ja = new JointAccount();
//    	System.out.println(ja.getApprovalStatus(1));
    	
//    	
//    	TransferDialogue td = new TransferDialogue();
//    	
//    	td.transfer(1);
    	
    //	ja.applyForAccount(1, 2);
    	
    	
//    	System.out.println(ja.getPosition(customerId));
    	
//    	System.out.println(ja.getAccountStatus(ja.getPosition(customerId)));
//    	
//    	System.out.println(ja.getApprovalStatus(ja.getPosition(customerId)));
//    	System.out.println(ja.getBalance(ja.getPosition(customerId)));
//    	System.out.println(ja.getPosition(2));
//    	ja.applyForAccount(1, 3);
//    	CheckingAccount ca = new CheckingAccount();
//    	ca.applyForAccount(customerId);
    	
//    	SavingsAccount sa = new SavingsAccount();
//    	sa.applyForAccount(customerId);
//    	
//    	init.init();
   	//cd.displayCustomer();
//    	cd.addNewCustomer();
    	
//    	TransferDialogue td = new TransferDialogue();
//    	int customerId = 4;
//    	td.transfer(customerId);
    	
    	
//		Customer customer = new Customer();
//		Employee employee = new Employee();
//		CustomerRegistration cr = new CustomerRegistration();
//		cr.makeBaselineUser();
//
//		cr.registerUser("10","smithj","pickebutt");
//		int c = cr.checkCustomerOnboarded("1");
//		System.out.println(c);
		//		int c = cr.checkUserExists("baltos");
//		System.out.println(c);
//		customer.makeBaselineCustomer();
//		int id = customer.generateCustomerId();
//		System.out.println(id);
//		customer.setCustomerFirstName("Tom");
//		customer.setCustomerLastName("Skirk");
//		customer.setCustomerAddress("423 Nine Oh St");
//		customer.setCustomerBirthDate(01,01,1987);
//		customer.setCustomerEmailAddress("Tom.Skirk@revature.com");
//		customer.setCustomerPhoneNumber("303-000-0002");
//		customer.setCustomerId(id);
//		customer.commitCustomer();
//		employee.makeBaselineEmployee();
//		employee.setEmployeeFirstName("Tom");
//		employee.setEmployeeLastName("Skirk");
//		employee.setEmployeeAddress("423 Nine Oh St");
//		employee.setEmployeeBirthDate(01,01,1987);
//		employee.setEmployeeEmailAddress("Tom.Skirk@revature.com");
//		employee.setEmployeePhoneNumber("303-000-0002");
//		employee.commitEmployee();
//		int customerId = 1;
//		customer.getCommittedCustomerInformation(customerId);
		
//		int employeeId = 1;
//		employee.getCommittedEmployeeInformation(employeeId);
//		
//		
//		System.out.println(employee.getEmployeeFirstName());
//		System.out.println(employee.getEmployeeLastName());
//		System.out.println(employee.getEmployeeAddress());
//		System.out.println(employee.getEmployeeBirthDate());
//		System.out.println(employee.getEmployeeEmailAddress());
//		System.out.println(employee.getEmployeePhoneNumber());
//
//		System.out.println(customer.getCustomerFirstName(customerId));
//		System.out.println(customer.getCustomerLastName(customerId));
//		System.out.println(customer.getCustomerAddress(customerId));
//		System.out.println(customer.getCustomerBirthDate(customerId));
//		System.out.println(customer.getCustomerEmailAddress(customerId));
//		System.out.println(customer.getCustomerPhoneNumber(customerId));

		
        

    }
}
