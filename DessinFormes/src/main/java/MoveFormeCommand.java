package main.java;

import java.util.List;

import main.java.forme.FormeComposite;
import main.java.forme.GraphicForme;

/**
 * Commande pour deplacer une forme
 * @author devc
 *
 */


public class MoveFormeCommand  implements Command{

	// dessin en cours
	FormeComposite dessin;
	
	// nom de la forme a deplacer
	String nomForme;
	
	// deplacement en X
	int deplX;
	
	// deplacement en Y
	int deplY;
	
	/**
	 * Commande pour deplacer une forme
	 * @param nomForme
	 * @param deplX
	 * @param deplY
	 * @param dessin
	 */
	public MoveFormeCommand (String nomForme, int deplX, int deplY, FormeComposite dessin) {
		this.dessin = dessin ;
		this.nomForme = nomForme;
		this.deplX = deplX;
		this.deplY = deplY;
		
	}
	
	
	public void execute () {
		
		List <GraphicForme> formes = dessin.getFormes();
		
		for (GraphicForme elt : formes) {
			if (elt.getNom().equals(nomForme)) {
				elt.deplace(deplX, deplY);
			}
		}
		
	}
	
}
