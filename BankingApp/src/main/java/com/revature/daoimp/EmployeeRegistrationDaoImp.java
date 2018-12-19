package com.revature.daoimp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.revature.Employee;
import com.revature.dao.EmployeeRegistrationDao;
import com.revature.jdbcinfo.JDBCConnectionInfo;

public class EmployeeRegistrationDaoImp implements EmployeeRegistrationDao{
	private JDBCConnectionInfo jdbc;
	private ArrayList<String> connectionInfo;
	private String username;
	private String password;
	private String url;
	private Employee employee;
	
	public EmployeeRegistrationDaoImp() {
		jdbc = new JDBCConnectionInfo();
		connectionInfo = jdbc.getJDBCCredentials();
		username = connectionInfo.get(0);
		password = connectionInfo.get(1);
		url = connectionInfo.get(2);
	}

	@Override
	public boolean getEmployeeById(int id) {
		boolean exists = false;
		try {
			Connection connection = DriverManager.getConnection(url, this.username, this.password);
			PreparedStatement preGetEmployee = null;
			String getEmployee = "SELECT * FROM Employee WHERE EmployeeId = ?";
			preGetEmployee = connection.prepareStatement(getEmployee);
			preGetEmployee.setInt(1, id);
			ResultSet rs = preGetEmployee.executeQuery();
			while(rs.next()) {
				exists = true;
			}

			
			connection.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return exists;
	}

	@Override
	public boolean getUserExists(String username) {
		boolean exists = false;
		try {
			Connection connection = DriverManager.getConnection(url, this.username, this.password);
			PreparedStatement preGetEmployee = null;
			String getEmployee = "SELECT * FROM EmployeeUsers WHERE Username = ?";
			preGetEmployee = connection.prepareStatement(getEmployee);
			preGetEmployee.setString(1, username);
			ResultSet rs = preGetEmployee.executeQuery();
			while(rs.next()) {
				exists = true;
			}

			
			connection.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return exists;
	}

	@Override
	public void addUser(String employeeId, String username, String password) {
		try {
			Connection connection = DriverManager.getConnection(url, this.username, this.password);
			PreparedStatement preAddEmployee = null;
			String addEmployee = "INSERT INTO EmployeeUsers(EmployeeId, Username, Password, Status) VALUES (?,?,?,?)";
			preAddEmployee = connection.prepareStatement(addEmployee);
			preAddEmployee.setInt(1,Integer.parseInt(employeeId));
			preAddEmployee.setString(2,username);
			preAddEmployee.setString(3,password);
			preAddEmployee.setInt(4,1);
			preAddEmployee.executeUpdate();
			connection.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public boolean getUserAlreadyHasAccount(int id) {
		boolean exists = false;
		try {
			Connection connection = DriverManager.getConnection(url, this.username, this.password);
			PreparedStatement preGetEmployee = null;
			String getEmployee = "SELECT * FROM EmployeeUsers WHERE EmployeeId = ?";
			preGetEmployee = connection.prepareStatement(getEmployee);
			preGetEmployee.setInt(1, id);
			ResultSet rs = preGetEmployee.executeQuery();
			while(rs.next()) {
				exists = true;
			}

			
			connection.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return exists;
	}

}
