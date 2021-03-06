package com.itparis.b3.associations.apprun;

import java.util.HashMap;

import com.itparis.b3.associations.bin.Connexion;
import com.itparis.b3.associations.common.Utilities;

/**
 * @role Entry Point to application
 *       <br> Contains app Main() method, sets connection parameters to SQL database
 */
public class Entry {
	
	public void setConnectionArgs () {
		HashMap <String,String> settings = Utilities.loadConfig("config.txt", ">");
		Connexion.setDriver(settings.get("Driver"));
		Connexion.setDriverClass(settings.get("DriverClass"));
		Connexion.setHost(settings.get("Host"));
		Connexion.setDb(settings.get("DataBase"));
		Connexion.setDbparams(settings.get("Param"));
		Connexion.setLog(settings.get("Log"));
		Connexion.setPass(settings.get("Pass"));
	}
	
	public static void main (String[] args) {
		
		Entry entry = new Entry ();
		entry.setConnectionArgs ();
		
	}
	

}
