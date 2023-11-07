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
import fr.afpa.pompey.cda22045.metier.Categorie;
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
				this.connect.prepareStatement(sqlInsertOrdonnance.toString(),Statement.RETURN_GENERATED_KEYS)	){
			
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
	    sqlUpdateOrdonnance.append("update ORDONNANCE set ORD_LABEL = ?, where ORD_ID = ?");

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
	public List<Ordonnance> findAll() throws MonException {
	    Singleton.getInstanceDB();
	    List<Ordonnance> ordonnances = new ArrayList<>();

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

}
