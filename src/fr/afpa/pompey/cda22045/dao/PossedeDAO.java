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
import fr.afpa.pompey.cda22045.metier.Possede;

/**
 * 
 */
public class PossedeDAO extends DAO<Possede>{

	/**
	 * 
	 */
	public PossedeDAO() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean create(Possede obj) {
		Singleton.getInstanceDB();
		
		StringBuilder sqlInsertPossede = new StringBuilder();
		sqlInsertPossede.append("insert into POSSEDE ");
		sqlInsertPossede.append("(DRO_ID, UTI_ID)" );
		sqlInsertPossede.append("values (?, ?)");
		
		boolean requeteOk = false;
		
		try ( PreparedStatement preparedStatement = 
				OrdonnanceDAO.connect.prepareStatement(sqlInsertPossede.toString(),Statement.RETURN_GENERATED_KEYS)	){
			
			preparedStatement.setInt(1, obj.getDroId());
			preparedStatement.setInt(2, obj.getUtiId());
			
			int rowCount = preparedStatement.executeUpdate();

	        if (rowCount > 0) {

	        	requeteOk = true;
	          
	        }
			
			
		} catch (SQLException sqle) {
			System.out.println("Erreur de relation avec la bdd : " + sqle.getMessage()
			+ " [ code d'erreur SQL : " + sqle.getSQLState() + " ]"	);
		}
				
		return requeteOk;
	}

	@Override
	public boolean delete(Possede obj) {
		Singleton.getInstanceDB();
		
	    StringBuilder sqlDeletePossede = new StringBuilder();
	    sqlDeletePossede.append("delete from POSSEDE where DRO_ID = ? and UTI_ID = ? ");

	    boolean requeteOk = false;

	    try (PreparedStatement preparedStatement = connect.prepareStatement(sqlDeletePossede.toString())) {
			preparedStatement.setInt(1, obj.getDroId());
			preparedStatement.setInt(2, obj.getUtiId());

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
	public boolean update(Possede obj) {
	    Singleton.getInstanceDB();

	    StringBuilder sqlUpdatePossede = new StringBuilder();
	    sqlUpdatePossede.append("update POSSEDE set DRO_ID = ?"
	    		+ " where UTI_ID = ?");

	    boolean requeteOk = false;

	    try (PreparedStatement preparedStatement = connect.prepareStatement(sqlUpdatePossede.toString())) {

			preparedStatement.setInt(1, obj.getDroId());
			preparedStatement.setInt(2, obj.getUtiId());

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
	public Possede find(Integer pId) throws MonException {
		Singleton.getInstanceDB();
		
		Possede possede = null;

	    StringBuilder sqlSelectPossede = new StringBuilder();
	    sqlSelectPossede.append("select * from POSSEDE where UTI_ID = ?");

	    try (PreparedStatement preparedStatement = connect.prepareStatement(sqlSelectPossede.toString())) {
			preparedStatement.setInt(1, pId);

	        ResultSet resultSet = preparedStatement.executeQuery();

	        if (resultSet.next()) {
	           
	        	int droId = resultSet.getInt("DRO_ID");
	            int utiId = resultSet.getInt("UTI_ID");

	            possede = new Possede(droId, utiId);
	        }


	    } catch (SQLException sqle) {
	        System.out.println("Erreur de relation avec la bdd : " + sqle.getMessage()
	                + " [ code d'erreur SQL : " + sqle.getSQLState() + " ]");
	    }

	    return possede;
	}

	@Override
	public ArrayList<Possede> findAll() throws MonException {
		// TODO Auto-generated method stub
		return null;
	}

}
