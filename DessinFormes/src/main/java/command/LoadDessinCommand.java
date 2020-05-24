package main.java.command;

import main.java.DrawingTUI;
import main.java.dao.FormeCompositeJdbcDao;
import main.java.dao.JdbcDAOFactory;
import main.java.forme.FormeComposite;

/**
 * Commande pour charger le dessin depuis la base de données
 * @author claire
 *
 */


public class LoadDessinCommand  implements Command{

	private String nomDessin ;
	
	private FormeComposite dessin ;
	
	/**
	 * 
	 * @param nomDessin
	 */
	public LoadDessinCommand (String nomDessin) {
		
		this.nomDessin = nomDessin ;
		
		
	}
	
	
	/**
	 * Affichage des formes
	 */
	public void execute () {

		JdbcDAOFactory daoFactory = new JdbcDAOFactory();
		FormeCompositeJdbcDao  daoFormeComposite = (FormeCompositeJdbcDao)daoFactory.getFormeCompositeDAO( "/home/devc/tools/derby/");

		// sauvegarde du FormeComposite en base
		dessin = daoFormeComposite.find(nomDessin);

		// affichage du dessin
		if (dessin != null) {
			dessin.affiche();

			// mise a jour du dessin dans la classe principale
			DrawingTUI drawingTUI = DrawingTUI.getInstance(dessin );
			drawingTUI.setDessin(dessin);
		}
		else {
			System.out.println("Aucun dessin de ce nom sauvegardé");
		}

	}


	
	
	
	
	
	
}
