package com.revature;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.*;

public class JDBCHelper {
	private String username;
	private String password;
	private String url;
	
	public void getJDBCCredentials() {
		try
		(
		FileInputStream fis = new FileInputStream("C:\\Users\\boydt\\Desktop\\Project0\\project-zero-BoydHarold\\BankingApp\\JDBCConnectionInfo.txt");
		BufferedReader br = new BufferedReader(new InputStreamReader(fis));
		) {
			String line = "";
			int count = 1;
			while((line = br.readLine()) != null) {
				if(count == 1) {
				this.username = line;
				} else if (count == 2) {
					this.password = line;
				} else if (count == 3) {
					this.url = line;
				}
				count++;

			}
		}
			catch (FileNotFoundException e) {
			
		} catch (IOException e) {
			
		}
	}
	
	public void establishConnection() {
		getJDBCCredentials();
		try {
			Connection connection = DriverManager.getConnection(url, username, password);
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery("SELECT * FROM Pizza;");
			
			while(resultSet.next()) {
				System.out.println(resultSet.getString(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	

}
