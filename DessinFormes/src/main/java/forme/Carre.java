package main.java.forme;

import java.awt.Point;

public class Carre extends Rectangle {

		
	private static final long serialVersionUID = 150;


	/**
	 * Constructeur
	 * @param nom
	 * @param pointHautGauche
	 * @param cote
	 */
	public Carre(String nom, Point pointHautGauche, int cote) {
		
		super( nom, pointHautGauche, cote,cote);
		
		
		
	}
	
	/**
	 * Constructeur
	 * @param nom
	 * @param x
	 */
	public Carre(String nom, int x ,int y, int cote) {
		super( nom, x,y, cote,cote);
		
	
		
	}
	
	/**
	 * Retourne le cote du carre
	 * @return
	 */
	
	public int getCote () {
		return getLongueur();
	}

	
	
	/**
	 * Affiche les attributs de la classe
	 * 
	 */
	public  void affiche()  {
		System.out.println (" CARRE : " +getNom() + "" + " Point Haut Gauche = (" + getPointHautGauche().x + " "+
	getPointHautGauche().y + ") cote = " + getCote());
		
	}

	
	
	
}
