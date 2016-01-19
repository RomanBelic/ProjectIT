package com.itparis.b3.associations.common;
import com.itparis.b3.associations.common.Table.*;

/* Classe BD;
 * Contient les Requetes parametrees
 * */

public final class DB {
	
	public static final String DBNullValue = "NULL";
	public static final String DBName = "dbassociations";
	
	public static final Table Utilisateurs = new UserTable ("utilisateurs","u");
	public static final Table TypeUtilisateurs = new TypeUserTable ("typeutilisateur","t");
	public static final Table Association = new AssociationTable ("association","a");
	public static final Table AssociationEvents = new AssociationEventsTable ("associationevents","e");
	public static final Table AssociationDesc = new AssociationDescTable ("associationdescription","d");
	public static final Table Authentification= new AuthentificationTable ("authentification","n");
	public static final Table FicheParticipant = new FicheParticipantTable ("ficheparticipant","f");
	public static final Table ParticipantEvents = new ParticipantEventsTable ("participantsevents","p");
	
	public static String makeJoin (String JoinType, Table Table1, Table Table2, String idKey1, String idKey2) {
		String str = " " + JoinType.toUpperCase() + " JOIN " + Table2 + " " + Table2.alias + " ON " +
			               Table1.alias + "." + idKey1 + "="+Table2.alias + "." + idKey2 + " ";
		return str;
	}
	
	public static class Queries {

		public static final String GetUserQuery = 
				" SELECT "+Utilisateurs.alias+".nomUtilisateur, "+Utilisateurs.alias+".prenomUtilisateur," +
		        " "+Utilisateurs.alias+".adrUtilisateur, "+Utilisateurs.alias+".telUtilisateur," +
				" "+Utilisateurs.alias+".idType, "+Utilisateurs.alias+".id," + 
		        " "+TypeUtilisateurs.alias+".Libelle" + 
		        " FROM " +Utilisateurs+" "+Utilisateurs.alias +
				" "+makeJoin("LEFT", Utilisateurs, TypeUtilisateurs, "idType", "id") +
				" Where 1=1 AND " +Utilisateurs.alias+".Statut = 1 ";
		
		public static final String GetLoginPassPQuery = 
				" SELECT Login, MDP From " + Authentification + " Where Login = ? and MDP = ? ; ";
		
        public static final String GetAssociationQuery = 
        		" SELECT "+Association.alias+".id, "+Association.alias+".LibelleAssociation," +
                " "+AssociationDesc.alias+ ".id as idDesc,"+
                " "+AssociationDesc.alias+ ".idPresident,"+AssociationDesc.alias+ ".nomAssoc,"+
                " "+AssociationDesc.alias+ ".nbParticipant,"+AssociationDesc.alias+ ".Description"+
        		" FROM "+Association+" "+Association.alias +
        		" "+makeJoin("LEFT", Association, AssociationDesc, "id", "idAssociation") +
        		" Where 1=1 " ;
        	
		public static final String GetUserDataPQuery = 
				" SELECT idUtilisateur, Login, MDP From " + Authentification + " Where Login = ? and MDP = ? ; ";
				
		public static final String GetEventQuery = 
				" SELECT "+AssociationEvents.alias+".id,"+AssociationEvents.alias+".dateEvent," +
		        " "+AssociationEvents.alias+".LibelleEvent, "+AssociationEvents.alias+".descriptionEvent," +
		        " "+AssociationEvents.alias+".nbParticipant,"+AssociationEvents.alias+".idAssociation" +
		        " FROM "+AssociationEvents+" "+AssociationEvents.alias+
		        " WHERE 1=1 ";
		
		public static final String GetEventParticipantQuery = 
				" SELECT "+ParticipantEvents.alias+".idAssociation,"+ParticipantEvents.alias+".idUtilisateur,"+
				" "+ParticipantEvents.alias+".Presence,"+ParticipantEvents.alias+".idEvenement,"+
				" "+Utilisateurs.alias+".nomUtilisateur,"+Utilisateurs.alias+".prenomUtilisateur,"+
				" "+TypeUtilisateurs.alias+".Libelle"+
				" FROM "+ParticipantEvents+" "+ParticipantEvents.alias +
				" "+makeJoin("LEFT", ParticipantEvents, Utilisateurs, "idUtilisateur", "id") +
				" "+makeJoin("LEFT", Utilisateurs, TypeUtilisateurs, "idType", "id") +
				" WHERE 1=1 ";
		
		public static final String GetFicheParticipantQuery =
				" SELECT "+FicheParticipant.alias+".id,"+FicheParticipant.alias+".idUtilisateur,"+
				" "+FicheParticipant.alias+".idAssociation,"+FicheParticipant.alias+".dateInscription,"+
				" "+FicheParticipant.alias+".dateDesinscription,"+FicheParticipant.alias+".Notes,"+
				" "+FicheParticipant.alias+".Anciennete,"+
				" "+Utilisateurs.alias+".nomUtilisateur, "+Utilisateurs.alias+".prenomUtilisateur," +
		        " "+Utilisateurs.alias+".adrUtilisateur, "+Utilisateurs.alias+".telUtilisateur," +
		        " "+TypeUtilisateurs.alias+".Libelle"+
				" FROM "+FicheParticipant+" "+FicheParticipant.alias+
				" "+makeJoin("LEFT", FicheParticipant, Utilisateurs, "idUtilisateur", "id")+
				" "+makeJoin("LEFT", Utilisateurs, TypeUtilisateurs, "idType", "id")+
				" WHERE 1=1 ";
		
