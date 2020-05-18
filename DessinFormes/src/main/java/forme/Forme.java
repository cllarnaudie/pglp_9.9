package main.java.forme;

import java.io.Serializable;

import main.java.forme.GraphicForme.TYPE_FORME;


/**
 * Classe Forme
 * @author claire
 *
 */

public abstract class Forme implements GraphicForme, Serializable {

	private  String nom;
	
	private  TYPE_FORME type;
	
	private static final long serialVersionUID = 150;

	
	public Forme(String nom, TYPE_FORME type) {
		super();
		this.nom = nom;
		this.type = type;
	}


	public String getNom() {
		return nom;
	}


	public void setNom(String nom) {
		this.nom = nom;
	}

	
	public TYPE_FORME getType() {
		return type;
	}


	public void setType(TYPE_FORME type) {
		this.type = type;
	}


	


	
	
	
	
}
