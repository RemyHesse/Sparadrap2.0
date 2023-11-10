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
import fr.afpa.pompey.cda22045.metier.Contient;

/**
 * 
 */
public class ContientDAO extends DAO<Contient>{

	/**
	 * 
	 */
	public ContientDAO() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean create(Contient obj) {
		Singleton.getInstanceDB();
		
		StringBuilder sqlInsertContient = new StringBuilder();
		sqlInsertContient.append("insert into CONTIENT ");
		sqlInsertContient.append("(MED_ID, STO_ID, MED_QTTE)" );
		sqlInsertContient.append("values (?, ?, ?)");
		
		boolean requeteOk = false;
		
		try ( PreparedStatement preparedStatement = 
				OrdonnanceDAO.connect.prepareStatement(sqlInsertContient.toString(),Statement.RETURN_GENERATED_KEYS)	){
			
			preparedStatement.setInt(1, obj.getMedId());
			preparedStatement.setInt(2, obj.getStoId());
			preparedStatement.setInt(3, obj.getMedQtte());
			
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
	public boolean delete(Contient obj) {
		Singleton.getInstanceDB();
		
	    StringBuilder sqlDeleteContient = new StringBuilder();
	    sqlDeleteContient.append("delete from CONTIENT where MED_ID = ? and STO_ID = ? and MED_QTTE = ?");

	    boolean requeteOk = false;

	    try (PreparedStatement preparedStatement = connect.prepareStatement(sqlDeleteContient.toString())) {
			preparedStatement.setInt(1, obj.getMedId());
			preparedStatement.setInt(2, obj.getStoId());
			preparedStatement.setInt(3, obj.getMedQtte());

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
	public boolean update(Contient obj) {
	    Singleton.getInstanceDB();

	    StringBuilder sqlUpdateContient = new StringBuilder();
	    sqlUpdateContient.append("update CONTIENT set MED_ID = ?, MED_QTTE = ?"
	    		+ " where STO_ID = ? ");

	    boolean requeteOk = false;

	    try (PreparedStatement preparedStatement = connect.prepareStatement(sqlUpdateContient.toString())) {
			preparedStatement.setInt(1, obj.getMedId());
			preparedStatement.setInt(2, obj.getMedQtte());
			preparedStatement.setInt(3, obj.getStoId());
			


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
	public Contient find(Integer pId) throws MonException {
		Singleton.getInstanceDB();
		
		Contient contient = null;

	    StringBuilder sqlSelectContient = new StringBuilder();
	    sqlSelectContient.append("select * from CONTIENT where MED_ID = ?");

	    try (PreparedStatement preparedStatement = connect.prepareStatement(sqlSelectContient.toString())) {
			preparedStatement.setInt(1, pId);

	        ResultSet resultSet = preparedStatement.executeQuery();

	        if (resultSet.next()) {
	           
	            int medId = resultSet.getInt("MED_ID");
	            int stoId = resultSet.getInt("STO_ID");
	            int medQtte = resultSet.getInt("MED_QTTE");

	            contient = new Contient(medId, stoId, medQtte);
	        }


	    } catch (SQLException sqle) {
	        System.out.println("Erreur de relation avec la bdd : " + sqle.getMessage()
	                + " [ code d'erreur SQL : " + sqle.getSQLState() + " ]");
	    }

	    return contient;
	}

	@Override
	public ArrayList<Contient> findAll() throws MonException {
	    Singleton.getInstanceDB();
	    ArrayList<Contient> contients = new ArrayList<>();

	    StringBuilder sqlSelectAllDelivre = new StringBuilder();
	    sqlSelectAllDelivre.append("select * from DELIVRE");

	    try (PreparedStatement preparedStatement = connect.prepareStatement(sqlSelectAllDelivre.toString())) {
	        ResultSet resultSet = preparedStatement.executeQuery();

	        while (resultSet.next()) {
	            int medId = resultSet.getInt("MED_ID");
	            int stoId = resultSet.getInt("STO_ID");
	            int medQtte = resultSet.getInt("MED_QTTE");
	            

	            Contient contient = new Contient(medId, stoId, medQtte);
	            contients.add(contient);
	        }


	    } catch (SQLException sqle) {
	        System.out.println("Erreur de relation avec la bdd : " + sqle.getMessage() +
	                " [ code d'erreur SQL : " + sqle.getSQLState() + " ]");
	    }

	    return contients;
	}

}
