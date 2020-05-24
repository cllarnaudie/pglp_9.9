package main.java.dao;

import main.java.forme.Carre;
import main.java.forme.Cercle;
import main.java.forme.FormeComposite;
import main.java.forme.Rectangle;
import main.java.forme.Triangle;

/**
 * Fabrique pour l instanciation des DAO
 * @author claire
 *
 */
public class JdbcDAOFactory  {
	

	public  DAO <FormeComposite> getFormeCompositeDAO(String repertoireBase) {

		return  new FormeCompositeJdbcDao("dessinbd", repertoireBase);

	}

	public  DAO <Carre> getCarreDAO(String repertoireBase) {

		return  new CarreJdbcDao("dessinbd", repertoireBase);

	}

	public  DAO <Cercle> getCercleDAO(String repertoireBase) {

		return  new CercleJdbcDao("dessinbd", repertoireBase);

	}

	public  DAO <Rectangle> getRectangleDAO(String repertoireBase) {

		return  new RectangleJdbcDao("dessinbd", repertoireBase);

	}

	public  DAO <Triangle> getTriangleDAO(String repertoireBase) {

		return  new TriangleJdbcDao("dessinbd", repertoireBase);

	}



	
}
