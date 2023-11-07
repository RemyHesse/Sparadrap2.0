/**
 * 
 */
package fr.afpa.pompey.cda22045.metier;

/**
 * 
 */
public class Ville {

	/**
	 * 
	 */
	private int vilId;
	private int depId;
	private int vilCP;
	private String vilNom;
	
	
	/**
	 * Constructeur d'une ville
	 * @param vilId son identifiant
	 * @param depId l'identifiant de son département
	 * @param vilCP son code postal
	 * @param vilNom son nom
	 */
	public Ville(int pVilId, int pDepId, int pVilCP, String pVilNom) {
		this.setVilId(pVilId);
		this.setDepId(pDepId);
		this.setVilCP(pVilCP);
		this.setVilNom(pVilNom);
	}
	
	public Ville( int pDepId, int pVilCP, String pVilNom) {
		this.setDepId(pDepId);
		this.setVilCP(pVilCP);
		this.setVilNom(pVilNom);
	}

	public Ville() {
		// TODO Auto-generated constructor stub
	}

	public int getVilId() {
		return vilId;
	}

	public void setVilId(int pVilId) {
		this.vilId = pVilId;
	}

	public int getDepId() {
		return depId;
	}

	public void setDepId(int pDepId) {
		this.depId = pDepId;
	}

	public int getVilCP() {
		return vilCP;
	}

	public void setVilCP(int pVilCP) {
		this.vilCP = pVilCP;
	}

	public String getVilNom() {
		return vilNom;
	}

	public void setVilNom(String pVilNom) {
		this.vilNom = pVilNom;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
