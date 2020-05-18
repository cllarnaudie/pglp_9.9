package main.java.dao;

/**
 * Interface DAO pour les operations CRUD ( create, read, update, delete)
 * @author claire
 *
 * @param <T>
 */
public abstract class DAO <T>{
	
	
	// creation
	public abstract T create (T obj) ;
	
	// recherche
	public abstract T find (String id) ;
	
	// mise a jour
	public abstract T update (T obj) ;
	
	// suppression
	public abstract void delete (T obj) ; 
}
