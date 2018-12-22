package com.revature.consolelogic;

import java.sql.Connection;

import com.revature.AdminRegistration;
import com.revature.Administrator;
import com.revature.Employee;
import com.revature.EmployeeRegistration;
import com.revature.dao.AdminRegistrationDao;
import com.revature.dao.AdministratorDao;
import com.revature.dao.EmployeeDao;
import com.revature.dao.EmployeeRegistrationDao;

public class Initialize {
	
	public void init(Connection connection) {
		LoginDialogue loginDialogue = new LoginDialogue();
		EmployeeRegistrationDao erd = new EmployeeRegistrationDao(connection);
		AdminRegistrationDao ard = new AdminRegistrationDao(connection);
		EmployeeDao employeeDao = new EmployeeDao(connection);
		AdministratorDao administratorDao = new AdministratorDao(connection);
		EmployeeRegistration er = new EmployeeRegistration(1,"employee","password",1);
		AdminRegistration ar = new AdminRegistration(1,"admin","password",1);
		Employee defaultEmployee = employeeDao.getEmployeeById(1);
		if(defaultEmployee == null) {
			Employee employee = new Employee("Default","Default","Default","Default","Default","Default",1,"a");
			Administrator administrator = new Administrator("Default","Default","Default","Default","Default","Default",1,"a");
			employeeDao.addEmployee(employee);
			administratorDao.addAdministrator(administrator);
			erd.addEmployeeUser(er);
			ard.addAdministratorUser(ar);
		}

		loginDialogue.login(connection);
		
	}
}
