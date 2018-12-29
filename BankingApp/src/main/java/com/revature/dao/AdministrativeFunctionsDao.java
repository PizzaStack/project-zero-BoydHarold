package com.revature.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.revature.jdbcinfo.EstablishConnection;

public class AdministrativeFunctionsDao {
	private int successcount;
	
	
	public void cancelAccount(int id, String accountType) {
		successcount = 0;
		if(accountType.equals("1")) {
			
		try {
			PreparedStatement preCancelCustomerAccount = EstablishConnection.connection.prepareCall("SELECT cancelcustomeraccount(?);");
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
				PreparedStatement preCancelEmployeeAccount = EstablishConnection.connection.prepareCall("SELECT cancelemployeeaccount(?);");
				preCancelEmployeeAccount.setInt(1, id);
				preCancelEmployeeAccount.executeUpdate();
			} catch (SQLException e) {
				
			}
			System.out.println("Employee account(s) disabled!");
		} else if(accountType.equals("3")) {
			
			try {
				PreparedStatement preCancelAdministratorAccount = EstablishConnection.connection.prepareCall("SELECT canceladminaccount(?);");
				preCancelAdministratorAccount.setInt(1, id);
				preCancelAdministratorAccount.executeUpdate();
			} catch (SQLException e) {
				
			}
			System.out.println("Administrator account(s) disabled!");
		}
		
	}
	
	public void activateAccount(int id, String accountType) {
		if(accountType.equals("1")) {
			try {
				PreparedStatement preCancelCustomerAccount = EstablishConnection.connection.prepareCall("SELECT activatecustomeraccount(?);");
				preCancelCustomerAccount.setInt(1, id);
				preCancelCustomerAccount.executeUpdate();
			} catch (SQLException e) {
				
			}
			System.out.println("Customer account(s) enabled!");
		} else if(accountType.equals("2")) {
			try {
				PreparedStatement preCancelEmployeeAccount = EstablishConnection.connection.prepareCall("SELECT activateemployeeaccount(?);");
				preCancelEmployeeAccount.setInt(1, id);
				preCancelEmployeeAccount.executeUpdate();
			} catch (SQLException e) {
				
			}
			System.out.println("Employee account(s) enabled!");
		} else if(accountType.equals("3")) {
			try {
				PreparedStatement preCancelAdministratorAccount = EstablishConnection.connection.prepareCall("SELECT activateadminaccount(?);");
				preCancelAdministratorAccount.setInt(1, id);
				preCancelAdministratorAccount.executeUpdate();
			} catch (SQLException e) {
				
			}
			System.out.println("Administrator account(s) enabled!");
		}
	}
}
