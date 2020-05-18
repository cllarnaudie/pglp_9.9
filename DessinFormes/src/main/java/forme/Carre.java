package main.java.forme;

import java.awt.Point;

import main.java.forme.GraphicForme.TYPE_FORME;

public class Carre extends Forme {

	private Point pointHautGauche ;

	private int cote;
		
	private static final long serialVersionUID = 150;


	/**
	 * Constructeur
	 * @param nom
	 * @param pointHautGauche
	 * @param cote
	 */
	public Carre(String nom, Point pointHautGauche, int cote) {
		super(nom, GraphicForme.TYPE_FORME.CARRE);
		
		this.pointHautGauche = pointHautGauche ;
		this.cote = cote;
		
		
	}
	
	/**
	 * 
	 * @param nom
	 * @param x
	 */
	public Carre(String nom, int x ,int y, int cote) {
		super(nom, GraphicForme.TYPE_FORME.CARRE);
		
		this.pointHautGauche = new Point (x,y) ;
		this.cote = cote;
		
		
	}


	/**
	 * Affiche les attributs de la classe
	 * 
	 */
	public  void affiche()  {
		System.out.println (" CARRE : " +getNom() + "" + "Point Haut Gauche : " + getPointHautGauche().x + " "+
	getPointHautGauche().y + " cote : " + getCote());
		
	}

	
	/**
	 * 
	 */
	public  void deplace (int deplX, int deplY )  {
		this.pointHautGauche.x += deplX;
		this.pointHautGauche.y += deplY;
		
	}

	public Point getPointHautGauche() {
		return pointHautGauche;
	}


	public void setPointHautGauche(Point pointHautGauche) {
		this.pointHautGauche = pointHautGauche;
	}


	public int getCote() {
		return cote;
	}


	public void setCote(int cote) {
		this.cote = cote;
	}


	



	
	
}
