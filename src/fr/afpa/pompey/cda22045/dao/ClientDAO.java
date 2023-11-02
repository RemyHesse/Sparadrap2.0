/**
 * 
 */
package fr.afpa.pompey.cda22045.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import fr.afpa.pompey.cda22045.exception.MonException;
import fr.afpa.pompey.cda22045.metier.Client;

/**
 * 
 */
public class ClientDAO extends DAO<Client>{

	public static int cliID;
	/**
	 * 
	 */
	public ClientDAO() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean create(Client obj) {
		// TODO Auto-generated method stub
		
		Singleton.getInstanceDB();
		
		StringBuilder sqlInsertPersonne = new StringBuilder();
		sqlInsertPersonne.append("insert into CLIENT ");
		sqlInsertPersonne.append("(ADR_ID, CLI_NOM, CLI_PRENOM, CLI_TEL, CLI_EMAIL, MUT_ID, CLI_DATE_NAISS)" );
		sqlInsertPersonne.append("values (?, ?, ?, ?, ?,? ,?)");
		
		boolean requeteOk = false;
		
		try ( PreparedStatement prepareStatement = 
				this.connect.prepareStatement(sqlInsertPersonne.toString(),Statement.RETURN_GENERATED_KEYS)	){
			
			prepareStatement.setInt(1,  obj.getAdresse() );
			prepareStatement.setString(2, obj.getNom());
			prepareStatement.setString(3, obj.getPrenom());
			prepareStatement.setString(4, obj.getTelephone());
			prepareStatement.setString(5, obj.getEmail());
			prepareStatement.setInt(6, obj.getMutId());
			prepareStatement.setDate(7, obj.getCliDateNaiss());
			
			
			int rowCount = prepareStatement.executeUpdate();

	        if (rowCount > 0) {
	            ResultSet generatedKeys = prepareStatement.getGeneratedKeys();
	            if (generatedKeys.next()) {
	                int generatedCliId = generatedKeys.getInt(1);
	                obj.setCliId(generatedCliId); 
	                requeteOk = true;
	            }
	        }
			
			
		} catch (SQLException sqle) {
			System.out.println("Erreur de relation avec la bdd : " + sqle.getMessage()
			+ " [ code d'erreur SQL : " + sqle.getSQLState() + " ]"	);
		}
				
		return requeteOk;
	}

	@Override
	public boolean delete(Client obj) {
		Singleton.getInstanceDB();
		
	    StringBuilder sqlDeleteClient = new StringBuilder();
	    sqlDeleteClient.append("delete from CLIENT where CLI_ID = ?");

	    boolean requeteOk = false;

	    try (PreparedStatement prepareStatement = connect.prepareStatement(sqlDeleteClient.toString())) {
	        prepareStatement.setInt(1, obj.getCliId());

	        int rowCount = prepareStatement.executeUpdate();

	        if (rowCount > 0) {
	            requeteOk = true;
	        }


	    } catch (SQLException sqle) {
	        System.out.println("Erreur de relation avec la bdd : " + sqle.getMessage()
	                + " [ code d'erreur SQL : " + sqle.getSQLState() + " ]");
	    }

	    return requeteOk;
	}


	@Override
	public boolean update(Client obj) {
	    Singleton.getInstanceDB();

	    StringBuilder sqlUpdateClient = new StringBuilder();
	    sqlUpdateClient.append("update CLIENT set ADR_ID = ?, CLI_NOM = ?, CLI_PRENOM = ?, CLI_TEL = ?, CLI_EMAIL = ?,"
	    		+ " MUT_ID = ?, CLI_DATE_NAISS = ? where CLI_ID = ?");

	    boolean requeteOk = false;

	    try (PreparedStatement preparedStatement = connect.prepareStatement(sqlUpdateClient.toString())) {
	        preparedStatement.setInt(1, obj.getAdresse());
	        preparedStatement.setString(2, obj.getNom());
	        preparedStatement.setString(3, obj.getPrenom());
	        preparedStatement.setString(4, obj.getTelephone());
	        preparedStatement.setString(5, obj.getEmail());
	        preparedStatement.setInt(6, obj.getMutId());
	        preparedStatement.setDate(7, obj.getCliDateNaiss());
	        preparedStatement.setInt(8, obj.getCliId()); // Assurez-vous d'avoir un getter pour l'ID du client (CLI_ID) dans la classe Client

	        int rowCount = preparedStatement.executeUpdate();

	        if (rowCount > 0) {
	            requeteOk = true;
	        }


	    } catch (SQLException sqle) {
	        System.out.println("Erreur de relation avec la bdd : " + sqle.getMessage() +
	                " [ code d'erreur SQL : " + sqle.getSQLState() + " ]");
	    }

	    return requeteOk;
	}


