package com.itparis.b3.associations.bin;
import java.sql.Connection;
import java.sql.DriverManager;

/*
 * Classe Connexion;
 * Contient les methodes et parametres de connexion a la BDD;
 * */
public final class Connexion  {
	
	private static String host = "";       //"localhost";
	private static String log = "";        //"root";
	private static String pass = "";       //"";
	private static String db = "";         //"dbassociations";
	private static String driver = "";     //"jdbc:mysql:";
	private static String driverClass = "";//"com.mysql.jdbc.Driver";
	private static String dbparams = "";   //"?zeroDateTimeBehavior=convertToNull&autoReconnect=true"
				                           //+ "&characterEncoding=UTF-8&characterSetResults=UTF-8"
	                                       //+ "&allowMultiQueries=true";

	private static Connexion instance = new Connexion();
	
	public static void setHost(String host) {
		Connexion.host = host;
	}

	public static void setLog(String log) {
		Connexion.log = log;
	}

	public static void setPass(String pass) {
		Connexion.pass = pass;
	}

	public static void setDb(String db) {
		Connexion.db = db;
	}

	public static void setDriver(String driver) {
		Connexion.driver = driver;
	}

	public static void setDriverClass(String driverClass) {
		Connexion.driverClass = driverClass;
	}

	public static void setDbparams(String dbparams) {
		Connexion.dbparams = dbparams;
	}

	public static void setInstance(Connexion instance) {
		Connexion.instance = instance;
	}
	
    private Connection CreateConnection () 
	{
    	Connection con = null;
    	try{
    		Class.forName(driverClass);
	        try {
	            con = DriverManager.getConnection(driver + "//" + host + "/" + db + dbparams, log, pass);
	        }
	        catch (Exception e)
	        {
	        	e.getMessage();
	        	System.out.println("connection could not be opened ");
	        }
    	}
    	catch (Exception e){
    		e.getMessage();
    		System.out.println ("Driver not found");
    	}
        return con;
	}
    
    public static Connection getConnection ()
    {
    	return instance.CreateConnection();
    }  
}
