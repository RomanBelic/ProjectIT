package com.itparis.b3.associations.metier;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.itparis.b3.associations.beans.AssociationEvent;
import com.itparis.b3.associations.beans.ParticipantEvents;
import com.itparis.b3.associations.common.DB;
import com.itparis.b3.associations.common.Utilities;
import com.itparis.b3.associations.dao.EventDAO;
import com.itparis.b3.associations.common.DBSpecialValues.*;

public class EventMetier {
	
	public static ArrayList <AssociationEvent> getListAssociationEvents (int idAssoc, String libEvent, String dateEvent,
			                                                         String OrderBy) {
		ArrayList <AssociationEvent> lstEvents = new ArrayList<AssociationEvent>();
		List<Object>lstPValues = new ArrayList <Object>();
		String filtre = "";
		if (idAssoc > 0){
	        lstPValues.add(idAssoc);
			filtre += " AND "+DB.AssociationEvents.alias+".idAssociation = ? ";
		}
		if (!Utilities.isNullOrEmptyString(libEvent)){
			lstPValues.add(new PreparedLikeClause(libEvent));
			filtre += " AND " +DB.AssociationEvents.alias+".LibelleEvent LIKE ? ";
		}
		if (!Utilities.isNullOrEmptyString(dateEvent)){
			lstPValues.add(dateEvent);
			filtre += " AND " +DB.AssociationEvents.alias+".dateEvent =  ? ";
		}
		if (!Utilities.isNullOrEmptyString(OrderBy)){
			filtre += " ORDER BY "+OrderBy;
		}	
		
		HashMap<Integer,Object> params = Utilities.putParams(lstPValues); 
		
		try {
			lstEvents = EventDAO.class.newInstance().getListAssociationEvents(filtre,params);
		} catch (InstantiationException | IllegalAccessException e) {}
		
		return lstEvents;	
	}
	
	public static AssociationEvent getAssociationEvent (int id) {
		AssociationEvent ae = new AssociationEvent ();
		String filtre = "";
		List<Object> lstPValues = new ArrayList<Object>();
		if (id > 0) {
			lstPValues.add(id);
			filtre += " AND "+DB.AssociationEvents.alias+".id = ? ";
		}
		
		HashMap<Integer,Object> params = Utilities.putParams(lstPValues) ;
		
		try{
			ae = EventDAO.class.newInstance().getAssociationEvent(filtre, params);
		}
		catch (InstantiationException | IllegalAccessException e) {}
		
		return ae;
	}
	
	public static ArrayList<ParticipantEvents> getListParticipantEvents (int idAssoc, int idEvent, String 
			                                                             UserName,String OrderBy) {
		ArrayList <ParticipantEvents> lstParticipant = new ArrayList<ParticipantEvents>();
		String filtre = "";
		List<Object> lstPValues = new ArrayList<Object> ();
		if (idAssoc > 0){
			lstPValues.add(idAssoc);
		    filtre += " AND "+DB.ParticipantEvents.alias+".idAssociation = ?";
		}
		if (idEvent > 0){
			lstPValues.add(idEvent);
			filtre += " AND "+DB.ParticipantEvents.alias+".idEvenement = ? ";
		}
		if (!Utilities.isNullOrEmptyString(UserName)){
			lstPValues.add(new PreparedLikeClause(UserName));
			filtre += " AND " +DB.Utilisateurs.alias+".nomUtilisateur LIKE ? ";
		}
		if (!Utilities.isNullOrEmptyString(OrderBy)){
			filtre += " ORDER BY "+OrderBy;
		}	
		
		HashMap<Integer,Object> params = Utilities.putParams(lstPValues);
		
		try {
			lstParticipant = EventDAO.class.newInstance().getListParticipantEvents(filtre, params);
		} catch (InstantiationException | IllegalAccessException e) {}
		
		return lstParticipant;
	}

	public static ParticipantEvents getParticipantEvent (int idAssoc, int idUser, int idEvent) {
	    ParticipantEvents pe = new ParticipantEvents();
     	String filtre = "";
     	List<Object> lstPValues = new ArrayList<Object>();
		if (idAssoc > 0){
			lstPValues.add(idAssoc);
	    	filtre += " AND "+DB.ParticipantEvents.alias+".idAssociation = ? ";
		}
		if (idUser > 0){
			lstPValues.add(idUser);
	     	filtre += " AND "+DB.ParticipantEvents.alias+".idUtilisateur = ? ";
		}
		if (idEvent > 0){
			lstPValues.add(idEvent);
		    filtre += " AND " +DB.ParticipantEvents.alias+".idEvenement = ? ";
		}	
		
		HashMap<Integer,Object> params = Utilities.putParams(lstPValues);
		
		try {
		    pe = EventDAO.class.newInstance().getParticipantEvents(filtre, params);
		} catch (InstantiationException | IllegalAccessException e) {}
	
	    return pe;
	}
	
	

}
