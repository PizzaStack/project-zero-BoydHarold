package com.revature;

import java.io.File;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.revature.consolelogic.Initialize;
import com.revature.consolelogic.LoginDialogue;
import com.revature.jdbcinfo.EstablishConnection;

public class App 
{
	static final Logger log = Logger.getLogger(App.class);
    public static void main( String[] args )
    { 	
    	PropertyConfigurator.configure(System.getProperty("user.dir") + File.separator +
                "\\src\\main\\resources\\log4j.properties");
    	
       	Initialize init = new Initialize();
       	LoginDialogue ld = new LoginDialogue();
		EstablishConnection establishConnection = new EstablishConnection();
		establishConnection.establishConnection();
		init.init();
		ld.login();
     	establishConnection.closeConnection();
    }
}
