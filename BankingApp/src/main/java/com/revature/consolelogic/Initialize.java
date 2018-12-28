package com.revature.consolelogic;


import com.revature.dao.InitializeDao;

public class Initialize {
	public void init() {
		LoginDialogue loginDialogue = new LoginDialogue();
		InitializeDao initializeDao = new InitializeDao();
		initializeDao.init();
		loginDialogue.login();
		
	}
}
