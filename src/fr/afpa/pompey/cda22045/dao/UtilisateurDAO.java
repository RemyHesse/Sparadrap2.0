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
import fr.afpa.pompey.cda22045.metier.Utilisateur;

/**
 * 
 */
public class UtilisateurDAO extends DAO<Utilisateur>{

	/**
	 * 
	 */
	public UtilisateurDAO() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean create(Utilisateur obj) {
		Singleton.getInstanceDB();
		
		StringBuilder sqlInsertUtilisateur = new StringBuilder();
		sqlInsertUtilisateur.append("insert into UTILISATEUR ");
		sqlInsertUtilisateur.append("(UTI_LOGIN, UTI_NOM, UTI_PRENOM)" );
		sqlInsertUtilisateur.append("values (?, ?, ?)");
		
		boolean requeteOk = false;
		
		try ( PreparedStatement preparedStatement = 
				UtilisateurDAO.connect.prepareStatement(sqlInsertUtilisateur.toString(),Statement.RETURN_GENERATED_KEYS)	){
			
			preparedStatement.setString(1,  obj.getUtiLogin() );
			preparedStatement.setString(2, obj.getUtiNom());
			preparedStatement.setString(3, obj.getUtiPrenom());			
			
			int rowCount = preparedStatement.executeUpdate();

	        if (rowCount > 0) {
	            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
	            if (generatedKeys.next()) {
	                int generatedCliId = generatedKeys.getInt(1);
	                obj.setUtiId(generatedCliId);
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
	public boolean delete(Utilisateur obj) {
		Singleton.getInstanceDB();
		
	    StringBuilder sqlDeleteUtilisateur = new StringBuilder();
	    sqlDeleteUtilisateur.append("delete from UTILISATEUR where UTI_ID = ?");

	    boolean requeteOk = false;

	    try (PreparedStatement preparedStatement = connect.prepareStatement(sqlDeleteUtilisateur.toString())) {
	        preparedStatement.setInt(1, obj.getUtiId());

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
	public boolean update(Utilisateur obj) {
	    Singleton.getInstanceDB();

	    StringBuilder sqlUpdateUtilisateur = new StringBuilder();
	    sqlUpdateUtilisateur.append("update UTILISATEUR set UTI_LOGIN = ? , UTI_NOM = ? , UTI_PRENOM = ? where UTI_ID = ?");

	    boolean requeteOk = false;

	    try (PreparedStatement preparedStatement = connect.prepareStatement(sqlUpdateUtilisateur.toString())) {
	        preparedStatement.setString(1, obj.getUtiLogin());
	        preparedStatement.setString(2, obj.getUtiNom());
	        preparedStatement.setString(3, obj.getUtiPrenom());
	        preparedStatement.setInt(4, obj.getUtiId()); 
	        

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
	public Utilisateur find(Integer pId) throws MonException {
		Singleton.getInstanceDB();
		
		Utilisateur utilisateur = null;

	    StringBuilder sqlSelectUtilisateur = new StringBuilder();
	    sqlSelectUtilisateur.append("select * from UTILISATEUR where UTI_ID = ?");

	    try (PreparedStatement preparedStatement = connect.prepareStatement(sqlSelectUtilisateur.toString())) {
	        preparedStatement.setInt(1, pId);

	        ResultSet resultSet = preparedStatement.executeQuery();

	        if (resultSet.next()) {
	           
	        	String utiLogin = resultSet.getString("UTI_LOGIN");
	            String utiNom = resultSet.getString("UTI_NOM");
	            String utiPrenom = resultSet.getString("UTI_PRENOM");
         


	            utilisateur = new Utilisateur(utiPrenom, utiNom, utiLogin);
	        }


	    } catch (SQLException sqle) {
	        System.out.println("Erreur de relation avec la bdd : " + sqle.getMessage()
	                + " [ code d'erreur SQL : " + sqle.getSQLState() + " ]");
	    }

	    return utilisateur;
	}

	@Override
	public ArrayList<Utilisateur> findAll() throws MonException {
	    Singleton.getInstanceDB();
	    ArrayList<Utilisateur> utilisateurs = new ArrayList<>();

	    StringBuilder sqlSelectAllUtilisateurs = new StringBuilder();
	    sqlSelectAllUtilisateurs.append("select * from CLIENT");

	    try (PreparedStatement preparedStatement = connect.prepareStatement(sqlSelectAllUtilisateurs.toString())) {
	        ResultSet resultSet = preparedStatement.executeQuery();

	        while (resultSet.next()) {
	        	String utiLogin = resultSet.getString("UTI_LOGIN");
	            String utiNom = resultSet.getString("UTI_NOM");
	            String utiPrenom = resultSet.getString("UTI_PRENOM");

	            Utilisateur utilisateur = new Utilisateur(utiLogin, utiNom, utiPrenom);
	            utilisateurs.add(utilisateur);
	        }


	    } catch (SQLException sqle) {
	        System.out.println("Erreur de relation avec la bdd : " + sqle.getMessage() +
	                " [ code d'erreur SQL : " + sqle.getSQLState() + " ]");
	    }

	    return utilisateurs;
	}

}
