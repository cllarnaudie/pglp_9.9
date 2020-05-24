package test.java.dao;

import java.awt.Point;

import org.junit.Test;

import main.java.dao.CarreJdbcDao;
import main.java.dao.JdbcDAOFactory;
import main.java.forme.Carre;

/**
 * Tests de la classe CarreJdbcDao
 * @author claire
 *
 */

public class TestCarreJdbcDao {
	
	
	
	@Test
	public void testCreate() {
		
		System.out.println("\nTests de creation");
		
		// recuperation de la Dao et ouverture de la connexion a la base
		JdbcDAOFactory daoFactory = new JdbcDAOFactory();
		CarreJdbcDao  daoCarre = (CarreJdbcDao)daoFactory.getCarreDAO( "/home/devc/tools/derby/");
		
		
		// creation d un carre
		Carre forme = new  Carre("Carre 1", new Point (20,30), 10);
		 
		// sauvegarde du Carre en base
		daoCarre.create(forme);
		 
		// recherche du Carre en base 
		Carre res = daoCarre.find("Carre 1") ;
				 
		if (res != null) {
				System.out.println("Creation et recherche OK");
				res.affiche();
		}
		else {
			System.out.println("Creation NOK");
		}
		
		daoCarre.delete(res);
			
		// fermeture de la connexion a la base
		daoCarre.closeConnection();
	}
	
	
	
	/**
	 * Tests de mise a jour de la table
	 */
	
	@Test
	public void testUpDate() {

		System.out.println("\nTests de mises a jour");



		// recuperation de la Dao et ouverture de la connexion a la base
		JdbcDAOFactory daoFactory = new JdbcDAOFactory();
		CarreJdbcDao  daoCarre = (CarreJdbcDao)daoFactory.getCarreDAO( "/home/devc/tools/derby/");


		Carre forme = new  Carre("Carre 1", new Point (20,30), 10);

	
		daoCarre.create(forme);
		
		// deplacement de la forme
		forme.deplace(10 , 15);

		// mise a jour d un Carre existant en base
		daoCarre.update(forme);
		
		Carre res = daoCarre.find("Carre 1") ;
		 
		if (res != null) {
				System.out.println("Mise à jour et recherche OK");
				res.affiche();
		}
		else {
			System.out.println("Mise a jour NOK");
		}
		
		daoCarre.delete(res);
			
		
		 
		 
		// mise a jour d un element non existant en base
		forme = new  Carre("Carre 2", new Point (20,30), 10);
		daoCarre.update(forme);
		
		
		 
		// fermeture de la connexion a la base
		daoCarre.closeConnection();

	}
	
	
	
	
	/**
	 * Test de suppression
	 */
	@Test
	public void testDelete() {
	
		System.out.println("\nTests de suppression");

		// recuperation de la Dao et ouverture de la connexion a la base
		JdbcDAOFactory daoFactory = new JdbcDAOFactory();
		CarreJdbcDao  daoCarre = (CarreJdbcDao)daoFactory.getCarreDAO( "/home/devc/tools/derby/");


		// creation des Carres
		Carre forme = new  Carre("Carre 1", new Point (20,30), 10);
		daoCarre.create(forme);

		forme = new  Carre("Carre 2", new Point (20,30), 10);
		daoCarre.create(forme);

		// suppression du Carre 2
		daoCarre.delete(forme);
		
		// recherche du Carre 2 en base
		Carre res = daoCarre.find("Carre 2") ;
		 
		 if (res == null) {
			 System.out.println("Suppression et recherche OK");
		 }
		 
		// fermeture de la connexion a la base
		 daoCarre.closeConnection();
	}
	
	
	
	

}
