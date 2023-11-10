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
import fr.afpa.pompey.cda22045.exception.MonException;
import fr.afpa.pompey.cda22045.metier.Medicament;
import fr.afpa.pompey.cda22045.metier.Ordonnance;

/**
 * 
 */
public class OrdonnanceDAO extends DAO<Ordonnance>{

	/**
	 * 
	 */
	public OrdonnanceDAO() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean create(Ordonnance obj) {
		Singleton.getInstanceDB();
		
		StringBuilder sqlInsertOrdonnance = new StringBuilder();
		sqlInsertOrdonnance.append("insert into ORDONNANCE ");
		sqlInsertOrdonnance.append("(ORD_DATE)" );
		sqlInsertOrdonnance.append("values (?)");
		
		boolean requeteOk = false;
		
		try ( PreparedStatement preparedStatement = 
				OrdonnanceDAO.connect.prepareStatement(sqlInsertOrdonnance.toString(),Statement.RETURN_GENERATED_KEYS)	){
			
			preparedStatement.setDate(1, Date.valueOf(obj.getOrdDate()));
			
			int rowCount = preparedStatement.executeUpdate();

	        if (rowCount > 0) {
	            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
	            if (generatedKeys.next()) {
	                int generatedCliId = generatedKeys.getInt(1);
	                obj.setOrdId(generatedCliId);
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
	public boolean delete(Ordonnance obj) {
		Singleton.getInstanceDB();
		
	    StringBuilder sqlDeleteOrdonnance = new StringBuilder();
	    sqlDeleteOrdonnance.append("delete from ORDONNANCE where ORD_ID = ?");

	    boolean requeteOk = false;

	    try (PreparedStatement preparedStatement = connect.prepareStatement(sqlDeleteOrdonnance.toString())) {
	        preparedStatement.setInt(1, obj.getOrdId());

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
	public boolean update(Ordonnance obj) {
	    Singleton.getInstanceDB();

	    StringBuilder sqlUpdateOrdonnance = new StringBuilder();
	    sqlUpdateOrdonnance.append("update ORDONNANCE set ORD_DATE = ?, where ORD_ID = ?");

	    boolean requeteOk = false;

	    try (PreparedStatement preparedStatement = connect.prepareStatement(sqlUpdateOrdonnance.toString())) {
	    	preparedStatement.setDate(1, Date.valueOf(obj.getOrdDate() ));
	        preparedStatement.setInt(2, obj.getOrdId()); 

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
	public Ordonnance find(Integer pOrdId) throws MonException {
		Singleton.getInstanceDB();
		
		Ordonnance ordonnance = null;

	    StringBuilder sqlSelectOrdonnance = new StringBuilder();
	    sqlSelectOrdonnance.append("select * from ORDONNANCE where ORD_ID = ?");

	    try (PreparedStatement preparedStatement = connect.prepareStatement(sqlSelectOrdonnance.toString())) {
	        preparedStatement.setInt(1, pOrdId);

	        ResultSet resultSet = preparedStatement.executeQuery();

	        if (resultSet.next()) {
	           
	            int ordId = resultSet.getInt("ORD_ID");
	            Date ordDate = resultSet.getDate("ORD_DATE");
	            
	            ordonnance = new Ordonnance(ordId, ((java.sql.Date) ordDate).toLocalDate());
	        }


	    } catch (SQLException sqle) {
	        System.out.println("Erreur de relation avec la bdd : " + sqle.getMessage()
	                + " [ code d'erreur SQL : " + sqle.getSQLState() + " ]");
	    }

	    return ordonnance;
	}

	@Override
	public ArrayList<Ordonnance> findAll() throws MonException {
	    Singleton.getInstanceDB();
	    ArrayList<Ordonnance> ordonnances = new ArrayList<>();

	    StringBuilder sqlSelectAllSpecialites = new StringBuilder();
	    sqlSelectAllSpecialites.append("select * from ORDONNANCE");

	    try (PreparedStatement preparedStatement = connect.prepareStatement(sqlSelectAllSpecialites.toString())) {
	        ResultSet resultSet = preparedStatement.executeQuery();

	        while (resultSet.next()) {
	        	int ordId = resultSet.getInt("ORD_ID");
	            Date ordDate = resultSet.getDate("ORD_DATE");

	            Ordonnance ordonnance = new Ordonnance(ordId, ((java.sql.Date) ordDate).toLocalDate());
	            ordonnances.add(ordonnance);
	        }


	    } catch (SQLException sqle) {
	        System.out.println("Erreur de relation avec la bdd : " + sqle.getMessage() +
	                " [ code d'erreur SQL : " + sqle.getSQLState() + " ]");
	    }

	    return ordonnances;
	}
	
	public String getNomMedecin(Ordonnance obj) {
		
		String nomMedecin = null;
		
	    Singleton.getInstanceDB();

	    StringBuilder sqlUpdateOrdonnance = new StringBuilder();
	    sqlUpdateOrdonnance.append("select MED.MED_PRENOM, MED.MED_NOM from MEDECIN MED"
	    		+ " join DELIVRE DEL on DEL.MED_ID = MED.MED_ID"
	    		+ " join ORDONNANCE ORD on ORD.ORD_ID = DEL.ORD_ID"	    		
	    		+ " where ORD.ORD_ID = ?");



	    try (PreparedStatement preparedStatement = connect.prepareStatement(sqlUpdateOrdonnance.toString())) {
	        preparedStatement.setInt(1, obj.getOrdId()); 

	        ResultSet resultSet = preparedStatement.executeQuery();

	        if (resultSet.next()) {
                String medPrenom = resultSet.getString("MED_PRENOM");
                String medNom = resultSet.getString("MED_NOM");
                nomMedecin = "Dr " + medPrenom + " " + medNom;
                return nomMedecin;
	        }


	    } catch (SQLException sqle) {
	        System.out.println("Erreur de relation avec la bdd : " + sqle.getMessage() +
	                " [ code d'erreur SQL : " + sqle.getSQLState() + " ]");
	    }
		
		
		return nomMedecin;
		
	}
	
	public String getNomClient(Ordonnance obj) {
		
		String nomClient = null;
		
	    Singleton.getInstanceDB();

	    StringBuilder sqlUpdateOrdonnance = new StringBuilder();
	    sqlUpdateOrdonnance.append("select CLI.CLI_PRENOM, CLI.CLI_NOM from CLIENT CLI"
	    		+ " join DELIVRE DEL on DEL.CLI_ID = CLI.CLI_ID"
	    		+ " join ORDONNANCE ORD on ORD.ORD_ID = DEL.ORD_ID"	    		
	    		+ " where ORD.ORD_ID = ?");



	    try (PreparedStatement preparedStatement = connect.prepareStatement(sqlUpdateOrdonnance.toString())) {
	        preparedStatement.setInt(1, obj.getOrdId()); 

	        ResultSet resultSet = preparedStatement.executeQuery();

	        if (resultSet.next()) {
                String cliPrenom = resultSet.getString("CLI_PRENOM");
                String cliNom = resultSet.getString("CLI_NOM");
                nomClient = cliPrenom + " " + cliNom;
	        }


	    } catch (SQLException sqle) {
	        System.out.println("Erreur de relation avec la bdd : " + sqle.getMessage() +
	                " [ code d'erreur SQL : " + sqle.getSQLState() + " ]");
	    }
				
		return nomClient;
		
	}	
	
	
	public ArrayList<Medicament> getListeMedoc(Ordonnance obj){
		
		ArrayList<Medicament> listeMedoc = new ArrayList<Medicament>();
		
	    StringBuilder sqlListeMedocParOrdo = new StringBuilder();
	    sqlListeMedocParOrdo.append("select MED.MED_ID, MED.CAT_ID, MED.MED_NOM, MED.MED_PRIX, MED.MED_MISE_EN_SERVICE"
	    		+ " from MEDICAMENT MED"
	    		+ " join PRESCRIPTION PRE on PRE.MED_ID = MED.MED_ID"
	    		+ " join ORDONNANCE ORD on ORD.ORD_ID = PRE.ORD_ID"
	    		+ " where ORD.ORD_ID = ?");
	    
	    try (PreparedStatement preparedStatement = connect.prepareStatement(sqlListeMedocParOrdo.toString())) {
	        preparedStatement.setInt(1, obj.getOrdId()); 

	        ResultSet resultSet = preparedStatement.executeQuery();

	        while (resultSet.next()) {
	        	int medId = resultSet.getInt("MED_ID");
	            int catId = resultSet.getInt("CAT_ID");
	            String mednom = resultSet.getString("MED_NOM");
	            float medPrix = resultSet.getFloat("MED_PRIX");
	            Date medMiseEnService= resultSet.getDate("MED_MISE_EN_SERVICE");


	            Medicament medicament = new Medicament(medId, catId, mednom, medPrix, medMiseEnService.toLocalDate());
                
                listeMedoc.add(medicament);
	        }


	    } catch (SQLException sqle) {
	        System.out.println("Erreur de relation avec la bdd : " + sqle.getMessage() +
	                " [ code d'erreur SQL : " + sqle.getSQLState() + " ]");
	    }
		
		
		return listeMedoc;
		
	}
	
	
	
	
	
	
	

}
