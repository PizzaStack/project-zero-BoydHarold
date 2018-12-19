package com.revature.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.revature.Administrator;
import com.revature.dao.AdministratorDao;
import com.revature.jdbcinfo.EstablishConnection;

public class AdministratorDao{
	private Connection connection;
	private Administrator administrator;
	
	public AdministratorDao(Connection connection) {
		this.connection = connection;
	}
	
	public Administrator getAdministratorById(int id) {
		try {
			PreparedStatement preGetAdministrator = null;
			String getAdministrator = "SELECT * FROM Administrator WHERE AdministratorId = ?";
			preGetAdministrator = connection.prepareStatement(getAdministrator);
			preGetAdministrator.setInt(1, id);
			ResultSet rs = preGetAdministrator.executeQuery();
			administrator = null;
			while(rs.next()) {
				administrator = new Administrator(rs.getString(2),
						rs.getString(3),
						rs.getString(4),
						rs.getString(5),
						rs.getString(6),
						rs.getString(7));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return administrator;
	}

	public void addAdministrator(Administrator administrator) {
		try {
			PreparedStatement preAddAdministrator = null;
			String addAdministrator = "INSERT INTO Administrator(FirstName, LastName, Address, BirthDate, EmailAddress, PhoneNumber, Status) VALUES (?,?,?,?,?,?,?)";
			preAddAdministrator = connection.prepareStatement(addAdministrator);
			preAddAdministrator.setString(1,administrator.getAdministratorFirstName());
			preAddAdministrator.setString(2,administrator.getAdministratorLastName());
			preAddAdministrator.setString(3,administrator.getAdministratorAddress());
			preAddAdministrator.setString(4,administrator.getAdministratorBirthDate());
			preAddAdministrator.setString(5,administrator.getAdministratorEmailAddress());
			preAddAdministrator.setString(6,administrator.getAdministratorPhoneNumber());
			preAddAdministrator.setInt(7, 1);
			preAddAdministrator.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public int getAdministratorId(Administrator administrator) {
		int id = 0;
		try {
			PreparedStatement preGetAdministratorId = null;
			String getAdministratorId = "SELECT AdministratorId FROM Administrator WHERE FirstName = ? AND LastName = ? AND Address = ? AND BirthDate = ? AND EmailAddress = ? AND PhoneNumber = ?;";
			preGetAdministratorId = connection.prepareStatement(getAdministratorId);
			preGetAdministratorId.setString(1,administrator.getAdministratorFirstName());
			preGetAdministratorId.setString(2,administrator.getAdministratorLastName());
			preGetAdministratorId.setString(3,administrator.getAdministratorAddress());
			preGetAdministratorId.setString(4,administrator.getAdministratorBirthDate());
			preGetAdministratorId.setString(5,administrator.getAdministratorEmailAddress());
			preGetAdministratorId.setString(6,administrator.getAdministratorPhoneNumber());
			ResultSet rs = preGetAdministratorId.executeQuery();
			
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
			PreparedStatement preGetAdministratorStatus = null;
			String getAdministratorStatus = "SELECT Status FROM Administrator WHERE AdministratorId = ?";
			preGetAdministratorStatus = connection.prepareStatement(getAdministratorStatus);
			preGetAdministratorStatus.setInt(1, id);
			ResultSet rs = preGetAdministratorStatus.executeQuery();
			
			while(rs.next()) {
				status = rs.getInt(1);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return status;
	}

}
