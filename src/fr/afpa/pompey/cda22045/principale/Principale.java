/**
 * 
 */
package fr.afpa.pompey.cda22045.principale;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import fr.afpa.pompey.cda22045.dao.DAO;
import fr.afpa.pompey.cda22045.dao.ClientDAO;
import fr.afpa.pompey.cda22045.dao.Singleton;
import fr.afpa.pompey.cda22045.exception.MonException;
import fr.afpa.pompey.cda22045.metier.Client;

/**
 * 
 */
public class Principale {

	private static Client client11;
	/**
	 * 
	 */
	public Principale() {
		// TODO Auto-generated constructor stub
	}


	/**
	 * @param args
	 * @throws MonException 
	 */
	public static void main(String[] args) throws MonException {
		// TODO Auto-generated method stub

//		try {
//			Singleton.getInstanceDB();
//
//			String sql = "select * from MEDICAMENT ";
//			Statement statement = Singleton.getConnection().createStatement();
//			ResultSet result = statement.executeQuery(sql);
//			while (result.next()) {
//				System.out.println(
//						"Résultat : " + result.getString("MED_NOM") + " : " + result.getDouble("MED_PRIX") + " €");
//			}
//			Singleton.closeInstanceDB();
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		
		DAO<Client> clientDAO = new ClientDAO();
		
		
		
		try {
			client11 = new Client("Alberto", "Almodovard",  10, "0444444444",  "alberto.almodovard@email.com",  5, Date.valueOf("1983-05-05"));
			System.out.println(clientDAO);
			
			if ( clientDAO.create(client11) ) {
				
				System.out.println("Client enregistré.");
			}else {
				System.out.println("Client non enregistré.");
			}
			
			System.out.println(clientDAO);
			
		} catch ( IllegalArgumentException e) {
			e.printStackTrace();
		}
		
		
		try {
			if ( clientDAO.find(12) != null ) {
				System.out.println(clientDAO.find(12).toString());
			}else {
				System.out.println("Client non trouvé");
			}
			
		}catch ( IllegalArgumentException e) {
			e.printStackTrace();
		}
		
		
		List<Client> clients = clientDAO.findAll();

		// Parcourez la liste des clients
		for (Client client : clients) {
		    System.out.println( client.toString());
		}
		
		
		
		
		
		
		
		
		
		
		
		
		
		
        Singleton.closeInstanceDB();	
		

	}

	
	
	
}
