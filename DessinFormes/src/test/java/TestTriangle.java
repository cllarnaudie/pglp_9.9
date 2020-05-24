package test.java;

import java.awt.Point;

import org.junit.Test;

import main.java.forme.Triangle;


public class TestTriangle {
	
 	@Test
	public void testCarre() {

		System.out.println("\n Test de la forme Triangle\n ");
		
		System.out.println("\n Trianle pt1: 20 30 pt2 : 40 60 pt3 : 30 70");
		
		Triangle forme = new  Triangle(" Triangle 1 ", new Point (20,30), new Point (40,60), new Point (30,70));
	    
		forme.affiche();
	    
	    System.out.println("\n Deplacement du triangle de 10 et 50");
	    
	    forme.deplace(10, 50);
		
	    forme.affiche();
	   
	}
 	
 	
}
