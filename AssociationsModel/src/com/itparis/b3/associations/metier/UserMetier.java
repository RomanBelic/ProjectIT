package com.itparis.b3.associations.metier;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.itparis.b3.associations.beans.FicheParticipant;
import com.itparis.b3.associations.beans.User;
import com.itparis.b3.associations.dao.UserDAO;
import com.itparis.b3.associations.common.DB;
import com.itparis.b3.associations.common.Utilities;
import com.itparis.b3.associations.common.DBSpecialValues.*;

public class UserMetier {
	
	public static ArrayList <User> getListUsers (int idAssoc, int idType, String OrderBy) {	
		ArrayList<User> lstUser = new ArrayList<User>();
		String filtre = "";
		List<Object> lstPValues = new ArrayList<Object> ();
		if (idAssoc > 0){
			lstPValues.add(idAssoc);
			filtre += " AND "+DB.Utilisateurs.alias+".id IN (Select "+DB.FicheParticipant.alias+".idUtilisateur "+
					  " From "+DB.FicheParticipant+" "+DB.FicheParticipant.alias +
					  " Where "+DB.FicheParticipant.alias+".idAssociation = ? )";
		}
		if (idType > 0) {
			lstPValues.add(idType);
			filtre += " AND "+DB.Utilisateurs.alias+".idType = ? ";
		}
		if (!Utilities.isNullOrEmptyString(OrderBy)){
			filtre += " ORDER BY "+OrderBy;
		}
		
		HashMap<Integer,Object> params = Utilities.putParams(lstPValues);
		
		try {
			lstUser = UserDAO.class.newInstance().getListUser(filtre, params);
		} 
		catch (InstantiationException | IllegalAccessException e) {}
		return lstUser;
	}
	
	public static User getUser (int id) {
		User u = new User ();
		String filtre = "";
		List<Object> lstPValues = new ArrayList<Object>();
		if (id > 0){
			lstPValues.add(id);
			filtre += " AND "+DB.Utilisateurs.alias+".id = ? ";
		}
			
		HashMap<Integer,Object> params = Utilities.putParams(lstPValues);
		
		try {
			u = UserDAO.class.newInstance().getUser(filtre, params);
		} 
		catch (InstantiationException  | IllegalAccessException e) {}
        return u;
	}
	
	public static ArrayList <FicheParticipant> getListFicheParticipant (int idAssoc, int idUser, int typeUser,
			                                                            String userName, String OrderBy) {	
		ArrayList<FicheParticipant> lstFiche = new ArrayList<FicheParticipant>();
		String filtre = "";
		List<Object> lstPValues = new ArrayList<Object>();
		if (idAssoc > 0){
			lstPValues.add(idAssoc);
			filtre += " AND "+DB.FicheParticipant.alias+".idAssociation = ? ";
		}
		if (idUser > 0) {
			lstPValues.add(idUser);
			filtre += " AND "+DB.FicheParticipant.alias+".idUtilisateur = ? ";
		}
		if (typeUser > 0) {
			lstPValues.add(typeUser);
			filtre += " AND "+DB.TypeUtilisateurs.alias+".id = ? ";
		}
		if (!Utilities.isNullOrEmptyString(userName)){
			lstPValues.add(new PreparedLikeClause (userName));
			filtre += " AND "+DB.Utilisateurs.alias+".nomUtilisateur LIKE ? ";
		}
		if (!Utilities.isNullOrEmptyString(OrderBy)){
			filtre += " ORDER BY "+OrderBy;
		}
		
		HashMap<Integer,Object> params = Utilities.putParams(lstPValues);
		
		try {
			lstFiche = UserDAO.class.newInstance().getListFicheParticipant(filtre, params);
		} 
		catch (InstantiationException | IllegalAccessException e) {}
		return lstFiche;
	}
	public static FicheParticipant getFicheParticipant (int idFiche) {	
		FicheParticipant fp = new FicheParticipant();
		String filtre = "";
		List<Object> lstPValues = new ArrayList<Object>();
		if (idFiche > 0){
			lstPValues.add(idFiche);
	    	filtre += " AND "+DB.FicheParticipant.alias+".id = ? ";
		}
		
		HashMap<Integer,Object> params = Utilities.putParams(lstPValues);
		
		try {
			fp = UserDAO.class.newInstance().getFicheParticipant(filtre, params);
		} 
		catch (InstantiationException | IllegalAccessException e) {}
		return fp;
		}
	
	public static ArrayList<User> getListUsersByStatus (int status, String userName, String OrderBy) {
		ArrayList <User> lstUser = new ArrayList<User> ();
		String filtre = "";
		List <Object> lstPValues = new ArrayList<Object> ();
		lstPValues.add(status);
        filtre += " AND " +DB.Utilisateurs.alias+".Statut = ? ";
        
		if (!Utilities.isNullOrEmptyString(userName)){
			lstPValues.add(userName);
			filtre += " AND "+DB.Utilisateurs.alias+".nomUtilisateur LIKE ? ";
		}
        if (!Utilities.isNullOrEmptyString(OrderBy)){
        	filtre += " ORDER BY "+OrderBy;
        }
        
        HashMap <Integer,Object> params = Utilities.putParams(lstPValues);
        
		try {
			lstUser = UserDAO.class.newInstance().getListUserByState(filtre, params);
		}
		catch (InstantiationException | IllegalAccessException e) {}
		
		return lstUser;
	}
	
	public static User getUserForAdmin (int id) {
		User u = new User ();
		String filtre = "";
		List<Object> lstPValues = new ArrayList<Object>();
		if (id > 0){
			lstPValues.add(id);
			filtre += " AND "+DB.Utilisateurs.alias+".id = "+id;
		}
		
		HashMap <Integer,Object> params = Utilities.putParams(lstPValues);
		
		try {
			u = UserDAO.class.newInstance().getUserSimple(filtre, params);
		} 
		catch (InstantiationException  | IllegalAccessException e) {}
        return u;
	}
	
	
	

}
