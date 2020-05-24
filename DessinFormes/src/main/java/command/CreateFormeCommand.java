package main.java.command;

import main.java.forme.Forme;
import main.java.forme.FormeComposite;

/**
 * Commande pour creer une forme
 * @author devc
 *
 */


public class CreateFormeCommand  implements Command{

	private FormeComposite dessin ;
	
	private Forme forme;
	
	/**
	 * Constructeur
	 * @param forme
	 * @param dessin
	 */
	public CreateFormeCommand (Forme forme, FormeComposite dessin) {
		
		this.dessin = dessin ;
		this.forme = forme;
		
	}
	
	
	/**
	 * Ajout de la forme au dessin 
	 */
	public void execute () {
		dessin.getFormes().add(forme);
		
		// affichage de la forme
		forme.affiche();
	}
	
}
