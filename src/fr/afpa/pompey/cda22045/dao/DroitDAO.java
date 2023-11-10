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
import fr.afpa.pompey.cda22045.metier.Droit;

/**
 * 
 */
public class DroitDAO extends DAO<Droit> {

	/**
	 * 
	 */
	public DroitDAO() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean create(Droit obj) {
		Singleton.getInstanceDB();
		
		StringBuilder sqlInsertDroit = new StringBuilder();
		sqlInsertDroit.append("insert into DROIT ");
		sqlInsertDroit.append("(DRO_LABEL)" );
		sqlInsertDroit.append("values (?)");
		
		boolean requeteOk = false;
		
		try ( PreparedStatement preparedStatement = 
				OrdonnanceDAO.connect.prepareStatement(sqlInsertDroit.toString(),Statement.RETURN_GENERATED_KEYS)	){
			
			preparedStatement.setString(1, obj.getDroLabel());
			
			int rowCount = preparedStatement.executeUpdate();

	        if (rowCount > 0) {
	            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
	            if (generatedKeys.next()) {
	                int generatedCliId = generatedKeys.getInt(1);
	                obj.setDroId(generatedCliId);
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
	public boolean delete(Droit obj) {
		Singleton.getInstanceDB();
		
	    StringBuilder sqlDeleteDroit = new StringBuilder();
	    sqlDeleteDroit.append("delete from DROIT where DRO_ID = ?");

	    boolean requeteOk = false;

	    try (PreparedStatement preparedStatement = connect.prepareStatement(sqlDeleteDroit.toString())) {
	        preparedStatement.setInt(1, obj.getDroId());

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
	public boolean update(Droit obj) {
	    Singleton.getInstanceDB();

	    StringBuilder sqlUpdateDroit = new StringBuilder();
	    sqlUpdateDroit.append("update DROIT set DRO_LABEL = ?, where DRO_ID = ?");

	    boolean requeteOk = false;

	    try (PreparedStatement preparedStatement = connect.prepareStatement(sqlUpdateDroit.toString())) {
	    	preparedStatement.setString(1, obj.getDroLabel() );
	        preparedStatement.setInt(2, obj.getDroId()); 

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
	public Droit find(Integer pId) throws MonException {
		Singleton.getInstanceDB();
		
		Droit droit = null;

	    StringBuilder sqlSelectDroit = new StringBuilder();
	    sqlSelectDroit.append("select * from DROIT where DRO_ID = ?");

	    try (PreparedStatement preparedStatement = connect.prepareStatement(sqlSelectDroit.toString())) {
	        preparedStatement.setInt(1, pId);

	        ResultSet resultSet = preparedStatement.executeQuery();

	        if (resultSet.next()) {
	           
	            int droId = resultSet.getInt("DRO_ID");
	            String droLabel = resultSet.getString("DRO_LABEL");
	            
	            droit = new Droit(droId, droLabel);
	        }


	    } catch (SQLException sqle) {
	        System.out.println("Erreur de relation avec la bdd : " + sqle.getMessage()
	                + " [ code d'erreur SQL : " + sqle.getSQLState() + " ]");
	    }

	    return droit;
	}

	@Override
	public ArrayList<Droit> findAll() throws MonException {
	    Singleton.getInstanceDB();
	    ArrayList<Droit> droits = new ArrayList<>();

	    StringBuilder sqlSelectAllDroit = new StringBuilder();
	    sqlSelectAllDroit.append("select * from DROIT");

	    try (PreparedStatement preparedStatement = connect.prepareStatement(sqlSelectAllDroit.toString())) {
	        ResultSet resultSet = preparedStatement.executeQuery();

	        while (resultSet.next()) {
	        	int droId = resultSet.getInt("DRO_ID");
	            String droLabel = resultSet.getString("DRO_LABEL");

	            Droit droit = new Droit(droId, droLabel);
	            droits.add(droit);
	        }


	    } catch (SQLException sqle) {
	        System.out.println("Erreur de relation avec la bdd : " + sqle.getMessage() +
	                " [ code d'erreur SQL : " + sqle.getSQLState() + " ]");
	    }

	    return droits;
	}

}
