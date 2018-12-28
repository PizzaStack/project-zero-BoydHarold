package com.revature.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.revature.jdbcinfo.EstablishConnection;

public class InitializeDao {
	
	public void init() {
		try {
			PreparedStatement preInit = EstablishConnection.connection.prepareCall("SELECT setupdefaultemployeeandadminaccounts();");
			preInit.executeUpdate();
		} catch (SQLException e) {
			
		}
	}
	
}
