/**
 * 
 */
package fr.afpa.pompey.cda22045.metier;

/**
 * 
 */
public class Prescription {

	/**
	 * 
	 */
	private int ordId;
	private int medId;
	private int medQtte;
	
	public Prescription() {
		// TODO Auto-generated constructor stub
	}
	
	 /**
	 * @param ordId
	 * @param medId
	 * @param medQtte
	 */
	public Prescription(int pOrdId, int pMedId, int pMedQtte) {
		this.setOrdId(pOrdId);
		this.setMedId(pMedId);
		this.setMedQtte(pMedQtte);
	}

	/**
	 * @param pMedId
	 * @param medQtte
	 */
	public Prescription(int pMedId, int pMedQtte) {
		this.setMedId(pMedId);
		this.setMedQtte(pMedQtte);
	}
	
	public Prescription(int pMedQtte) {
		this.setMedQtte(pMedQtte);
	}
	
	

	public int getOrdId() {
		return ordId;
	}

	public void setOrdId(int ordId) {
		this.ordId = ordId;
	}

	public int getMedId() {
		return medId;
	}

	public void setMedId(int pMedId) {
		this.medId = pMedId;
	}

	public int getMedQtte() {
		return medQtte;
	}

	public void setMedQtte(int pMedQtte) {
		this.medQtte = pMedQtte;
	}

	
	
	
}
