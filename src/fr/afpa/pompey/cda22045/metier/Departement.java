/**
 * 
 */
package fr.afpa.pompey.cda22045.metier;

/**
 * 
 */
public class Departement {

	/**
	 * 
	 */
	private int depId;
	private int depNum;
	private String depNom;
	
	
	
	/**
	 * Constructeur complet d'un département
	 * @param depId son identifiant
	 * @param depNum son numéro
	 * @param depNom son nom
	 */
	public Departement(int pDepId, int pDepNum, String pDepNom) {
		this.setDepId(pDepId);
		this.setDepNum(pDepNum);
		this.setDepNom(pDepNom);
	}
	
	public Departement( int pDepNum, String pDepNom) {
		this.setDepNum(pDepNum);
		this.setDepNom(pDepNom);
	}

	public Departement() {
		// TODO Auto-generated constructor stub
	}

	public int getDepId() {
		return depId;
	}

	public void setDepId(int pDepId) {
		this.depId = pDepId;
	}

	public int getDepNum() {
		return depNum;
	}

	public void setDepNum(int pDepNum) {
		this.depNum = pDepNum;
	}

	public String getDepNom() {
		return depNom;
	}

	public void setDepNom(String pDepNom) {
		this.depNom = pDepNom;
	}

	
	
}
