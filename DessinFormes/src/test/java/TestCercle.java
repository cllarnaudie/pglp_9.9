package test.java;

import java.awt.Point;

import org.junit.Test;

import main.java.forme.Cercle;


public class TestCercle {
	
 	@Test
	public void testCarre() {

		System.out.println("\n Test de la forme cercle\n ");
		
		System.out.println("\n Cercle en 20 30 et de rayon 10 ");
		
		Cercle forme = new  Cercle(" Cercle 1 ", new Point (20,30), 10);
	    
		forme.affiche();
	    
	    System.out.println("\n Deplacement du cercle de 10 et 50");
	    
	    forme.deplace(10, 50);
		
	    forme.affiche();
	   
	}
 	
 	
}
