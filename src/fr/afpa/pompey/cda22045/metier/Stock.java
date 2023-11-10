/**
 * 
 */
package fr.afpa.pompey.cda22045.metier;

/**
 * 
 */
public class Stock {

	/**
	 * 
	 */
	private int stoId;
	private String stoLieux;
	
	/**
	 * Constructeur d'un emplacement de stockage
	 * @param stoId son identifiant
	 * @param stoLieux l'emplacement
	 */
	public Stock(int pStoID, String pStoLieux) {
		this.setStoId(pStoID);
		this.setStoLieux(pStoLieux);
	}

	public Stock() {
		// TODO Auto-generated constructor stub
	}

	public int getStoId() {
		return stoId;
	}

	public void setStoId(int pStoID) {
		this.stoId = pStoID;
	}

	public String getStoLieux() {
		return stoLieux;
	}

	public void setStoLieux(String pStoLieux) {
		this.stoLieux = pStoLieux;
	}
	
	

}
