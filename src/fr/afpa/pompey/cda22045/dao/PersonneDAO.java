/**
 * 
 */
package fr.afpa.pompey.cda22045.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

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
				this.connect.prepareStatement(sqlInsertPersonne.toString())	){
			
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
		// TODO Auto-generated method stub
		return false;
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
	public List<Personne> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
