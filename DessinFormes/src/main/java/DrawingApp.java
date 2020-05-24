package main.java;

import java.util.Scanner;

import main.java.command.Command;
import main.java.forme.FormeComposite;

/**
 * Classe principale
 * @author claire
 *
 */
public class DrawingApp {


	/**
	 * Saisie des commandes et execution des commandes
	 */
	void run () {
		
		// saisie du nom du dessin
		Scanner sc = new Scanner(System.in);
		System.out.println("\nSaisissez le nom du dessin");
		String nomDessin = sc.nextLine();

		// creation de la forme composite
		FormeComposite dessin = new FormeComposite(nomDessin);
		DrawingTUI drawingTUI = DrawingTUI.getInstance(dessin );

		// affichage d instructions pour l utilisateur

		System.out.println("Pour creer une forme : nom de la forme, Type de forme, attributs de la forme");
		System.out.println("Attributs :  Cercle : centreX centreY rayon  Triangle : xPoint1 yPoint1 xPoint2 yPoint2 xPoint3 yPoint3");
		System.out.println("             Carre : xPointHautGauche yPointHautGauche cote   Rectangle : xPointHautGauche yPointHautGauche largeur longueur");
		System.out.println("Pour deplacer une forme : move, nom de la forme, deplacement en x, deplacement en y");
		System.out.println("Pour charger un dessin : load nomDuDessin ");
		System.out.println("Pour sauvegarder le dessin : save ");


		// saisie des commandes

		boolean fin = false;

		while (sc.hasNext() && !fin) {

			// saisie de la prochaine commande par l utilisateur
			String saisieUtilisateur = sc.nextLine();

			if (!saisieUtilisateur.trim().startsWith("fin") ) {
				// mise en forme de la commande
				Command command = drawingTUI.nextCommand(saisieUtilisateur.trim());

				// execution de la commande
				if (command != null) {
					command.execute();
				}
			}
			else {
				fin = true;
				System.out.println("Fin du programme");
				System.exit(0);
			}

		}

		sc.close();
	}



	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		DrawingApp dr = new DrawingApp();

		dr.run ();

	}

}
