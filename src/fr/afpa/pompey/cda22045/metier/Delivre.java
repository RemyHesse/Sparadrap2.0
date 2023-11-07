/**
 * 
 */
package fr.afpa.pompey.cda22045.metier;

/**
 * 
 */
public class Delivre {

	/**
	 * 
	 */
	private int medId;
	private int cliId;
	private int ordId;
	
	/**
	 * @param medId
	 * @param cliId
	 * @param ordId
	 */
	public Delivre(int pMedId, int pCliId, int pOrdId) {
		super();
		this.setMedId(pMedId);
		this.setCliId(pCliId);
		this.setOrdId(pOrdId);
	}

	public Delivre() {
		// TODO Auto-generated constructor stub
	}

	public int getMedId() {
		return medId;
	}

	public void setMedId(int pMedId) {
		this.medId = pMedId;
	}

	public int getCliId() {
		return cliId;
	}

	public void setCliId(int pCliId) {
		this.cliId = pCliId;
	}

	public int getOrdId() {
		return ordId;
	}

	public void setOrdId(int pOrdId) {
		this.ordId = pOrdId;
	}
	
	
	
	
	
	
	

}
