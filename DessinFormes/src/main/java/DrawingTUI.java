package main.java;

import java.awt.Point;
import java.util.StringTokenizer;

import main.java.command.AfficheDessinCommand;
import main.java.command.Command;
import main.java.command.CreateFormeCommand;
import main.java.command.LoadDessinCommand;
import main.java.command.MoveFormeCommand;
import main.java.command.SaveDessinCommand;
import main.java.forme.Carre;
import main.java.forme.Cercle;
import main.java.forme.FormeComposite;
import main.java.forme.GraphicForme;
import main.java.forme.Rectangle;
import main.java.forme.Triangle;

/**
 * Interactions avec l utilisateur
 * @author claire
 *
 */


public class DrawingTUI {
	
	
	private static DrawingTUI instance;
	// dessin en cours
	FormeComposite dessin;
	
	/**
	 * Constructeur
	 * @param dessin
	 */
	
	private DrawingTUI(FormeComposite dessin) {
		super();
		this.dessin = dessin;
	}
	
	
	public static DrawingTUI getInstance(FormeComposite dessin) {
		
		if (instance == null) {
			instance = new DrawingTUI(dessin);
		}
		return instance;
		
	}
	
	

	public FormeComposite getDessin() {
		return dessin;
	}



	public void setDessin(FormeComposite dessin) {
		this.dessin = dessin;
	}



	/**
	 * Retourne la valeur entiere du String
	 * @param valeur
	 * @param messageErreurAffiche
	 * @return
	 */
	Integer getResultatEntier (String valeur, String messageErreurAffiche) {
		
		Integer res = null;
		try {
			res = Integer.parseInt (valeur);
		}
		catch (NumberFormatException ex) {
			System.out.println(messageErreurAffiche);
		}
		
		return res;
		
	}

	/**
	 * Analyse le texte de l utilisateur et retourne une commande a executer
	 * @param texteUtilisateur
	 * @return
	 */
	Command nextCommand (String texteUtilisateur)  {
		Command command = null;
		
		// si la commande debute par move
		//     Commande pour deplacer une forme
		//     move, nom de la forme, deplacement en x,deplacement en y
		// sinon  ( commande de creation de forme)
		//     nom de la forme, =, Type de forme, attributs de la forme
		
		if (texteUtilisateur.startsWith("move")) {
				
			// initialisation de la commande - 
			command = initCommandMove(texteUtilisateur);
								
		}
		else if  (texteUtilisateur.startsWith("affiche")) {
			command = afficheDessin();
		}
		else if  (texteUtilisateur.startsWith("save")) {
			command = saveDessin();
		}
		else if  (texteUtilisateur.startsWith("load")) {
			command = loadDessin(texteUtilisateur);
		}
		else {
			// initialisation de la commande - 
			command = initCommandCreate(texteUtilisateur);
			
		}
		
		
		
		
		return command;
	}
	
	
	/**
	 * Initialisation de la commande de deplacement
	 * @param texteUtilisateur
	 * @return
	 */
    Command initCommandMove (String texteCommandeMove)  {

	Command command = null;


	//     Commande pour deplacer une forme
	//     move, nom de la forme, deplacement en x,deplacement en y
	

	String nomForme= "" ;
	int deplX=0 ;
	int deplY =0;
	Integer resInter;

	boolean succes = true;

	// decoupage de la chaine avec les delimiteurs
	StringTokenizer st = new StringTokenizer(texteCommandeMove);

	int index = 0 ;
	while (st.hasMoreTokens() && succes) {

		index ++ ;
		
		if (index == 1) {
			// move
			 st.nextToken() ; 
		}else if (index == 2) {
			// nom de la forme
			nomForme = st.nextToken() ; 
			if (nomForme == null) {
				System.out.println ("Le nom de la commande est vide");
				succes = false;
			}

		} else if (index == 3) {
			// deplacement en x
			resInter = getResultatEntier(st.nextToken(), " La valeur du deplacement en x est incorrecte");
			if (resInter != null) {
				deplX = resInter.intValue() ;
			}
			else {
				succes = false;
			}

		}else if (index == 4) {
			// deplacement en y
			resInter = getResultatEntier(st.nextToken(), " La valeur du deplacement en y est incorrecte");
			if (resInter != null) {
				deplY = resInter.intValue() ;
			}else {
				succes = false;
			}
		}		
	}

	// initialisation de la commande
	if (succes) {
		command = new MoveFormeCommand(nomForme, deplX, deplY, dessin);
	}


		return command ;
    }

