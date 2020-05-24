package main.java.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import main.java.forme.Rectangle;


/**
 * 
 * CRUD pour acceder a la table RECTANGLE sur la base de donnees DERBY via jdbc
 * 

 * 
 *   La table RECTANGLE contient les colonnes      
 *        nom
 *        xHautGauche : x du  point en haut et a gauche
 *        yHautGauche : y du point en haut et a gauche
 *        largeur 
 *        longueur 
 *        
 * 
 * 
 * 
 * 
 * @author claire
 *
 */

public class RectangleJdbcDao extends DAO <Rectangle>  {
	
	
	// connexion a la base de donnees
	Connection connect ;
	
	String nomDessin;
	
	
	/**
	 * Constructeur
	 * @param nomBase
	 * @param repertoireBase
	 */
	
	public RectangleJdbcDao (String nomBase,String repertoireBase) {
		
		JdbcConnexionDerby driver = new JdbcConnexionDerby(nomBase, repertoireBase);
		
		connect = driver.getChaineConnexion();
	}
	
	/**
	 * Constructeur
	 * @param connect
	 * @param nomDessin
	 */
	public RectangleJdbcDao (Connection connect , String nomDessin) {
				
		this.connect = connect;
		this.nomDessin = nomDessin;
	}
	
	
	/**
	 * Creation d'un Rectangle dans la table Rectangle
	 * @param Rectangle : Rectangle
	 * @return
	 */

	
	public Rectangle create(Rectangle rectangle) {
		if (rectangle != null) {

			PreparedStatement prepare =null;
			
			// si la connexion a la base est OK et que le Rectangle n'est pas deja cree en base
			if ((connect != null)   && (find(rectangle.getNom()) == null) ){

				try {
					String chaineSQL ;
					
					if (nomDessin != null) {
						chaineSQL = "INSERT INTO RECTANGLE (nom,xHautGauche,yHautGauche,largeur,longueur,nomDessin) VALUES (?,?,?,?,?,?)";
					}
					else {
						chaineSQL = "INSERT INTO RECTANGLE (nom,xHautGauche,yHautGauche,largeur,longueur) VALUES (?,?,?,?,?)";
					}
					prepare = connect.prepareStatement(chaineSQL);
					prepare.setString(1, rectangle.getNom());
					prepare.setInt(2, rectangle.getPointHautGauche().x);
					prepare.setInt(3, rectangle.getPointHautGauche().y);
					prepare.setInt(4, rectangle.getLargeur());
					prepare.setInt(5, rectangle.getLongueur());

					if (nomDessin != null) {
						prepare.setString(6, nomDessin);
					}
					
					int result = prepare.executeUpdate();
					
					assert result == 1 ;


				}
				catch (SQLException ex) {
					ex.printStackTrace();
				}

		}

	}

	return rectangle;
}

	
	
	
	/**
	 * Mise a jour d'un Rectangle dans la table Rectangle
	 * @param Rectangle : rectangle
	 * @return
	 */

	public Rectangle update(Rectangle rectangle) {

		if (rectangle != null) {

			PreparedStatement prepare =null;


			// si la connexion a la base est OK et que le Rectangle est  deja cree en base
			if ((connect != null)   && (find(rectangle.getNom()) != null) ){

				try {

					String chaineSQL = "UPDATE RECTANGLE SET xHautGauche = ?, yHautGauche = ? WHERE nom = ?   ";

					prepare = connect.prepareStatement(chaineSQL);

					prepare.setInt(1, rectangle.getPointHautGauche().x);
					prepare.setInt(2, rectangle.getPointHautGauche().y);
					prepare.setString(3, rectangle.getNom());


					prepare.executeUpdate();


				}
				catch (SQLException ex) {
					ex.printStackTrace();
				}
			}
		}

		return rectangle;

	}


	
	/**
	 *Suppression d'un Rectangle dans la table 
	 * @param rectangle : rectangle a supprimer
	 * @return
	 */
	
	public void delete(Rectangle rectangle) {



		PreparedStatement prepare =null;

		// si la connexion a la base est OK et que le Rectangle est  cree en base
		if ((connect != null)   && ( find(rectangle.getNom()) != null) ){

			try {

				String sqlChaine = "DELETE FROM Rectangle WHERE nom = ? ";

				prepare = connect.prepareStatement(sqlChaine);

				prepare.setString(1, rectangle.getNom());


				prepare.executeUpdate();


			}
			catch (SQLException ex) {
				ex.printStackTrace();
			}	
		}


	}



	
	/**
	 * Recherche d'un Rectangle à partir de son nom
	 * @param nom : nom du Rectangle
	 * @return
	 * 
	 */
	@Override
	public Rectangle find(String nom) {

		Rectangle res = null ;

		PreparedStatement prepare =null;
		if (connect != null) {

			try {
				prepare = connect.prepareStatement("SELECT * FROM RECTANGLE WHERE nom= ? ");

				prepare.setString(1, nom);

				ResultSet result = prepare.executeQuery();

				while (result.next()) {

					int x = result.getInt("xHautGauche");
					int y = result.getInt("yHautGauche");
					int largeur = result.getInt("largeur");
					int longueur = result.getInt("longueur");
					
					res = new Rectangle(nom, x, y, largeur, longueur);

				}
			}
			catch (SQLException ex) {
				ex.printStackTrace();
			}


		}

		return res;

	}

	
	/**
	 * Recherche des rectangles à partir du nom du dessin
	 * @param nomDessin
	 * @return
	 */
	public List<Rectangle> findFormes(String nomDessin) {

		List<Rectangle> res = new ArrayList<>();

		PreparedStatement prepare =null;
		if (connect != null) {

			try {
				prepare = connect.prepareStatement("SELECT * FROM RECTANGLE WHERE nomDessin= ? ");

				prepare.setString(1, nomDessin);

				ResultSet result = prepare.executeQuery();

				while (result.next()) {
					String nom = result.getString("nom");
					int x = result.getInt("xHautGauche");
					int y = result.getInt("yHautGauche");
					int largeur = result.getInt("largeur");
					int longueur = result.getInt("longueur");
					
					Rectangle rectangle = new Rectangle(nom, x, y, largeur, longueur);
					res.add(rectangle);

				}
			}
			catch (SQLException ex) {
				ex.printStackTrace();
			}


		}

		return res;

	}

	
	
	
	
	
	
	
	/**
	 * Fermeture de la connexion a la table
	 */
	public void closeConnection () {
		
		
		JdbcConnexionDerby.closeConnexion(connect);
		
	}

	

}
