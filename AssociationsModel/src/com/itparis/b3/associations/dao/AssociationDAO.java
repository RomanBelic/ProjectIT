package com.itparis.b3.associations.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import com.itparis.b3.associations.beans.Association;
import com.itparis.b3.associations.bin.Connexion;
import com.itparis.b3.associations.common.DB.Queries;
import com.itparis.b3.associations.common.Utilities;

public class AssociationDAO {
	
	private Association RemplirAssociation (ResultSet rs){
		
		Association a = new Association ();
		try {
            a.setId(rs.getInt("id"));
            a.setLibelle(rs.getString("LibelleAssociation"));
	        a.desc.setId(rs.getInt("idDesc"));
	        a.desc.setIdPresident(rs.getInt("idPresident"));
	        a.desc.setNbParticipant(rs.getInt("nbParticipant"));;
	        a.desc.setNomAssoc(rs.getString("nomAssoc"));
	        a.desc.setDescription(rs.getString("Description"));  
		}
		catch (Exception e){
			e.getMessage();
			e.printStackTrace();
		}
		return a;
	} 

	public ArrayList<Association> getListAssociation (String filtre, HashMap <Integer, Object> params) {
		Connection con = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		
		String req = Queries.GetAssociationQuery + filtre;
		
		ArrayList <Association> lstAssoc = new  ArrayList<Association>();
		try {
		    con = Connexion.getConnection();
		    st = con.prepareStatement(req);
		    
		    Utilities.setSQLParams (st, params);
		    
		    rs = st.executeQuery();
		    while (rs.next()) {		            
		    	Association a = RemplirAssociation (rs);
		    	lstAssoc.add(a);
	           	}
		}
		catch (Exception e){
			e.getMessage();
			e.printStackTrace();
			Association a = new Association();
			lstAssoc.add(a);
		    Utilities.setListError(lstAssoc);
		}
	    try {
	    	if (rs != null) rs.close();
	    	if (st != null) st.close();
	    	if (con != null) con.close();
	    }
	    catch (Exception e){}
		return lstAssoc;

	}
	
	public Association getAssociation (String filtre,HashMap<Integer,Object> params) {
		Connection con = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		
		String req = Queries.GetAssociationQuery + filtre;
		
		Association a = new Association ();
		
		try {
		    con = Connexion.getConnection();
		    st = con.prepareStatement(req);
		    
		    Utilities.setSQLParams(st, params);
		    
		    rs = st.executeQuery();
		    if (rs.next()) {		            
		    	a = RemplirAssociation (rs);
	           	}
		}
		catch (Exception e){
			e.getMessage();
			e.printStackTrace();
		    Utilities.setError(a);
		}
	    try {
	    	if (rs != null) rs.close();
	    	if (st != null) st.close();
	    	if (con != null) con.close();
	    }
	    catch (Exception e){}
		return a;
	}
	

}
