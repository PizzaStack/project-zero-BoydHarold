package com.revature.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.revature.Customer;
import com.revature.Employee;
import com.revature.dao.EmployeeDao;
import com.revature.jdbcinfo.EstablishConnection;

public class EmployeeDao{
	private Employee employee;
	private Connection connection;
	
	public EmployeeDao(Connection connection) {
		this.connection = connection;
	}
	
	
	
	public List<Employee> getAllEmployees(){
		List<Employee> employees = new ArrayList<>();
		try {
			PreparedStatement preGetAllEmployees = null;
			String getAllEmployees = "SELECT * FROM Employee;";
			preGetAllEmployees = connection.prepareStatement(getAllEmployees);
			ResultSet rs = preGetAllEmployees.executeQuery();
			while(rs.next()) {
				employee = new Employee(rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getInt(8));
				employee.setEmployeeId(rs.getInt(1));
				employees.add(employee);
			}
		} catch (SQLException e) {
			
		}
		
		return employees;
	}
	
	public Employee getEmployeeById(int id) {
		try {
			PreparedStatement preGetEmployee = null;
			String getEmployee = "SELECT * FROM Employee WHERE EmployeeId = ?";
			preGetEmployee = connection.prepareStatement(getEmployee);
			preGetEmployee.setInt(1, id);
			ResultSet rs = preGetEmployee.executeQuery();
			employee = null;
			while(rs.next()) {
				employee = new Employee(rs.getString(2),
						rs.getString(3),
						rs.getString(4),
						rs.getString(5),
						rs.getString(6),
						rs.getString(7),
						rs.getInt(8));
			}

			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return employee;
	
	}

	
	public void addEmployee(Employee employee) {
		try {
			PreparedStatement preAddEmployee = null;
			String addEmployee = "INSERT INTO Employee(FirstName, LastName, Address, BirthDate, EmailAddress, PhoneNumber, Status) VALUES (?,?,?,?,?,?,?)";
			preAddEmployee = connection.prepareStatement(addEmployee);
			preAddEmployee.setString(1,employee.getEmployeeFirstName());
			preAddEmployee.setString(2,employee.getEmployeeLastName());
			preAddEmployee.setString(3,employee.getEmployeeAddress());
			preAddEmployee.setString(4,employee.getEmployeeBirthDate());
			preAddEmployee.setString(5,employee.getEmployeeEmailAddress());
			preAddEmployee.setString(6,employee.getEmployeePhoneNumber());
			preAddEmployee.setInt(7, employee.getEmployeeIsActive());
			preAddEmployee.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		

		
	}
	

}
