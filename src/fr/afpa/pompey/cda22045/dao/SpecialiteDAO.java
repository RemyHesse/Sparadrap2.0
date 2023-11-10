package fr.afpa.pompey.cda22045.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import fr.afpa.pompey.cda22045.exception.MonException;
import fr.afpa.pompey.cda22045.metier.Specialite;

public class SpecialiteDAO extends DAO<Specialite> {

	public SpecialiteDAO() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean create(Specialite obj) {
		Singleton.getInstanceDB();
		
		StringBuilder sqlInsertSpecialite = new StringBuilder();
		sqlInsertSpecialite.append("insert into SPECIALITE ");
		sqlInsertSpecialite.append("(SPE_LABEL)" );
		sqlInsertSpecialite.append("values (?)");
		
		boolean requeteOk = false;
		
		try ( PreparedStatement preparedStatement = 
				SpecialiteDAO.connect.prepareStatement(sqlInsertSpecialite.toString(),Statement.RETURN_GENERATED_KEYS)	){
			
			preparedStatement.setString(1,  obj.getSpeLabel() );

			
			
			int rowCount = preparedStatement.executeUpdate();

	        if (rowCount > 0) {
	            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
	            if (generatedKeys.next()) {
	                int generatedCliId = generatedKeys.getInt(1);
	                obj.setSpeId(generatedCliId);
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
	public boolean delete(Specialite obj) {
		Singleton.getInstanceDB();
		
	    StringBuilder sqlDeleteSpecialite = new StringBuilder();
	    sqlDeleteSpecialite.append("delete from SPECIALITE where SPE_ID = ?");

	    boolean requeteOk = false;

	    try (PreparedStatement preparedStatement = connect.prepareStatement(sqlDeleteSpecialite.toString())) {
	        preparedStatement.setInt(1, obj.getSpeId());

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
	public boolean update(Specialite obj) {
	    Singleton.getInstanceDB();

	    StringBuilder sqlUpdateSpecialite = new StringBuilder();
	    sqlUpdateSpecialite.append("update SPECIALITE set SPE_LABEL = ?, where SPE_ID = ?");

	    boolean requeteOk = false;

	    try (PreparedStatement preparedStatement = connect.prepareStatement(sqlUpdateSpecialite.toString())) {
	    	preparedStatement.setString(1,  obj.getSpeLabel() );
	        preparedStatement.setInt(2, obj.getSpeId()); 

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
	public Specialite find(Integer pSpeId) throws MonException {
		Singleton.getInstanceDB();
		
		Specialite specialite = null;

	    StringBuilder sqlSelectSpecialite = new StringBuilder();
	    sqlSelectSpecialite.append("select * from SPECIALITE where SPE_ID = ?");

	    try (PreparedStatement preparedStatement = connect.prepareStatement(sqlSelectSpecialite.toString())) {
	        preparedStatement.setInt(1, pSpeId);

	        ResultSet resultSet = preparedStatement.executeQuery();

	        if (resultSet.next()) {
	           
	            int speId = resultSet.getInt("SPE_ID");
	            String speLabel = resultSet.getString("SPE_LABEL");
	            
	            specialite = new Specialite(speId, speLabel);
	        }


	    } catch (SQLException sqle) {
	        System.out.println("Erreur de relation avec la bdd : " + sqle.getMessage()
	                + " [ code d'erreur SQL : " + sqle.getSQLState() + " ]");
	    }

	    return specialite;
	}

	@Override
	public ArrayList<Specialite> findAll() throws MonException {
	    Singleton.getInstanceDB();
	    ArrayList<Specialite> specialites = new ArrayList<>();

	    StringBuilder sqlSelectAllSpecialites = new StringBuilder();
	    sqlSelectAllSpecialites.append("select * from SPECIALITE");

	    try (PreparedStatement preparedStatement = connect.prepareStatement(sqlSelectAllSpecialites.toString())) {
	        ResultSet resultSet = preparedStatement.executeQuery();

	        while (resultSet.next()) {
	            int speId = resultSet.getInt("SPE_ID");
	            String speLabel = resultSet.getString("SPE_LABEL");

	            Specialite specialite = new Specialite(speId, speLabel);
	            specialites.add(specialite);
	        }


	    } catch (SQLException sqle) {
	        System.out.println("Erreur de relation avec la bdd : " + sqle.getMessage() +
	                " [ code d'erreur SQL : " + sqle.getSQLState() + " ]");
	    }

	    return specialites;
	}

}
