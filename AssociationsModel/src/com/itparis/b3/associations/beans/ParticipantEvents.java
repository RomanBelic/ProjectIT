package com.itparis.b3.associations.beans;
/*
 * Classe ParticipantEvents est un modele de donnees;
 * Sert a representer les participants d'evenement;
 * Elle a la meme structure que la table "participantsevents" dans la BDD;
 * Proprietes de la Classe : 
	- int idAssoc;
    - int idUser;
    - int presence;
    - int idEvent;
    - String presenceString;

    + User utilisateur;
    + TypeUser userType;
 * */

public class ParticipantEvents {
	
	private int idAssoc;
    private int idUser;
    private int presence;
    private int idEvent;
    private String presenceString;

    public User utilisateur = new User ();
    public TypeUser userType = new TypeUser();
    
	public int getIdAssoc() {
		return idAssoc;
	}
	public void setIdAssoc(int idAssoc) {
		this.idAssoc = idAssoc;
	}
	public int getIdUser() {
		return idUser;
	}
	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}
	public int getPresence() {
		return presence;
	}
	public void setPresence(int presence) {
		this.presence = presence;
		switch (presence) {
		 case 1 :
			 presenceString = "Present(e)";
			 break;
		 case 0 :
			 presenceString = "Absent(e)";
			 break;
		 case -1:
			 presenceString = "n/a";	
			 break;
		} 
	}
	
	public String getPresenceString (){
       	return presenceString;
	}

	public int getIdEvent() {
		return idEvent;
	}

	public void setIdEvent(int idEvent) {
		this.idEvent = idEvent;
	}

}
