package test.java;

import java.awt.Point;
import java.util.List;

import org.junit.Test;

import main.java.dao.CarreJdbcDao;
import main.java.dao.CercleJdbcDao;
import main.java.dao.RectangleJdbcDao;
import main.java.dao.TriangleJdbcDao;
import main.java.forme.Carre;
import main.java.forme.Cercle;
import main.java.forme.FormeComposite;
import main.java.forme.GraphicForme;
import main.java.forme.Rectangle;
import main.java.forme.Triangle;


public class TestFormeComposite {
	
 	@Test
	public void testCarre() {

		System.out.println("\n Test de la forme Composite ");
		
		Triangle triangle = new  Triangle(" Triangle 1 ", new Point (20,30), new Point (40,60), new Point (30,70));
		
		Carre carre = new  Carre(" Carre 1 ", new Point (30,40), 10);
		
		Cercle cercle = new  Cercle(" Cercle 1 ", new Point (50,60), 10);
		
		Rectangle rectangle = new  Rectangle("Rectangle 1 ", new Point (70,80), 5,40);
		
		System.out.println("\n Ajout des formes a la forme Composite");
		FormeComposite dessin = new FormeComposite ("dessin 1");
		dessin.ajouterForme(triangle);
		dessin.ajouterForme(carre);
		dessin.ajouterForme(cercle);
		dessin.ajouterForme(rectangle);
		
		dessin.affiche();
		
	    System.out.println("\n Deplacement des formes  de 10 et 20");
	    
	    dessin.deplace(10, 20);
	    dessin.affiche();
		
	    System.out.println("\n Suppression des formes de la forme Composite\n ");
	    dessin.supprimerForme(triangle);
		dessin.supprimerForme(carre);
		dessin.supprimerForme(cercle);
		dessin.supprimerForme(rectangle);
		

	    dessin.affiche();
	    
	    
	    
	   
	}
 	
 	
}
