package fr.afpa.pompey.cda22045.metier;

import fr.afpa.pompey.cda22045.dao.MedecinDAO;
import fr.afpa.pompey.cda22045.exception.MonException;

public class Medecin extends Personne {

	
	private int medAgrement;
	
	public Medecin() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param pPrenom
	 * @param pNom
	 * @param pAdresse
	 * @param pTelephone
	 * @param pEmail
	 * @param medAgrement
	 * @throws MonException
	 */
	public Medecin(String pPrenom, String pNom, int pAdresse, String pTelephone, String pEmail, int medAgrement)
			throws MonException {
		super(pPrenom, pNom, pAdresse, pTelephone, pEmail);
		this.setMedAgrement(medAgrement);
	}

	/**
	 * @param pPerId
	 */
	public Medecin(int pPerId, String pPrenom, String pNom, int pAdresse, String pTelephone, String pEmail,
			int medAgrement) throws MonException {
		super(pPerId, pPrenom, pNom, pAdresse, pTelephone, pEmail);
		this.setMedAgrement(medAgrement);
	}

	public int getMedAgrement() {
		return medAgrement;
	}

	public void setMedAgrement(int medAgrement) {
		this.medAgrement = medAgrement;
	}

	@Override
	public String toString() {
		String medAdresse = MedecinDAO.getMedAddresse(getAdresse());
		return "Medecin [Numéro d'agrément = " + medAgrement + ", " + super.toStringSansAdresse() + ", réside au : " + medAdresse +" ]";
	}
	
	
	
	

}
