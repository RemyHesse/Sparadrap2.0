/**
 * 
 */
package fr.afpa.pompey.cda22045.metier;

/**
 * 
 */
/**
 * 
 */
public class Droit {

	/**
	 * 
	 */
	private int droId;
	private String droLabel;
	
	/**
	 * @param droId
	 * @param droLabel
	 */
	public Droit(int pDroId, String pDroLabel) {
		this.setDroId(pDroId);
		this.setDroLabel(pDroLabel);
	}
	
	public Droit(String pDroLabel) {
		this.setDroLabel(pDroLabel);
	}


	public Droit() {
		// TODO Auto-generated constructor stub
	}

	public int getDroId() {
		return droId;
	}

	public void setDroId(int pDroId) {
		this.droId = pDroId;
	}

	public String getDroLabel() {
		return droLabel;
	}

	public void setDroLabel(String pDroLabel) {
		this.droLabel = pDroLabel;
	}

	
	
	
}
