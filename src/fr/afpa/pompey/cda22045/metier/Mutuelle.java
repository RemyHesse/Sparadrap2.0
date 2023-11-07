/**
 * 
 */
package fr.afpa.pompey.cda22045.metier;

/**
 * 
 */
public class Mutuelle {

	/**
	 * 
	 */
	private int mutId;
	private int adrId;
	private String mutNom;
	private String mutEmail;
	private int mutTaux;
	
	public Mutuelle() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param mutId
	 * @param adrId
	 * @param mutNom
	 * @param mutEmail
	 * @param mutTaux
	 */
	public Mutuelle(int pMutId, int pAdrId, String pMutNom, String pMutEmail, int pMutTaux) {
		this.setMutId(pMutId);
		this.setAdrId(pAdrId);
		this.setMutNom(pMutNom);
		this.setMutEmail(pMutEmail);
		this.setMutTaux(pMutTaux);
	}

	public Mutuelle(int pAdrId, String pMutNom, String pMutEmail, int pMutTaux) {
		this.setAdrId(pAdrId);
		this.setMutNom(pMutNom);
		this.setMutEmail(pMutEmail);
		this.setMutTaux(pMutTaux);
	}


	public int getMutId() {
		return mutId;
	}

	public void setMutId(int mutId) {
		this.mutId = mutId;
	}

	public int getAdrId() {
		return adrId;
	}

	public void setAdrId(int adrId) {
		this.adrId = adrId;
	}

	public String getMutNom() {
		return mutNom;
	}

	public void setMutNom(String mutNom) {
		this.mutNom = mutNom;
	}

	public String getMutEmail() {
		return mutEmail;
	}

	public void setMutEmail(String mutEmail) {
		this.mutEmail = mutEmail;
	}

	public int getMutTaux() {
		return mutTaux;
	}

	public void setMutTaux(int mutTaux) {
		this.mutTaux = mutTaux;
	}

	@Override
	public String toString() {
		return "Mutuelle [mutId=" + mutId + ", adrId=" + adrId + ", mutNom=" + mutNom + ", mutEmail=" + mutEmail
				+ ", mutTaux=" + mutTaux + "]";
	}
	
		
}
