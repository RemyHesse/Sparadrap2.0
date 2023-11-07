/**
 * 
 */
package fr.afpa.pompey.cda22045.metier;

/**
 * 
 */
public class Specialite {

	/**
	 * 
	 */
	private int speId;
	private String speLabel;
	
	
	public Specialite() {
		// TODO Auto-generated constructor stub
	}


	/**
	 * @param speId
	 * @param speLabel
	 */
	public Specialite(int pSpeId, String pSpeLabel) {
		super();
		this.setSpeId(pSpeId);
		this.setSpeLabel(pSpeLabel);
	}


	public int getSpeId() {
		return speId;
	}


	public void setSpeId(int speId) {
		this.speId = speId;
	}


	public String getSpeLabel() {
		return speLabel;
	}


	public void setSpeLabel(String speLabel) {
		this.speLabel = speLabel;
	}


	@Override
	public String toString() {
		return "Specialite [speId=" + speId + ", speLabel=" + speLabel + "]";
	}
	
	
	

}
