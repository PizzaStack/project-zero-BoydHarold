package com.revature.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdministrativeFunctionsDao {
	private Connection connection;
	private int successcount;
	
	public AdministrativeFunctionsDao(Connection connection) {
		this.connection = connection;
	}
	
	public void cancelAccount(int id, String accountType) {
		successcount = 0;
		if(accountType.equals("1")) {
			
		try {
			PreparedStatement preCancelCustomerAccount = connection.prepareCall("SELECT cancelcustomeraccount(?);");
			preCancelCustomerAccount.setInt(1, id);
			ResultSet rs = preCancelCustomerAccount.executeQuery();
			
			while(rs.next()) {
				successcount = rs.getInt(1);
			}
		} catch (SQLException e) {
			
		}
			if(successcount == 1 || successcount == 2) {
			System.out.println("Customer account(s) disabled!");
			} else if(successcount == 0){
				System.out.println("Both customers linked to the joint account must have checking accounts. \nAs a result, the funds within the joint account cannot be transferred out of the joint account prior to deletion. \nNo changes made. Setup a checking account for one/both of the customer(s) in question then try again!");
			}
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
