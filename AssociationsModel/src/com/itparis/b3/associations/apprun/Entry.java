package com.itparis.b3.associations.apprun;

import java.util.HashMap;

import com.itparis.b3.associations.bin.Connexion;
import com.itparis.b3.associations.common.Utilities;

public class Entry {
	
	private void SetConnectionArgs () {
		
		HashMap <String,String> cfg = Utilities.ReadConfig("config.txt", ">");
		Connexion.setDriver(cfg.get("Driver"));
		Connexion.setDriverClass(cfg.get("DriverClass"));
		Connexion.setHost(cfg.get("Host"));
		Connexion.setDb(cfg.get("DataBase"));
		Connexion.setDbparams(cfg.get("Param"));
		Connexion.setLog(cfg.get("Log"));
		Connexion.setPass(cfg.get("Pass"));
	}
	
	public static void main (String[] args) {
		
		Entry entry = new Entry ();
		entry.SetConnectionArgs ();
		

		
	
		
		
	}
	

}
