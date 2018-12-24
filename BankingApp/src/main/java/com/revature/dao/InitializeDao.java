package com.revature.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class InitializeDao {
	private Connection connection;
	
	public InitializeDao(Connection connection) {
		this.connection = connection;
	}
	
	public void init() {
		try {
			PreparedStatement preInit = connection.prepareCall("SELECT setupdefaultemployeeandadminaccounts();");
			preInit.executeUpdate();
		} catch (SQLException e) {
			
		}
	}
	
}
