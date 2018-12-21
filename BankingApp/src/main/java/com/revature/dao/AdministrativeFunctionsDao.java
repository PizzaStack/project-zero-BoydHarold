package com.revature.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AdministrativeFunctionsDao {
	private Connection connection;
	
	public AdministrativeFunctionsDao(Connection connection) {
		this.connection = connection;
	}
	
	public void cancelAccount(int id, String accountType) {
		if(accountType.equals("Customer")) {
			
		try {
			PreparedStatement preCancelCustomerAccount = connection.prepareCall("SELECT cancelcustomeraccount(?);");
			preCancelCustomerAccount.setInt(1, id);
			preCancelCustomerAccount.executeUpdate();
		} catch (SQLException e) {
			
		}
		
		} else if(accountType.equals("Employee")) {

			try {
				PreparedStatement preCancelEmployeeAccount = connection.prepareCall("SELECT cancelemployeeaccount(?);");
				preCancelEmployeeAccount.setInt(1, id);
				preCancelEmployeeAccount.executeUpdate();
			} catch (SQLException e) {
				
			}
			
		} else if(accountType.equals("Administrator")) {
			
			try {
				PreparedStatement preCancelAdministratorAccount = connection.prepareCall("SELECT canceladminaccount(?);");
				preCancelAdministratorAccount.setInt(1, id);
				preCancelAdministratorAccount.executeUpdate();
			} catch (SQLException e) {
				
			}
			
		}
	}
}