		public static final String GetUserByStatusQuery = 
				" SELECT "+Utilisateurs.alias+".nomUtilisateur, "+Utilisateurs.alias+".prenomUtilisateur," +
				" "+Utilisateurs.alias+".id,"+Utilisateurs.alias+".Statut"+ 
				" FROM " +Utilisateurs+" "+Utilisateurs.alias +
				" Where 1=1 ";
		
		public static final String GetTypeUserQuery = 
				" SELECT " + TypeUtilisateurs.alias + ".id, " + TypeUtilisateurs.alias + ".Libelle " +
				" FROM " + TypeUtilisateurs + " " + TypeUtilisateurs.alias + " WHERE 1=1 ";

		public static final String DeleteTypeUserPQuery = 
				" DELETE FROM "+TypeUtilisateurs+" WHERE "+TypeUtilisateurs+".id = ? ;";
		
		public static final String DeleteParticipantEventPQuery = 
				" DELETE FROM "+ParticipantEvents+" WHERE "+ParticipantEvents+".idUtilisateur = ? "+
		        " AND "+ParticipantEvents+".idEvenement = ? AND "+ParticipantEvents+".idAssociation = ? ;";
				
		public static final String DeleteFicheParticipantPQuery = 
				" DELETE FROM "+FicheParticipant+" WHERE "+FicheParticipant+".id = ? ;";
		
		public static final String DeleteAuthentificationPQuery = 
				" DELETE FROM "+Authentification+" WHERE "+Authentification+".idUtilisateur = ? ;";
		
		public static final String DeleteAssociationEventPQuery = 
				" DELETE FROM "+AssociationEvents+" WHERE "+AssociationEvents+".id = ? ;";
		
		public static final String DeleteAssociationDescPQuery = 
				" DELETE FROM "+AssociationDesc+" WHERE "+AssociationDesc+".id = ? ;";
		
		public static final String DeleteAssociationPQuery = 
				" DELETE FROM "+Association+" WHERE "+Association+".id = ? ;";
		
		public static final String DeleteUserAndAuthPQuery = 
				" START TRANSACTION; " + 
				" DELETE FROM "+Utilisateurs+" WHERE "+Utilisateurs+".id = ? ; " +
		        " DELETE FROM "+Authentification+" WHERE "+Authentification+".idUtilisateur = ? ;";
		
		public static final String InsertNewUser = 
				" START TRANSACTION; " + 
				    " INSERT INTO "+Utilisateurs+" (idType, nomUtilisateur, prenomUtilisateur, adrUtilisateur"+
				    " , telUtilisateur, Statut) "+
				    " VALUES (?, ?, ?, ?, ?, ?); " +
					" SET @id = LAST_INSERT_ID(); " +
					" INSERT INTO "+Authentification+" (idUtilisateur, Login, MDP) " +
					" VALUES (@id, ?, ?); ";
		
		public static final String InsertNewAssociation = 
				" START TRANSACTION; " + 
					    " INSERT INTO " + Association + " (LibelleAssociation) VALUES ( ? ); " + 
						" SET @id = LAST_INSERT_ID(); " +
						" INSERT INTO " + AssociationDesc + " (idAssociation, idPresident, nomAssoc ," + 
						" nbParticipant, Description) " +
						" VALUES (@id, ?, ?, ?, ?); ";
				
		public static final String InsertNewTypeUser = 
				"INSERT INTO " + TypeUtilisateurs + " (id, Libelle) VALUES (? , ? );"; 
		
		public static final String InsertNewTypeUser2 = 
				"START TRANSACTION; " +
				"SELECT IF (EXISTS(SELECT id FROM "+TypeUtilisateurs+" WHERE id = ? ), 1, 0) as Existence; "+
				"INSERT INTO " + TypeUtilisateurs + " (id, Libelle) VALUES (? , ? );"; 
		
		public static final String InsertNewEvent = 
				"INSERT INTO " + AssociationEvents + " (dateEvent, LibelleEvent, descriptionEvent,"+
				"nbParticipant, idAssociation) "+
			    "VALUES (?, ?, ?, ?, ? );";
		
		public static final String InsertNewUserIntoAssociation = 
				"INSERT INTO " + FicheParticipant + " (idUtilisateur, dateInscription, idAssociation)"+
			    "VALUES (?, ?, ? );";
		
		public static final String InsertNewParticipantEvents = 
				"INSERT INTO " + ParticipantEvents + " (idAssociation, idEvenement, idUtilisateur)"+
			    "VALUES (?, ?, ? );";

		public static final String CheckExistingFieldPQuery (Table table, String field)
		{
		  return " SELECT IF (EXISTS " +
		  		 "(SELECT " + field + " FROM " + table + " WHERE " + field + " = ? ), 1, 0) " +
		  		 "as Existence; ";
		}
				

		
		
		
		
		
		
		
			
		
		
				

		
		
	}
}
