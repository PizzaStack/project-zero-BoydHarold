package com.revature;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

import com.revature.consolelogic.TransferDialogue;


/**
 * Hello world!
 *
 */
public class App 
{

	
    public static void main( String[] args )
    {
    	

    	TransferDialogue td = new TransferDialogue();
    	int customerId = 4;
    	td.transfer(customerId);
    	
    	
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