package main.java.forme;

import java.awt.Point;


/**
 * Classe des cercles
 * @author claire
 *
 */
public class Triangle extends Forme {

	// premier point du triangle
	private Point point1 ;
	
	// deuxieme point du triangle
	private Point point2 ;
	
	// troisieme point du triangle
	private Point point3 ;

	private static final long serialVersionUID = 150;


	/**
	 * Constructeur
	 * @param nom
	 * @param point1
	 * @param point2
	 * @param point3
	 */
	public Triangle(String nom, Point point1, Point point2, Point point3) {
		super(nom, GraphicForme.TYPE_FORME.TRIANGLE);
		
		this.point1 = point1 ;
		this.point2 = point2 ;
		this.point3 = point3 ;
		
	}

	
	/**
	 * 
	 */
	public  void deplace(int deplX, int deplY)  {
		this.point1.x += deplX ;
		this.point1.y += deplY ;
		
		this.point2.x += deplX ;
		this.point2.y += deplY ;
		
		this.point3.x += deplX ;
		this.point3.y += deplY ;
		
	}

	/**
	 * Affiche les attributs de la classe
	 * 
	 */
	public  void affiche()  {
	
			
		System.out.println (" TRIANGLE: " +getNom() +
				" Point 1 : " + getPoint1().x + " "+ getPoint1().y +
				" Point 2 : " + getPoint2().x + " "+ getPoint2().y + 
				" Point 3 : " + getPoint3().x + " "+ getPoint3().y     );
			
	}


	public Point getPoint1() {
		return point1;
	}


	public void setPoint1(Point point1) {
		this.point1 = point1;
	}


	public Point getPoint2() {
		return point2;
	}


	public void setPoint2(Point point2) {
		this.point2 = point2;
	}


	public Point getPoint3() {
		return point3;
	}


	public void setPoint3(Point point3) {
		this.point3 = point3;
	}


	
	
}
