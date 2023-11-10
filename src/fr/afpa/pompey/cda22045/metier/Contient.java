/**
 * 
 */
package fr.afpa.pompey.cda22045.metier;

/**
 * 
 */
public class Contient {

	/**
	 * 
	 */
	private int medId;
	private int stoId;
	private int medQtte;
	
	/**
	 * Constructeur pour lier une quantité de médicaments à un emplacement de stockage
	 * @param medId 
	 * @param stoId
	 * @param medQtte
	 */
	public Contient(int pMedId, int pStoId, int pMedQtte) {
		this.setMedId(pMedId);
		this.setStoId(pStoId);
		this.setMedQtte(pMedQtte);
	}

	public Contient() {
		// TODO Auto-generated constructor stub
	}

	public int getMedId() {
		return medId;
	}

	public void setMedId(int pMedId) {
		this.medId = pMedId;
	}

	public int getStoId() {
		return stoId;
	}

	public void setStoId(int pStoId) {
		this.stoId = pStoId;
	}

	public int getMedQtte() {
		return medQtte;
	}

	public void setMedQtte(int pMedQtte) {
		this.medQtte = pMedQtte;
	}

	
	
	
}
