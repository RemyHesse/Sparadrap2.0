/**
 * 
 */
package fr.afpa.pompey.cda22045.metier;

import fr.afpa.pompey.cda22045.dao.ClientDAO;
import fr.afpa.pompey.cda22045.exception.MonException;
import fr.afpa.pompey.cda22045.utilitaires.Saisie;

/**
 * Classe abstraite commune aux personnes
 */
public abstract class Personne {

	/**
	 * Attributs tous de type String sauf les clé étrangères type int
	 */
	protected int perId;
	private String perPrenom;
	private String perNom;
	private int adrId;
	private String perTel;
	private String perEmail;
	
	public Personne() {};

	/**
	 * Constructeur complet
	 * 
	 * @param pPrenom     pour le prénom de la personne
	 * @param pNom        pour le perNom de famille de la personne
	 * @param pAdresse    pour l'adrId de la personne
	 * @param pCodePostal le code postal de la ville de la personne
	 * @param pVille      pour le perNom de la ville de la personne
	 * @param pTelephone  pour le numéro de téléphone de la personne
	 * @param pEmail      pour l'adrId perEmail de la personne
	 * @throws MonException pour les erreurs liées aux entrées du constructeur et de
	 *                      setters
	 */
	public Personne(String pPrenom, String pNom, int pAdresse, String pTelephone, String pEmail) throws MonException {
		super();
		this.setPrenom(pPrenom);
		this.setNom(pNom);
		this.setAdresse(pAdresse);
		this.setTelephone(pTelephone);
		this.setEmail(pEmail);
	}

	public Personne(int pPerId, String pPrenom, String pNom, int pAdresse, String pTelephone, String pEmail) throws MonException {
		super();
		this.setPerId(pPerId);
		this.setPrenom(pPrenom);
		this.setNom(pNom);
		this.setAdresse(pAdresse);
		this.setTelephone(pTelephone);
		this.setEmail(pEmail);
	}
	
	// -----------------------------------------
	// Getters/Setters
	
	
	
	public int getPerId() {
		return perId;
	}
	
	public void setPerId(int perId) {
		this.perId = perId;
	}

	// -
	public String getPrenom() {
		return perPrenom;
	}

	// -
	protected void setPrenom(String pPrenom) throws MonException {
		try {
			if (Saisie.lireNomPrenom(pPrenom)) {
				this.perPrenom = pPrenom;
			} else {
				throw new MonException("Prénom invalide");
			}
		} catch (NullPointerException e) {
			System.err.println("Erreur : " + e.getMessage());
		}
	}

	// -
	public String getNom() {
		return perNom;
	}

	// -
	protected void setNom(String pNom) {
		try {
			if (Saisie.lireNomPrenom(pNom)) {
				this.perNom = pNom;
			} else {
				throw new IllegalArgumentException("Nom invalide");
			}
		} catch (NullPointerException e) {
			System.err.println("Erreur : " + e.getMessage());
		}
	}

	// -
	public int getAdresse() {
		return adrId;
	}

	// -
	protected void setAdresse(int pAdresse) {
		try {
			this.adrId = pAdresse;
		} catch (NullPointerException e) {
			System.err.println("Erreur : " + e.getMessage());
		}
	}



	// -
	public String getTelephone() {
		return perTel;
	}

	// -
	protected void setTelephone(String pTelephone) {
		try {
			if (Saisie.lireTelephone(pTelephone)) {
				this.perTel = pTelephone;
			} else {
				throw new IllegalArgumentException("Numéro de téléphone invalide");
			}
		} catch (NullPointerException e) {
			System.err.println("Erreur : " + e.getMessage());
		}
	}

	// -
	public String getEmail() {
		return perEmail;
	}

	// -
	protected void setEmail(String pEmail) {
		try {
			if (Saisie.lireEmailSwing(pEmail)) {
				this.perEmail = pEmail;
			} else {
				throw new IllegalArgumentException("Adresse e-mail invalide");
			}
		} catch (NullPointerException e) {
			System.err.println("Erreur : " + e.getMessage());
		}
	}

	// -
	/**
	 * Méthode toString() reprenant toutes les informations
	 */
	public String toStringCli() {
		
		String cliAdresse = ClientDAO.getCliAddresse(adrId);

		return this.getPrenom() + " " + this.getNom() + " habite au " + cliAdresse + " " 
				+ " Téléphone : " + this.getTelephone() + " E-Mail : " + this.getEmail();

	}

	/**
	 * Méthode toString() reprenant uniquement le prénom et le perNom de famille
	 * 
	 * @return
	 */
	public String toStringNom() {

		return this.getPrenom() + " " + this.getNom();

	}
	
	
	public String toStringSansAdresse() {

		return this.getPrenom() + " " + this.getNom() + ", " + this.getTelephone() + ", " + this.getEmail();

	}

}
