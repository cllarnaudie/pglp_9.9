package main.java.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import main.java.forme.Carre;
import main.java.forme.Cercle;
import main.java.forme.Forme;
import main.java.forme.FormeComposite;
import main.java.forme.GraphicForme;
import main.java.forme.Rectangle;
import main.java.forme.Triangle;


/**
 * 
 * CRUD pour acceder a la table FORMECOMPOSITE sur la base de donnees DERBY via jdbc
 * 
 * 
 *   La table DESSIN contient les colonnes      
 *        nom
 *        nomForme
 *        typeForme
 *       
 * 
 * @author claire
 *
 */

public class FormeCompositeJdbcDao extends DAO <FormeComposite>  {
	
	
	// connexion a la base de donnees
	Connection connect ;
	
	
	/**
	 * 
	 * @param nomBase
	 * @param repertoireBase
	 */
	
	public FormeCompositeJdbcDao (String nomBase,String repertoireBase) {
		
		JdbcConnexionDerby driver = new JdbcConnexionDerby(nomBase, repertoireBase);
		
		connect = driver.getChaineConnexion();
	}
	
	
	/**
	 * Creation d'un FormeComposite dans la table FormeComposite
	 * @param formeComposite : ensemble des formes
	 * @return
	 */

	
	public FormeComposite create(FormeComposite formeComposite) {
		if (formeComposite != null) {

			PreparedStatement prepare =null;
			
			// si la connexion a la base est OK et que la FormeComposite n'est pas deja cree en base
			if ((connect != null)   && (find(formeComposite.getNom()) == null) ){
				
				for ( GraphicForme elt : formeComposite.getFormes())  {
					
				try {
					
					// sauvegarde de la forme elementaire
					createForme(elt) ;
				
					// sauvegarde dans le dessin
					String chaineSQL = "INSERT INTO DESSIN (nom,nomForme,typeForme) VALUES (?,?,?)";

					prepare = connect.prepareStatement(chaineSQL);
					
					prepare.setString(1, formeComposite.getNom());
					prepare.setString(2, elt.getNom());
					prepare.setString(3, elt.getType().toString());
					
					int result = prepare.executeUpdate();
					
					assert result == 1 ;


				}
				catch (SQLException ex) {
					ex.printStackTrace();
				}
				}

		}

	}

	return formeComposite;
   }

	/**
	 * Sauvegarde des formes elementaires
	 * @param elt
	 * @return
	 */
	
	public void createForme (GraphicForme  elt ) {
		if (  elt != null) {
			
			if ( elt instanceof Cercle) {
				
				Cercle cercle = (Cercle)elt;
				CercleJdbcDao dao = new CercleJdbcDao(connect);
				dao.create(cercle);
				
			}else if ( elt instanceof Carre) {
				
				Carre carre = (Carre)elt;
				CarreJdbcDao dao = new CarreJdbcDao(connect);
				dao.create(carre);
				
			}else if ( elt instanceof Rectangle) {
				
				Rectangle rectangle = (Rectangle)elt;
				RectangleJdbcDao dao = new RectangleJdbcDao(connect);
				dao.create(rectangle);
				
			}else if( elt instanceof Triangle) {
				
				Triangle triangle = (Triangle)elt;
				TriangleJdbcDao dao = new TriangleJdbcDao(connect);
				dao.create(triangle);
				
			}
		}
			
			
			
   }
	
	/**
	 * Suppression des formes elementaires
	 * @param elt
	 * @return
	 */
	
	public void deleteForme (GraphicForme  elt ) {
		if (  elt != null) {
			
			if ( elt instanceof Cercle) {
				
				Cercle cercle = (Cercle)elt;
				CercleJdbcDao dao = new CercleJdbcDao(connect);
				dao.delete(cercle);
				
			}else if ( elt instanceof Carre) {
				
				Carre carre = (Carre)elt;
				CarreJdbcDao dao = new CarreJdbcDao(connect);
				dao.delete(carre);
				
			}else if ( elt instanceof Rectangle) {
				
				Rectangle rectangle = (Rectangle)elt;
				RectangleJdbcDao dao = new RectangleJdbcDao(connect);
				dao.delete(rectangle);
				
			}else if( elt instanceof Triangle) {
				
				Triangle triangle = (Triangle)elt;
				TriangleJdbcDao dao = new TriangleJdbcDao(connect);
				dao.delete(triangle);
				
			}
		}
			
			
			
   }

	
	
	
	/**
	 * Mise a jour d'un FormeComposite dans la table FormeComposite
	 * @param FormeComposite : FormeComposite
	 * @return
	 */

