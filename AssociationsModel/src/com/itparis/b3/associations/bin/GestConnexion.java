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
	private static Statement st = null;
		
	public static Statement getStatement (Class <? extends Statement> cls, String preparedQuery){
		try {
			con = Connexion.getConnection();
			
			if (cls.getName() == PreparedStatement.class.getName()) {
	            st = (PreparedStatement) con.prepareStatement(preparedQuery);
	            return st;
			}
			if (cls.getName() == Statement.class.getName()) {
				st = (Statement) con.createStatement();
				return st;
			}
		}
		catch (Exception e) {}
	return st;
	}
	
	public static void CloseConnection () {
		try {
		if (st != null)
			st.close();
		if (con != null)
			con.close();
		}
		catch (Exception e){e.getMessage();e.printStackTrace();}
	}
	
}
