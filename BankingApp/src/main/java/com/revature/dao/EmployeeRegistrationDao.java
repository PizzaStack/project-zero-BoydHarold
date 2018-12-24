package com.revature.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.revature.EmployeeRegistration;
import com.revature.dao.EmployeeRegistrationDao;

public class EmployeeRegistrationDao{
	private Connection connection;
	
	public EmployeeRegistrationDao(Connection connection) {
		this.connection = connection;
	}

	
	
	public List<EmployeeRegistration> getAllEmployeeUsers(){
		List<EmployeeRegistration> employeeUserList = new ArrayList<>();
		EmployeeRegistration employeeUser = null;
		try {
			PreparedStatement preGetAllEmployeeUsers = null;
			String getAllEmployeeUsers = "SELECT * FROM EmployeeUsers;";
			preGetAllEmployeeUsers = connection.prepareStatement(getAllEmployeeUsers);
			ResultSet rs = preGetAllEmployeeUsers.executeQuery();

			while(rs.next()) {
				employeeUser = new EmployeeRegistration(rs.getInt(2), rs.getString(3), rs.getString(4), rs.getInt(5));
				employeeUser.setUserId(rs.getInt(1));
				employeeUserList.add(employeeUser);
			}
		} catch (SQLException e) {
			
		}
		
		return employeeUserList;
	}
	
	public EmployeeRegistration getEmployeeUserByUsername(String username) {
			EmployeeRegistration employeeUser = null;
		try {
			PreparedStatement preGetEmployeeRegistration = null;
			String getEmployeeRegistration = "SELECT * FROM EmployeeUsers WHERE Username = ?";
			preGetEmployeeRegistration = connection.prepareStatement(getEmployeeRegistration);
			preGetEmployeeRegistration.setString(1, username);
			ResultSet rs = preGetEmployeeRegistration.executeQuery();
			while(rs.next()) {
				employeeUser = new EmployeeRegistration(rs.getInt(2), rs.getString(3), rs.getString(4), rs.getInt(5));
				employeeUser.setUserId(rs.getInt(1));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return employeeUser;
	}
	
	public void addEmployeeUser(EmployeeRegistration employeeUser) {
		try {
			PreparedStatement preAddEmployeeUser = null;
			String addEmployeeUser = "INSERT INTO EmployeeUsers(EmployeeId, Username, Password, Status) VALUES (?,?,?,?)";
			preAddEmployeeUser = connection.prepareStatement(addEmployeeUser);
			preAddEmployeeUser.setInt(1,employeeUser.getEmployeeId());
			preAddEmployeeUser.setString(2,employeeUser.getUsername());
			preAddEmployeeUser.setString(3,employeeUser.getPassword());
			preAddEmployeeUser.setInt(4,employeeUser.getStatus());
			preAddEmployeeUser.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
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
