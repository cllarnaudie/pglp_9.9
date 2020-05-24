package main.java.dao;

import java.awt.Point;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import main.java.forme.Triangle;


/**
 * 
 * CRUD pour acceder a la table TRIANGLE sur la base de donnees DERBY via jdbc
 * 
 * 
 *   La table TRIANGLE contient les colonnes      
 *        nom : nom du triangle
 *        xPoint1 : x du premier point du triangle
 *        yPoint1 : y du premier point du triangle
 *        xPoint2 : x du deuxieme point du triangle
 *        yPoint2 : y du deuxieme point du triangle
 *        xPoint3 : x du troisieme point du triangle
 *        yPoint3 : y du troisieme point du triangle
 *        		   
 *        
 *       
 * 
 * 
 * 
 * 
 * 
 * @author claire
 *
 */

public class TriangleJdbcDao extends DAO <Triangle>  {
	
	
	// connexion a la base de donnees
	Connection connect ;
	
	String nomDessin;
	
	/**
	 * Constructeur
	 * @param nomBase
	 * @param repertoireBase
	 */
	
	public TriangleJdbcDao (String nomBase,String repertoireBase) {
		
		JdbcConnexionDerby driver = new JdbcConnexionDerby(nomBase, repertoireBase);
		
		connect = driver.getChaineConnexion();
	}
	
	
	/**
	 *  Constructeur
	 * @param connect
	 * @param nomDessin
	 */
	public TriangleJdbcDao (Connection connect, String nomDessin ) {
				
		this.connect = connect;
		this.nomDessin = nomDessin ;
	}
	
	
	/**
	 * Creation d'un Triangle dans la table Triangle
	 * @param Triangle : Triangle
	 * @return
	 */

	
	public Triangle create(Triangle triangle) {
		if (triangle != null) {

			PreparedStatement prepare =null;
			
			// si la connexion a la base est OK et que le Triangle n'est pas deja cree en base
			if ((connect != null)   && (find(triangle.getNom()) == null) ){

				try {
					String chaineSQL ;
					
					if (nomDessin != null) {
						chaineSQL = "INSERT INTO TRIANGLE (nom,xPoint1,yPoint1,xPoint2,yPoint2,xPoint3,yPoint3,nomDessin) VALUES (?,?,?,?,?,?,?,?)";
					}
					else {
						chaineSQL = "INSERT INTO TRIANGLE (nom,xPoint1,yPoint1,xPoint2,yPoint2,xPoint3,yPoint3) VALUES (?,?,?,?,?,?,?)";

					}
						
					prepare = connect.prepareStatement(chaineSQL);
					
					prepare.setString(1, triangle.getNom());
					prepare.setInt(2, triangle.getPoint1().x);
					prepare.setInt(3, triangle.getPoint1().y);
					
					prepare.setInt(4, triangle.getPoint2().x);
					prepare.setInt(5, triangle.getPoint2().y);
					
					prepare.setInt(6, triangle.getPoint3().x);
					prepare.setInt(7, triangle.getPoint3().y);
					
					if (nomDessin != null) {
						prepare.setString(8, nomDessin);
					}
					
					int result = prepare.executeUpdate();
					
					assert result == 1 ;


				}
				catch (SQLException ex) {
					ex.printStackTrace();
				}

		}

	}

	return triangle;
}

	
	
	
	/**
	 * Mise a jour d'un triangle dans la table Triangle
	 * @param Triangle : triangle
	 * @return
	 */

	public Triangle update(Triangle triangle) {

		if (triangle != null) {

			PreparedStatement prepare =null;


			// si la connexion a la base est OK et que le Triangle est  deja cree en base
			if ((connect != null)   && (find(triangle.getNom()) != null) ){

				try {

					String chaineSQL = "UPDATE TRIANGLE SET xPoint1 = ?, yPoint1= ?, xPoint2 = ?, yPoint2= ?,xPoint3 = ?,yPoint3= ?  WHERE nom = ?   ";

					prepare = connect.prepareStatement(chaineSQL);

					prepare.setInt(1, triangle.getPoint1().x);
					prepare.setInt(2, triangle.getPoint1().y);
					
					prepare.setInt(3, triangle.getPoint2().x);
					prepare.setInt(4, triangle.getPoint2().y);
					
					prepare.setInt(5, triangle.getPoint3().x);
					prepare.setInt(6, triangle.getPoint3().y);
					
					prepare.setString(7, triangle.getNom());
					
					prepare.executeUpdate();


				}
				catch (SQLException ex) {
					ex.printStackTrace();
				}
			}
		}

		return triangle;

	}


	
	/**
	 *Suppression d'un Triangle dans la table 
	 * @param Triangle : triangle a supprimer
	 * @return
	 */
	
	public void delete(Triangle triangle) {


		PreparedStatement prepare =null;

		// si la connexion a la base est OK et que le triangle est  cree en base
		if ((connect != null)   && ( find(triangle.getNom()) != null) ){

			try {

				String sqlChaine = "DELETE FROM TRIANGLE WHERE nom = ? ";

				prepare = connect.prepareStatement(sqlChaine);

				prepare.setString(1, triangle.getNom());


				prepare.executeUpdate();


			}
			catch (SQLException ex) {
				ex.printStackTrace();
			}	
		}


	}



	
	/**
	 * Recherche d'un triangle à partir de son nom
	 * @param nom : nom du Triangle
	 * @return
	 * 
	 */
	@Override
	public Triangle find(String nom) {

		Triangle res = null ;

		PreparedStatement prepare =null;
		if (connect != null) {

			try {
				prepare = connect.prepareStatement("SELECT * FROM TRIANGLE WHERE nom= ? ");

				prepare.setString(1, nom);

				ResultSet result = prepare.executeQuery();

				while (result.next()) {

					int x1 = result.getInt("xPoint1");
					int y1 = result.getInt("yPoint1");
					
					int x2 = result.getInt("xPoint2");
					int y2 = result.getInt("yPoint2");
					
					int x3 = result.getInt("xPoint3");
					int y3 = result.getInt("yPoint3");

					res = new Triangle(nom, new Point(x1,y1) , new Point(x2,y2),new Point(x3,y3));

				}
			}
			catch (SQLException ex) {
				ex.printStackTrace();
			}


		}

		return res;

	}

	
	/**
	 *  Recherche des triangless à partir du nom du dessin
	 * @param nomDessin
	 * @return
	 */
	
	public List<Triangle> findFormes(String nomDessin) {

		List<Triangle> res = new ArrayList<>();

		PreparedStatement prepare =null;
		if (connect != null) {

			try {
				prepare = connect.prepareStatement("SELECT * FROM TRIANGLE WHERE nomDessin= ? ");

				prepare.setString(1, nomDessin);

				ResultSet result = prepare.executeQuery();

				while (result.next()) {
					String nom = result.getString("nom");
					int x1 = result.getInt("xPoint1");
					int y1 = result.getInt("yPoint1");
					
					int x2 = result.getInt("xPoint2");
					int y2 = result.getInt("yPoint2");
					
					int x3 = result.getInt("xPoint3");
					int y3 = result.getInt("yPoint3");

					Triangle triangle= new Triangle(nom, new Point(x1,y1) , new Point(x2,y2),new Point(x3,y3));
					res.add(triangle);

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
