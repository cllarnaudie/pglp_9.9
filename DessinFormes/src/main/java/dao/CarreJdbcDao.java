package main.java.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import main.java.forme.Carre;


/**
 * 
 * CRUD pour acceder a la table CARRE sur la base de donnees DERBY via jdbc
 * 
 * 
 *   La table Carre contient les colonnes      
 *        nom
 *        xHautGauche : x du  point en haut et a gauche
 *        yHautGauche : y du point en haut et a gauche
 *        cote
 *       
 * 
 * 
 * 
 * 
 * @author claire
 *
 */

public class CarreJdbcDao extends DAO <Carre>  {
	
	
	// connexion a la base de donnees
	Connection connect ;
	
	
	/**
	 * Constructeur
	 * @param nomBase
	 * @param repertoireBase
	 */
	
	public CarreJdbcDao (String nomBase,String repertoireBase) {
		
		JdbcConnexionDerby driver = new JdbcConnexionDerby(nomBase, repertoireBase);
		
		connect = driver.getChaineConnexion();
	}
	
	
	/**
	 * Constructeur
	 * @param connect
	 */
	public CarreJdbcDao (Connection connect ) {
				
		this.connect = connect;
	}
	
	
	/**
	 * Creation d'un Carre dans la table Carre
	 * @param Carre : Carre
	 * @return
	 */

	
	public Carre create(Carre carre) {
		if (carre != null) {

			PreparedStatement prepare =null;
			
			// si la connexion a la base est OK et que le Carre n'est pas deja cree en base
			if ((connect != null)   && (find(carre.getNom()) == null) ){

				try {
					
					String chaineSQL = "INSERT INTO CARRE (nom,xHautGauche,yHautGauche,cote) VALUES (?,?,?,?)";

					prepare = connect.prepareStatement(chaineSQL);
					prepare.setString(1, carre.getNom());
					prepare.setInt(2, carre.getPointHautGauche().x);
					prepare.setInt(3, carre.getPointHautGauche().y);
					prepare.setInt(4, carre.getCote());
					

					int result = prepare.executeUpdate();
					
					assert result == 1 ;


				}
				catch (SQLException ex) {
					ex.printStackTrace();
				}

		}

	}

	return carre;
}

	
	
	
	/**
	 * Mise a jour d'un Carre dans la table Carre
	 * @param Carre : Carre
	 * @return
	 */

	public Carre update(Carre carre) {

		if (carre != null) {

			PreparedStatement prepare =null;


			if (connect != null) {

				try {

					String chaineSQL = "UPDATE CARRE SET xHautGauche = ?, yHautGauche = ? WHERE nom = ?   ";

					prepare = connect.prepareStatement(chaineSQL);

					prepare.setInt(1, carre.getPointHautGauche().x);
					prepare.setInt(2, carre.getPointHautGauche().y);
					prepare.setString(3, carre.getNom());


					prepare.executeUpdate();


				}
				catch (SQLException ex) {
					ex.printStackTrace();
				}
			}
		}

		return carre;

	}


	
	/**
	 *Suppression d'un Carre dans la table 
	 * @param Carre : Carre a supprimer
	 * @return
	 */
	
	public void delete(Carre carre) {



		PreparedStatement prepare =null;

		// si la connexion a la base est OK et que le Carre est  cree en base
		if ((connect != null)   && ( find(carre.getNom()) != null) ){

			try {

				String sqlChaine = "DELETE FROM CARRE WHERE nom = ? ";

				prepare = connect.prepareStatement(sqlChaine);

				prepare.setString(1, carre.getNom());


				prepare.executeUpdate();


			}
			catch (SQLException ex) {
				ex.printStackTrace();
			}	
		}


	}



	
	/**
	 * Recherche d'un Carre à partir de son nom
	 * @param nom : nom du Carre
	 * @return
	 * 
	 */
	@Override
	public Carre find(String nom) {

		Carre res = null ;

		PreparedStatement prepare =null;
		if (connect != null) {

			try {
				prepare = connect.prepareStatement("SELECT * FROM CARRE WHERE nom= ? ");

				prepare.setString(1, nom);

				ResultSet result = prepare.executeQuery();

				while (result.next()) {

					int x = result.getInt("xHautGauche");
					int y = result.getInt("yHautGauche");
					int cote = result.getInt("cote");
					
					
					res = new Carre(nom,x,y,cote);
							

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
