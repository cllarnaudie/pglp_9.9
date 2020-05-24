package main.java.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import main.java.forme.Cercle;


/**
 * 
 * CRUD pour acceder a la table CERCLE sur la base de donnees DERBY via jdbc
 * 
 * 
 *   La table CERCLE contient les colonnes      
 *        nom
 *        centreX : x du centre du cercle
 *        centreY : y du centre du cercle *        		   
 *        rayon : rayon du cercle
 *       
 * 
 * 
 * 
 * 
 * 
 * @author claire
 *
 */

public class CercleJdbcDao extends DAO <Cercle>  {
	
	
	// connexion a la base de donnees
	Connection connect =null ;
	
	// dessin
	String nomDessin = null;
	
	/**
	 * Constructeur
	 * @param nomBase
	 * @param repertoireBase
	 */
	
	public CercleJdbcDao (String nomBase,String repertoireBase) {
		
		JdbcConnexionDerby driver = new JdbcConnexionDerby(nomBase, repertoireBase);
		
		connect = driver.getChaineConnexion();
	}
	
	/**
	 *  Constructeur
	 * @param connect
	 * @param nomDessin
	 */
	public CercleJdbcDao (Connection connect,String nomDessin ) {
				
		this.connect = connect;
		this.nomDessin = nomDessin;
	}
	
	
	/**
	 * Creation d'un cercle dans la table CERCLE
	 * @param cercle : cercle
	 * @return
	 */

	
	public Cercle create(Cercle cercle) {
		if (cercle != null) {

			PreparedStatement prepare =null;
			
			// si la connexion a la base est OK et que le cercle n'est pas deja cree en base
			if ((connect != null)   && (find(cercle.getNom()) == null) ){

				try {
					String chaineSQL ;
					
					if (nomDessin != null) {
						chaineSQL = "INSERT INTO CERCLE (nom,centreX,centreY,rayon,nomDessin) VALUES (?,?,?,?,?)";
					}
					else {
						chaineSQL = "INSERT INTO CERCLE (nom,centreX,centreY,rayon) VALUES (?,?,?,?)";
					}

					prepare = connect.prepareStatement(chaineSQL);
					prepare.setString(1, cercle.getNom());
					prepare.setInt(2, cercle.getCentre().x);
					prepare.setInt(3, cercle.getCentre().y);
					prepare.setInt(4, cercle.getRayon());
					
					if (nomDessin != null) {
						prepare.setString(5, nomDessin);
					}

					int result = prepare.executeUpdate();
					
					assert result == 1 ;


				}
				catch (SQLException ex) {
					ex.printStackTrace();
				}

		}

	}

	return cercle;
}

	
	
	
	/**
	 * Mise a jour d'un cercle dans la table CERCLE
	 * @param cercle : cercle
	 * @return
	 */

	public Cercle update(Cercle cercle) {

		if (cercle != null) {

			PreparedStatement prepare =null;

			// si la connexion a la base est OK et que le cercle est  deja cree en base
			if ((connect != null)   && (find(cercle.getNom()) != null) ){
		
				try {

					String chaineSQL = "UPDATE CERCLE SET centreX = ?, centreY = ? WHERE nom = ?   ";

					prepare = connect.prepareStatement(chaineSQL);

					prepare.setInt(1, cercle.getCentre().x);
					prepare.setInt(2, cercle.getCentre().y);
					prepare.setString(3, cercle.getNom());


					prepare.executeUpdate();


				}
				catch (SQLException ex) {
					ex.printStackTrace();
				}
			}
		}

		return cercle;

	}


	
	/**
	 *Suppression d'un cercle dans la table 
	 * @param cercle : cercle a supprimer
	 * @return
	 */
	
	public void delete(Cercle cercle) {



		PreparedStatement prepare =null;

		// si la connexion a la base est OK et que le cercle est  cree en base
		if ((connect != null)   && ( find(cercle.getNom()) != null) ){

			try {

				String sqlChaine = "DELETE FROM CERCLE WHERE nom = ? ";

				prepare = connect.prepareStatement(sqlChaine);

				prepare.setString(1, cercle.getNom());


				prepare.executeUpdate();


			}
			catch (SQLException ex) {
				ex.printStackTrace();
			}	
		}


	}



	
	/**
	 * Recherche d'un cercle à partir de son nom
	 * @param nom : nom du cercle
	 * @return
	 * 
	 */
	@Override
	public Cercle find(String nom) {

		Cercle res = null ;

		PreparedStatement prepare =null;
		if (connect != null) {

			try {
				prepare = connect.prepareStatement("SELECT * FROM CERCLE WHERE nom= ? ");

				prepare.setString(1, nom);

				ResultSet result = prepare.executeQuery();

				while (result.next()) {

					int rayon = result.getInt("rayon");
					int x = result.getInt("centreX");
					int y = result.getInt("centreY");

					res = new Cercle(nom, x, y, rayon);

				}
			}
			catch (SQLException ex) {
				ex.printStackTrace();
			}


		}

		return res;

	}

	
	/**
	 * Recherche des cercles à partir du nom du dessin
	 * @param nom : nom du dessin
	 * @return
	 * 
	 */
	
	public List<Cercle> findFormes(String nomDessin) {

		List <Cercle> res = new ArrayList<>();

		PreparedStatement prepare =null;
		if (connect != null) {

			try {
				prepare = connect.prepareStatement("SELECT * FROM CERCLE WHERE nomDessin= ? ");

				prepare.setString(1, nomDessin);

				ResultSet result = prepare.executeQuery();

				while (result.next()) {

					String nom = result.getString("nom");
					int rayon = result.getInt("rayon");
					int x = result.getInt("centreX");
					int y = result.getInt("centreY");

					Cercle cercle = new Cercle(nom, x, y, rayon);
					res.add(cercle);

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
