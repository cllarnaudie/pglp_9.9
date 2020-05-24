package test.java.dao;

import java.awt.Point;

import org.junit.Test;

import main.java.dao.FormeCompositeJdbcDao;
import main.java.dao.JdbcDAOFactory;
import main.java.forme.Carre;
import main.java.forme.Cercle;
import main.java.forme.FormeComposite;
import main.java.forme.Rectangle;
import main.java.forme.Triangle;






/**
 * Tests de la classe FormeCompositeJdbcDao
 * @author claire
 *
 */

public class TestFormeCompositeJdbcDao {

	String nomDessin1 = "FormeComposite 1";
	String nomDessin2 = "FormeComposite 2";

	private FormeComposite initFormeComposite (String nomDessin) {
		Triangle triangle = new  Triangle(" Triangle 1 ", new Point (20,30), new Point (40,60), new Point (30,70));

		Carre carre = new  Carre(" Carre 1 ", new Point (30,40), 10);

		Cercle cercle = new  Cercle(" Cercle 1 ", new Point (50,60), 10);

		Rectangle rectangle = new  Rectangle("Rectangle 1 ", new Point (70,80), 5,40);

		FormeComposite dessin = new FormeComposite (nomDessin);
		dessin.ajouterForme(triangle);
		dessin.ajouterForme(carre);
		dessin.ajouterForme(cercle);
		dessin.ajouterForme(rectangle);

		return dessin;
	}




	@Test
	public void testCreate() {

		System.out.println("\nTests de creation en base");

		// recuperation de la Dao et ouverture de la connexion a la base
		JdbcDAOFactory daoFactory = new JdbcDAOFactory();
		FormeCompositeJdbcDao  daoFormeComposite = (FormeCompositeJdbcDao)daoFactory.getFormeCompositeDAO( "/home/devc/tools/derby/");


		// creation d un FormeComposite
		FormeComposite forme = initFormeComposite(nomDessin1);

		// sauvegarde du FormeComposite en base
		daoFormeComposite.create(forme);

		// recherche du FormeComposite en base 
		FormeComposite res = daoFormeComposite.find(nomDessin1) ;

		if (res != null) {
			System.out.println("Creation et recherche OK");
			res.affiche();
		}
		else {
			System.out.println("Creation NOK");
		}

		daoFormeComposite.delete(res);

		// fermeture de la connexion a la base
		daoFormeComposite.closeConnection();
	}



	/**
	 * Tests de mise a jour de la table
	 */

	@Test
	public void testUpDate() {

		System.out.println("\nTests de mises a jour");



		// recuperation de la Dao et ouverture de la connexion a la base
		JdbcDAOFactory daoFactory = new JdbcDAOFactory();
		FormeCompositeJdbcDao  daoFormeComposite = (FormeCompositeJdbcDao)daoFactory.getFormeCompositeDAO( "/home/devc/tools/derby/");


		// creation d une FormeComposite
		FormeComposite forme = initFormeComposite(nomDessin1);

		daoFormeComposite.create(forme);

		// deplacement de la forme
		forme.deplace(10 , 15);

		// mise a jour d un FormeComposite existant en base
		daoFormeComposite.update(forme);

		FormeComposite res = daoFormeComposite.find(nomDessin1) ;

		if (res != null) {
			System.out.println("Mise à jour d'un élement et recherche OK");
			res.affiche();
		}
		else {
			System.out.println("Mise a jour NOK");
		}

		daoFormeComposite.delete(res);




		// mise a jour d un element non existant en base
		forme = initFormeComposite(nomDessin2);
		daoFormeComposite.update(forme);
		res = daoFormeComposite.find(nomDessin2) ;

		if (res != null) {

			System.out.println("Mise a jour d'un element non existant en base NOK");
		}
		

		// fermeture de la connexion a la base
		daoFormeComposite.closeConnection();

	}




	/**
	 * Test de suppression
	 */
	@Test
	public void testDelete() {

		System.out.println("\nTests de suppression");

		// recuperation de la Dao et ouverture de la connexion a la base
		JdbcDAOFactory daoFactory = new JdbcDAOFactory();
		FormeCompositeJdbcDao  daoFormeComposite = (FormeCompositeJdbcDao)daoFactory.getFormeCompositeDAO( "/home/devc/tools/derby/");


		// creation des FormeComposites
		FormeComposite forme = initFormeComposite(nomDessin1);
		daoFormeComposite.create(forme);

		// suppression du FormeComposite 2
		daoFormeComposite.delete(forme);

		// recherche du FormeComposite  en base
		FormeComposite res = daoFormeComposite.find(nomDessin1) ;

		if (res == null) {
			System.out.println("Suppression et recherche OK");
		}
		else {
			System.out.println("Suppression et recherche NOK");
		}

		// fermeture de la connexion a la base
		daoFormeComposite.closeConnection();
	}



	

}
