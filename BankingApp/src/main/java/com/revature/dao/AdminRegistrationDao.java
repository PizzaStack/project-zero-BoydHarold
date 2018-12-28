package com.revature.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.revature.AdminRegistration;
import com.revature.dao.AdminRegistrationDao;
import com.revature.jdbcinfo.EstablishConnection;

public class AdminRegistrationDao{
	
	public List<AdminRegistration> getAllAdministratorUsers(){
		List<AdminRegistration> administratorUserList = new ArrayList<>();
		AdminRegistration administratorUser = null;
		try {
			PreparedStatement preGetAllAdministratorUsers = null;
			String getAllAdministratorUsers = "SELECT * FROM AdministratorUsers;";
			preGetAllAdministratorUsers = EstablishConnection.connection.prepareStatement(getAllAdministratorUsers);
			ResultSet rs = preGetAllAdministratorUsers.executeQuery();

			while(rs.next()) {
				administratorUser = new AdminRegistration(rs.getInt(2), rs.getString(3), rs.getString(4), rs.getInt(5));
				administratorUser.setUserId(rs.getInt(1));
				administratorUserList.add(administratorUser);
			}
		} catch (SQLException e) {
			
		}
		
		return administratorUserList;
	}
	
	public AdminRegistration getAdministratorUserByUsername(String username) {
			AdminRegistration administratorUser = null;
		try {
			PreparedStatement preGetAdminRegistration = null;
			String getAdminRegistration = "SELECT * FROM AdministratorUsers WHERE Username = ?";
			preGetAdminRegistration = EstablishConnection.connection.prepareStatement(getAdminRegistration);
			preGetAdminRegistration.setString(1, username);
			ResultSet rs = preGetAdminRegistration.executeQuery();
			while(rs.next()) {
				administratorUser = new AdminRegistration(rs.getInt(2), rs.getString(3), rs.getString(4), rs.getInt(5));
				administratorUser.setUserId(rs.getInt(1));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return administratorUser;
	}
	
	public void addAdministratorUser(AdminRegistration administratorUser) {
		try {
			PreparedStatement preAddAdministratorUser = null;
			String addAdministratorUser = "INSERT INTO AdministratorUsers(AdministratorId, Username, Password, Status) VALUES (?,?,?,?)";
			preAddAdministratorUser = EstablishConnection.connection.prepareStatement(addAdministratorUser);
			preAddAdministratorUser.setInt(1,administratorUser.getAdministratorId());
			preAddAdministratorUser.setString(2,administratorUser.getUsername());
			preAddAdministratorUser.setString(3,administratorUser.getPassword());
			preAddAdministratorUser.setInt(4,administratorUser.getStatus());
			preAddAdministratorUser.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	

}