    /**
     * Recupertaion de la chaine apres le type de forme
     * @param chaine
     * @param rech
     * @return
     */
    String getChaineAttributs(String chaine, String rech) {
    	
    	String rep = null;
    	
    	int index = chaine.indexOf(rech);
    	
    	int longueurChaine = rech.length();
    	
    	int tailleChaine = chaine.length();
    	
    	if ( (index >0 )  && (index+longueurChaine <= tailleChaine) ){
    		
    		rep = chaine.substring(index + longueurChaine);
    	}
     	
    	
    	return rep;
    }
    
    
    
    /**
	 * Initialisation de la commande de creation de forme
	 * @param texteUtilisateur
	 * @return
	 */
   Command initCommandCreate(String texteUtilisateur)  {

	Command command = null;


	//     Commande pour creer une forme
	//     nom de la forme, type de forme, attributs de la forme
	//     Exemples :
	//          forme1 cercle 10 20 30 
	//          forme2 carre 10 20 30 
	//  		forme3 rectangle 10 20 30 20
	//			forme4 triangle 10 20 30 20 25 50
	

	String nomForme= "" ;
	
	boolean succes = true;

	// decoupage de la chaine avec les delimiteurs
	StringTokenizer st = new StringTokenizer(texteUtilisateur);

	int index = 0 ;
	while (st.hasMoreTokens() && succes && (index < 3)) {

		index ++ ;
		
		if (index == 1) {
			// nom de la forme
			nomForme = st.nextToken() ; 
			if (nomForme == null) {
				System.out.println ("Le nom de la commande est vide");
				succes = false;
			}

		}
		
		if (index == 2) {
			// type de la forme
			String typeForme = st.nextToken() ; 
			if (typeForme.equals(GraphicForme.TYPE_FORME.CERCLE.name())) {
				String attributs = getChaineAttributs(texteUtilisateur, GraphicForme.TYPE_FORME.CERCLE.name());
				if (attributs != null) {
					Cercle cercle = attributsCercle(attributs, nomForme);
					
					if (cercle != null) {
						command = new CreateFormeCommand(cercle, dessin);
					}
				}
				else {
					System.out.println ("Les attributs du cercle ne sont pas renseignés");
					succes = false;
				}
			}
			
			
			else if (typeForme.equals(GraphicForme.TYPE_FORME.CARRE.name())) {
				String attributs = getChaineAttributs(texteUtilisateur, GraphicForme.TYPE_FORME.CARRE.name());
				if (attributs != null) {
					Carre forme = attributsCarre(attributs, nomForme);
					
					if (forme != null) {
						command = new CreateFormeCommand(forme, dessin);
					}
				}
				else {
					System.out.println ("Les attributs du carre ne sont pas renseignés");
					succes = false;
				}
			}
			
			else if (typeForme.equals(GraphicForme.TYPE_FORME.RECTANGLE.name())) {
				String attributs = getChaineAttributs(texteUtilisateur, GraphicForme.TYPE_FORME.RECTANGLE.name());
				if (attributs != null) {
					Rectangle forme = attributsRectangle(attributs, nomForme);
					
					if (forme != null) {
						command = new CreateFormeCommand(forme, dessin);
					}
				}
				else {
					System.out.println ("Les attributs du rectangle ne sont pas renseignés");
					succes = false;
				}
			}
			
			else if (typeForme.equals(GraphicForme.TYPE_FORME.TRIANGLE.name())) {
				String attributs = getChaineAttributs(texteUtilisateur, GraphicForme.TYPE_FORME.TRIANGLE.name());
				if (attributs != null) {
					Triangle forme = attributsTriangle(attributs, nomForme);
					
					if (forme != null) {
						command = new CreateFormeCommand(forme, dessin);
					}
				}
				else {
					System.out.println ("Les attributs du triangle ne sont pas renseignés");
					succes = false;
				}
			}
			
		}


	}



		return command ;
   }



   
   /**
  	 * Initialisation de la commande de creation de forme
  	 * @param texteUtilisateur
  	 * @return
  	 */
    Cercle attributsCercle(String attributs, String nomForme)  {

  

  	//     Commande pour creer une forme
  	//     nom de la forme, type de forme, attributs de la forme
  	//     Exemples :
  	//          forme1 cercle 10 20 30 
  	//          forme2 carre 10 20 30 
  	//  		forme3 rectangle 10 20 30 20
  	//			forme4 triangle 10 20 30 20 25 50
  	

  	
  	int x=0 ;
  	int y =0;
  	int rayon =0;
  	Integer resInter;
  	Cercle cercle=null;

  	boolean succes = true;

  	// decoupage de la chaine avec les delimiteurs
  	StringTokenizer st = new StringTokenizer(attributs);

  	int index = 0 ;
  	while (st.hasMoreTokens() && succes) {

  		index ++ ;
  		
  		if (index == 1) {
  			// x du centre
  			resInter = getResultatEntier(st.nextToken(), " L abcisse du centre est incorrecte");
  			if (resInter != null) {
  				x= resInter.intValue() ;
  			}
  			else {
  				succes = false;
  			}

  		}

  		if (index == 2) {
  			// y du centre
  			resInter = getResultatEntier(st.nextToken(), " L ordonnee du centre est incorrecte");
  			if (resInter != null) {
  				y = resInter.intValue() ;
  			}else {
  				succes = false;
  			}
  		}		
  		

  		if (index == 3) {
  			// rayon
  			resInter = getResultatEntier(st.nextToken(), " Le rayon du centre est incorrect");
  			if (resInter != null) {
  				rayon = resInter.intValue() ;
  			}else {
  				succes = false;
  			}
  		}		
  	}

  	// initialisation de la commande
  	if (succes) {
  		cercle = new Cercle(nomForme, x,y,rayon);
  	}


  		return cercle ;
     }

