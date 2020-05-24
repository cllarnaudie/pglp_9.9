package main.java.command;

import main.java.forme.FormeComposite;

/**
 * Commande pour afficher les formes
 * @author claire
 *
 */


public class AfficheDessinCommand  implements Command{

	private FormeComposite dessin ;
	
	
	
	public AfficheDessinCommand (FormeComposite dessin) {
		
		this.dessin = dessin ;
		
		
	}
	
	
	/**
	 * Affichage des formes
	 */
	public void execute () {
		
		dessin.affiche();
	}
	
}
