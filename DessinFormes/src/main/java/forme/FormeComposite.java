package main.java.forme;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List; 

/**
 * 
 * @author claire
 *
 */


public  class FormeComposite implements GraphicForme, Serializable{
	
	private  static  final  long serialVersionUID= 150 ; 

	private List <GraphicForme> formes= new ArrayList<GraphicForme>();  
	
	private String nom ;
	

	/**
	 * 
	 * @param nom
	 */
	public FormeComposite(String nom) {
		this.nom = nom;
		
	}
	
	
	/**
	 * Ajouter une forme a la liste des formes
	 * @param forme
	 */
	public void ajouterForme(Forme forme) {
		if (formes.contains(forme) == true) {
			System.out.println ("Le membre est déjà dans la liste \n"); 
		}
		else {
			formes.add(forme); 
			
		}
	}

	/**
	 * Supprimer une forme a la liste des formes
	 * @param forme
	 */
	public void supprimerForme(Forme forme) {
		if (formes.isEmpty() == true) {
			System.out.println ("La liste est vide \n"); 
		}
		else {

			if (formes.contains(forme) == true) {
				formes.remove(forme); 
				System.out.println ("La forme " + forme.getNom() + " a ete supprimee de la liste \n"); 
			}
		}
	}


	
	
	
	/**
	 * Affiche les attributs de la classe
	 * */
	 
	 
	public void affiche () {
		
		System.out.println ( getNom());

		for ( GraphicForme elt : formes) {

			elt.affiche();
		
		}
	}
	
	
	
	/**
	 * Deplacement
	 */
	public void deplace(int x, int y ) {
		
		for ( GraphicForme elt : formes) {

			elt.deplace(x,y);
		
		}
	}
	
	
	
	/**
	 * Affiche le nom des formes
	 */
	public String getNom() {

		
		return nom ;
	}
	
	
	/**
	 * 
	 */
	public TYPE_FORME getType() {
		
		return GraphicForme.TYPE_FORME.COMPOSE;
	}
	
	

	public List<GraphicForme> getFormes() {
		return formes;
	}

	public void setFormes(List<GraphicForme> formes) {
		this.formes = formes;
	}
	
	
	



}
