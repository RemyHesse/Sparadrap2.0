/**
 * 
 */
package fr.afpa.pompey.cda22045.principale;

import java.awt.EventQueue;

import javax.swing.UIManager;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;

import fr.afpa.pompey.cda22045.exception.MonException;
import fr.afpa.pompey.cda22045.frame.Accueil;

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
	 * @throws MonException 
	 */
	public static void main(String[] args) throws MonException {
		// TODO Auto-generated method stub

		// Application du thème Nimbus
		try {

			UIManager.setLookAndFeel(new NimbusLookAndFeel());

		} catch (Exception e) {

			System.out.println("Nimbus ne fonctionne pas sur cet OS");

		}
//		Debug debug = new Debug();
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Accueil accueil = new Accueil();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
		
		
		
		
		
		
//        Singleton.closeInstanceDB();	
		

	}

	
	
	
}
