package com.itparis.b3.associations.zzzdeveloppementzzz;

import java.sql.*;

import com.itparis.b3.associations.bin.Connexion;

public class ObjectTheory {
	private static Statement st = null;
	private static Connection con = null;
	
	@SuppressWarnings("unchecked")
	public static <T extends Statement> T ReturnState (Class <? extends Statement> cls){
		try {
			con = Connexion.getConnection();
			
			if (cls.getName() == PreparedStatement.class.getName()) {
	            st = (PreparedStatement) con.prepareStatement("?");
	            return (T)st;
			}
			if (cls.getName() == Statement.class.getName()) {
				st = (Statement) con.createStatement();
				return (T) st;
			}
		}
		catch (Exception e) {}
	  	
	return null;
	}
	
	public static void main (String[]args){
		ReturnState(Statement.class);


	}
	

}
