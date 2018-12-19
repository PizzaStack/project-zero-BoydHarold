package com.revature.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.revature.Administrator;
import com.revature.dao.AdminRegistrationDao;
import com.revature.jdbcinfo.EstablishConnection;

public class AdminRegistrationDao{
	private Connection connection;
	private Administrator administrator;
	
	public AdminRegistrationDao(Connection connection) {
		this.connection = connection;
	}

	public boolean getAdminById(int id) {
		boolean exists = false;
		try {
			PreparedStatement preGetAdministrator = null;
			String getAdministrator = "SELECT * FROM Administrator WHERE AdministratorId = ?";
			preGetAdministrator = connection.prepareStatement(getAdministrator);
			preGetAdministrator.setInt(1, id);
			ResultSet rs = preGetAdministrator.executeQuery();
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
			PreparedStatement preGetAdministrator = null;
			String getAdministrator = "SELECT * FROM AdministratorUsers WHERE Username = ?";
			preGetAdministrator = connection.prepareStatement(getAdministrator);
			preGetAdministrator.setString(1, username);
			ResultSet rs = preGetAdministrator.executeQuery();
			while(rs.next()) {
				exists = true;
			}

			
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return exists;
	}

	public void addUser(String administratorId, String username, String password) {
		try {
			PreparedStatement preAddAdministrator = null;
			String addAdministrator = "INSERT INTO AdministratorUsers(AdministratorId, Username, Password, Status) VALUES (?,?,?,?)";
			preAddAdministrator = connection.prepareStatement(addAdministrator);
			preAddAdministrator.setInt(1,Integer.parseInt(administratorId));
			preAddAdministrator.setString(2,username);
			preAddAdministrator.setString(3,password);
			preAddAdministrator.setInt(4,1);
			preAddAdministrator.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	public boolean getUserAlreadyHasAccount(int id) {
		boolean exists = false;
		try {
			PreparedStatement preGetAdministrator = null;
			String getAdministrator = "SELECT * FROM AdministratorUsers WHERE AdministratorId = ?";
			preGetAdministrator = connection.prepareStatement(getAdministrator);
			preGetAdministrator.setInt(1, id);
			ResultSet rs = preGetAdministrator.executeQuery();
			while(rs.next()) {
				exists = true;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return exists;
	}

}
