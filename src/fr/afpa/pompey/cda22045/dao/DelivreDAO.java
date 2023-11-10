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
import fr.afpa.pompey.cda22045.metier.Delivre;

/**
 * 
 */
public class DelivreDAO extends DAO<Delivre>{

	/**
	 * 
	 */
	public DelivreDAO() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean create(Delivre obj) {
		Singleton.getInstanceDB();
		
		StringBuilder sqlInsertDelivre = new StringBuilder();
		sqlInsertDelivre.append("insert into DELIVRE ");
		sqlInsertDelivre.append("(MED_ID, CLI_ID, ORD_ID)" );
		sqlInsertDelivre.append("values (?, ?, ?)");
		
		boolean requeteOk = false;
		
		try ( PreparedStatement preparedStatement = 
				DelivreDAO.connect.prepareStatement(sqlInsertDelivre.toString(),Statement.RETURN_GENERATED_KEYS)	){
			
			preparedStatement.setInt(1, obj.getMedId());
			preparedStatement.setInt(2, obj.getCliId());
			preparedStatement.setInt(3, obj.getOrdId());
			
			
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
	public boolean delete(Delivre obj) {
		Singleton.getInstanceDB();
		
	    StringBuilder sqlDeleteDelivre = new StringBuilder();
	    sqlDeleteDelivre.append("delete from DELIVRE where MED_ID = ? and CLI_ID = ? and ORD_ID = ?");

	    boolean requeteOk = false;

	    try (PreparedStatement preparedStatement = connect.prepareStatement(sqlDeleteDelivre.toString())) {
	        preparedStatement.setInt(1, obj.getMedId());
	        preparedStatement.setInt(2, obj.getCliId());
	        preparedStatement.setInt(3, obj.getOrdId());

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
	public boolean update(Delivre obj) {
	    Singleton.getInstanceDB();

	    StringBuilder sqlUpdateDelivre = new StringBuilder();
	    sqlUpdateDelivre.append("update DELIVRE set MED_ID = ?, CLI_ID = ?, ORD_ID = ?"
	    		+ " where MED_ID = ? and CLI_ID = ? and ORD_ID = ?");

	    boolean requeteOk = false;

	    try (PreparedStatement preparedStatement = connect.prepareStatement(sqlUpdateDelivre.toString())) {
	        preparedStatement.setInt(1, obj.getMedId());
	        preparedStatement.setInt(2, obj.getCliId());
	        preparedStatement.setInt(3, obj.getOrdId());


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
	
	public Delivre find(Delivre obj) {
		Singleton.getInstanceDB();
		
		Delivre delivre = null;

	    StringBuilder sqlSelectDelivre = new StringBuilder();
	    sqlSelectDelivre.append("select * from DELIVRE where MED_ID = ? and CLI_ID = ? and ORD_ID = ?");

	    try (PreparedStatement preparedStatement = connect.prepareStatement(sqlSelectDelivre.toString())) {
	        preparedStatement.setInt(1, obj.getMedId());
	        preparedStatement.setInt(2, obj.getCliId());
	        preparedStatement.setInt(3, obj.getOrdId());

	        ResultSet resultSet = preparedStatement.executeQuery();

	        if (resultSet.next()) {
	           
	            int medecinID = resultSet.getInt("MED_ID");
	            int clientId = resultSet.getInt("CLI_ID");
	            int ordoId = resultSet.getInt("ORD_ID");

	            delivre = new Delivre(medecinID, clientId, ordoId);
	        }


	    } catch (SQLException sqle) {
	        System.out.println("Erreur de relation avec la bdd : " + sqle.getMessage()
	                + " [ code d'erreur SQL : " + sqle.getSQLState() + " ]");
	    }

	    return delivre;
	}

	@Override
	public Delivre find(Integer pId) throws MonException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Delivre> findAll() throws MonException {
	    Singleton.getInstanceDB();
	    ArrayList<Delivre> delivres = new ArrayList<>();

	    StringBuilder sqlSelectAllDelivre = new StringBuilder();
	    sqlSelectAllDelivre.append("select * from DELIVRE");

	    try (PreparedStatement preparedStatement = connect.prepareStatement(sqlSelectAllDelivre.toString())) {
	        ResultSet resultSet = preparedStatement.executeQuery();

	        while (resultSet.next()) {
	            int medecinID = resultSet.getInt("MED_ID");
	            int clientId = resultSet.getInt("CLI_ID");
	            int ordoId = resultSet.getInt("ORD_ID");
	            

	            Delivre delivre = new Delivre(medecinID, clientId, ordoId);
	            delivres.add(delivre);
	        }


	    } catch (SQLException sqle) {
	        System.out.println("Erreur de relation avec la bdd : " + sqle.getMessage() +
	                " [ code d'erreur SQL : " + sqle.getSQLState() + " ]");
	    }

	    return delivres;
	}

}
