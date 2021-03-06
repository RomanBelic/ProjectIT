package com.itparis.b3.associations.beans;
/**
 * Classe AssociationDesc est un modele de donnees;<br>
 * Sert a representer la description des associations;<br>
 * Elle a la meme structure que la table "associationdescription" dans la BDD;<br>
 * Proprietes de la Classe : <br>
    - int id;<br>
    - int idPresident;<br>
    - int nbParticipant;<br>
    - String nomAssoc;<br>
    - String description;<br>
 * */
public class AssociationDesc {

	private int    id;
	private int    idPresident;
	private int    nbParticipant;
	private String nomAssoc;
	private String description;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getIdPresident() {
		return idPresident;
	}
	public void setIdPresident(int idPresident) {
		this.idPresident = idPresident;
	}
	public String getNomAssoc() {
		return nomAssoc;
	}
	public void setNomAssoc(String nomAssoc) {
		this.nomAssoc = nomAssoc;
	}
	public int getNbParticipant() {
		return nbParticipant;
	}
	public void setNbParticipant(int nbParticipant) {
		this.nbParticipant = nbParticipant;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	
	
}
