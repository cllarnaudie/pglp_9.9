package test.java;

import java.awt.Point;

import org.junit.Test;

import main.java.forme.Rectangle;


public class TestRectangle {
	
 	@Test
	public void testCarre() {

		System.out.println("\n Test de la forme Rectangle\n ");
		
		System.out.println("\n Rectangle en 20 30  - largeur 5 - longueur 40 ");
		
		Rectangle forme = new  Rectangle("Rectangle 1 ", new Point (20,30), 5,40);
	    
		forme.affiche();
	    
	    System.out.println("\n Deplacement du rectangle de 10 et 50");
	    
	    forme.deplace(10, 50);
		
	    forme.affiche();
	   
	}
 	
 	
}