	@Override
	public Client find(Integer pId) throws MonException {
		
		Singleton.getInstanceDB();
		
	    Client client = null;

	    StringBuilder sqlSelectClient = new StringBuilder();
	    sqlSelectClient.append("select * from CLIENT where CLI_ID = ?");

	    try (PreparedStatement preparedStatement = connect.prepareStatement(sqlSelectClient.toString())) {
	        preparedStatement.setInt(1, pId);

	        ResultSet resultSet = preparedStatement.executeQuery();

	        if (resultSet.next()) {
	           
	            int clientId = resultSet.getInt("CLI_ID");
	            int adresseId = resultSet.getInt("ADR_ID");
	            String nom = resultSet.getString("CLI_NOM");
	            String prenom = resultSet.getString("CLI_PRENOM");
	            String telephone = resultSet.getString("CLI_TEL");
	            String email = resultSet.getString("CLI_EMAIL");
	            int mutuelleId = resultSet.getInt("MUT_ID");
	            Date dateNaissance = resultSet.getDate("CLI_DATE_NAISS");

	            client = new Client(clientId, prenom, nom, adresseId, telephone, email, mutuelleId, (java.sql.Date) dateNaissance);
	        }


	    } catch (SQLException sqle) {
	        System.out.println("Erreur de relation avec la bdd : " + sqle.getMessage()
	                + " [ code d'erreur SQL : " + sqle.getSQLState() + " ]");
	    }

	    return client;
	}

	@Override
	public List<Client> findAll() throws MonException {
	    Singleton.getInstanceDB();
	    List<Client> clients = new ArrayList<>();

	    StringBuilder sqlSelectAllClients = new StringBuilder();
	    sqlSelectAllClients.append("select * from CLIENT");

	    try (PreparedStatement preparedStatement = connect.prepareStatement(sqlSelectAllClients.toString())) {
	        ResultSet resultSet = preparedStatement.executeQuery();

	        while (resultSet.next()) {
	            int clientId = resultSet.getInt("CLI_ID");
	            int adresseId = resultSet.getInt("ADR_ID");
	            String nom = resultSet.getString("CLI_NOM");
	            String prenom = resultSet.getString("CLI_PRENOM");
	            String telephone = resultSet.getString("CLI_TEL");
	            String email = resultSet.getString("CLI_EMAIL");
	            int mutuelleId = resultSet.getInt("MUT_ID");
	            Date dateNaissance = resultSet.getDate("CLI_DATE_NAISS");

	            Client client = new Client(clientId, nom, prenom, adresseId, telephone, email, mutuelleId, (java.sql.Date) dateNaissance);
	            clients.add(client);
	        }


	    } catch (SQLException sqle) {
	        System.out.println("Erreur de relation avec la bdd : " + sqle.getMessage() +
	                " [ code d'erreur SQL : " + sqle.getSQLState() + " ]");
	    }

	    return clients;
	}

	// Méthode pour récupérer le nom de la mutuelle d'un client
	public static String getMutNom(int mutId) {
	    String mutNom = null;
	    
	    try {
	        String sql = "select MUT_NOM from MUTUELLE where MUT_ID = ?";
	        
	        try (PreparedStatement preparedStatement = connect.prepareStatement(sql)) {
	            preparedStatement.setInt(1, mutId);
	            ResultSet resultSet = preparedStatement.executeQuery();
	            
	            if (resultSet.next()) {
	            	mutNom = resultSet.getString("MUT_NOM");
	            }
	        }
	    } catch (SQLException sqle) {
	        System.out.println("Erreur de relation avec la bdd : " + sqle.getMessage()
	                + " [ code d'erreur SQL : " + sqle.getSQLState() + " ]");
	    }
	    
	    return mutNom;
	}

	// Méthode pour récupérer l'adresse complète d'un client
	public static String getCliAddresse(int cliID) {
	    String clientAddress = null;

	    try {
	        String sql = "select ADR.ADR_NUM, ADR.ADR_LIGNE1, ADR.ADR_COMPLEMENT, VIL.VIL_NOM, DEP.DEP_NOM " +
	                     "from CLIENT CLI " +
	                     "inner join ADRESSE ADR on CLI.ADR_ID = ADR.ADR_ID " +
	                     "inner join VILLE VIL on ADR.VIL_ID = VIL.VIL_ID " +
	                     "inner join DEPARTEMENT DEP on VIL.DEP_ID = DEP.DEP_ID " +
	                     "where CLI.CLI_ID = ?";

	        try (PreparedStatement preparedStatement = connect.prepareStatement(sql)) {
	            preparedStatement.setInt(1, cliID);
	            ResultSet resultSet = preparedStatement.executeQuery();

	            if (resultSet.next()) {
	                int adrNum = resultSet.getInt("ADR_NUM");
	                String adrLigne1 = resultSet.getString("ADR_LIGNE1");
	                String adrComplement = resultSet.getString("ADR_COMPLEMENT");
	                String vilNom = resultSet.getString("VIL_NOM");
	                String depNom = resultSet.getString("DEP_NOM");

	                clientAddress = adrNum + " " + adrLigne1 + " " + adrComplement + ", " + vilNom + ", " + depNom;
	            }
	        }
	    } catch (SQLException sqle) {
	        System.out.println("Erreur de relation avec la bdd : " + sqle.getMessage()
	                + " [ code d'erreur SQL : " + sqle.getSQLState() + " ]");
	    }

	    return clientAddress;
	}

	
}
