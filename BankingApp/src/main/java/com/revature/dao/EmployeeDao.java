package com.revature.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.revature.Employee;
import com.revature.dao.EmployeeDao;
import com.revature.jdbcinfo.EstablishConnection;

public class EmployeeDao{
	private Employee employee;
	private Connection connection;
	
	public EmployeeDao(Connection connection) {
		this.connection = connection;
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
						rs.getString(7));
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
			preAddEmployee.setInt(7, 1);
			preAddEmployee.executeUpdate();

			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
	}

	
	public int getEmployeeId(Employee employee) {
		int id = 0;
		try {
			PreparedStatement preGetEmployeeId = null;
			String getEmployeeId = "SELECT EmployeeId FROM Employee WHERE FirstName = ? AND LastName = ? AND Address = ? AND BirthDate = ? AND EmailAddress = ? AND PhoneNumber = ?;";
			preGetEmployeeId = connection.prepareStatement(getEmployeeId);
			preGetEmployeeId.setString(1,employee.getEmployeeFirstName());
			preGetEmployeeId.setString(2,employee.getEmployeeLastName());
			preGetEmployeeId.setString(3,employee.getEmployeeAddress());
			preGetEmployeeId.setString(4,employee.getEmployeeBirthDate());
			preGetEmployeeId.setString(5,employee.getEmployeeEmailAddress());
			preGetEmployeeId.setString(6,employee.getEmployeePhoneNumber());
			ResultSet rs = preGetEmployeeId.executeQuery();
			
			while(rs.next()) {
				id = rs.getInt(1);
			}
			
			
			

			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return id;
	}

	
	public int getStatus(int id) {
		int status = 0;
		try {
			PreparedStatement preGetEmployeeStatus = null;
			String getEmployeeStatus = "SELECT Status FROM Employee WHERE EmployeeId = ?";
			preGetEmployeeStatus = connection.prepareStatement(getEmployeeStatus);
			preGetEmployeeStatus.setInt(1, id);
			ResultSet rs = preGetEmployeeStatus.executeQuery();
			
			while(rs.next()) {
				status = rs.getInt(1);
			}

			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return status;
	}

	

}
