package test.java.dao;

import java.awt.Point;

import org.junit.Test;

import main.java.dao.TriangleJdbcDao;
import main.java.dao.JdbcDAOFactory;
import main.java.forme.Triangle;

/**
 * Tests de la classe TriangleJdbcDao
 * @author claire
 *
 */

public class TestTriangleJdbcDao {
	
	
	
	@Test
	public void testCreate() {
		
		System.out.println("\nTests de creation");
		
		// recuperation de la Dao et ouverture de la connexion a la base
		JdbcDAOFactory daoFactory = new JdbcDAOFactory();
		TriangleJdbcDao  daoTriangle = (TriangleJdbcDao)daoFactory.getTriangleDAO( "/home/devc/tools/derby/");
		
		
		// creation d un Triangle
		Triangle forme = new  Triangle("Triangle 1", new Point (20,30), new Point (40,40),new Point (50,60));
		 
		// sauvegarde du Triangle en base
		daoTriangle.create(forme);
		 
		// recherche du Triangle en base 
		Triangle res = daoTriangle.find("Triangle 1") ;
				 
		if (res != null) {
				System.out.println("Creation et recherche OK");
				res.affiche();
		}
		else {
			System.out.println("Creation NOK");
		}
		
		daoTriangle.delete(res);
			
		// fermeture de la connexion a la base
		daoTriangle.closeConnection();
	}
	
	
	
	/**
	 * Tests de mise a jour de la table
	 */
	
	@Test
	public void testUpDate() {

		System.out.println("\nTests de mises a jour");



		// recuperation de la Dao et ouverture de la connexion a la base
		JdbcDAOFactory daoFactory = new JdbcDAOFactory();
		TriangleJdbcDao  daoTriangle = (TriangleJdbcDao)daoFactory.getTriangleDAO( "/home/devc/tools/derby/");


		
		Triangle forme = new  Triangle("Triangle 1", new Point (20,30), new Point (40,40),new Point (50,60));
	
		daoTriangle.create(forme);
		
		// deplacement de la forme
		forme.deplace(10 , 15);

		// mise a jour d un Triangle existant en base
		daoTriangle.update(forme);
		
		Triangle res = daoTriangle.find("Triangle 1") ;
		 
		if (res != null) {
				System.out.println("Mise à jour et recherche OK");
				res.affiche();
		}
		else {
			System.out.println("Mise a jour NOK");
		}
		
		daoTriangle.delete(res);
			
		
		 
		 
		// mise a jour d un element non existant en base
		
		Triangle forme1 = new  Triangle("Triangle 2", new Point (20,30), new Point (40,40),new Point (50,60));
		daoTriangle.update(forme1);
		
		
		 
		// fermeture de la connexion a la base
		daoTriangle.closeConnection();

	}
	
	
	
	
	/**
	 * Test de suppression
	 */
	@Test
	public void testDelete() {
	
		System.out.println("\nTests de suppression");

		// recuperation de la Dao et ouverture de la connexion a la base
		JdbcDAOFactory daoFactory = new JdbcDAOFactory();
		TriangleJdbcDao  daoTriangle = (TriangleJdbcDao)daoFactory.getTriangleDAO( "/home/devc/tools/derby/");


		// creation des Triangles
		Triangle forme = new  Triangle("Triangle 1", new Point (20,30), new Point (40,40),new Point (50,60));
		daoTriangle.create(forme);

		forme = new  Triangle("Triangle 2", new Point (20,30), new Point (40,40),new Point (50,60));
		daoTriangle.create(forme);

		// suppression du Triangle 2
		daoTriangle.delete(forme);
		
		// recherche du Triangle 2 en base
		Triangle res = daoTriangle.find("Triangle 2") ;
		 
		 if (res == null) {
			 System.out.println("Suppression et recherche OK");
		 }
		 
		// fermeture de la connexion a la base
		 daoTriangle.closeConnection();
	}
	
	
	
	

}
