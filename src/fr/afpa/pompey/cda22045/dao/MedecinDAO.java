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
import fr.afpa.pompey.cda22045.metier.Medecin;

/**
 * 
 */
public class MedecinDAO extends DAO<Medecin>{

	/**
	 * 
	 */
	public MedecinDAO() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean create(Medecin obj) {
		Singleton.getInstanceDB();
		
		StringBuilder sqlInsertMedecin = new StringBuilder();
		sqlInsertMedecin.append("insert into MEDECIN ");
		sqlInsertMedecin.append("(ADR_ID, MED_NOM, MED_PRENOM, MED_TEL, MED_EMAIL, MED_AGREMENT)" );
		sqlInsertMedecin.append("values (?, ?, ?, ?, ?, ?)");
		
		boolean requeteOk = false;
		
		try ( PreparedStatement preparedStatement = 
				MedecinDAO.connect.prepareStatement(sqlInsertMedecin.toString(),Statement.RETURN_GENERATED_KEYS)	){
			
			preparedStatement.setInt(1, obj.getAdresse());
			preparedStatement.setString(2, obj.getNom());
			preparedStatement.setString(3, obj.getPrenom());
			preparedStatement.setString(4, obj.getTelephone());
			preparedStatement.setString(5, obj.getEmail());
			preparedStatement.setInt(6, obj.getMedAgrement());

			
			
			int rowCount = preparedStatement.executeUpdate();

	        if (rowCount > 0) {
	            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
	            if (generatedKeys.next()) {
	                int generatedMedId = generatedKeys.getInt(1);
	                obj.setPerId(generatedMedId);
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
	public boolean delete(Medecin obj) {
		Singleton.getInstanceDB();
		
	    StringBuilder sqlDeleteMedecin = new StringBuilder();
	    sqlDeleteMedecin.append("delete from MEDECIN where MED_ID = ?");

	    boolean requeteOk = false;

	    try (PreparedStatement preparedStatement = connect.prepareStatement(sqlDeleteMedecin.toString())) {
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
	public boolean update(Medecin obj) {
	    Singleton.getInstanceDB();

	    StringBuilder sqlUpdateMedecin = new StringBuilder();
	    sqlUpdateMedecin.append("update MEDECIN set ADR_ID = ?, MED_NOM = ?, MED_PRENOM = ?, MED_TEL = ?, MED_EMAIL = ?,"
	    		+ " MED_AGREMENT = ? where MED_ID = ?");

	    boolean requeteOk = false;

	    try (PreparedStatement preparedStatement = connect.prepareStatement(sqlUpdateMedecin.toString())) {
	        preparedStatement.setInt(1, obj.getAdresse());
	        preparedStatement.setString(2, obj.getNom());
	        preparedStatement.setString(3, obj.getPrenom());
	        preparedStatement.setString(4, obj.getTelephone());
	        preparedStatement.setString(5, obj.getEmail());
	        preparedStatement.setInt(6, obj.getMedAgrement());
	        preparedStatement.setInt(7, obj.getPerId()); 

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
	public Medecin find(Integer perId) throws MonException {
		Singleton.getInstanceDB();
		
	    Medecin medecin = null;

	    StringBuilder sqlSelectMedecin = new StringBuilder();
	    sqlSelectMedecin.append("select * from MEDECIN where MED_ID = ?");

	    try (PreparedStatement preparedStatement = connect.prepareStatement(sqlSelectMedecin.toString())) {
	        preparedStatement.setInt(1, perId);

	        ResultSet resultSet = preparedStatement.executeQuery();

	        if (resultSet.next()) {
	           
	            int medecinID = resultSet.getInt("MED_ID");
	            int adresseId = resultSet.getInt("ADR_ID");
	            String nom = resultSet.getString("MED_NOM");
	            String prenom = resultSet.getString("MED_PRENOM");
	            String telephone = resultSet.getString("MED_TEL");
	            String email = resultSet.getString("MED_EMAIL");
	            int medAgrement = resultSet.getInt("MED_AGREMENT");

	            medecin = new Medecin(medecinID, prenom, nom, adresseId, telephone, email, medAgrement);
	        }


	    } catch (SQLException sqle) {
	        System.out.println("Erreur de relation avec la bdd : " + sqle.getMessage()
	                + " [ code d'erreur SQL : " + sqle.getSQLState() + " ]");
	    }

	    return medecin;
	}

	@Override
	public ArrayList<Medecin> findAll() throws MonException {
	    Singleton.getInstanceDB();
	    ArrayList<Medecin> medecins = new ArrayList<>();

	    StringBuilder sqlSelectAllMedecins = new StringBuilder();
	    sqlSelectAllMedecins.append("select * from MEDECIN");

	    try (PreparedStatement preparedStatement = connect.prepareStatement(sqlSelectAllMedecins.toString())) {
	        ResultSet resultSet = preparedStatement.executeQuery();

	        while (resultSet.next()) {
	            int medId = resultSet.getInt("MED_ID");
	            int adresseId = resultSet.getInt("ADR_ID");
	            String nom = resultSet.getString("MED_NOM");
	            String prenom = resultSet.getString("MED_PRENOM");
	            String telephone = resultSet.getString("MED_TEL");
	            String email = resultSet.getString("MED_EMAIL");
	            int medAgrement = resultSet.getInt("MED_AGREMENT");
	            

	            Medecin medecin = new Medecin(medId, prenom, nom, adresseId, telephone, email, medAgrement);
	            medecins.add(medecin);
	        }


	    } catch (SQLException sqle) {
	        System.out.println("Erreur de relation avec la bdd : " + sqle.getMessage() +
	                " [ code d'erreur SQL : " + sqle.getSQLState() + " ]");
	    }

	    return medecins;
	}
	
	public static String getMedAddresse(int adrID) {
	    String medAddresse = "Aucune adresse connue";

	    try {
	        String sql = "select ADR.ADR_NUM, ADR.ADR_LIGNE1, ADR.ADR_COMPLEMENT, VIL.VIL_NOM, DEP.DEP_NOM " +
	                     "from MEDECIN MED " +
	                     "inner join ADRESSE ADR on MED.ADR_ID = ADR.ADR_ID " +
	                     "inner join VILLE VIL on ADR.VIL_ID = VIL.VIL_ID " +
	                     "inner join DEPARTEMENT DEP on VIL.DEP_ID = DEP.DEP_ID " +
	                     "where MED.ADR_ID = ?";

	        try (PreparedStatement preparedStatement = connect.prepareStatement(sql)) {
	            preparedStatement.setInt(1, adrID);
	            ResultSet resultSet = preparedStatement.executeQuery();

	            if (resultSet.next()) {
	                int adrNum = resultSet.getInt("ADR_NUM");
	                String adrLigne1 = resultSet.getString("ADR_LIGNE1");
	                String adrComplement = resultSet.getString("ADR_COMPLEMENT");
	                String vilNom = resultSet.getString("VIL_NOM");
	                String depNom = resultSet.getString("DEP_NOM");

	                medAddresse = adrNum + " " + adrLigne1 + " " + adrComplement + ", " + vilNom + ", " + depNom;
	            }
	        }
	    } catch (SQLException sqle) {
	        System.out.println("Erreur de relation avec la bdd : " + sqle.getMessage()
	                + " [ code d'erreur SQL : " + sqle.getSQLState() + " ]");
	    }

	    return medAddresse;
	}
	
	public String getMedSpecialite(int pMedId) {
		String medSpecialite = "Aucune spécialité enregistrée";
		
		try {
			String requeteSql = " select SPE.SPE_LABEL from SPECIALITE SPE"
					+ "inner join SPECIALISTE SPEC on SPE.SPE_ID = SPEC.SPE_ID"
					+ "inner join MEDECIN MED on SPEC.MED_ID = MED.MED_ID"
					+ "where MED.MED_ID = ?";
			
			 try (PreparedStatement preparedStatement = connect.prepareStatement(requeteSql)) {
		            preparedStatement.setInt(1, pMedId);
		            ResultSet resultSet = preparedStatement.executeQuery();

		            if (resultSet.next()) {
		                medSpecialite = resultSet.getString("SPE_LABEL");
		            }
		        }
		    } catch (SQLException sqle) {
		        System.out.println("Erreur de relation avec la bdd : " + sqle.getMessage()
		                + " [ code d'erreur SQL : " + sqle.getSQLState() + " ]");
		    }
		
		return medSpecialite;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
