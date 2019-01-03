package com.revature.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.revature.Administrator;
import com.revature.dao.AdministratorDao;
import com.revature.jdbcinfo.EstablishConnection;

public class AdministratorDao {
	private Administrator administrator;

	public List<Administrator> getAllAdministrators() {
		List<Administrator> administrators = new ArrayList<>();
		try {
			PreparedStatement preGetAllAdministrators = null;
			String getAllAdministrators = "SELECT * FROM Administrator;";
			preGetAllAdministrators = EstablishConnection.connection.prepareStatement(getAllAdministrators);
			ResultSet rs = preGetAllAdministrators.executeQuery();
			while (rs.next()) {
				administrator = new Administrator(rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5),
						rs.getString(6), rs.getString(7), rs.getInt(8), rs.getString(9));
				administrator.setAdministratorId(rs.getInt(1));
				administrators.add(administrator);
			}
		} catch (SQLException e) {

		}

		return administrators;
	}

	public Administrator getAdministratorById(int id) {
		try {
			PreparedStatement preGetAdministrator = null;
			String getAdministrator = "SELECT * FROM Administrator WHERE AdministratorId = ?";
			preGetAdministrator = EstablishConnection.connection.prepareStatement(getAdministrator);
			preGetAdministrator.setInt(1, id);
			ResultSet rs = preGetAdministrator.executeQuery();
			administrator = null;
			while (rs.next()) {
				administrator = new Administrator(rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5),
						rs.getString(6), rs.getString(7), rs.getInt(8), rs.getString(9));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return administrator;

	}

	public void addAdministrator(Administrator administrator) {
		try {
			PreparedStatement preAddAdministrator = null;
			String addAdministrator = "INSERT INTO Administrator(FirstName, LastName, Address, BirthDate, EmailAddress, PhoneNumber, Status, ApprovalStatus) VALUES (?,?,?,?,?,?,?,?)";
			preAddAdministrator = EstablishConnection.connection.prepareStatement(addAdministrator);
			preAddAdministrator.setString(1, administrator.getAdministratorFirstName());
			preAddAdministrator.setString(2, administrator.getAdministratorLastName());
			preAddAdministrator.setString(3, administrator.getAdministratorAddress());
			preAddAdministrator.setString(4, administrator.getAdministratorBirthDate());
			preAddAdministrator.setString(5, administrator.getAdministratorEmailAddress());
			preAddAdministrator.setString(6, administrator.getAdministratorPhoneNumber());
			preAddAdministrator.setInt(7, administrator.getAdministratorIsActive());
			preAddAdministrator.setString(8, administrator.getApprovalStatus());
			preAddAdministrator.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
