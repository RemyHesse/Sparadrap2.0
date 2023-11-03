package fr.afpa.pompey.cda22045.utilitaires;

import java.sql.Date;
import java.util.List;

import fr.afpa.pompey.cda22045.dao.ClientDAO;
import fr.afpa.pompey.cda22045.dao.MedecinDAO;
import fr.afpa.pompey.cda22045.dao.DAO;
import fr.afpa.pompey.cda22045.exception.MonException;
import fr.afpa.pompey.cda22045.metier.Client;
import fr.afpa.pompey.cda22045.metier.Medecin;

public class Debug {

	private static Client client11;
	private static Medecin medecin6;

	public Debug() throws MonException {
		// TODO Auto-generated constructor stub

//		try {
//		Singleton.getInstanceDB();
//
//		String sql = "select * from MEDICAMENT ";
//		Statement statement = Singleton.getConnection().createStatement();
//		ResultSet result = statement.executeQuery(sql);
//		while (result.next()) {
//			System.out.println(
//					"Résultat : " + result.getString("MED_NOM") + " : " + result.getDouble("MED_PRIX") + " €");
//		}
//		Singleton.closeInstanceDB();
//	} catch (SQLException e) {
//		// TODO Auto-generated catch block
//		e.printStackTrace();
//	}

//		DAO<Client> clientDAO = new ClientDAO();
//
//		try {
//			client11 = new Client("Alberto", "Almodovard", 10, "0444444444", "alberto.almodovard@email.com", 5,
//					Date.valueOf("1983-05-05"));
//			System.out.println(clientDAO);
//
//			if (clientDAO.create(client11)) {
//
//				System.out.println("Client enregistré.");
//			} else {
//				System.out.println("Client non enregistré.");
//			}
//
//			System.out.println(clientDAO);
//
//		} catch (IllegalArgumentException e) {
//			e.printStackTrace();
//		}
//
//		try {
//			if (clientDAO.find(12) != null) {
//				System.out.println(clientDAO.find(12).toString());
//			} else {
//				System.out.println("Client non trouvé");
//			}
//
//		} catch (IllegalArgumentException e) {
//			e.printStackTrace();
//		}
//
//		List<Client> clients = clientDAO.findAll();
//
//		// Parcourez la liste des clients
//		for (Client client : clients) {
//			System.out.println(client.toString());
//		}
//
//		Client cli3 = clientDAO.find(3);
//		System.out.println(cli3.toString());
		
		
		DAO<Medecin> medecinDAO = new MedecinDAO();
		
		try {
			medecin6 = new Medecin("Bernard", "Bouvier", 8, "0444444444", "bernard.bouvier@email.com", 6666);
			System.out.println(medecinDAO);

			if (medecinDAO.create(medecin6)) {

				System.out.println("Médecin enregistré.");
			} else {
				System.out.println("Médecin non enregistré.");
			}

			System.out.println(medecinDAO);

		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		}
		
		
		try {
			if (medecinDAO.find(6) != null) {
				System.out.println(medecinDAO.find(6).toString());
			} else {
				System.out.println("Médecin non trouvé");
			}

		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		}
		
		List<Medecin> medecins = medecinDAO.findAll();

		// Parcourez la liste des clients
		for (Medecin medecin : medecins) {
			System.out.println(medecin.toString());
		}

		Medecin med3 = medecinDAO.find(3);
		System.out.println(med3.toString());
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		

	}

}
