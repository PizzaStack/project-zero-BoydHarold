package com.revature.jdbcinfo;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

public class EstablishConnection {
	private ArrayList<String> connectionInfo;
	public static Connection connection;

	public Connection establishConnection() {
		connectionInfo = new ArrayList<String>();
		try (FileInputStream fis = new FileInputStream(
				"C:\\Users\\boydt\\Desktop\\Project0\\project-zero-BoydHarold\\BankingApp\\JDBCConnectionInfo.txt");
				BufferedReader br = new BufferedReader(new InputStreamReader(fis));) {
			String line = "";
			int count = 1;
			while ((line = br.readLine()) != null) {
				if (count == 1) {
					connectionInfo.add(line);
				} else if (count == 2) {
					connectionInfo.add(line);
				} else if (count == 3) {
					connectionInfo.add(line);
				}
				count++;

			}
		} catch (FileNotFoundException e) {

		} catch (IOException e) {

		}

		try {
			if (connection == null) {
				connection = DriverManager.getConnection(connectionInfo.get(2), connectionInfo.get(0),
						connectionInfo.get(1));
			} else {
				return connection;
			}
		} catch (SQLException e) {

		}

		return connection;

	}

	public void closeConnection() {
		try {
			connection.close();
		} catch (SQLException e) {

		}
	}

}
