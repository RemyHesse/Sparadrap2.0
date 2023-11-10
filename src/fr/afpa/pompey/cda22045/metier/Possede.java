/**
 * 
 */
package fr.afpa.pompey.cda22045.metier;

/**
 * 
 */
public class Possede {

	/**
	 * 
	 */
	private int droId;
	private int utiId;	
	
	/**
	 * @param droId
	 * @param utiId
	 */
	public Possede(int pDroId, int pUtiId) {
		super();
		this.setDroId(pDroId);
		this.setUtiId(pUtiId);
	}

	public Possede() {
		// TODO Auto-generated constructor stub
	}

	public int getDroId() {
		return droId;
	}

	public void setDroId(int pDroId) {
		this.droId = pDroId;
	}

	public int getUtiId() {
		return utiId;
	}

	public void setUtiId(int pUtiId) {
		this.utiId = pUtiId;
	}
	
	

}
