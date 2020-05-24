package main.java.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import main.java.forme.Carre;
import main.java.forme.Cercle;
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
			try {
				// si la connexion a la base est OK et que la FormeComposite n'est pas deja cree en base
				String nomDessin = formeComposite.getNom();
				
				if ((connect != null)   && (find(nomDessin) == null) ){

					// sauvegarde dans la table DESSIN
					String chaineSQL = "INSERT INTO DESSIN (nomDessin) VALUES (?)";

					prepare = connect.prepareStatement(chaineSQL);

					prepare.setString(1, nomDessin);

					int result = prepare.executeUpdate();

					assert result == 1 ;

					// sauvegarde des  formes elementaires
					for ( GraphicForme elt : formeComposite.getFormes())  {
						createForme(elt, nomDessin) ;
					}

				}
			}
			catch (SQLException ex) {
				ex.printStackTrace();
			}
		}

	return formeComposite;
}

	/**
	 * Sauvegarde des formes elementaires
	 * @param elt
	 * @return
	 */
	
	public void createForme (GraphicForme  elt, String nomDessin ) {
		if (  elt != null) {
			
			if ( elt instanceof Cercle) {
				
				Cercle cercle = (Cercle)elt;
				CercleJdbcDao dao = new CercleJdbcDao(connect, nomDessin);
				dao.create(cercle);
				
			}else if ( elt instanceof Carre) {
				
				Carre carre = (Carre)elt;
				CarreJdbcDao dao = new CarreJdbcDao(connect, nomDessin);
				dao.create(carre);
				
			}else if ( elt instanceof Rectangle) {
				
				Rectangle rectangle = (Rectangle)elt;
				RectangleJdbcDao dao = new RectangleJdbcDao(connect, nomDessin);
				dao.create(rectangle);
				
			}else if( elt instanceof Triangle) {
				
				Triangle triangle = (Triangle)elt;
				TriangleJdbcDao dao = new TriangleJdbcDao(connect, nomDessin);
				dao.create(triangle);
				
			}
		}
			
			
			
   }
	
	/**
	 * Suppression des formes elementaires
	 * @param elt
	 * @return
	 */
	
	public void deleteForme (GraphicForme  elt, String nomDessin ) {
		if (  elt != null) {
			
			if ( elt instanceof Cercle) {
				
				Cercle cercle = (Cercle)elt;
				CercleJdbcDao dao = new CercleJdbcDao(connect, nomDessin);
				dao.delete(cercle);
				
			}else if ( elt instanceof Carre) {
				
				Carre carre = (Carre)elt;
				CarreJdbcDao dao = new CarreJdbcDao(connect,nomDessin);
				dao.delete(carre);
				
			}else if ( elt instanceof Rectangle) {
				
				Rectangle rectangle = (Rectangle)elt;
				RectangleJdbcDao dao = new RectangleJdbcDao(connect, nomDessin);
				dao.delete(rectangle);
				
			}else if( elt instanceof Triangle) {
				
				Triangle triangle = (Triangle)elt;
				TriangleJdbcDao dao = new TriangleJdbcDao(connect,nomDessin);
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
			String nomDessin = formeComposite.getNom();
			
			if ((connect != null)   && (find(nomDessin) != null) ){

				for ( GraphicForme elt : formeComposite.getFormes())  {
					if (  elt != null) {
						
						if ( elt instanceof Cercle) {
							
							Cercle cercle = (Cercle)elt;
							CercleJdbcDao dao = new CercleJdbcDao(connect, nomDessin);
							dao.update(cercle);
							
						}else if ( elt instanceof Carre) {
							
							Carre carre = (Carre)elt;
							CarreJdbcDao dao = new CarreJdbcDao(connect, nomDessin);
							dao.update(carre);
							
						}else if ( elt instanceof Rectangle) {
							
							Rectangle rectangle = (Rectangle)elt;
							RectangleJdbcDao dao = new RectangleJdbcDao(connect, nomDessin);
							dao.update(rectangle);
							
						}else if( elt instanceof Triangle) {
							
							Triangle triangle = (Triangle)elt;
							TriangleJdbcDao dao = new TriangleJdbcDao(connect, nomDessin);
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
		String nomDessin = formeComposite.getNom();
		
		if ((connect != null)   && ( find(nomDessin) != null) ){

			try {
				
				// suppression des formes elementaires
				for ( GraphicForme elt : formeComposite.getFormes())  {
					deleteForme(elt, nomDessin);
				}
				
				String sqlChaine = "DELETE FROM DESSIN WHERE nomDessin = ? ";

				prepare = connect.prepareStatement(sqlChaine);

				prepare.setString(1, formeComposite.getNom());


				prepare.executeUpdate();
				
				


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

		if (connect != null) {

			// recherche des cercles
			CercleJdbcDao dao = new CercleJdbcDao(connect,nom);

			List<Cercle> cercles = dao.findFormes(nom);

			if (!cercles.isEmpty()) {
			
				for (Cercle forme : cercles) {
					res.ajouterForme(forme);
				}

			}
			// recherche des carres
			CarreJdbcDao daoC = new CarreJdbcDao(connect,nom);

			List<Carre> carres = daoC.findFormes(nom);

			if (!carres.isEmpty()) {

				for (Carre forme : carres) {
					res.ajouterForme(forme);
				}

			}


			// recherche des rectangles
			RectangleJdbcDao daoR = new RectangleJdbcDao(connect,nom);

			List<Rectangle> rectangles = daoR.findFormes(nom);

			if (!rectangles.isEmpty()) {

				for (Rectangle forme : rectangles) {
					res.ajouterForme(forme);
				}

			}


			// recherche des triangles
			TriangleJdbcDao daoT = new TriangleJdbcDao(connect,nom);

			List<Triangle> triangles = daoT.findFormes(nom);

			if (!triangles.isEmpty()) {

				for (Triangle forme : triangles) {
					res.ajouterForme(forme);
				}

			}
		}
		
		if (res.getFormes().isEmpty()) {
			res = null;
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