	public FormeComposite update(FormeComposite formeComposite) {


		if (formeComposite != null) {

			// si la connexion a la base est OK et que la FormeComposite est  deja cree en base
			if ((connect != null)   && (find(formeComposite.getNom()) != null) ){

				for ( GraphicForme elt : formeComposite.getFormes())  {
					if (  elt != null) {
						
						if ( elt instanceof Cercle) {
							
							Cercle cercle = (Cercle)elt;
							CercleJdbcDao dao = new CercleJdbcDao(connect);
							dao.update(cercle);
							
						}else if ( elt instanceof Carre) {
							
							Carre carre = (Carre)elt;
							CarreJdbcDao dao = new CarreJdbcDao(connect);
							dao.update(carre);
							
						}else if ( elt instanceof Rectangle) {
							
							Rectangle rectangle = (Rectangle)elt;
							RectangleJdbcDao dao = new RectangleJdbcDao(connect);
							dao.update(rectangle);
							
						}else if( elt instanceof Triangle) {
							
							Triangle triangle = (Triangle)elt;
							TriangleJdbcDao dao = new TriangleJdbcDao(connect);
							dao.update(triangle);
							
						}
					}
						
				}


			}
		}

		

		return formeComposite;

	}
	
	
	
	


	
	/**
	 *Suppression d'un FormeComposite dans la table 
	 * @param formeComposite : FormeComposite a supprimer
	 * @return
	 */
	
	public void delete(FormeComposite formeComposite) {



		PreparedStatement prepare =null;

		// si la connexion a la base est OK et que la formeComposite est  cree en base
		if ((connect != null)   && ( find(formeComposite.getNom()) != null) ){

			try {
				

				String sqlChaine = "DELETE FROM DESSIN WHERE nom = ? ";

				prepare = connect.prepareStatement(sqlChaine);

				prepare.setString(1, formeComposite.getNom());


				prepare.executeUpdate();
				
				// suppression des formes elementaires
				for ( GraphicForme elt : formeComposite.getFormes())  {
					deleteForme(elt);
				}


			}
			catch (SQLException ex) {
				ex.printStackTrace();
			}	
		}


	}



	
	/**
	 * Recherche d'un FormeComposite à partir de son nom
	 * @param nom : nom du FormeComposite
	 * @return
	 * 
	 */
	@Override
	public FormeComposite find(String nom) {

		FormeComposite res = new FormeComposite(nom) ;
		boolean trouve = false ;
		PreparedStatement prepare =null;
		if (connect != null) {

			try {
				prepare = connect.prepareStatement("SELECT * FROM DESSIN WHERE nom= ? ");

				prepare.setString(1, nom);

				ResultSet result = prepare.executeQuery();

				while (result.next()) {
					trouve = true;
				
					String nomForme  = result.getString("nomForme");
					String typeForme = result.getString("typeForme");
					
					// recherche de la forme elementaire
                    Forme forme = findForme (nomForme, typeForme);
                    
                    if (forme != null) {
                    	res.getFormes().add(forme);
                    }
					

				}
			}
			catch (SQLException ex) {
				ex.printStackTrace();
			}


		}

		if (!trouve ) {
			res = null;
		}
		return res;

	}

	/**
	 * Recherche d une forme elementaire
	 * @param nomForme
	 * @param typeForme
	 * @return
	 */
	public Forme findForme (String nomForme, String typeForme) {

        Forme res = null ;
		
        if (typeForme.equals(GraphicForme.TYPE_FORME.CARRE.toString())) {
        	
    		CarreJdbcDao dao = new CarreJdbcDao(connect);
    		res = dao.find(nomForme);
    		
        } else if (typeForme.equals(GraphicForme.TYPE_FORME.RECTANGLE.toString())) {
        	
    		RectangleJdbcDao dao = new RectangleJdbcDao(connect);
    		res = dao.find(nomForme);
        }else if (typeForme.equals(GraphicForme.TYPE_FORME.CERCLE.toString())) {
        	
    		CercleJdbcDao dao = new CercleJdbcDao(connect);
    		res = dao.find(nomForme);
        }else if (typeForme.equals(GraphicForme.TYPE_FORME.TRIANGLE.toString())) {
        	
    		TriangleJdbcDao dao = new TriangleJdbcDao(connect);
    		res = dao.find(nomForme);
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
