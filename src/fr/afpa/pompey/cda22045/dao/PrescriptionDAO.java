/**
 * 
 */
package fr.afpa.pompey.cda22045.dao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import fr.afpa.pompey.cda22045.exception.MonException;
import fr.afpa.pompey.cda22045.metier.Ordonnance;
import fr.afpa.pompey.cda22045.metier.Prescription;

/**
 * 
 */
public class PrescriptionDAO extends DAO<Prescription>{

	/**
	 * 
	 */
	public PrescriptionDAO() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean create(Prescription obj) {
		Singleton.getInstanceDB();
		
		StringBuilder sqlInsertPrescription = new StringBuilder();
		sqlInsertPrescription.append("insert into PRESCRIPTION ");
		sqlInsertPrescription.append("(ORD_ID, MED_ID, MED_QTTE)" );
		sqlInsertPrescription.append("values (?, ?, ?)");
		
		boolean requeteOk = false;
		
		try ( PreparedStatement preparedStatement = 
				this.connect.prepareStatement(sqlInsertPrescription.toString(),Statement.RETURN_GENERATED_KEYS)	){
			
			preparedStatement.setInt(1, obj.getOrdId());
			preparedStatement.setInt(2, obj.getMedId());
			preparedStatement.setInt(3, obj.getMedQtte());
			
			int rowCount = preparedStatement.executeUpdate();

	        if (rowCount > 0) {
//	            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
//	            if (generatedKeys.next()) {
//	                int generatedCliId = generatedKeys.getInt(1);
//	                obj.setOrdId(generatedCliId);
	                requeteOk = true;
//	            }
	        }
			
			
		} catch (SQLException sqle) {
			System.out.println("Erreur de relation avec la bdd : " + sqle.getMessage()
			+ " [ code d'erreur SQL : " + sqle.getSQLState() + " ]"	);
		}
				
		return requeteOk;
	}

	@Override
	public boolean delete(Prescription obj) {
		Singleton.getInstanceDB();
		
	    StringBuilder sqlDeletePrescription = new StringBuilder();
	    sqlDeletePrescription.append("delete from PRESCRIPTION where ORD_ID = ? and MED_ID = ?");

	    boolean requeteOk = false;

	    try (PreparedStatement preparedStatement = connect.prepareStatement(sqlDeletePrescription.toString())) {
	        preparedStatement.setInt(1, obj.getOrdId());
	        preparedStatement.setInt(2, obj.getMedId());

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
	public boolean update(Prescription obj) {
	    Singleton.getInstanceDB();

	    StringBuilder sqlUpdatePrescription = new StringBuilder();
	    sqlUpdatePrescription.append("update PRESCRIPTION set MED_QTTE = ?, where ORD_ID = ? and MED_ID = ?");

	    boolean requeteOk = false;

	    try (PreparedStatement preparedStatement = connect.prepareStatement(sqlUpdatePrescription.toString())) {
	    	preparedStatement.setInt(1, obj.getMedQtte() );
	    	preparedStatement.setInt(2, obj.getOrdId() );
	        preparedStatement.setInt(3, obj.getMedId()); 

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
	public Prescription find(Integer pOrdId, Integer pMedId) throws MonException {
		Singleton.getInstanceDB();
		
		Prescription prescription = null;

	    StringBuilder sqlSelectPrescription = new StringBuilder();
	    sqlSelectPrescription.append("select * from PRESCRIPTION where ORD_ID = ? and MED_ID = ?");

	    try (PreparedStatement preparedStatement = connect.prepareStatement(sqlSelectPrescription.toString())) {
	        preparedStatement.setInt(1, pOrdId);
	        preparedStatement.setInt(2, pMedId);

	        ResultSet resultSet = preparedStatement.executeQuery();

	        if (resultSet.next()) {
	           
	            int ordId = resultSet.getInt("ORD_ID");
	            int medId= resultSet.getInt("MED_ID");
	            int medQtte= resultSet.getInt("MED_QTTE");
	            
	            prescription = new Prescription(ordId, medId, medQtte);
	        }


	    } catch (SQLException sqle) {
	        System.out.println("Erreur de relation avec la bdd : " + sqle.getMessage()
	                + " [ code d'erreur SQL : " + sqle.getSQLState() + " ]");
	    }

	    return prescription;
	}

	@Override
	public List<Prescription> findAll() throws MonException {
	    Singleton.getInstanceDB();
	    List<Prescription> prescriptions = new ArrayList<>();

	    StringBuilder sqlSelectAllPrescriptions = new StringBuilder();
	    sqlSelectAllPrescriptions.append("select * from PRESCRIPTION");

	    try (PreparedStatement preparedStatement = connect.prepareStatement(sqlSelectAllPrescriptions.toString())) {
	        ResultSet resultSet = preparedStatement.executeQuery();

	        while (resultSet.next()) {
	        	int ordId = resultSet.getInt("ORD_ID");
	            int medId = resultSet.getInt("MED_ID");
	            int medQtte = resultSet.getInt("MED_QTTE");

	            Prescription prescription = new Prescription(ordId, medId, medQtte);
	            prescriptions.add(prescription);
	        }


	    } catch (SQLException sqle) {
	        System.out.println("Erreur de relation avec la bdd : " + sqle.getMessage() +
	                " [ code d'erreur SQL : " + sqle.getSQLState() + " ]");
	    }

	    return prescriptions;
	}

	@Override
	public Prescription find(Integer pId) throws MonException {
		// TODO Auto-generated method stub
		return null;
	}

}
