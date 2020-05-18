package main.java.forme;

/**
 * 
 * @author claire
 *
 */

public interface GraphicForme {

	
	public  enum TYPE_FORME {CERCLE, CARRE,RECTANGLE,TRIANGLE,COMPOSE}
	
	public  void affiche() ;
	
	public void deplace(int deplX, int deplY);
	
	public String getNom();
	
	public TYPE_FORME getType() ;
	
}