    /**
  	 * Initialisation de la commande de creation de forme
  	 * @param texteUtilisateur
  	 * @return
  	 */
    Carre attributsCarre(String attributs, String nomForme)  {

  

  	//     Commande pour creer une forme
  	//     nom de la forme, type de forme, attributs de la forme
  	//     Exemples :
  	//          forme2 carre 10 20 30 

  	
  	int x=0 ;
  	int y =0;
  	int cote =0;
  	Integer resInter;
  	Carre carre=null;

  	boolean succes = true;

  	// decoupage de la chaine avec les delimiteurs
  	StringTokenizer st = new StringTokenizer(attributs);

  	int index = 0 ;
  	while (st.hasMoreTokens() && succes) {

  		index ++ ;
  		
  		if (index == 1) {
  			// x du centre
  			resInter = getResultatEntier(st.nextToken(), " L abcisse du carre est incorrecte");
  			if (resInter != null) {
  				x= resInter.intValue() ;
  			}
  			else {
  				succes = false;
  			}

  		}

  		if (index == 2) {
  			// y du centre
  			resInter = getResultatEntier(st.nextToken(), " L ordonnee du carre est incorrecte");
  			if (resInter != null) {
  				y = resInter.intValue() ;
  			}else {
  				succes = false;
  			}
  		}		
  		

  		if (index == 3) {
  			// rayon
  			resInter = getResultatEntier(st.nextToken(), " Le cote du carre est incorrect");
  			if (resInter != null) {
  				cote = resInter.intValue() ;
  			}else {
  				succes = false;
  			}
  		}		
  	}

  	// initialisation de la commande
  	if (succes) {
  		carre = new Carre(nomForme, x,y,cote);
  	}


  		return carre ;
     }


