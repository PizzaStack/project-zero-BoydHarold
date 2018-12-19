package com.revature.daoimp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.revature.Administrator;
import com.revature.dao.AdminRegistrationDao;
import com.revature.jdbcinfo.JDBCConnectionInfo;

public class AdminRegistrationDaoImp implements AdminRegistrationDao{
	private JDBCConnectionInfo jdbc;
	private ArrayList<String> connectionInfo;
	private String username;
	private String password;
	private String url;
	private Administrator administrator;
	
	public AdminRegistrationDaoImp() {
		jdbc = new JDBCConnectionInfo();
		connectionInfo = jdbc.getJDBCCredentials();
		username = connectionInfo.get(0);
		password = connectionInfo.get(1);
		url = connectionInfo.get(2);
	}

	@Override
	public boolean getAdminById(int id) {
		boolean exists = false;
		try {
			Connection connection = DriverManager.getConnection(url, this.username, this.password);
			PreparedStatement preGetAdministrator = null;
			String getAdministrator = "SELECT * FROM Administrator WHERE AdministratorId = ?";
			preGetAdministrator = connection.prepareStatement(getAdministrator);
			preGetAdministrator.setInt(1, id);
			ResultSet rs = preGetAdministrator.executeQuery();
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
			PreparedStatement preGetAdministrator = null;
			String getAdministrator = "SELECT * FROM AdministratorUsers WHERE Username = ?";
			preGetAdministrator = connection.prepareStatement(getAdministrator);
			preGetAdministrator.setString(1, username);
			ResultSet rs = preGetAdministrator.executeQuery();
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
	public void addUser(String administratorId, String username, String password) {
		try {
			Connection connection = DriverManager.getConnection(url, this.username, this.password);
			PreparedStatement preAddAdministrator = null;
			String addAdministrator = "INSERT INTO AdministratorUsers(AdministratorId, Username, Password, Status) VALUES (?,?,?,?)";
			preAddAdministrator = connection.prepareStatement(addAdministrator);
			preAddAdministrator.setInt(1,Integer.parseInt(administratorId));
			preAddAdministrator.setString(2,username);
			preAddAdministrator.setString(3,password);
			preAddAdministrator.setInt(4,1);
			preAddAdministrator.executeUpdate();
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
			PreparedStatement preGetAdministrator = null;
			String getAdministrator = "SELECT * FROM AdministratorUsers WHERE AdministratorId = ?";
			preGetAdministrator = connection.prepareStatement(getAdministrator);
			preGetAdministrator.setInt(1, id);
			ResultSet rs = preGetAdministrator.executeQuery();
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
