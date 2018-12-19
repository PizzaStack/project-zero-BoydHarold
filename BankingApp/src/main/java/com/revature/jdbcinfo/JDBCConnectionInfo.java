package com.revature.jdbcinfo;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class JDBCConnectionInfo {
	
	public ArrayList<String> getJDBCCredentials() {
		ArrayList<String> connectionInfo = new ArrayList<String>();
		try
		(
		FileInputStream fis = new FileInputStream("C:\\Users\\boydt\\Desktop\\Project0\\project-zero-BoydHarold\\BankingApp\\JDBCConnectionInfo.txt");
		BufferedReader br = new BufferedReader(new InputStreamReader(fis));
		) {
			String line = "";
			int count = 1;
			while((line = br.readLine()) != null) {
				if(count == 1) {
				connectionInfo.add(line);
				} else if (count == 2) {
					connectionInfo.add(line);
				} else if (count == 3) {
					connectionInfo.add(line);
				}
				count++;

			}
		}
			catch (FileNotFoundException e) {
			
		} catch (IOException e) {
			
		}
		
		return connectionInfo;
	}
}
