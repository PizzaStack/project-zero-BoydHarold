package com.revature;

import org.apache.log4j.xml.DOMConfigurator;

import com.revature.consolelogic.Initialize;
import com.revature.consolelogic.LoginDialogue;
import com.revature.jdbcinfo.EstablishConnection;
import com.sun.istack.internal.logging.Logger;


/**
 * Hello world!
 *
 */
public class App 
{
	final static Logger log = Logger.getLogger(App.class);
    public static void main( String[] args )
    { 	
    	DOMConfigurator.configure("log4j.xml");
    	log.info("Application started");
       	Initialize init = new Initialize();
       	LoginDialogue ld = new LoginDialogue();
		EstablishConnection establishConnection = new EstablishConnection();
		init.init(establishConnection.establishConnection());
		ld.login(establishConnection.establishConnection());
     	establishConnection.closeConnection();
    }
}
