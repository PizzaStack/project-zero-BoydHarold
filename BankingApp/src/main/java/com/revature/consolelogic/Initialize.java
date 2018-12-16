package com.revature.consolelogic;

import com.revature.AdminRegistration;
import com.revature.Administrator;
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
	Administrator administrator = new Administrator();
	EmployeeRegistration employeeRegistration = new EmployeeRegistration();
	CustomerRegistration customerRegistration = new CustomerRegistration();
	AdminRegistration adminRegistration = new AdminRegistration();
	
	public void init() {
		ca.setupDefaultValues();
		sa.setupDefaultValues();
		ja.setupDefaultValues();
		customer.makeBaselineCustomer();
		employee.makeBaselineEmployee();
		administrator.makeBaselineAdministrator();
		employeeRegistration.makeBaselineUser();
		customerRegistration.makeBaselineUser();
		adminRegistration.makeBaselineUser();
	}
}
