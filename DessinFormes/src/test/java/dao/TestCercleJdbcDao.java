package test.java.dao;

import java.awt.Point;

import org.junit.Test;

import main.java.dao.CercleJdbcDao;
import main.java.dao.JdbcDAOFactory;
import main.java.forme.Cercle;

/**
 * Tests de la classe CercleJdbcDao
 * @author claire
 *
 */

public class TestCercleJdbcDao {
	
	
	
	@Test
	public void testCreate() {
		
		System.out.println("\nTests de creation en base");
		
		// recuperation de la Dao et ouverture de la connexion a la base
		JdbcDAOFactory daoFactory = new JdbcDAOFactory();
		CercleJdbcDao  daoCercle = (CercleJdbcDao)daoFactory.getCercleDAO( "/home/devc/tools/derby/");
		
		
		// creation d un cercle
		Cercle forme = new  Cercle("Cercle 1", new Point (20,30), 10);
		 
		// sauvegarde du cercle en base
		daoCercle.create(forme);
		 
		// recherche du cercle en base 
		Cercle res = daoCercle.find("Cercle 1") ;
				 
		if (res != null) {
				System.out.println("Creation et recherche OK");
				res.affiche();
		}
		else {
			System.out.println("Creation NOK");
		}
		
		daoCercle.delete(res);
			
		// fermeture de la connexion a la base
		daoCercle.closeConnection();
	}
	
	
	
	/**
	 * Tests de mise a jour de la table
	 */
	
	@Test
	public void testUpDate() {

		System.out.println("\nTests de mises a jour");



		// recuperation de la Dao et ouverture de la connexion a la base
		JdbcDAOFactory daoFactory = new JdbcDAOFactory();
		CercleJdbcDao  daoCercle = (CercleJdbcDao)daoFactory.getCercleDAO( "/home/devc/tools/derby/");


		Cercle forme = new  Cercle("Cercle 1", new Point (20,30), 10);

	
		daoCercle.create(forme);
		
		// deplacement de la forme
		forme.deplace(10 , 15);

		// mise a jour d un cercle existant en base
		daoCercle.update(forme);
		
		Cercle res = daoCercle.find("Cercle 1") ;
		 
		if (res != null) {
				System.out.println("Mise à jour d'un élement et recherche OK");
				res.affiche();
		}
		else {
			System.out.println("Mise a jour NOK");
		}
		
		daoCercle.delete(res);
			
		
		 
		 
		// mise a jour d un element non existant en base
		forme = new  Cercle("Cercle 2", new Point (20,30), 10);
		daoCercle.update(forme);
		res = daoCercle.find("Cercle 2") ;
		 
		if (res != null) {
				
			System.out.println("Mise a jour d'un element non existant en base NOK");
		}
		
		 
		// fermeture de la connexion a la base
		daoCercle.closeConnection();

	}
	
	
	
	
	/**
	 * Test de suppression
	 */
	@Test
	public void testDelete() {
	
		System.out.println("\nTests de suppression");

		// recuperation de la Dao et ouverture de la connexion a la base
		JdbcDAOFactory daoFactory = new JdbcDAOFactory();
		CercleJdbcDao  daoCercle = (CercleJdbcDao)daoFactory.getCercleDAO( "/home/devc/tools/derby/");


		// creation des cercles
		Cercle forme = new  Cercle("Cercle 1", new Point (20,30), 10);
		daoCercle.create(forme);

		forme = new  Cercle("Cercle 2", new Point (20,30), 10);
		daoCercle.create(forme);

		// suppression du cercle 2
		daoCercle.delete(forme);
		
		// recherche du cercle 2 en base
		Cercle res = daoCercle.find("Cercle 2") ;
		 
		 if (res == null) {
			 System.out.println("Suppression et recherche OK");
		 }
		 
		// fermeture de la connexion a la base
		 daoCercle.closeConnection();
	}
	
	
	
	

}
