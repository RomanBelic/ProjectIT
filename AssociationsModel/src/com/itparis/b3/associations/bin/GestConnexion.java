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
	
	private static Connection con = null;
	private static PreparedStatement pst = null;
	private static Statement st = null;
		
	public static Statement GetStatement ()  {
		//Connection con = null;
		//Statement st = null;
		try {
			con = Connexion.getConnection();
			st = con.createStatement();
		}
		catch (Exception e){e.getMessage();e.printStackTrace();}
	   return st;
	}
	
	public static Statement GetPreparedStatement (String req)  {
		//Connection con = null;
		//PreparedStatement pst = null;
		try {
			con = Connexion.getConnection();
			pst = con.prepareStatement(req);
		}
		catch (Exception e){e.getMessage();e.printStackTrace();}
	   return pst;
	}
	
	public static void CloseConnection () {
		try {
		if (st != null)
			st.close();
		if (pst != null)
			pst.close();
		if (con != null)
			con.close();
		}
		catch (Exception e){e.getMessage();e.printStackTrace();}
	}
	
}
