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
import fr.afpa.pompey.cda22045.metier.Medicament;

/**
 * 
 */
public class MedicamentDAO extends DAO<Medicament>{

	/**
	 * 
	 */
	public MedicamentDAO() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean create(Medicament obj) {
		Singleton.getInstanceDB();
		
		StringBuilder sqlInsertMedicament = new StringBuilder();
		sqlInsertMedicament.append("insert into MEDICAMENT ");
		sqlInsertMedicament.append("(CAT_ID, MED_NOM, MED_PRIX, MED_MISE_EN_SERVICE)" );
		sqlInsertMedicament.append("values (?, ?, ?, ?)");
		
		boolean requeteOk = false;
		
		try ( PreparedStatement preparedStatement = 
				this.connect.prepareStatement(sqlInsertMedicament.toString(),Statement.RETURN_GENERATED_KEYS)	){
			
			preparedStatement.setInt(1, obj.getCatId());
			preparedStatement.setString(2, obj.getMedNom());
			preparedStatement.setFloat(3, obj.getMedPrix());
			preparedStatement.setDate(4, Date.valueOf(obj.getMedMiseEnService()));
			
			
			int rowCount = preparedStatement.executeUpdate();

	        if (rowCount > 0) {
	            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
	            if (generatedKeys.next()) {
	                int generatedMedId = generatedKeys.getInt(1);
	                obj.setMedId(generatedMedId);
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
	public boolean delete(Medicament obj) {
		Singleton.getInstanceDB();
		
	    StringBuilder sqlDeleteMedicament = new StringBuilder();
	    sqlDeleteMedicament.append("delete from MEDICAMENT where MED_ID = ?");

	    boolean requeteOk = false;

	    try (PreparedStatement preparedStatement = connect.prepareStatement(sqlDeleteMedicament.toString())) {
	        preparedStatement.setInt(1, obj.getMedId());

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
	public boolean update(Medicament obj) {
	    Singleton.getInstanceDB();

	    StringBuilder sqlUpdateMedicament = new StringBuilder();
	    sqlUpdateMedicament.append("update MEDICAMENT set CAT_ID = ?, MED_NOM = ?, MED_PRIX = ?, MED_MISE_EN_SERVICE = ? where MED_ID = ?");

	    boolean requeteOk = false;

	    try (PreparedStatement preparedStatement = connect.prepareStatement(sqlUpdateMedicament.toString())) {
	        preparedStatement.setInt(1, obj.getCatId());
	        preparedStatement.setString(2, obj.getMedNom());
	        preparedStatement.setFloat(3, obj.getMedPrix());
	        preparedStatement.setDate(4, Date.valueOf(obj.getMedMiseEnService()));

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
	public Medicament find(Integer pMedId) throws MonException {
		Singleton.getInstanceDB();
		
		Medicament medicament = null;

	    StringBuilder sqlSelectMedicament = new StringBuilder();
	    sqlSelectMedicament.append("select * from MEDICAMENT where MED_ID = ?");

	    try (PreparedStatement preparedStatement = connect.prepareStatement(sqlSelectMedicament.toString())) {
	        preparedStatement.setInt(1, pMedId);

	        ResultSet resultSet = preparedStatement.executeQuery();

	        if (resultSet.next()) {
	           
	            int medId = resultSet.getInt("MED_ID");
	            int catId = resultSet.getInt("CAT_ID");
	            String mednom = resultSet.getString("MED_NOM");
	            float medPrix = resultSet.getFloat("MED_PRIX");
	            Date medMiseEnService= resultSet.getDate("MED_MISE_EN_SERVICE");


	            medicament = new Medicament(medId, catId, mednom, medPrix, medMiseEnService.toLocalDate());
	        }


	    } catch (SQLException sqle) {
	        System.out.println("Erreur de relation avec la bdd : " + sqle.getMessage()
	                + " [ code d'erreur SQL : " + sqle.getSQLState() + " ]");
	    }

	    return medicament;
	}

	@Override
	public List<Medicament> findAll() throws MonException {
	    Singleton.getInstanceDB();
	    List<Medicament> medicaments = new ArrayList<>();

	    StringBuilder sqlSelectAllMedicaments = new StringBuilder();
	    sqlSelectAllMedicaments.append("select * from MEDICAMENT");

	    try (PreparedStatement preparedStatement = connect.prepareStatement(sqlSelectAllMedicaments.toString())) {
	        ResultSet resultSet = preparedStatement.executeQuery();

	        while (resultSet.next()) {
	            int medId = resultSet.getInt("MED_ID");
	            int catId = resultSet.getInt("CAT_ID");
	            String mednom = resultSet.getString("MED_NOM");
	            float medPrix = resultSet.getFloat("MED_PRIX");
	            Date medMiseEnService= resultSet.getDate("MED_MISE_EN_SERVICE");

	            Medicament medicament = new Medicament(medId, catId, mednom, medPrix, ((java.sql.Date) medMiseEnService).toLocalDate());
	            medicaments.add(medicament);
	        }


	    } catch (SQLException sqle) {
	        System.out.println("Erreur de relation avec la bdd : " + sqle.getMessage() +
	                " [ code d'erreur SQL : " + sqle.getSQLState() + " ]");
	    }

	    return medicaments;
	}

}
