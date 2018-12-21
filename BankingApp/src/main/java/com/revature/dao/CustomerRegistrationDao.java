package com.revature.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.revature.Customer;
import com.revature.CustomerRegistration;
import com.revature.dao.CustomerRegistrationDao;
import com.revature.jdbcinfo.EstablishConnection;

public class CustomerRegistrationDao{
	private Connection connection;
	
	public CustomerRegistrationDao(Connection connection) {
		this.connection = connection;
	}

	public List<CustomerRegistration> getAllCustomerUsers(){
		List<CustomerRegistration> customerUserList = new ArrayList<>();
		CustomerRegistration customerUser = null;
		try {
			PreparedStatement preGetAllCustomerUsers = null;
			String getAllCustomerUsers = "SELECT * FROM CustomerUsers;";
			preGetAllCustomerUsers = connection.prepareStatement(getAllCustomerUsers);
			ResultSet rs = preGetAllCustomerUsers.executeQuery();

			while(rs.next()) {
				customerUser = new CustomerRegistration(rs.getInt(2), rs.getString(3), rs.getString(4), rs.getInt(5));
				customerUser.setUserId(rs.getInt(1));
				customerUserList.add(customerUser);
			}
		} catch (SQLException e) {
			
		}
		
		return customerUserList;
	}
	
	public CustomerRegistration getCustomerUserByUsername(String username) {
			CustomerRegistration customerUser = null;
		try {
			PreparedStatement preGetCustomerRegistration = null;
			String getCustomerRegistration = "SELECT * FROM CustomerUsers WHERE Username = ?";
			preGetCustomerRegistration = connection.prepareStatement(getCustomerRegistration);
			preGetCustomerRegistration.setString(1, username);
			ResultSet rs = preGetCustomerRegistration.executeQuery();
			while(rs.next()) {
				customerUser = new CustomerRegistration(rs.getInt(2), rs.getString(3), rs.getString(4), rs.getInt(5));
				customerUser.setUserId(rs.getInt(1));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return customerUser;
	}
	
	public void addCustomerUser(CustomerRegistration customerUser) {
		try {
			PreparedStatement preAddCustomerUser = null;
			String addCustomerUser = "INSERT INTO CustomerUsers(CustomerId, Username, Password, Status) VALUES (?,?,?,?)";
			preAddCustomerUser = connection.prepareStatement(addCustomerUser);
			preAddCustomerUser.setInt(1,customerUser.getCustomerId());
			preAddCustomerUser.setString(2,customerUser.getUsername());
			preAddCustomerUser.setString(3,customerUser.getPassword());
			preAddCustomerUser.setInt(4,customerUser.getStatus());
			preAddCustomerUser.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

}
