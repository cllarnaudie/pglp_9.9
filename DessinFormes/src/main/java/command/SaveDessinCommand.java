package main.java.command;

import main.java.dao.FormeCompositeJdbcDao;
import main.java.dao.JdbcDAOFactory;
import main.java.forme.FormeComposite;

/**
 * Commande pour enregistrer le dessin en cours en base de donnees
 * @author claire
 *
 */


public class SaveDessinCommand  implements Command{

	private FormeComposite dessin ;
	
	
	
	/**
	 * Constructeur
	 * @param dessin
	 */
	public SaveDessinCommand (FormeComposite dessin) {
		
		this.dessin = dessin ;
		
		
	}
	
	
	/**
	 * Enregistrement du dessin en base de donnees
	 */
	public void execute () {
		
		JdbcDAOFactory daoFactory = new JdbcDAOFactory();
		FormeCompositeJdbcDao  daoFormeComposite = (FormeCompositeJdbcDao)daoFactory.getFormeCompositeDAO( "/home/devc/tools/derby/");

		// sauvegarde du FormeComposite en base
		daoFormeComposite.create(dessin);
		
		System.out.println("Sauvegarde reussie");
		
	}
	
}
