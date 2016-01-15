package com.itparis.b3.associations.common;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.itparis.b3.associations.bin.Connexion;

/*
 * Classe ReqMetier;
 * Contient des methodes DAO parametrees;
 * */

public class ReqMetier {
	
	public static int executeUpdate(String req) {
		int rows = 0;
		Connection con = null;
		Statement st = null;
		try {
			con = Connexion.getConnection();
		    con.setAutoCommit(false);
			st = con.createStatement();
			rows = st.executeUpdate(req);
			con.commit();
		}
		catch (Exception e) {
			e.getMessage();
			e.printStackTrace();
			try {
			    if (con != null) con.rollback();
			}
			catch (Exception e1) {e.getMessage();e.printStackTrace();}
		}
		try {
			if (st != null) st.close();
			if (con != null){
				con.setAutoCommit(true);
				con.close();
				}
		} catch (Exception e) {}
		return rows;
	}

	public static int executePreparedQuery(String req, HashMap<Integer, Object> params) {
		int rows = 0;
		Connection con = null;
		PreparedStatement st = null;
		try {
			con = Connexion.getConnection();
			con.setAutoCommit(false);
			st = con.prepareStatement(req);
			
			Utilities.setSQLParams(st, params);
			
			rows = st.executeUpdate();
			con.commit();
		} catch (Exception e) {
			e.getMessage();
			e.printStackTrace();
			if (con != null)
				try {
					con.rollback();
				} 
			    catch (SQLException e1) {e1.printStackTrace();e1.getMessage();}
		}
		try {
			if (st != null) st.close();
			if (con != null){
				con.setAutoCommit(true);
				con.close();
			}
		} catch (Exception e) {}
		return rows;
	}

	public static int executeParameteredUpdate(String table, HashMap<String, String> paramsTable, String paramWhere) {
		int rows = 0;
		String req = "Update " + table + " SET ";
		Connection con = null;
		Statement st = null;
		try {
			con = Connexion.getConnection();
			con.setAutoCommit(false);
			st = con.createStatement();

			if (paramsTable.size() > 0) {
				for (Map.Entry<String, String> entry : paramsTable.entrySet()) {
					req += entry.getKey() + " = " + entry.getValue() + ", ";
				}
				int index = req.lastIndexOf(",");
				req = req.substring(0, index);
			}

			req += " Where 1=1 " + paramWhere;

			rows = st.executeUpdate(req);
			con.commit();
		} catch (Exception e) {
			e.printStackTrace();
			e.getMessage();
			try {
				con.rollback();
			} catch (SQLException e1) {}
		}
		try {
			if (st != null) st.close();
			if (con != null){
				con.setAutoCommit(true);
				con.close();
				}
		} catch (Exception e) {
		}
		return rows;
	}

	public static int executeParameteredDelete(String table, String paramWhere) {
		int rows = 0;
		String req = "Delete From " + table + " Where 1=1 ";
		Connection con = null;
		Statement st = null;
		try {
			con = Connexion.getConnection();
			con.setAutoCommit(false);
			st = con.createStatement();
            req += paramWhere;
			
			rows = st.executeUpdate(req);
			con.commit();
		} catch (Exception e) {
			e.printStackTrace();
			e.getMessage();
			try {
				con.rollback();
			} catch (SQLException e1) {}
		}
		try {
			if (st != null) st.close();
			if (con != null){
				 con.setAutoCommit(true);
				 con.close();
				}
		} catch (Exception e) {	
		}
		return rows;
	}
	
	public static int executeParameteredInsert(String table, HashMap<String,String>paramsTable) {
		int rows = 0;
		Connection con = null;
		Statement st = null;
		String req = "Insert Into "+ table +" (";
		try {
			con = Connexion.getConnection();
			con.setAutoCommit(false);
			st = con.createStatement();
			
			if (paramsTable.size() > 0) {
				for (Map.Entry<String, String> entry : paramsTable.entrySet()) {
					req += entry.getKey()+ ", ";
				}
				int index = req.lastIndexOf(",");
				req = req.substring(0, index);
			}
			
			req +=") Values (";
			
			if (paramsTable.size() > 0) {
				for (Map.Entry<String, String> entry : paramsTable.entrySet()) {
					req += entry.getValue()+ ", ";
				}
				int index = req.lastIndexOf(",");
				req = req.substring(0, index);
			}
			req += ")";
			
			rows = st.executeUpdate(req);
			con.commit();
		}
		catch (Exception e){
			e.getMessage();
			e.printStackTrace();
			try {
				con.rollback();
			} catch (SQLException e1) {}
		}
		try {
			if (st != null) st.close();
			if (con != null){
				 con.setAutoCommit(true);
				 con.close();
				}
		} catch (Exception e) {}
		return rows;
	}
	
	public static int executeBatch (List<String>lstQueries) {
		Connection con = null;
		Statement st = null;
		int[] rowsArray = {0};
		int rows = 0;
		try {
			if (lstQueries.size() > 0){
				con = Connexion.getConnection();
				con.setAutoCommit(false);
				st = con.createStatement();
			    for (String s : lstQueries){
			    	st.addBatch(s);
			    }
				rowsArray = st.executeBatch();
				con.commit();
				
				for (int i : rowsArray) {
					rows += rowsArray [i];
				}
			}
		}
		catch (Exception e){
			e.getMessage();
			e.printStackTrace();
			try {
				con.rollback();
			} catch (SQLException e1) {}
		}
		try {
			if (st != null) st.close();
			if (con != null){
				con.setAutoCommit(true);
				con.close();
			}
		} catch (Exception e) {}	
		return rows;
	}

}
