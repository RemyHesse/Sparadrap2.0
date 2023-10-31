/**
 * 
 */
package fr.afpa.pompey.cda22045.dao;

import java.awt.HeadlessException;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * 
 */
public class Singleton {

	/**
	 * 
	 */
	private static final Properties props = new Properties();
	private static Connection connection; 

	private Singleton() {
		// TODO Auto-generated constructor stub
		try (FileInputStream fichier = new FileInputStream("./ressources/propriete.properties")) {

			props.load(fichier);
			
			Class.forName(props.getProperty("jdbc.driver.class"));
			props.setProperty("user", props.getProperty("jdbc.user"));
			props.setProperty("password",props.getProperty("jdbc.password"));
			
			connection = DriverManager.getConnection(props.getProperty("jdbc.url"), props);
			
			System.out.println("Connexion réussie");
			
			
		} catch (HeadlessException | IOException | ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Erreur de connection : " + e.getMessage());
			e.printStackTrace();
		}
		
	}	
	
	public static Connection getConnection() { return connection; }
	
	public static Connection getInstanceDB() {
		
		if (getConnection() == null ) {
			new Singleton();
			System.out.println("Connexion à la base établie.");
		}else {
			System.out.println("Connexion à la base déjà existante.");
		}
		return getConnection();
		
	}
		
	public static void closeInstanceDB() {
		
		try {
			Singleton.getConnection().close();
			System.out.println("Connexion à la base terminée.");
		}catch(SQLException sqle) {
			System.out.println("Erreur de communication avec la base : "
				+ sqle.getMessage() + " [Code erreur SQL : " + sqle.getSQLState() + " ]") ;
			
		}
		
	}

}
