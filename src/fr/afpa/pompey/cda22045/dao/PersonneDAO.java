/**
 * 
 */
package fr.afpa.pompey.cda22045.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import fr.afpa.pompey.cda22045.metier.Personne;

/**
 * 
 */
public class PersonneDAO extends DAO<Personne>{

	/**
	 * 
	 */
	public PersonneDAO() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean create(Personne obj) {
		// TODO Auto-generated method stub
		StringBuilder sqlInsertPersonne = new StringBuilder();
		sqlInsertPersonne.append("insert into PERSONNE ");
		sqlInsertPersonne.append("(ADR_ID, PER_NOM, PER_PRENOM, PER_TEL, PER_EMAIL)" );
		sqlInsertPersonne.append("values (?, ?, ?, ?, ?)");
		
		boolean requeteOk = false;
		
		try ( PreparedStatement prepareStatement = 
				PersonneDAO.connect.prepareStatement(sqlInsertPersonne.toString())	){
			
			prepareStatement.setInt(1,  obj.getAdresse());
			prepareStatement.setString(2, obj.getNom());
			prepareStatement.setString(3, obj.getPrenom());
			prepareStatement.setString(4, obj.getTelephone());
			prepareStatement.setString(5, obj.getEmail());
			
			prepareStatement.executeUpdate();
			requeteOk = true;
		} catch (SQLException sqle) {
			System.out.println("Erreur de relation avec la bdd : " + sqle.getMessage()
			+ " [ code d'erreur SQL : " + sqle.getSQLState() + " ]"	);
		}
				
		return requeteOk;
	}

	@Override
	public boolean delete(Personne obj) {
		Singleton.getInstanceDB();
		
	    StringBuilder sqlDeletePersonne = new StringBuilder();
	    sqlDeletePersonne.append("delete from PERSONNE where PER_ID = ?");

	    boolean requeteOk = false;

	    try (PreparedStatement preparedStatement = connect.prepareStatement(sqlDeletePersonne.toString())) {
	        preparedStatement.setInt(1, obj.getPerId());

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
	public boolean update(Personne obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Personne find(Integer pId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Personne> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
