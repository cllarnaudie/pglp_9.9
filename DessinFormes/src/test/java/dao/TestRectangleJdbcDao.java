package test.java.dao;

import java.awt.Point;

import org.junit.Test;

import main.java.dao.JdbcDAOFactory;
import main.java.dao.RectangleJdbcDao;
import main.java.forme.Rectangle;

/**
 * Tests de la classe RectangleJdbcDao
 * @author claire
 *
 */

public class TestRectangleJdbcDao {
	
	
	
	@Test
	public void testCreate() {
		
		System.out.println("\nTests de creation");
		
		// recuperation de la Dao et ouverture de la connexion a la base
		JdbcDAOFactory daoFactory = new JdbcDAOFactory();
		RectangleJdbcDao  daoRectangle = (RectangleJdbcDao)daoFactory.getRectangleDAO( "/home/devc/tools/derby/");
		
		
		// creation d un Rectangle
		Rectangle forme = new  Rectangle("Rectangle 1", new Point (20,30), 10,20);
		 
		// sauvegarde du Rectangle en base
		daoRectangle.create(forme);
		 
		// recherche du Rectangle en base 
		Rectangle res = daoRectangle.find("Rectangle 1") ;
				 
		if (res != null) {
				System.out.println("Creation et recherche OK");
				res.affiche();
		}
		else {
			System.out.println("Creation NOK");
		}
		
		daoRectangle.delete(res);
			
		// fermeture de la connexion a la base
		daoRectangle.closeConnection();
	}
	
	
	
	/**
	 * Tests de mise a jour de la table
	 */
	
	@Test
	public void testUpDate() {

		System.out.println("\nTests de mises a jour");



		// recuperation de la Dao et ouverture de la connexion a la base
		JdbcDAOFactory daoFactory = new JdbcDAOFactory();
		RectangleJdbcDao  daoRectangle = (RectangleJdbcDao)daoFactory.getRectangleDAO( "/home/devc/tools/derby/");


		Rectangle forme = new  Rectangle("Rectangle 1", new Point (20,30), 10,25);

	
		daoRectangle.create(forme);
		
		// deplacement de la forme
		forme.deplace(10 , 15);

		// mise a jour d un Rectangle existant en base
		daoRectangle.update(forme);
		
		Rectangle res = daoRectangle.find("Rectangle 1") ;
		 
		if (res != null) {
				System.out.println("Mise à jour et recherche OK");
				res.affiche();
		}
		else {
			System.out.println("Mise a jour NOK");
		}
		
		daoRectangle.delete(res);
			
		
		 
		 
		// mise a jour d un element non existant en base
		forme = new  Rectangle("Rectangle 2", new Point (20,30), 10,25);
		daoRectangle.update(forme);
		
		
		 
		// fermeture de la connexion a la base
		daoRectangle.closeConnection();

	}
	
	
	
	
	/**
	 * Test de suppression
	 */
	@Test
	public void testDelete() {
	
		System.out.println("\nTests de suppression");

		// recuperation de la Dao et ouverture de la connexion a la base
		JdbcDAOFactory daoFactory = new JdbcDAOFactory();
		RectangleJdbcDao  daoRectangle = (RectangleJdbcDao)daoFactory.getRectangleDAO( "/home/devc/tools/derby/");


		// creation des Rectangles
		Rectangle forme = new  Rectangle("Rectangle 1", new Point (20,30), 10,25);
		daoRectangle.create(forme);

		forme = new  Rectangle("Rectangle 2", new Point (20,30), 10,35);
		daoRectangle.create(forme);

		// suppression du Rectangle 2
		daoRectangle.delete(forme);
		
		// recherche du Rectangle 2 en base
		Rectangle res = daoRectangle.find("Rectangle 2") ;
		 
		 if (res == null) {
			 System.out.println("Suppression et recherche OK");
		 }
		 
		// fermeture de la connexion a la base
		 daoRectangle.closeConnection();
	}
	
	
	
	

}
