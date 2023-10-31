/**
 * 
 */
package fr.afpa.pompey.cda22045.principale;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import fr.afpa.pompey.cda22045.dao.Singleton;

/**
 * 
 */
public class Principale {

	/**
	 * 
	 */
	public Principale() {
		// TODO Auto-generated constructor stub
	}


	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		try {
			Singleton.getInstanceDB();

			String sql = "select * from MEDICAMENT ";
			Statement statement = Singleton.getConnection().createStatement();
			ResultSet result = statement.executeQuery(sql);
			while (result.next()) {
				System.out.println(
						"Résultat : " + result.getString("MED_NOM") + " : " + result.getDouble("MED_PRIX") + " €");
			}
			Singleton.closeInstanceDB();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	
	
	
}