    /**
  	 * Initialisation des attributs du rectangle
  	 * @param texteUtilisateur
  	 * @return
  	 */
    Rectangle attributsRectangle(String attributs, String nomForme)  {

  

  	//     Commande pour creer une forme
  	//     nom de la forme, type de forme, attributs de la forme ( x,y, largeur, longueur)
  	//     Exemples :
  	//          forme2 rectangle 10 20 30  20 

  	
  	int x=0 ;
  	int y =0;
  	int largeur =0;
  	int longueur =0;
  	Integer resInter;
  	Rectangle forme=null;

  	boolean succes = true;

  	// decoupage de la chaine avec les delimiteurs
  	StringTokenizer st = new StringTokenizer(attributs);

  	int index = 0 ;
  	while (st.hasMoreTokens() && succes) {

  		index ++ ;
  		
  		if (index == 1) {
  			// x du centre
  			resInter = getResultatEntier(st.nextToken(), " L abcisse du rectangle est incorrecte");
  			if (resInter != null) {
  				x= resInter.intValue() ;
  			}
  			else {
  				succes = false;
  			}

  		}

  		if (index == 2) {
  			// y du centre
  			resInter = getResultatEntier(st.nextToken(), " L ordonnee du rectangle est incorrecte");
  			if (resInter != null) {
  				y = resInter.intValue() ;
  			}else {
  				succes = false;
  			}
  		}		
  		

  		if (index == 3) {
  			// largeur
  			resInter = getResultatEntier(st.nextToken(), " La largeur du rectangle est incorrecte");
  			if (resInter != null) {
  				largeur = resInter.intValue() ;
  			}else {
  				succes = false;
  			}
  		}	
  		
  		
  		if (index == 4) {
  			// longueur
  			resInter = getResultatEntier(st.nextToken(), " La longueur du rectangle est incorrect");
  			if (resInter != null) {
  				longueur = resInter.intValue() ;
  			}else {
  				succes = false;
  			}
  		}	
  	}

  	// initialisation de la commande
  	if (succes) {
  		forme = new Rectangle(nomForme,x,y, largeur, longueur);
  	}


  		return forme ;
     }

    
    /**
  	 * Initialisation des attributs du rectangle
  	 * @param texteUtilisateur
  	 * @return
  	 */
    Triangle attributsTriangle(String attributs, String nomForme)  {

  

  	//     Commande pour creer une forme
  	//     nom de la forme, type de forme, attributs de la forme ( x,y, largeur, longueur)
  	//     Exemples :
  	//          forme2 rectangle 10 20 30  20 

  	
  	int x1=0 ;
  	int y1 =0;
  	int x2=0 ;
  	int y2 =0;
  	int x3=0 ;
  	int y3 =0;
  	Integer resInter;
  	Triangle forme=null;

  	boolean succes = true;

  	// decoupage de la chaine avec les delimiteurs
  	StringTokenizer st = new StringTokenizer(attributs);

  	int index = 0 ;
  	while (st.hasMoreTokens() && succes) {

  		index ++ ;
  		
  		if (index == 1) {
  			// x du point1
  			resInter = getResultatEntier(st.nextToken(), " L abcisse du premier point du triangle est incorrecte");
  			if (resInter != null) {
  				x1= resInter.intValue() ;
  			}
  			else {
  				succes = false;
  			}

  		}

  		if (index == 2) {
  			// y du point 1
  			resInter = getResultatEntier(st.nextToken(), " L ordonnee du premier point du triangle est incorrecte");
  			if (resInter != null) {
  				y1 = resInter.intValue() ;
  			}else {
  				succes = false;
  			}
  		}		
  		
  		if (index == 3) {
  			// x du point2
  			resInter = getResultatEntier(st.nextToken(), " L abcisse du deuxieme point du triangle est incorrecte");
  			if (resInter != null) {
  				x2= resInter.intValue() ;
  			}
  			else {
  				succes = false;
  			}

  		}

  		if (index == 4) {
  			// y du point 2
  			resInter = getResultatEntier(st.nextToken(), " L ordonnee du deuxieme point du triangle est incorrecte");
  			if (resInter != null) {
  				y2 = resInter.intValue() ;
  			}else {
  				succes = false;
  			}
  		}		
  		
  		if (index == 5) {
  			// x du point3
  			resInter = getResultatEntier(st.nextToken(), " L abcisse du troisieme point du triangle est incorrecte");
  			if (resInter != null) {
  				x3= resInter.intValue() ;
  			}
  			else {
  				succes = false;
  			}

  		}

  		if (index == 6) {
  			// y du point 3
  			resInter = getResultatEntier(st.nextToken(), " L ordonnee du troisieme point du triangle est incorrecte");
  			if (resInter != null) {
  				y3 = resInter.intValue() ;
  			}else {
  				succes = false;
  			}
  		}		
  	}

  	// initialisation de la commande
  	if (succes) {
  		forme = new Triangle(nomForme, new Point(x1,y1), new Point(x2,y2),new Point(x3,y3) );
  	}


  		return forme ;
     }


	
	/**
	 * Affiche un dessin
	 */
	Command  afficheDessin () {
		
		Command command = new AfficheDessinCommand(dessin);
		
		return command;
	}
	
	

	/**
	 * Sauvegarde  du dessin
	 */
	Command  saveDessin () {
		
		Command command = new SaveDessinCommand(dessin);
		
		return command;
	}
	

	/**
	 * Initialise une commande pour charger un dessin depuis la base
	 * @param texteUtilisateur
	 * @return
	 */
	Command  loadDessin (String texteUtilisateur) {

		String nomDessin = null;
		Command command =null;

		// decoupage de la chaine avec les delimiteurs
		StringTokenizer st = new StringTokenizer(texteUtilisateur);

		int index = 0 ;
		while (st.hasMoreTokens() && index <=2) {

			index ++ ;
			
			if (index == 1) {
				// move
				 st.nextToken() ; 
				 
			}else if (index == 2) {
				nomDessin = st.nextToken();

			}

		}


		if (nomDessin != null) {

			command = new LoadDessinCommand(nomDessin);
		}

		return command;
	}


}
