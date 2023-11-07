/**
 * 
 */
package fr.afpa.pompey.cda22045.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import fr.afpa.pompey.cda22045.exception.MonException;
import fr.afpa.pompey.cda22045.metier.Categorie;

/**
 * 
 */
public class CategorieDAO extends DAO<Categorie> {

	/**
	 * 
	 */
	public CategorieDAO() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean create(Categorie obj) {
		Singleton.getInstanceDB();
		
		StringBuilder sqlInsertCategorie = new StringBuilder();
		sqlInsertCategorie.append("insert into CATEGORIE ");
		sqlInsertCategorie.append("(CAT_LABEL)" );
		sqlInsertCategorie.append("values (?)");
		
		boolean requeteOk = false;
		
		try ( PreparedStatement preparedStatement = 
				this.connect.prepareStatement(sqlInsertCategorie.toString(),Statement.RETURN_GENERATED_KEYS)	){
			
			preparedStatement.setString(1,  obj.getCatLabel() );

			
			
			int rowCount = preparedStatement.executeUpdate();

	        if (rowCount > 0) {
	            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
	            if (generatedKeys.next()) {
	                int generatedCliId = generatedKeys.getInt(1);
	                obj.setCatId(generatedCliId);
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
	public boolean delete(Categorie obj) {
		Singleton.getInstanceDB();
		
	    StringBuilder sqlDeleteCategorie = new StringBuilder();
	    sqlDeleteCategorie.append("delete from CATEGORIE where CAT_ID = ?");

	    boolean requeteOk = false;

	    try (PreparedStatement preparedStatement = connect.prepareStatement(sqlDeleteCategorie.toString())) {
	        preparedStatement.setInt(1, obj.getCatId());

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
	public boolean update(Categorie obj) {
	    Singleton.getInstanceDB();

	    StringBuilder sqlUpdateCategorie = new StringBuilder();
	    sqlUpdateCategorie.append("update CATEGORIE set CAT_LABEL = ?, where CAT_ID = ?");

	    boolean requeteOk = false;

	    try (PreparedStatement preparedStatement = connect.prepareStatement(sqlUpdateCategorie.toString())) {
	    	preparedStatement.setString(1,  obj.getCatLabel() );
	        preparedStatement.setInt(2, obj.getCatId()); 

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
	public Categorie find(Integer pCatId) throws MonException {
		Singleton.getInstanceDB();
		
		Categorie categorie = null;

	    StringBuilder sqlSelectCategorie = new StringBuilder();
	    sqlSelectCategorie.append("select * from CATEGORIE where CAT_ID = ?");

	    try (PreparedStatement preparedStatement = connect.prepareStatement(sqlSelectCategorie.toString())) {
	        preparedStatement.setInt(1, pCatId);

	        ResultSet resultSet = preparedStatement.executeQuery();

	        if (resultSet.next()) {
	           
	            int catId = resultSet.getInt("CAT_ID");
	            String catLabel = resultSet.getString("CAT_LABEL");
	            
	            categorie = new Categorie(catId, catLabel);
	        }


	    } catch (SQLException sqle) {
	        System.out.println("Erreur de relation avec la bdd : " + sqle.getMessage()
	                + " [ code d'erreur SQL : " + sqle.getSQLState() + " ]");
	    }

	    return categorie;
	}

	@Override
	public List<Categorie> findAll() throws MonException {
	    Singleton.getInstanceDB();
	    List<Categorie> categories = new ArrayList<>();

	    StringBuilder sqlSelectAllSpecialites = new StringBuilder();
	    sqlSelectAllSpecialites.append("select * from CATEGORIE");

	    try (PreparedStatement preparedStatement = connect.prepareStatement(sqlSelectAllSpecialites.toString())) {
	        ResultSet resultSet = preparedStatement.executeQuery();

	        while (resultSet.next()) {
	            int catId = resultSet.getInt("CAT_ID");
	            String catLabel = resultSet.getString("CAT_LABEL");

	            Categorie categorie = new Categorie(catId, catLabel);
	            categories.add(categorie);
	        }


	    } catch (SQLException sqle) {
	        System.out.println("Erreur de relation avec la bdd : " + sqle.getMessage() +
	                " [ code d'erreur SQL : " + sqle.getSQLState() + " ]");
	    }

	    return categories;
	}

}
