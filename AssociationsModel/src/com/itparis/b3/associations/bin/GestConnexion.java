package com.itparis.b3.associations.bin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;

public final class GestConnexion {
	
	/*
	 * Gestionnaire de Connexion a la BDD
	 * Ouvre le state automatiquement a la BDD
	 * Retourne l'objet de State
	 * Fermer le State et la Connexion
	 * 
	 * Les proprietes de la Classe "GestConnexion" pointent vers les proprietes de la Classe "Connexion"
	 */
	
	private Connection con = null;
	private Statement st = null;
	private static GestConnexion instance = new GestConnexion ();
	
	private Statement returnStatement () {
		
		try {
			con = Connexion.getConnection();
			st = con.createStatement();
		}
		catch (Exception e) {e.getMessage();e.printStackTrace();}
		return st;
	}
	
	public static Statement getStatement () {
		return instance.returnStatement();
	}
	
	private PreparedStatement returnPreparedStatement (String preparedQuery) {
		
		try {
			con = Connexion.getConnection();
	        st = (PreparedStatement) con.prepareStatement(preparedQuery);
		}
		catch (Exception e) {e.getMessage();e.printStackTrace();}
		return (PreparedStatement) instance.st;
		
	}
	
	public static PreparedStatement getPreparedStatement (String preparedQuery){
    	return instance.returnPreparedStatement(preparedQuery);
	}
	
	public static void CloseConnection () {
		try {
		if (instance.st != null)
			instance.st.close();
		if (instance.con != null)
			instance.con.close();
		}
		catch (Exception e){e.getMessage();e.printStackTrace();}
	}
}
