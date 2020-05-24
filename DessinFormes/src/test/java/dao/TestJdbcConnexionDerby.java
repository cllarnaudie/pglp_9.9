package test.java.dao;

import java.sql.Connection;

import org.junit.Test;

import main.java.dao.JdbcConnexionDerby;

public class TestJdbcConnexionDerby {

	@Test
	public void testConnexion() {
		
		JdbcConnexionDerby driver = new JdbcConnexionDerby("dessinbd", "/home/devc/tools/derby/");
	      
		// connexion a la base
		Connection connect = driver.getChaineConnexion() ;
		
		// fermeture de la connexion
		JdbcConnexionDerby.closeConnexion(connect);
		
	}

}
