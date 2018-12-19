package com.revature.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.revature.Employee;
import com.revature.dao.EmployeeRegistrationDao;
import com.revature.jdbcinfo.EstablishConnection;

public class EmployeeRegistrationDao{
	private Connection connection;
	private Employee employee;
	
	public EmployeeRegistrationDao(Connection connection) {
		this.connection = connection;
	}

	
	public boolean getEmployeeById(int id) {
		boolean exists = false;
		try {
			PreparedStatement preGetEmployee = null;
			String getEmployee = "SELECT * FROM Employee WHERE EmployeeId = ?";
			preGetEmployee = connection.prepareStatement(getEmployee);
			preGetEmployee.setInt(1, id);
			ResultSet rs = preGetEmployee.executeQuery();
			while(rs.next()) {
				exists = true;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return exists;
	}

	
	public boolean getUserExists(String username) {
		boolean exists = false;
		try {
			PreparedStatement preGetEmployee = null;
			String getEmployee = "SELECT * FROM EmployeeUsers WHERE Username = ?";
			preGetEmployee = connection.prepareStatement(getEmployee);
			preGetEmployee.setString(1, username);
			ResultSet rs = preGetEmployee.executeQuery();
			while(rs.next()) {
				exists = true;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return exists;
	}

	
	public void addUser(String employeeId, String username, String password) {
		try {
			PreparedStatement preAddEmployee = null;
			String addEmployee = "INSERT INTO EmployeeUsers(EmployeeId, Username, Password, Status) VALUES (?,?,?,?)";
			preAddEmployee = connection.prepareStatement(addEmployee);
			preAddEmployee.setInt(1,Integer.parseInt(employeeId));
			preAddEmployee.setString(2,username);
			preAddEmployee.setString(3,password);
			preAddEmployee.setInt(4,1);
			preAddEmployee.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	
	public boolean getUserAlreadyHasAccount(int id) {
		boolean exists = false;
		try {
			PreparedStatement preGetEmployee = null;
			String getEmployee = "SELECT * FROM EmployeeUsers WHERE EmployeeId = ?";
			preGetEmployee = connection.prepareStatement(getEmployee);
			preGetEmployee.setInt(1, id);
			ResultSet rs = preGetEmployee.executeQuery();
			while(rs.next()) {
				exists = true;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return exists;
	}

}
