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
		if(accountType.equals("1")) {
			
		try {
			PreparedStatement preCancelCustomerAccount = connection.prepareCall("SELECT cancelcustomeraccount(?);");
			preCancelCustomerAccount.setInt(1, id);
			preCancelCustomerAccount.executeUpdate();
		} catch (SQLException e) {
			
		}
			System.out.println("Customer account(s) disabled!");
		} else if(accountType.equals("2")) {

			try {
				PreparedStatement preCancelEmployeeAccount = connection.prepareCall("SELECT cancelemployeeaccount(?);");
				preCancelEmployeeAccount.setInt(1, id);
				preCancelEmployeeAccount.executeUpdate();
			} catch (SQLException e) {
				
			}
			System.out.println("Employee account(s) disabled!");
		} else if(accountType.equals("3")) {
			
			try {
				PreparedStatement preCancelAdministratorAccount = connection.prepareCall("SELECT canceladminaccount(?);");
				preCancelAdministratorAccount.setInt(1, id);
				preCancelAdministratorAccount.executeUpdate();
			} catch (SQLException e) {
				
			}
			System.out.println("Administrator account(s) disabled!");
		}
		
		
	}
}
