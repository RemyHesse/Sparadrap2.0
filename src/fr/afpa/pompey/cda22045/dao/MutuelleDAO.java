/**
 * 
 */
package fr.afpa.pompey.cda22045.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import fr.afpa.pompey.cda22045.exception.MonException;
import fr.afpa.pompey.cda22045.metier.Mutuelle;

/**
 * 
 */
public class MutuelleDAO extends DAO<Mutuelle>{

	/**
	 * 
	 */
	public MutuelleDAO() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean create(Mutuelle obj) {
		Singleton.getInstanceDB();
		
		StringBuilder sqlInsertMutuelle = new StringBuilder();
		sqlInsertMutuelle.append("insert into MUTUELLE ");
		sqlInsertMutuelle.append("(ADR_ID, MUT_NOM, MUT_EMAIL, MUT_TAUX)" );
		sqlInsertMutuelle.append("values (?, ?, ?, ?)");
		
		boolean requeteOk = false;
		
		try ( PreparedStatement preparedStatement = 
				MutuelleDAO.connect.prepareStatement(sqlInsertMutuelle.toString(),Statement.RETURN_GENERATED_KEYS)	){
			
			preparedStatement.setInt(1,  obj.getAdrId() );
			preparedStatement.setString(2, obj.getMutNom());
			preparedStatement.setString(3, obj.getMutEmail());
			preparedStatement.setInt(4, obj.getMutTaux());
			
			
			int rowCount = preparedStatement.executeUpdate();

	        if (rowCount > 0) {
	            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
	            if (generatedKeys.next()) {
	                int generatedMutId = generatedKeys.getInt(1);
	                obj.setMutId(generatedMutId);
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
	public boolean delete(Mutuelle obj) {
		Singleton.getInstanceDB();
		
	    StringBuilder sqlDeleteMutuelle = new StringBuilder();
	    sqlDeleteMutuelle.append("delete from MUTUELLE where MUT_ID = ?");

	    boolean requeteOk = false;

	    try (PreparedStatement preparedStatement = connect.prepareStatement(sqlDeleteMutuelle.toString())) {
	        preparedStatement.setInt(1, obj.getMutId());

	        int rowCount = preparedStatement.executeUpdate();

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
	public boolean update(Mutuelle obj) {
	    Singleton.getInstanceDB();

	    StringBuilder sqlUpdateClient = new StringBuilder();
	    sqlUpdateClient.append("update MUTUELLE set ADR_ID = ?, MUT_NOM = ?, MUT_EMAIL = ?, MUT_TAUX = ? where MUT_ID = ?");

	    boolean requeteOk = false;

	    try (PreparedStatement preparedStatement = connect.prepareStatement(sqlUpdateClient.toString())) {
        preparedStatement.setInt(1, obj.getAdrId());
	        preparedStatement.setString(2, obj.getMutNom());
	        preparedStatement.setString(3, obj.getMutEmail());
	        preparedStatement.setInt(4, obj.getMutTaux());
	        preparedStatement.setInt(5, obj.getMutId()); 

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
	public Mutuelle find(Integer pMutId) throws MonException {
		Singleton.getInstanceDB();
		
		Mutuelle mutuelle = null;

	    StringBuilder sqlSelectClient = new StringBuilder();
	    sqlSelectClient.append("select * from CLIENT where CLI_ID = ?");

	    try (PreparedStatement preparedStatement = connect.prepareStatement(sqlSelectClient.toString())) {
	        preparedStatement.setInt(1, pMutId);

	        ResultSet resultSet = preparedStatement.executeQuery();

	        if (resultSet.next()) {
	           
	            int mutId = resultSet.getInt("MUT_ID");
	            int adresseId = resultSet.getInt("ADR_ID");
	            String nom = resultSet.getString("Mut_NOM");
	            String email = resultSet.getString("MUT_EMAIL");
	            int mutTaux = resultSet.getInt("MUT_TAUX");

	            mutuelle = new Mutuelle(mutId, adresseId, nom, email, mutTaux);
	        }


	    } catch (SQLException sqle) {
	        System.out.println("Erreur de relation avec la bdd : " + sqle.getMessage()
	                + " [ code d'erreur SQL : " + sqle.getSQLState() + " ]");
	    }

	    return mutuelle;
	}

	@Override
	public ArrayList<Mutuelle> findAll() throws MonException {
	    Singleton.getInstanceDB();
	    ArrayList<Mutuelle> mutuelles = new ArrayList<>();

	    StringBuilder sqlSelectAllClients = new StringBuilder();
	    sqlSelectAllClients.append("select * from MUTUELLE");

	    try (PreparedStatement preparedStatement = connect.prepareStatement(sqlSelectAllClients.toString())) {
	        ResultSet resultSet = preparedStatement.executeQuery();

	        while (resultSet.next()) {
	            int mutId = resultSet.getInt("MUT_ID");
	            int adresseId = resultSet.getInt("ADR_ID");
	            String nom = resultSet.getString("Mut_NOM");
	            String email = resultSet.getString("MUT_EMAIL");
	            int mutTaux = resultSet.getInt("MUT_TAUX");

	            Mutuelle mutuelle = new Mutuelle(mutId, adresseId, nom, email, mutTaux);
	            mutuelles.add(mutuelle);
	        }


	    } catch (SQLException sqle) {
	        System.out.println("Erreur de relation avec la bdd : " + sqle.getMessage() +
	                " [ code d'erreur SQL : " + sqle.getSQLState() + " ]");
	    }

	    return mutuelles;
	}

}
