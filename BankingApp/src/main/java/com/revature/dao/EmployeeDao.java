package com.revature.dao;

import java.util.List;

import com.revature.Employee;

public interface EmployeeDao {
	Employee getEmployeeById(int id);
	void addEmployee(Employee employee);
	int getEmployeeId(Employee employee);
	int getStatus(int id);
}
