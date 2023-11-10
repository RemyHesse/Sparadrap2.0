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
import fr.afpa.pompey.cda22045.metier.Stock;

/**
 * 
 */
public class StockDAO extends DAO<Stock>{

	/**
	 * 
	 */
	public StockDAO() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean create(Stock obj) {
		Singleton.getInstanceDB();
		
		StringBuilder sqlInsertStock = new StringBuilder();
		sqlInsertStock.append("insert into STOCK ");
		sqlInsertStock.append("(STO_LIEUX)" );
		sqlInsertStock.append("values (?, ?)");
		
		boolean requeteOk = false;
		
		try ( PreparedStatement preparedStatement = 
				DelivreDAO.connect.prepareStatement(sqlInsertStock.toString(),Statement.RETURN_GENERATED_KEYS)	){
			
			preparedStatement.setString(1, obj.getStoLieux());

			
			
			int rowCount = preparedStatement.executeUpdate();

			if (rowCount > 0) {
	            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
	            if (generatedKeys.next()) {
	                int generatedCliId = generatedKeys.getInt(1);
	                obj.setStoId(generatedCliId);
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
	public boolean delete(Stock obj) {
		Singleton.getInstanceDB();
		
	    StringBuilder sqlDeleteStock = new StringBuilder();
	    sqlDeleteStock.append("delete from STOCK where STO_ID = ?");

	    boolean requeteOk = false;

	    try (PreparedStatement preparedStatement = connect.prepareStatement(sqlDeleteStock.toString())) {
	        preparedStatement.setInt(1, obj.getStoId());

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
	public boolean update(Stock obj) {
	    Singleton.getInstanceDB();

	    StringBuilder sqlUpdateStock = new StringBuilder();
	    sqlUpdateStock.append("update STOCK set STO_LIEUX = ?, where STO_ID = ?");

	    boolean requeteOk = false;

	    try (PreparedStatement preparedStatement = connect.prepareStatement(sqlUpdateStock.toString())) {
	    	preparedStatement.setDate(1, Date.valueOf(obj.getStoLieux() ));
	        preparedStatement.setInt(2, obj.getStoId()); 

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
	public Stock find(Integer pId) throws MonException {
		Singleton.getInstanceDB();
		
		Stock stock = null;

	    StringBuilder sqlSelectStock = new StringBuilder();
	    sqlSelectStock.append("select * from STOCK where STO_ID = ?");

	    try (PreparedStatement preparedStatement = connect.prepareStatement(sqlSelectStock.toString())) {
	        preparedStatement.setInt(1, pId);

	        ResultSet resultSet = preparedStatement.executeQuery();

	        if (resultSet.next()) {
	           
	            int stoId = resultSet.getInt("STO_ID");
	            String stoLieux = resultSet.getString("STO_LIEUX");
	            
	            stock = new Stock(stoId, stoLieux);
	        }


	    } catch (SQLException sqle) {
	        System.out.println("Erreur de relation avec la bdd : " + sqle.getMessage()
	                + " [ code d'erreur SQL : " + sqle.getSQLState() + " ]");
	    }

	    return stock;
	}

	@Override
	public ArrayList<Stock> findAll() throws MonException {
	    Singleton.getInstanceDB();
	    ArrayList<Stock> stocks = new ArrayList<>();

	    StringBuilder sqlSelectAllStock = new StringBuilder();
	    sqlSelectAllStock.append("select * from ORDONNANCE");

	    try (PreparedStatement preparedStatement = connect.prepareStatement(sqlSelectAllStock.toString())) {
	        ResultSet resultSet = preparedStatement.executeQuery();

	        while (resultSet.next()) {
	            int stoId = resultSet.getInt("STO_ID");
	            String stoLieux = resultSet.getString("STO_LIEUX");

	            Stock stock = new Stock(stoId, stoLieux);
	            stocks.add(stock);
	        }


	    } catch (SQLException sqle) {
	        System.out.println("Erreur de relation avec la bdd : " + sqle.getMessage() +
	                " [ code d'erreur SQL : " + sqle.getSQLState() + " ]");
	    }

	    return stocks;
	}

}
