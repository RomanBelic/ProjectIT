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
		
		public static final String GetLoginPassQuery = 
				" SELECT Login, MDP From " + Authentification + " Where Login = ? and MDP = ? ";
		
        public static final String GetAssociationQuery = 
        		" SELECT "+Association.alias+".id, "+Association.alias+".LibelleAssociation," +
                " "+AssociationDesc.alias+ ".id as idDesc,"+
                " "+AssociationDesc.alias+ ".idPresident,"+AssociationDesc.alias+ ".nomAssoc,"+
                " "+AssociationDesc.alias+ ".nbParticipant,"+AssociationDesc.alias+ ".Description"+
        		" FROM "+Association+" "+Association.alias +
        		" "+makeJoin("LEFT", Association, AssociationDesc, "id", "idAssociation") +
        		" Where 1=1 " ;
        	
		public static final String GetUserData = 
				" SELECT idUtilisateur, Login, MDP From " + Authentification + " Where Login = ? and MDP = ? ";
				
		public static final String GetEvent = 
				" SELECT "+AssociationEvents.alias+".id,"+AssociationEvents.alias+".dateEvent," +
		        " "+AssociationEvents.alias+".LibelleEvent, "+AssociationEvents.alias+".descriptionEvent," +
		        " "+AssociationEvents.alias+".nbParticipant,"+AssociationEvents.alias+".idAssociation" +
		        " FROM "+AssociationEvents+" "+AssociationEvents.alias+
		        " WHERE 1=1 ";
		
		public static final String GetEventParticipant = 
				" SELECT "+ParticipantEvents.alias+".idAssociation,"+ParticipantEvents.alias+".idUtilisateur,"+
				" "+ParticipantEvents.alias+".Presence,"+ParticipantEvents.alias+".idEvenement,"+
				" "+Utilisateurs.alias+".nomUtilisateur,"+Utilisateurs.alias+".prenomUtilisateur,"+
				" "+TypeUtilisateurs.alias+".Libelle"+
				" FROM "+ParticipantEvents+" "+ParticipantEvents.alias +
				" "+makeJoin("LEFT", ParticipantEvents, Utilisateurs, "idUtilisateur", "id") +
				" "+makeJoin("LEFT", Utilisateurs, TypeUtilisateurs, "idType", "id") +
				" WHERE 1=1 ";
		
		public static final String GetFicheParticipant =
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
		
		public static final String DeleteUserPQuery = 
				" DELETE FROM "+Utilisateurs+" WHERE "+Utilisateurs+".id = ? ";
				
		public static final String DeleteTypeUserPQuery = 
				" DELETE FROM "+TypeUtilisateurs+" WHERE "+TypeUtilisateurs+".id = ? ";
		
		public static final String DeleteParticipantEventsPQuery = 
				" DELETE FROM "+ParticipantEvents+" WHERE "+ParticipantEvents+".id = ? ";
		
		public static final String DeleteFicheParticipantPQuery = 
				" DELETE FROM "+FicheParticipant+" WHERE "+FicheParticipant+".id = ? ";
		
		public static final String DeleteAuthentificationPQuery = 
				" DELETE FROM "+Authentification+" WHERE "+Authentification+".idUtilisateur = ? ";
		
		public static final String DeleteAssociationEventPQuery = 
				" DELETE FROM "+AssociationEvents+" WHERE "+AssociationEvents+".id = ? ";
		
		public static final String DeleteAssociationDescPQuery = 
				" DELETE FROM "+AssociationDesc+" WHERE "+AssociationDesc+".id = ? ";
		
		public static final String DeleteAssociation = 
				" DELETE FROM "+Association+" WHERE "+Association+".id = ? ";
		
		public static final String InsertNewUser = 
				" START TRANSACTION; " + 
				    " INSERT INTO "+Utilisateurs+" (idType, nomUtilisateur, prenomUtilisateur, adrUtilisateur"+
				    " , telUtilisateur, Statut) "+
				    " VALUES (?, ?, ?, ?, ?, ?); " +
					" SET @id = LAST_INSERT_ID(); " +
					" INSERT INTO "+Authentification+" (idUtilisateur, Login, MDP) " +
					" VALUES (@id, ?, ?); "+
				" END TRANSACTION; ";
		
				
		
		
		
		
		
		
		
		
			
		
		
				

		
		
	}
}
