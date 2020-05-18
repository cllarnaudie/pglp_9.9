package main.java.forme;

import java.awt.Point;

import main.java.forme.GraphicForme.TYPE_FORME;


/**
 * Classe des cercles
 * @author claire
 *
 */
public class Cercle extends Forme  {

	private Point centre ;

	private int rayon ;
	
	private static final long serialVersionUID = 150;

	
    /**
     * Constructeur
     * @param nom
     * @param centre
     * @param rayon
     */

	public Cercle(String nom, Point centre, int rayon) {
		super(nom, GraphicForme.TYPE_FORME.CERCLE);
		
		this.centre = centre ;
		this.rayon = rayon;
		
	}

	/**
	 * Constructeur
	 * @param nom
	 * @param x
	 * @param y
	 * @param rayon
	 */
	public Cercle(String nom, int x , int y, int rayon) {
		super(nom, GraphicForme.TYPE_FORME.CERCLE);
		
		this.centre = new Point(x,y) ;
		this.rayon = rayon;
		
	}
	

	/**
	 * Affiche les attributs de la classe
	 * 
	 */
	public  void affiche()  {
		System.out.println (" CERCLE : " +getNom() + " Centre = (" + getCentre().x + ","+
				getCentre().y + ") rayon = " + getRayon());
	}
	
	/**
	 * 
	 */
	public  void deplace(int deplX, int deplY)  {
		this.centre.x += deplX ;
		this.centre.y += deplY ;
		
	}


	public Point getCentre() {
		return centre;
	}


	public void setCentre(Point centre) {
		this.centre = centre;
	}


	public int getRayon() {
		return rayon;
	}


	public void setRayon(int rayon) {
		this.rayon = rayon;
	}
	
	
}
