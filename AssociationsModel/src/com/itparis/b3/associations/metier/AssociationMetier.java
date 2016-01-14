package com.itparis.b3.associations.metier;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.itparis.b3.associations.beans.Association;
import com.itparis.b3.associations.common.DBSpecialValues.*;
import com.itparis.b3.associations.common.Utilities;
import com.itparis.b3.associations.common.DB;
import com.itparis.b3.associations.dao.AssociationDAO;

public class AssociationMetier {
		
	public static ArrayList<Association> getListAssociations (int idUser, String libAssoc, String nomAssoc, String OrderBy){
		ArrayList<Association>lstAssoc = new ArrayList <Association> ();
		List<Object> lstPValues = new ArrayList<Object> ();
		String filtre = "";
		if (idUser > 0){
			lstPValues.add(idUser);
			filtre += " AND "+DB.Association.alias+".id IN (Select "+DB.FicheParticipant.alias+".idAssociation "+
					  " From "+DB.FicheParticipant+" "+DB.FicheParticipant.alias +
					  " Where "+DB.FicheParticipant.alias+".idUtilisateur = ? )";
		}
		if (!Utilities.isNullOrEmptyString(nomAssoc))
		{
			lstPValues.add(new PreparedLike (nomAssoc));
			filtre += " AND " +DB.AssociationDesc.alias+".nomAssoc LIKE ? ";
		}
		if (!Utilities.isNullOrEmptyString(libAssoc))
		{
			lstPValues.add(new PreparedLike (libAssoc));
			filtre += " AND " +DB.Association.alias+".LibelleAssociation LIKE ? ";
		}
		if (!Utilities.isNullOrEmptyString(OrderBy)){
			filtre += " ORDER BY "+ OrderBy;
		}
		
		HashMap <Integer,Object> params = Utilities.PutParams(lstPValues);
		
		try {
			lstAssoc = AssociationDAO.class.newInstance().getListAssociation(filtre, params);
		} catch (InstantiationException | IllegalAccessException e) {}
		
		return lstAssoc;
	}
	
	public static Association getAssociation (int id) {
		Association a = new Association ();
		String filtre = "";
		if (id > 0){
			filtre += " AND " +DB.Association.alias+".id ="+ id;
		}
		try {
			a = AssociationDAO.class.newInstance().getAssociation(filtre);
		} 
	    catch (IllegalAccessException | InstantiationException e) {}
        return a;
	}
	
	
	
	
	

}
