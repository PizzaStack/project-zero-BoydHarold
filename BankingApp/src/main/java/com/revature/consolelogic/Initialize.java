package com.revature.consolelogic;

import java.sql.Connection;

import com.revature.dao.InitializeDao;

public class Initialize {
	
	public void init(Connection connection) {
		LoginDialogue loginDialogue = new LoginDialogue();
		InitializeDao initializeDao = new InitializeDao(connection);
		initializeDao.init();
		loginDialogue.login(connection);
		
	}
}
