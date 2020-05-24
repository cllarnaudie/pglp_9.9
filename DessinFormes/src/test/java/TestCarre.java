package test.java;

import java.awt.Point;

import org.junit.Test;

import main.java.forme.Carre;


public class TestCarre {
	
 	@Test
	public void testCarre() {

		System.out.println("\n Test de la forme Carre\n ");
		
		System.out.println("\n Carre en 20 30 et de rayon 10 ");
		
		Carre carre = new  Carre(" Carre 1 ", new Point (20,30), 10);
	    
	    carre.affiche();
	    
	    System.out.println("\n Deplacement du carre de 10 et 50");
	    
	    carre.deplace(10, 50);
		
	    carre.affiche();
	   
	}
 	
 	
}
