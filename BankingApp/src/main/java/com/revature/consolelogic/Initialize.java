package com.revature.consolelogic;

import com.revature.CheckingAccount;
import com.revature.Customer;
import com.revature.CustomerRegistration;
import com.revature.Employee;
import com.revature.EmployeeRegistration;
import com.revature.JointAccount;
import com.revature.SavingsAccount;

public class Initialize {
	Customer customer = new Customer();
	CheckingAccount ca = new CheckingAccount();
	SavingsAccount sa = new SavingsAccount();
	JointAccount ja = new JointAccount();
	Employee employee = new Employee();
	EmployeeRegistration employeeRegistration = new EmployeeRegistration();
	CustomerRegistration customerRegistration = new CustomerRegistration();
	
	public void init() {
		customer.makeBaselineCustomer();
		ca.setupDefaultValues();
		sa.setupDefaultValues();
		ja.setupDefaultValues();
		employee.makeBaselineEmployee();
		employeeRegistration.makeBaselineUser();
		customerRegistration.makeBaselineUser();
	}
}
