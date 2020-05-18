package main.java.forme;

import java.awt.Point;

import main.java.forme.GraphicForme.TYPE_FORME;

/**
 * 
 * @author claire
 *
 */
public class Rectangle extends Forme  {

	private Point pointHautGauche ;

	private int largeur ;
	
	private int longueur ;
	
	private static final long serialVersionUID = 150;


	/**
	 * Constructeur
	 * @param nom
	 * @param pointHautGauche
	 * @param largeur
	 * @param longueur
	 */
	public Rectangle(String nom, Point pointHautGauche, int largeur, int longueur) {
		super(nom, GraphicForme.TYPE_FORME.RECTANGLE);
		
		this.pointHautGauche = pointHautGauche ;
		this.largeur = largeur;
		this.longueur = longueur;
		
	}
	
	
	
	/**
	 * 
	 * @param nom
	 * @param xPointHautGauche
	 * @param yPointHautGauche
	 * @param largeur
	 * @param longueur
	 */
	public Rectangle(String nom, int xPointHautGauche, int yPointHautGauche, int largeur, int longueur) {
		super(nom, GraphicForme.TYPE_FORME.RECTANGLE);
		
		this.pointHautGauche = new Point (xPointHautGauche, yPointHautGauche) ;
		this.largeur = largeur;
		this.longueur = longueur;
		
	}



	/**
	 * Affiche les attributs de la classe
	 * 
	 */
	public  void affiche()  {
		
			System.out.println (" RECTANGLE : " +getNom() + " Point Haut Gauche : " + getPointHautGauche().x + " "+
		getPointHautGauche().y +  " Largeur : "+ getLargeur()  +" Longueur : "+ getLongueur()  );
			
		
	}
	
	
	/**
	 * 
	 */
	public  void deplace(int deplX, int deplY)  {
		this.pointHautGauche.x += deplX ;
		this.pointHautGauche.y += deplY ;
		
	}


	public Point getPointHautGauche() {
		return pointHautGauche;
	}


	public void setPointHautGauche(Point pointHautGauche) {
		this.pointHautGauche = pointHautGauche;
	}


	public int getLargeur() {
		return largeur;
	}


	public void setLargeur(int largeur) {
		this.largeur = largeur;
	}


	public int getLongueur() {
		return longueur;
	}


	public void setLongueur(int longueur) {
		this.longueur = longueur;
	}
	
	
}
