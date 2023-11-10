/**
 * 
 */
package fr.afpa.pompey.cda22045.metier;

/**
 * 
 */
public class Utilisateur {

	/**
	 * 
	 */
	private int utiId;
	private String utiLogin;
	private String utiNom;
	private String utiPrenom;
	
	/**
	 * Constructeur pour un Utilisateur
	 * @param utiId son identifiant
	 * @param utiLogin son login
	 * @param utiNom son nom
	 * @param utiPrenom son prénom
	 */
	public Utilisateur(int pUtiId, String pUtiLogin, String pUtiNom, String pUtiPrenom) {
		this.setUtiId(pUtiId);
		this.setUtiLogin(pUtiLogin);
		this.setUtiNom(pUtiNom);
		this.setUtiPrenom(pUtiPrenom);
	}
	
	public Utilisateur(String pUtiLogin, String pUtiNom, String pUtiPrenom) {
		this.setUtiLogin(pUtiLogin);
		this.setUtiNom(pUtiNom);
		this.setUtiPrenom(pUtiPrenom);
	}

	public Utilisateur() {
		// TODO Auto-generated constructor stub
	}

	public int getUtiId() {
		return utiId;
	}

	public void setUtiId(int pUtiId) {
		this.utiId = pUtiId;
	}

	public String getUtiLogin() {
		return utiLogin;
	}

	public void setUtiLogin(String pUtiLogin) {
		this.utiLogin = pUtiLogin;
	}

	public String getUtiNom() {
		return utiNom;
	}

	public void setUtiNom(String pUtiNom) {
		this.utiNom = pUtiNom;
	}

	public String getUtiPrenom() {
		return utiPrenom;
	}

	public void setUtiPrenom(String pUtiPrenom) {
		this.utiPrenom = pUtiPrenom;
	}
	
	

}
